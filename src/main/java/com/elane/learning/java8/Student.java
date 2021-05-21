package com.elane.learning.java8;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Student {

  private int id;
  private String name;
  private String score;
  private int classNo;

  public Student(int id, String name, String score, int classNo) {
    this.id = id;
    this.name = name;
    this.score = score;
    this.classNo = classNo;
  }
}
