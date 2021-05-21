//package com.elane.learning.scheduler;
//
//import static java.time.LocalDateTime.now;
//
//import com.elane.learning.mybatis.mapper.CronMapper;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//
//
//@Configuration
//public class DynamicScheduleTask implements SchedulingConfigurer {
//
//  @Autowired
//  private CronMapper cronMapper;
//
//  @Override
//  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//    taskRegistrar.addTriggerTask(
//        //1.添加任务内容(Runnable)
//        //其中configureTasks()是需要执行的任务
//        () -> {System.out.println("执行动态定时任务: " + now());
//          scheduleTask();
//        },
//        //2.设置执行周期(Trigger)
//        triggerContext -> {
//          //2.1 从数据库获取执行周期
//          String cron = cronMapper.getCron();
//          System.out.println("cron="+cron);
//          //2.2 合法性校验.
//          if (StringUtils.isEmpty(cron)) {
//            // Omitted Code ..
//          }
//          //2.3 返回执行周期(Date)
//          return new CronTrigger(cron).nextExecutionTime(triggerContext);
//        }
//    );
//  }
//
//  private void scheduleTask() {
//    System.out.println("go");
//  }
//}
