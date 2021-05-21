package com.elane.learning.mapstruct;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
  private Long id;
  private String name;
  private String email;
  private Date birthday;
  private User user;
}