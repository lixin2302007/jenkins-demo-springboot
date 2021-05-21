package com.elane.learning.aynsc;

import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OuterService {

  @Async("outerExecutor")
  public Future<String> getUserInfo() throws Exception {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      log.error("获取用户详情失败",e);
    }
    log.info("获取用户详情成功");
    return new AsyncResult<>("success");
  }
}

