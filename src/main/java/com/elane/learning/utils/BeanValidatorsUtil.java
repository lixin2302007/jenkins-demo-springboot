package com.elane.learning.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.hibernate.validator.HibernateValidator;

public class BeanValidatorsUtil {
  /**
   * 使用hibernate的注解来进行验证
   */
  private static final Validator validator = Validation.byProvider(HibernateValidator.class).configure()
      .failFast(true).buildValidatorFactory().getValidator();


  public static <T> void validateWithException(T obj) throws Exception {
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
    // 抛出检验异常
    if (constraintViolations.size() > 0) {
      throw new Exception(String.format("参数校验失败:%s", constraintViolations.iterator().next().getMessage()));
    }
  }

  public static <T> String[] validate(T obj) {
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
    if (constraintViolations.size() > 0) {
      List<String> errors = new ArrayList<>();
      for (ConstraintViolation<T> constraintViolation : constraintViolations) {
        errors.add(constraintViolation.getMessage());
      }
      return errors.toArray(new String[]{});
    }
    return new String[]{};
  }
}
