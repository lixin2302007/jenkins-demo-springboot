package com.elane.learning.transcation;

import com.elane.learning.controller.User;
import com.elane.learning.mybatis.mapper.UserMapper;
import com.elane.learning.utils.SpringContextHolder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.SneakyThrows;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

//  private final static ExecutorService executor = Executors.newFixedThreadPool(3);

  @Autowired
  private UserMapper userMapper;

//  @Transactional
  public void insert(User user) throws Exception {
    System.out.println("begining ");

//    ThreadPoolUtil.getPool().execute(new Runnable() {
//      @SneakyThrows
//      @Override
//      public void run() {
//        saveUser1(user);
//      }
//    });
//    userMapper.insert(user);
    //没回滚，同类内方法调用，没有发生回滚
//    userMapper.deleteById(8);
//    saveUser(user);
    TransactionService transactionService = (TransactionService)AopContext.currentProxy();
    transactionService.saveUser(user);
//    TransactionService transactionService = SpringContextHolder.getApplicationContext().getBean(TransactionService.class);
//    transactionService.saveUser(user);
//    System.out.println(id.get());
    System.out.println("ending");
  }


  @Async
//  @Transactional(rollbackFor = Exception.class)
  public void saveUser(User user) throws Exception {
//    saveUser1(user);
    userMapper.insert(user);
    Thread.sleep(1000);
    throw new Exception("rollback");
//    return new AsyncResult<>(user.getId());
  }

  @Transactional(rollbackFor = Exception.class)
  public void saveUser1(User user)  throws Exception {
    User user1 = userMapper.selectById(user.getId());
    if (user1 == null) {
      userMapper.insert(user);
    } else {
      userMapper.updateById(user);
    }
    Thread.sleep(2000);
    throw new Exception("rollback");
  }
}
