package com.elane.learning.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XsteamUtil {

  public static <T> T toBean(Class<T> clazz, String xml) {
    T xmlObject = null;
    XStream xstream = new XStream();
    xstream.processAnnotations(clazz);
    xstream.autodetectAnnotations(true);
    xmlObject = (T)xstream.fromXML(xml);
    return xmlObject;
  }

  public static String toXml(Object o) {
    XStream xstream = new XStream(new StaxDriver());
    xstream.autodetectAnnotations(true);
    String xml = xstream.toXML(o);
    return xml;
  }

  public static String toXml(Object o, QNameMap qNameMap) {
    XStream xstream = new XStream(new StaxDriver(qNameMap));
    xstream.autodetectAnnotations(true);
    String xml = xstream.toXML(o);
    return xml;
  }
}
