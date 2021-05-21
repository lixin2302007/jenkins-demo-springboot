package com.elane.learning.aynsc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

  @Async("mailExecutor")
  public void sendMail() throws Exception {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      log.error(e.getMessage());
    }
    log.info("发送邮件成功");
  }
}
