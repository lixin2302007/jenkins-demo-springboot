package com.elane.learning.validate;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class ValidatorConfig {

  /**
   * 配置验证器
   *
   * @return validator
   */
  @Bean
  public Validator validator() {
    ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
        .configure()
        // 快速失败模式
        .failFast(true)
        // .addProperty( "hibernate.validator.fail_fast", "true" )
        .buildValidatorFactory();
    return validatorFactory.getValidator();
  }

  /**
   * 设置方法参数验证器
   */
  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
    // 设置validator模式为快速失败返回
    postProcessor.setValidator(validator());
    return postProcessor;
  }

}
