package com.elane.learning;

import com.elane.learning.controller.User;
import com.elane.learning.transcation.TransactionService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = LearningApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionServiceTest {

  @Autowired
  private TransactionService transactionService;

  @Test(expected = Exception.class)
  public void test_insert() throws Exception {
    for (int i =15; i< 20; i++) {
      User user = new User();
      user.setId(i+"");
      user.setName("wang");
      user.setEmail("elane.com");
      user.setAge(18);
      transactionService.insert(user);
    }
    Thread.sleep(10000);
  }
}
