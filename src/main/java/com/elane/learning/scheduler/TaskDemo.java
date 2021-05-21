package com.elane.learning.scheduler;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class TaskDemo {

  public void out() {
    System.out.println("当前时间：" + LocalDateTime.now());
  }

}
