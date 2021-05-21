package com.elane.learning.scheduler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elane.learning.mybatis.mapper.TaskMapper;
import com.elane.learning.utils.SpringContextHolder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

@Slf4j
@Configuration
public class SysTaskController implements SchedulingConfigurer {

  @Resource
  private TaskMapper taskMapper;

  private static Map<String, ScheduledFuture> scheduledFutureMap = new HashMap<>();

  @Override
  public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
    scheduledTaskRegistrar.setTaskScheduler(taskScheduler());
    List<Task> allTask = getAllTask();
    if (CollectionUtils.isEmpty(allTask)) {
      return;
    }
    log.info("定时任务启动,预计启动任务数量=" + allTask.size() +"; time=" + LocalDateTime.now());
    int count = 0;
    for (Task task : allTask) {
      try {
        startTask(task);
        count++;
      } catch (Exception e) {
        log.error("定时任务启动错误:" + task.getClassName() + ";" + task.getMethodName() + ";" + e.getMessage());
      }
    }
    log.info("定时任务实际启动数量=" + count +"; time=" + LocalDateTime.now());

  }

  @Bean(destroyMethod = "shutdown")
  public TaskScheduler taskScheduler() {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setThreadNamePrefix("sys-scheduling-");
    threadPoolTaskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors());
    threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
    return threadPoolTaskScheduler;
  }

  private List<Task> getAllTask() {
    return taskMapper.selectList(new QueryWrapper());
  }

  private static void startTask(Task task) {
    TaskScheduler taskScheduler = (TaskScheduler) SpringContextHolder.getApplicationContext().getBean("taskScheduler");
    ScheduledFuture<?> schedule = taskScheduler.schedule(getRunnable(task), getTrigger(task));
    scheduledFutureMap.putIfAbsent(task.getId(), schedule);
  }

  private static Runnable getRunnable(Task task) {
    return () -> {
      try {
        Class cls = Class.forName(task.getClassName());
        Object bean = SpringContextHolder.getApplicationContext().getBean(cls);
        Method method = bean.getClass().getDeclaredMethod(task.getMethodName());
        method.invoke(bean);
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    };
  }

  private static Trigger getTrigger(Task task) {
    return triggerContext -> {
      String cron = task.getCron();
      // 初始化定时任务周期
      if (!CronExpression.isValidExpression(cron)) {
        //默认10秒
        cron = "0/10 * * * * ?";
      }
      CronTrigger trigger = new CronTrigger(cron);
      return trigger.nextExecutionTime(triggerContext);
    };
  }

  /**
   * 取消定时任务
   * @param task
   */
  public static void cancel(Task task){

    ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(task.getId());

    if(scheduledFuture != null && !scheduledFuture.isCancelled()){
      scheduledFuture.cancel(Boolean.FALSE);
    }

    scheduledFutureMap.remove(task.getId());
    log.info("取消定时任务" + task.getId() );

  }

  /**
   * 编辑
   * @param task
   * @param
   */
  public static void reset(Task task){
    log.info("修改定时任务开始" + task.getId() );
    cancel(task);
    startTask(task);
    log.info("修改定时任务结束" + task.getId());
  }

}
