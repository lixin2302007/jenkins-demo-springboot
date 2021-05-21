package com.elane.learning.convert.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * 班级
 */
@XStreamAlias("grade")
public class GradeXStream {
  //省略集合根节点
  @XStreamImplicit
  private List<StudentXStream> students;

  public List<StudentXStream> getStudents() {
    return students;
  }

  public void setStudents(List<StudentXStream> students) {
    this.students = students;
  }

  @Override
  public String toString() {
    return "GradeXStream{" +
        "students=" + students +
        '}';
  }

}
