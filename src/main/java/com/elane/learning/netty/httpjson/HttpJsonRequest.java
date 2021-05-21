package com.elane.learning.netty.httpjson;

import io.netty.handler.codec.http.FullHttpRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpJsonRequest {

  private FullHttpRequest request;
  private Object body;

}
