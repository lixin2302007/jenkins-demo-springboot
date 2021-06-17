package com.elane.learning.easypoi;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;

public class MapImportHandler extends ExcelDataHandlerDefaultImpl<TalentUserInputEntity> {

  @Override
  public String importHandler(TalentUserInputEntity entity, String originKey, Object value) {
    return value.equals("男") ? "0" : value.equals("女") ? "1" : value.toString();
  }


}
