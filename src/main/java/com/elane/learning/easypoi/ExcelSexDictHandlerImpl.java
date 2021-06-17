package com.elane.learning.easypoi;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;

public class ExcelSexDictHandlerImpl implements IExcelDictHandler {

  @Override
  public String toName(String dict, Object obj, String name, Object value) {
    System.out.println("1dict:" + dict + ", obj:" + obj + ", name:" + name + ", value:" + value);
    if ("sex".equals(dict)) {
      int sex = Integer.parseInt(value.toString());
      switch (sex) {
        case 0:
          return "男";
        case 1:
          return "女";
      }
    }
    return null;
  }

  @Override
  public String toValue(String dict, Object obj, String name, Object value) {
    System.out.println("2dict:" + dict + ", obj:" + obj + ", name:" + name + ", value:" + value);
    if ("sex".equals(dict)) {
      String sex = value.toString();
      switch (sex) {
        case "男":
          value = "0";
          break;
        case "女":
          value = "1";
          break;
      }
    }
    return null;
  }
}
