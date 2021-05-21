package com.elane.learning.beancopy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.springframework.cglib.core.Converter;

public class CustomConverter implements Converter {

  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @SuppressWarnings("rawtypes")
  @Override
  public Object convert(Object value, Class target, Object context) {
    if (value instanceof Integer) {
      return (Integer) value;
    } else if (value instanceof Timestamp) {
      Timestamp date = (Timestamp) value;
      return sdf.format(date);
    } else if (value instanceof BigDecimal) {
      BigDecimal bd = (BigDecimal) value;
      return bd.toPlainString();
    }
    return null;
  }
}
