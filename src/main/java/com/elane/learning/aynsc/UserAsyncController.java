package com.elane.learning.aynsc;

import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAsync")
@Slf4j
public class UserAsyncController {

  @Autowired
  private UserAynscService userService;

  @Autowired
  private MailService mailService;

  @Autowired
  private OuterService outerService;

  @GetMapping("/register")
  public String register(){
    try {
      long start = System.currentTimeMillis();
      Future<String> userInfoFuture = outerService.getUserInfo();
      userService.insert();
      mailService.sendMail();
      String userInfo = userInfoFuture.get();
      userService.update(userInfo);
      log.info("执行时间"+(System.currentTimeMillis()-start));
      return "注册成功";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "注册失败";
  }
}