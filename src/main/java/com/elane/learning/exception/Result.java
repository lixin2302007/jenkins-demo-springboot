package com.elane.learning.exception;

import lombok.Data;

@Data
public class Result<T> {

  private T data;
  private String message;

  public Result(String message) {
    this.message = message;
  }

  public static Result fail(String defaultMessage) {
    return new Result(defaultMessage);
  }
}
