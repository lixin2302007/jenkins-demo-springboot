package com.elane.learning.netty.httpjson;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import java.util.List;

public class HttpJsonResponseEncoder extends AbstractHttpJsonEncoder<HttpJsonResponse>{

  @Override
  protected void encode(ChannelHandlerContext ctx,
      HttpJsonResponse msg, List<Object> out) throws Exception {
    //编码
    ByteBuf body = encode0(ctx, msg.getResult());
    FullHttpResponse response = msg.getResponse();
    if (response == null) {
      response = new DefaultFullHttpResponse(HTTP_1_1, OK, body);
    } else {
      response = new DefaultFullHttpResponse(msg.getResponse()
          .protocolVersion(), msg.getResponse().status(),
          body);
    }
    response.headers().set(CONTENT_TYPE, "text/json");
    HttpUtil.setContentLength(response, body.readableBytes());
    out.add(response);
  }
}
