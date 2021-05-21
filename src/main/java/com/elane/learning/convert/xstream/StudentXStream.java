package com.elane.learning.convert.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 学生
 */
@XStreamAlias("student")
public class StudentXStream {
  //字段设置成XML属性
  @XStreamAsAttribute
  private int rollno;
  @XStreamAsAttribute
  private int age;
  private String firstname;
  private String lastname;
  private String nickname;
  //隐藏字段
  @XStreamOmitField
  private String marks;
  //设置转换器：@XStreamConverter(value= BooleanConverter.class, booleans={true, false}, strings={"男","女"})

  public StudentXStream() {}

  public StudentXStream(int rollno, int age, String firstname, String lastname, String nickname, String marks) {
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
    return "StudentXStream{" +
        "rollno=" + rollno +
        ", age=" + age +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", nickname='" + nickname + '\'' +
        ", marks='" + marks + '\'' +
        '}';
  }

}
