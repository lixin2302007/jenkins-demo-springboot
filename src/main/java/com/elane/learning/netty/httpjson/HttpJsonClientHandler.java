package com.elane.learning.netty.httpjson;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HttpJsonClientHandler extends ChannelInboundHandlerAdapter {

  // 发送HttpJsonRequest-> HttpJsonRequestEncoder编码 -> FullHttpRequest -> channel
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("连接上服务器...");
    HttpJsonRequest request = new HttpJsonRequest(null, OrderFactory.create(123));
    ctx.writeAndFlush(request);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println(msg.getClass().getName());
    System.out.println("接收到了数据..." + msg);
  }

    /*@Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpJsonResponse msg) throws Exception {
        System.out.println("The client receive response of http header is : "
                + msg.getHttpResponse().headers().names());
        System.out.println("The client receive response of http body is : "
                + msg.getResult());
    }*/


  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }

}
