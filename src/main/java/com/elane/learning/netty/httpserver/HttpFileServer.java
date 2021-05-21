package com.elane.learning.netty.httpserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {

  private static final String DEFAULT_URL = "/";

  public void run(int port, String url) throws Exception{
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try{
      //服务器辅助启动类配置
      ServerBootstrap b=new ServerBootstrap();
      b.group(bossGroup, workerGroup)
          .channel(NioServerSocketChannel.class)
          .option(ChannelOption.SO_BACKLOG, 100)
          .handler(new LoggingHandler(LogLevel.INFO))
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              ch.pipeline().addLast("codec", new HttpServerCodec());
              ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
              ch.pipeline().addLast("httpserver-chunked", new ChunkedWriteHandler());//目的是支持异步大文件传输（）
              ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));// 业务逻辑
            }
          });
      //绑定端口 同步等待绑定成功
      ChannelFuture f=b.bind(port).sync();
      System.out.println("HTTP文件目录服务器启动，网址是 : " + "httpserver://127.0.0.1:" + port + url);
      //等到服务端监听端口关闭
      f.channel().closeFuture().sync();
    }finally{
      //优雅释放线程资源
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) throws Exception {
    int port = 8080;
    if (args.length > 0) {
      try {
        port = Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }
    String url = DEFAULT_URL;
    if (args.length > 1)
      url = args[1];
    new HttpFileServer().run(port, url);
  }
}
