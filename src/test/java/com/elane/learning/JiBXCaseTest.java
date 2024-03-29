package com.elane.learning;

import com.elane.learning.convert.jibx.Grade;
import com.elane.learning.convert.jibx.Student;
import java.util.ArrayList;
import java.util.List;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用JiBX处理xml
 */
public class JiBXCaseTest {

  private static Logger logger = LoggerFactory.getLogger(JiBXCaseTest.class);

  /**
   * java对象转成xml
   * @throws Exception
   */
  @Test
  public void javaToXml() throws Exception {
    List<Student> students = new ArrayList<>();
    Student student1 = new Student(1, 11,"cxx1", "Bob1", "stars1", "85");
    Student student2 = new Student(2, 12, "cxx2", "Bob2", "stars2", "85");
    Student student3 = new Student(3, 13, "cxx3", "Bob3", "stars3", "85");
    students.add(student1);
    students.add(student2);
    students.add(student3);
    Grade grade = new Grade();
    grade.setStudents(students);

    IBindingFactory factory = BindingDirectory.getFactory(Grade.class);
    IMarshallingContext marshallingContext = factory.createMarshallingContext();
    marshallingContext.marshalDocument(grade, "utf-8", true, System.out);
  }

  /**
   * xml转成java对象
   * @throws Exception
   */
  @Test
  public void xmlToJava() throws Exception {
    IBindingFactory factory = BindingDirectory.getFactory(Grade.class);
    IUnmarshallingContext unmarshallingContext = factory.createUnmarshallingContext();
    Grade grade = (Grade) unmarshallingContext.unmarshalDocument(JiBXCaseTest.class.getResourceAsStream(
        "/student3.xml"), null);
    logger.info(grade.toString());
  }
}
