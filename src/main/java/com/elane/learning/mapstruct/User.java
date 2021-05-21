package com.elane.learning.mapstruct;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class User {
  private Long id;
  private String username;
  private String password; // 密码
  private Integer sex;  // 性别
  private LocalDate birthday; // 生日
  private LocalDateTime createTime; // 创建时间
  private String config; // 其他扩展信息，以JSON格式存储
  private String test; // 测试字段

  private Integer age;
  public User(Long id) {
    this.id = id;
  }
}
