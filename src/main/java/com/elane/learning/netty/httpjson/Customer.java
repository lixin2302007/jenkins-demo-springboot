package com.elane.learning.netty.httpjson;

import java.util.List;
import lombok.Data;

@Data
public class Customer {
  private Long id;
  private String lastName;
  private String firstName;
  private List<String> fullName;

}
