package com.elane.learning.netty.httpjson;

import io.netty.handler.codec.http.FullHttpResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpJsonResponse {

  private FullHttpResponse response;
  private Object result;
}
