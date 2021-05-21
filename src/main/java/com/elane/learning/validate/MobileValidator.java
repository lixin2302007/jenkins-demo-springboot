package com.elane.learning.validate;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<Mobile, String> {

  /**
   * 手机验证规则
   */
  private Pattern pattern;

  @Override
  public void initialize(Mobile mobile) {
    pattern = Pattern.compile(mobile.regexp());
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (value == null) {
      return true;
    }
    return pattern.matcher(value).matches();
  }
}
