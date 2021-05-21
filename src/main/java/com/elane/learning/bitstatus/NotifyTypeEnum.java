package com.elane.learning.bitstatus;

import lombok.Getter;

@Getter
public enum NotifyTypeEnum {
  IN_MAIL(1, "站内信提醒", 1),
  EMAIL(2, "邮件提醒", 2),
  SMS(3, "短信提醒", 4),
  ;

  // 枚举值
  private Integer code;
  // 枚举描述
  private String desc;
  // 状态位
  private Integer tag;

  NotifyTypeEnum(Integer code, String desc, Integer tag) {
    this.code = code;
    this.desc = desc;
    this.tag = tag;
  }

  public boolean hasTag(int tags) {
    return (tags & this.tag) == tag;
  }

  public static void main(String[] args) {
    int tags = 5;
    System.out.println(NotifyTypeEnum.IN_MAIL.hasTag(tags)); // true
    System.out.println(NotifyTypeEnum.EMAIL.hasTag(tags)); // false
    System.out.println(NotifyTypeEnum.SMS.hasTag(tags)); // true
  }

  //数据库筛选
  //select * from table_name where (notice_flags & #{flag} != 0)
}