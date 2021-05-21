package com.elane.learning;

import com.elane.learning.convert.xstream.BaseXStream;
import com.elane.learning.convert.xstream.GradeConvert;
import com.elane.learning.convert.xstream.GradeXStream;
import com.elane.learning.convert.xstream.StudentXStream;
import com.elane.learning.utils.XsteamUtil;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

@Slf4j
public class XstreamCaseTest {
  /**
   * java对象转成xml(注解)
   * @throws Exception
   */
  @Test
  public void javaToXmlAnnotation() {
    List<StudentXStream> students = new ArrayList<>();
    StudentXStream student1 = new StudentXStream(1, 11,"cxx1", "Bob1", "stars1", "85");
    StudentXStream student2 = new StudentXStream(2, 12, "cxx2", "Bob2", "stars2", "85");
    StudentXStream student3 = new StudentXStream(3, 13, "cxx3", "Bob3", "stars3", "85");
    students.add(student1);
    students.add(student2);
    students.add(student3);
    GradeXStream grade = new GradeXStream();
    grade.setStudents(students);

    QNameMap qNameMap = new QNameMap();
    qNameMap.setDefaultNamespace("http://www.w3.org/TR/html4/school/");
    qNameMap.setDefaultPrefix("school");

    XStream xstream = new XStream(new StaxDriver(qNameMap));
    xstream.autodetectAnnotations(true);
    String xml = xstream.toXML(grade);
    log.info(xml);
  }

  /**
   * xml转成java对象(注解)
   * @throws Exception
   */
  @Test
  public void xmlToJavaAnnotation() {
    QNameMap qNameMap = new QNameMap();
    qNameMap.setDefaultNamespace("http://www.w3.org/TR/html4/school/");
    qNameMap.setDefaultPrefix("school");

    XStream xstream = new XStream(new StaxDriver(qNameMap));
    xstream.ignoreUnknownElements();
    xstream.autodetectAnnotations(true);
    xstream.processAnnotations(GradeXStream.class);
    GradeXStream grade = (GradeXStream) xstream.fromXML(XstreamCaseTest.class.getResourceAsStream("/student3.xml"));
    log.error(grade.toString());
  }

  /**
   * java对象转成xml(自定义转换器)
   * @throws Exception
   */
  @Test
  public void javaToXmlCustomerConvert() {
    List<StudentXStream> students = new ArrayList<>();
    StudentXStream student1 = new StudentXStream(1, 11,"cxx1", "Bob1", "stars1", "85");
    StudentXStream student2 = new StudentXStream(2, 12, "cxx2", "Bob2", "stars2", "85");
    StudentXStream student3 = new StudentXStream(3, 13, "cxx3", "Bob3", "stars3", "85");
    students.add(student1);
    students.add(student2);
    students.add(student3);
    GradeXStream grade = new GradeXStream();
    grade.setStudents(students);

    XStream xstream = new XStream(new StaxDriver());
    xstream.alias("school:grade", GradeXStream.class);
    xstream.registerConverter(new GradeConvert());
    String xml = xstream.toXML(grade);
    log.info(xml);
  }

  /**
   * xml转成java对象(自定义转换器)
   * @throws Exception
   */
  @Test
  public void xmlToJavaCustomerConvert() {
    QNameMap qNameMap = new QNameMap();
    qNameMap.setDefaultNamespace("http://www.w3.org/TR/html4/school/");
    qNameMap.setDefaultPrefix("school");
    XStream xstream = new XStream(new StaxDriver(qNameMap));
    xstream.alias("grade", GradeXStream.class);
    xstream.ignoreUnknownElements();
    xstream.registerConverter(new GradeConvert());
    GradeXStream grade = (GradeXStream) xstream.fromXML(XstreamCaseTest.class.getResourceAsStream("/student3.xml"));
    log.info(grade.toString());
  }

  /**
   * xml转成java对象(注解)
   * @throws Exception
   */
  @Test
  public void xmlToJavaAnnotationShipOwner() {
    XStream xstream = new XStream(new StaxDriver());
    xstream.ignoreUnknownElements();
    xstream.autodetectAnnotations(true);
    xstream.processAnnotations(BaseXStream.class);
    BaseXStream grade = (BaseXStream) xstream.fromXML(BaseXStream.class.getResourceAsStream("/shipowner.xml"));
    log.error(new Gson().toJson(grade));
  }

  @Test
  public void xmlToJavaUtil() throws IOException {
    InputStream is = BaseXStream.class.getResourceAsStream("/shipowner.xml");
    String str = IOUtils.toString(is, "utf-8");
    BaseXStream baseXStream = XsteamUtil.toBean(BaseXStream.class, str);
    log.error(new Gson().toJson(baseXStream));
  }

}
