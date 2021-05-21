package com.elane.learning.mybatis.mapper;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elane.learning.controller.User;
import com.elane.learning.netty.httpjson.Order;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;

  public void getUserListForExcel() throws IOException {
    List<User> list = new ArrayList<>();
    userMapper.export(new ResultHandler<User>() {
      @Override
      public void handleResult(ResultContext<? extends User> result) {
        User user = result.getResultObject();
        list.add(user);
      }
    });

    Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户表", "数据"), User.class,
        list);
    FileOutputStream fos = new FileOutputStream("D:/用户表.tt.xls");
    workbook.write(fos);
    fos.close();
  }

  public void getUserListForEasyExcel() {
    List<User> list = new ArrayList<>();
    userMapper.export(new ResultHandler<User>() {
      @Override
      public void handleResult(ResultContext<? extends User> result) {
        User user = result.getResultObject();
        list.add(user);
      }
    });


  }
}
