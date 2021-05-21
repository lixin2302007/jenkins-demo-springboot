package com.elane.learning.retry;

import java.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements  PayService{

  private Logger logger = LoggerFactory.getLogger(getClass());

  private final int totalNum = 100000;

  @Autowired
  private RetryTemplate retryTemplate;

  /**
   * 方法内部调用retry失效，需要从外部类调用方法
   * @param num
   * @return
   * @throws Exception
   */
  public int minGoodssum(int num) throws Exception {
    return minGoodsums(num);
  }

  /**
   * @Retryable注解中的参数说明：
   * maxAttempts :最大重试次数，默认为3，如果要设置的重试次数为3，可以不写；加上正常调用次数才3次
   * value：抛出指定异常才会重试
   * include：和value一样，默认为空，当exclude也为空时，所有异常都重试
   * exclude：指定不处理的异常，默认空，当include也为空时，所有异常都重试
   * backoff：重试等待策略，默认使用@Backoff@Backoff的value默认为1000L，我们设置为2000L。
   *
   * @Backoff注解中的参数说明：
   * value：隔多少毫秒后重试，默认为1000L，我们设置为2000L；
   * delay：和value一样，但是默认为0；
   * multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒。
   *
   * @param num
   * @return
   * @throws Exception
   */
//  @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
  private int minGoodsums(int num) throws Exception {
    logger.info("minGoodsnum开始"+ LocalTime.now());
    if(num <= 0){
      throw new Exception("数量不对");
    }
    logger.info("minGoodsnum执行结束");
    return totalNum - num;
  }

//  @Recover
  public int recover(Exception e){
    logger.warn("减库存失败！！！");
    //记日志到数据库
    return totalNum;
  }

  public void retrySomethings(int num) throws Exception {
    retryTemplate.execute(arg0 -> {
      minGoodsums(num);
      return null;
    });
  }
}
