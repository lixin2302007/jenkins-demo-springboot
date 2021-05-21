package com.elane.learning.netty.httpjson;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.nio.charset.Charset;
import java.util.List;

public abstract class AbstractHttpJsonEncoder<T> extends MessageToMessageEncoder<T> {

  final static Charset UTF_8 = Charset.forName("utf-8");

  protected ByteBuf encode0(ChannelHandlerContext ctx, Object body) {
    String jsonStr = new Gson().toJson(body);
    ByteBuf encodeBuf = Unpooled.copiedBuffer(jsonStr, UTF_8);
    return encodeBuf;
  }

}
