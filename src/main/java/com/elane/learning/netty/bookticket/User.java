package com.elane.learning.netty.bookticket;

import java.io.Serializable;
import lombok.Data;

@Data
public class User implements Serializable {

  private static final long serialVersionUID = 146144217210600230L;

  private String name;
  private String idCard;
  private String phone;
}
