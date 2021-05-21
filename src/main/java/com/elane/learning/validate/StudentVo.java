package com.elane.learning.validate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVo {

  @NotNull(message = "学号不能为空")
  private Integer id;

  @NotNull(message = "姓名不能为空")
  private String name;

  @NotNull(message = "邮箱地址不能为空")
  @Email(message = "邮箱地址不正确")
  private String email;

  private Integer age;
}
