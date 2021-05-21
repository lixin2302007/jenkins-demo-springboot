package com.elane.learning.scheduler;

import java.util.Date;
import lombok.Data;

@Data
public class Task {

  private String id;
  private String taskName;
  private String className;
  private String methodName;
  private String cron;
  private Date createTime;
  private String status;

}
