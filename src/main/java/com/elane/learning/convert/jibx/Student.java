package com.elane.learning.convert.jibx;

public class Student {
  private int rollno;
  private int age;
  private String firstname;
  private String lastname;
  private String nickname;
  private String marks;

  public Student() {}

  public Student(int rollno, int age, String firstname, String lastname, String nickname, String marks) {
    this.rollno = rollno;
    this.age = age;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.marks = marks;
  }

  public int getRollno() {
    return rollno;
  }

  public void setRollno(int rollno) {
    this.rollno = rollno;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getMarks() {
    return marks;
  }

  public void setMarks(String marks) {
    this.marks = marks;
  }

  @Override
  public String toString() {
    return "Student{" +
        "rollno=" + rollno +
        ", age=" + age +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", nickname='" + nickname + '\'' +
        ", marks='" + marks + '\'' +
        '}';
  }
}
