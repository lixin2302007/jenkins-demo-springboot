package com.elane.learning.netty.httpjson;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.nio.charset.Charset;

public abstract class AbstractHttpJsonDecoder<T> extends MessageToMessageDecoder<T> {

  private Class<?> clazz;
  private boolean isPrint;
  private final static Charset UTF_8 = Charset.forName("UTF-8");

  protected AbstractHttpJsonDecoder(Class<?> clazz) {
    this(clazz, false);
  }

  protected AbstractHttpJsonDecoder(Class<?> clazz, boolean isPrint) {
    this.clazz = clazz;
    this.isPrint = isPrint;
  }

  protected Object decode0(ChannelHandlerContext ctx, ByteBuf body) {
    String content = body.toString(UTF_8);
    if (isPrint)
      System.out.println("The body is : " + content);
    Object result = new Gson().fromJson(content, clazz);
    return result;
  }

}
