package com.elane.learning.enums;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sex {

  Man(1,"男"), WOMAN(2,"女");

  public Integer code;
  public String msg;

  private static HashMap<Integer,Sex> data = new HashMap<Integer,Sex>();

  static {
    for(Sex d : Sex.values()){
      data.put(d.code, d);
    }
  }

  public static Sex parse(Integer code) {
    if(data.containsKey(code)){
      return data.get(code);
    }
    return null;
  }

}
