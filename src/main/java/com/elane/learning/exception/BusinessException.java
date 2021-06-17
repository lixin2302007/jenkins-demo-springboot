package com.elane.learning.exception;

public class BusinessException extends BaseException {


  private static final long serialVersionUID = 1L;

  public BusinessException(IResponseEnum responseEnum, Object[] args, String message) {
    super(responseEnum, args, message);
  }

  public BusinessException(IResponseEnum responseEnum, Object[] args, String message, Throwable t) {
    super(responseEnum, args, message, t);
  }
}
