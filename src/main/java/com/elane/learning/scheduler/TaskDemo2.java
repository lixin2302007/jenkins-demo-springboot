package com.elane.learning.scheduler;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskDemo2 extends ConfigurerScheduling {

  @Value(value = "${task.taskName2.switch}")
  private Boolean isSwitch;

  @Value(value = "${task.taskName2.cron}")
  private String cron;

  @Override
  protected void processTask() {
    if (isSwitch) {
      System.out.println("基于接口SchedulingConfigurer的动态定时任务:"
          + LocalDateTime.now() + "，线程名称：" + Thread.currentThread().getName()
          + " 线程id：" + Thread.currentThread().getId());
    }
  }

  @Override
  protected String getCron() {
    return cron;
  }

}
