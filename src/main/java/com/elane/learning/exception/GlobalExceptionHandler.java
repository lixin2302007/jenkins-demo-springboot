package com.elane.learning.exception;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * @Valid或Spring的 @Validated
   * 如果验证不通过会抛出BindException异常，并变成400（BAD_REQUEST）响应
   * @param e
   * @return
   */
  @ExceptionHandler(BindException.class)
  @ResponseBody
  public Result validateErrorHandler(BindException e) {
    ObjectError error = e.getAllErrors().get(0);
    return Result.fail(error.getDefaultMessage());
  }

  /**
   * @RequestBody注解，验证错误会抛出MethodArgumentNotValidException异常
   * @param e
   * @return
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public Result<?> validateErrorHandler(MethodArgumentNotValidException e) {
    ObjectError error = e.getBindingResult().getAllErrors().get(0);
    return Result.fail(error.getDefaultMessage());
  }

  @ExceptionHandler(Throwable.class)
  @ResponseBody
  public Result<?> handleException(HttpServletRequest request, Throwable ex) {
    return Result.fail(ex.getMessage());
  }

  /**
   * spring validator 方法参数验证异常拦截
   *
   * @param e 绑定验证异常
   * @return 错误返回消息
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public Result defaultErrorHandler(ConstraintViolationException e) {
    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    ConstraintViolation<?> violation = violations.iterator().next();
    log.info("数据验证异常：{}", violation.getMessage());
    return Result.fail(violation.getMessage());
  }

}
