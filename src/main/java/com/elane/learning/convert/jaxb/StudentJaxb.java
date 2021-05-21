package com.elane.learning.convert.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 学生
 */
@XmlType(propOrder = {"marks", "firstname", "lastname", "nickname"})
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentJaxb {
  @XmlAttribute
  private int rollno;
  @XmlAttribute(namespace = "http://www.w3.org/TR/html4/school/")
  private int age;
  @XmlElement(namespace = "http://www.w3.org/TR/html4/school/")
  private String firstname;
  private String lastname;
  private String nickname;
  private String marks;

  public StudentJaxb() {}

  public StudentJaxb(int rollno, int age, String firstname, String lastname, String nickname, String marks) {
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
    return "StudentJaxb{" +
        "rollno=" + rollno +
        ", age=" + age +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", nickname='" + nickname + '\'' +
        ", marks='" + marks + '\'' +
        '}';
  }

}
