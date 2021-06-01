package com.elane.learning;

import com.elane.learning.design.Activity;
import com.elane.learning.design.ActivityController;
import com.google.gson.Gson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityTest {
  private Logger logger = LoggerFactory.getLogger(ActivityTest.class);

  private ActivityController activityController = new ActivityController();

  @Test
  public void test_queryActivityInfo() throws InterruptedException {
    for (int idx = 0; idx < 10; idx++) {
      Long req = 10001L;
      Activity activity = activityController.queryActivityInfo(req);
      logger.error("测试结果：{} {}", req, new Gson().toJson(activity));
      Thread.sleep(1200);
    }
  }
}
