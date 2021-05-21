package com.elane.learning;

import com.elane.learning.validate.Mobile;
import com.elane.learning.validate.StudentVo;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.executable.ExecutableValidator;
import org.hibernate.validator.HibernateValidator;
import org.junit.Test;

public class ValidatorTest {

  private static Validator validator;
  private static ExecutableValidator executableValidator;

  static {
    ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
        .configure()
        // 快速失败
        .failFast(false)
        .buildValidatorFactory();
    validator = factory.getValidator();
    executableValidator = validator.forExecutables();
  }

  @Test
  public void validatorTest() {
    StudentVo user = new StudentVo(null, "", "!@#$", 11);

    // 验证所有bean的所有约束
    Set<ConstraintViolation<StudentVo>> constraintViolations = validator.validate(user);
    // 验证单个属性
    Set<ConstraintViolation<StudentVo>> constraintViolations2 = validator
        .validateProperty(user, "name");
    // 检查给定类的单个属性是否可以成功验证
    Set<ConstraintViolation<StudentVo>> constraintViolations3 = validator
        .validateValue(StudentVo.class, "email", "sa!");

    constraintViolations
        .forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
    constraintViolations2
        .forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
    constraintViolations3
        .forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
  }

  @Test
  public void executableValidatorTest() throws NoSuchMethodException {
    ValidatorTest rentalStation = new ValidatorTest();

    Method method = ValidatorTest.class.getMethod("rentCar", LocalDate.class, int.class);
    Object[] parameterValues = {LocalDate.now().minusDays(1), 0};
    Set<ConstraintViolation<ValidatorTest>> violations = executableValidator.validateParameters(
        rentalStation, method, parameterValues);

    violations.forEach(violation -> System.out.println(violation.getMessage()));

  }

  @Test
  public void manufacturerIsNull() throws NoSuchMethodException {
    ValidatorTest mobileTest = new ValidatorTest();

    Method method = ValidatorTest.class.getMethod("setMobile", String.class);
    Object[] parameterValues = {"1111111"};
    Set<ConstraintViolation<ValidatorTest>> violations = executableValidator.validateParameters(
        mobileTest, method, parameterValues);

    violations.forEach(violation -> System.out.println(violation.getMessage()));
  }


  public void rentCar(@NotNull @Future LocalDate startDate, @Min(1) int durationInDays) {
    //...
  }

  @NotNull
  @Size(min = 1)
  public List<@NotNull String> getCustomers() {
    //...
    return null;
  }

  public void setMobile(@Mobile String mobile){
    // to do
  }
}
