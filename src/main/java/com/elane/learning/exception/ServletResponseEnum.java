package com.elane.learning.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@AllArgsConstructor
@Getter
public enum ServletResponseEnum {

  NoHandlerFoundException(1, ""),
  HttpRequestMethodNotSupportedException(2, ""),
  HttpMediaTypeNotSupportedException(3, ""),
  MissingPathVariableException(4, ""),
  MissingServletRequestParameterException(5, ""),
  TypeMismatchException(6, ""),
  HttpMessageNotReadableException(7, ""),
  HttpMessageNotWritableException(8, ""),
  HttpMediaTypeNotAcceptableException(9, ""),
  ServletRequestBindingException(10, ""),
  ConversionNotSupportedException(11, ""),
  MissingServletRequestPartException(12, ""),
  AsyncRequestTimeoutException(13, "");
  
  private int code;
  private String message;
}
