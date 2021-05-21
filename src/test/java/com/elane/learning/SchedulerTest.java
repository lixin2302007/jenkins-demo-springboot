package com.elane.learning;

import com.elane.learning.scheduler.SysTaskController;
import com.elane.learning.scheduler.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearningApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SchedulerTest {

  @Test
  public void testCancel() {
    Task task = new Task();
    task.setId("1");
    SysTaskController.cancel(task);
  }

}
