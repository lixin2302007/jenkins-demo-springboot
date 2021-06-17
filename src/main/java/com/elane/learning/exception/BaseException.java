package com.elane.learning.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException{

  private IResponseEnum responseEnum;
  private Object[] args;
  private String message;

  public BaseException(IResponseEnum responseEnum) {
    this.responseEnum = responseEnum;
  }

  public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
    this.responseEnum = responseEnum;
    this.args = args;
    this.message = message;
  }

  public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable t) {
    super(t);
    this.responseEnum = responseEnum;
    this.args = args;
    this.message = message;
  }
}
