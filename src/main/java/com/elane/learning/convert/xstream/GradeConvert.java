package com.elane.learning.convert.xstream;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义转换器
 */
public class GradeConvert implements Converter {
  /**
   * Java对象转成XML
   */
  @Override
  public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext context) {
    GradeXStream grade = (GradeXStream) o;
    List<StudentXStream> students = grade.getStudents();
    writer.addAttribute("xmlns:school", "http://www.w3.org/TR/html4/school/");
    for (StudentXStream student : students) {
      writer.startNode("school:student");
      writer.addAttribute("rollno", student.getRollno() + "");
      writer.addAttribute("age", student.getAge() + "");
      writer.startNode("school:firstname");
      writer.setValue(student.getFirstname());
      writer.endNode();
      writer.startNode("school:lastname");
      writer.setValue(student.getLastname());
      writer.endNode();
      writer.startNode("school:nickname");
      writer.setValue(student.getNickname());
      writer.endNode();
      writer.startNode("school:marks");
      writer.setValue(student.getMarks());
      writer.endNode();
      writer.endNode();
    }
  }

  /**
   * XML转Java对象
   */
  @Override
  public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
    GradeXStream grade = new GradeXStream();
    List<StudentXStream> students = new ArrayList<>();
    grade.setStudents(students);
    while (reader.hasMoreChildren()) {
      StudentXStream student = new StudentXStream();
      students.add(student);
      reader.moveDown();
      student.setRollno(Integer.parseInt(reader.getAttribute("rollno")));
      student.setAge(Integer.parseInt(reader.getAttribute("age")));
      reader.moveDown();
      student.setFirstname(reader.getValue());
      reader.moveUp();
      reader.moveDown();
      student.setLastname(reader.getValue());
      reader.moveUp();
      reader.moveDown();
      student.setNickname(reader.getValue());
      reader.moveUp();
      reader.moveDown();
      student.setMarks(reader.getValue());
      reader.moveUp();
      reader.moveUp();
    }
    return grade;
  }

  @Override
  public boolean canConvert(Class type) {
    return type.equals(GradeXStream.class);
  }
}
