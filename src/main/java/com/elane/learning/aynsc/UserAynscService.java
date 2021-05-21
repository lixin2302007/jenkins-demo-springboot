package com.elane.learning.aynsc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserAynscService {

  public void insert(){
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.info("添加用户成功");
  }

  public void update(String userInfo) {
    log.info("更新用户成功");
  }
}
