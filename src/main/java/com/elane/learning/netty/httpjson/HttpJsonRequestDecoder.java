package com.elane.learning.netty.httpjson;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;
import java.util.List;

/**
 * 转换FullHttpRequest为HttpJsonRequest
 */
public class HttpJsonRequestDecoder extends AbstractHttpJsonDecoder<FullHttpRequest>{

  public HttpJsonRequestDecoder(Class<?> clazz) {
    this(clazz, false);
  }

  /**
   * 构造器
   *
   * @param clazz   解码的对象信息
   * @param isPrint 是否需要打印
   */
  public HttpJsonRequestDecoder(Class<?> clazz, boolean isPrint) {
    super(clazz, isPrint);
  }


  @Override
  protected void decode(ChannelHandlerContext ctx, FullHttpRequest msg,
      List<Object> out) throws Exception {
    if (!msg.decoderResult().isSuccess()) {
      sendError(ctx, HttpResponseStatus.BAD_REQUEST);
      return;
    }
    HttpJsonRequest request = new HttpJsonRequest(msg, decode0(ctx, msg.content()));
    out.add(request);
  }

  private static void sendError(ChannelHandlerContext ctx,
      HttpResponseStatus status) {
    FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
        status, Unpooled.copiedBuffer("Failure: " + status.toString()
        + "\r\n", CharsetUtil.UTF_8));
    response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
    ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
  }
}
