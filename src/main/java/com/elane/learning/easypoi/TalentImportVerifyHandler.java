package com.elane.learning.easypoi;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class TalentImportVerifyHandler implements IExcelVerifyHandler<TalentUserInputEntity> {

  private final ThreadLocal<List<TalentUserInputEntity>> threadLocal = new ThreadLocal<>();

  @Resource
  private MockTalentDataService mockTalentDataService;

  @Override
  public ExcelVerifyHandlerResult verifyHandler(TalentUserInputEntity inputEntity) {
    StringJoiner joiner = new StringJoiner(",");
    // 根据姓名与手机号判断数据是否重复
    String name = inputEntity.getName();
    String phone = inputEntity.getPhone();
    // mock 数据库
    boolean duplicates = mockTalentDataService.checkForDuplicates(name, phone);
    if (duplicates) {
      joiner.add("数据与数据库数据重复");
    }

    List<TalentUserInputEntity> threadLocalVal = threadLocal.get();
    if (threadLocalVal == null) {
      threadLocalVal = new ArrayList<>();
    }

    threadLocalVal.forEach(e -> {
      if (e.equals(inputEntity)) {
        int lineNumber = e.getRowNum() + 1;
        joiner.add("数据与第" + lineNumber + "行重复");
      }
    });
    // 添加本行数据对象到ThreadLocal中
    threadLocalVal.add(inputEntity);
    threadLocal.set(threadLocalVal);
    if (joiner.length() != 0) {
      return new ExcelVerifyHandlerResult(false, joiner.toString());
    }
    return new ExcelVerifyHandlerResult(true);
  }

  public ThreadLocal<List<TalentUserInputEntity>> getThreadLocal() {
    return threadLocal;
  }
}
