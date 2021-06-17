package com.elane.learning.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArgumentResponseEnum {

  VALID_ERROR(2000, "");

  private int code;
  private String message;
}
