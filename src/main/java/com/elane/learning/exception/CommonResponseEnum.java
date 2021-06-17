package com.elane.learning.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponseEnum implements IResponseEnum {

  SERVER_ERROR(9999, "");

  private int code;

  private String message;
}
