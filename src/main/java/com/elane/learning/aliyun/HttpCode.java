package com.elane.learning.aliyun;

public enum HttpCode {

  IOEXCEPTION(400, "IO exception, retry later."),
  INVALID_SEARCHTIME_RANGE(400, "search time range cannot be longer than a month.");

  int code;
  String message;

  HttpCode(final int code, final String message) {
    this.code = code;
    this.message = message;
  }
}
