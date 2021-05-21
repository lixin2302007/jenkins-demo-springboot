package com.elane.learning.mapstruct;

public class CustomMapping {
  static final String[] SEX = {"女", "男", "未知"};

  public static String sexName(Integer sex) {

    if (sex < 0 && sex > 2){
      throw new IllegalArgumentException("invalid sex: " + sex);
    }
    return SEX[sex];
  }

  public static String ageLevel(Integer age) {
    if (age < 18) {
      return "少年";
    } else if (age >= 18 && age < 30) {
      return "青年";
    } else if (age >= 30 && age < 60) {
      return "中年";
    } else {
      return "老年";
    }
  }
}
