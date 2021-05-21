package com.elane.learning.controller;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    @Excel(name = "编号")
    private String id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "年龄")
    private Integer age;
    @Excel(name = "邮箱")
    private String email;
}
