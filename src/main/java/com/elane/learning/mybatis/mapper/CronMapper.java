package com.elane.learning.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

public interface CronMapper {

  @Select("select cron from cron limit 1")
  String getCron();

}
