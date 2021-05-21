package com.elane.learning.netty.httpserver;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
import static io.netty.handler.codec.http.HttpResponseStatus.FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpResponseStatus.METHOD_NOT_ALLOWED;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressiveFuture;
import io.netty.channel.ChannelProgressiveFutureListener;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;
import javax.activation.MimetypesFileTypeMap;

public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

  private String url;

  public HttpFileServerHandler(String url) {
    this.url = url;
  }

  //(1) 不能解码返回400，只支持GET请求，否则返回405.
  //
  //(2) 对url包装，使用UTF-8字符集，转换为绝对path
  //
  //(3) 如果是目录，创建一个html页面
  //
  //(4) 如果是文件，设置content-type和content-length，使用netty的chunkedfile直接写到缓冲，异步的方式
  //
  //(5) 如果是非keepalived的，服务器端主动关闭，否则等待客户端主动关闭。

  @Override
  protected void channelRead0(ChannelHandlerContext ctx,
      FullHttpRequest request) throws Exception {
    if (request.decoderResult().isFailure()) {
      sendError(ctx, BAD_REQUEST);
      return;
    }
    if (!request.method().name().equalsIgnoreCase("GET")) {
      sendError(ctx, METHOD_NOT_ALLOWED);
      return;
    }

    String uri = request.uri();
    String path = sanitizeUri(uri);
    if (path == null) {
      sendError(ctx, FORBIDDEN);
      return;
    }

    File file = new File(path);
    //如果文件不可访问或者文件不存在
    if (file.isHidden() || !file.exists()) {
      sendError(ctx, NOT_FOUND);
      return;
    }

    //如果是目录
    if (file.isDirectory()) {
      // 以/结尾就列出所有文件
      if (uri.endsWith("/")) {
        sendListing(ctx, file);
      } else {
        //否则自动+/
        sendRedirect(ctx, uri + "/");
      }
      return;
    }

    if (!file.isFile()) {
      sendError(ctx, FORBIDDEN);
      return;
    }

    RandomAccessFile randomAccessFile = null;
    try {
      randomAccessFile = new RandomAccessFile(file, "r");// 以只读的方式打开文件
    } catch (FileNotFoundException fnfe) {
      sendError(ctx, NOT_FOUND);
      return;
    }
    long fileLength = randomAccessFile.length();
    //创建一个默认的HTTP响应
    HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
    //设置Content Length
    HttpUtil.setContentLength(response, fileLength);
    //设置Content Type
    setContentTypeHeader(response, file);
    //如果request中有KEEP ALIVE信息
    if (HttpUtil.isKeepAlive(request)) {
      response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
    }
    ctx.write(response);
    ChannelFuture sendFileFuture;
    //通过Netty的ChunkedFile对象直接将文件写入发送到缓冲区中
    sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile, 0,
        fileLength, 8192), ctx.newProgressivePromise());
    sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
      @Override
      public void operationProgressed(ChannelProgressiveFuture future,
          long progress, long total) {
        if (total < 0) { // total unknown
          System.err.println("Transfer progress: " + progress);
        } else {
          System.err.println("Transfer progress: " + progress + " / "
              + total);
        }
      }

      @Override
      public void operationComplete(ChannelProgressiveFuture future)
          throws Exception {
        System.out.println("Transfer complete.");
      }
    });
    // 发送编码结束的空消息体，标识所有消息体已发送完成
    ChannelFuture lastContentFuture = ctx
        .writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
    //如果不支持keep-Alive，服务器端主动关闭请求
    if (!HttpUtil.isKeepAlive(request)) {
      lastContentFuture.addListener(ChannelFutureListener.CLOSE);
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
      throws Exception {
    cause.printStackTrace();
    if (ctx.channel().isActive()) {
      sendError(ctx, INTERNAL_SERVER_ERROR);
    }
  }

  private static final Pattern INSECURE_URI = Pattern.compile(".*[<>&\"].*");

  private String sanitizeUri(String uri) {
    try {
      uri = URLDecoder.decode(uri, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      try {
        uri = URLDecoder.decode(uri, "ISO-8859-1");
      } catch (UnsupportedEncodingException e1) {
        throw new Error();
      }
    }
    if (!uri.startsWith(url)) {
      return null;
    }
    if (!uri.startsWith("/")) {
      return null;
    }
    uri = uri.replace('/', File.separatorChar);
    if (uri.contains(File.separator + '.')
        || uri.contains('.' + File.separator) || uri.startsWith(".")
        || uri.endsWith(".") || INSECURE_URI.matcher(uri).matches()) {
      return null;
    }
    return System.getProperty("user.dir") + File.separator + uri;
  }

  private static final Pattern ALLOWED_FILE_NAME = Pattern
      .compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");

  private static void sendListing(ChannelHandlerContext ctx, File dir) {
    FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
    response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
    StringBuilder buf = new StringBuilder();
    String dirPath = dir.getPath();
    buf.append("<!DOCTYPE html>\r\n");
    buf.append("<html><head><title>");
    buf.append(dirPath);
    buf.append(" 目录：");
    buf.append("</title></head><body>\r\n");
    buf.append("<h3>");
    buf.append(dirPath).append(" 目录：");
    buf.append("</h3>\r\n");
    buf.append("<ul>");
    buf.append("<li>链接：<a href=\"../\">..</a></li>\r\n");
    for (File f : dir.listFiles()) {
      if (f.isHidden() || !f.canRead()) {
        continue;
      }
      String name = f.getName();
      if (!ALLOWED_FILE_NAME.matcher(name).matches()) {
        continue;
      }
      buf.append("<li>链接：<a href=\"");
      buf.append(name);
      buf.append("\">");
      buf.append(name);
      buf.append("</a></li>\r\n");
    }
    buf.append("</ul></body></html>\r\n");
    ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
    response.content().writeBytes(buffer);
    buffer.release();
    ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
  }

  private static void sendRedirect(ChannelHandlerContext ctx, String newUri) {
    FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, FOUND);
    response.headers().set(HttpHeaderNames.LOCATION, newUri);
    ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
  }

  private static void sendError(ChannelHandlerContext ctx,
      HttpResponseStatus status) {
    FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
        status, Unpooled.copiedBuffer("Failure: " + status.toString()
        + "\r\n", CharsetUtil.UTF_8));
    response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
    ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
  }

  private static void setContentTypeHeader(HttpResponse response, File file) {
    MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
    response.headers().set(HttpHeaderNames.CONTENT_TYPE,
        mimeTypesMap.getContentType(file.getPath()));
  }
}
