package com.elane.learning.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public interface PayService {
  @Retryable(value = Exception.class, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}", multiplier = 1.5))
  int minGoodssum(int num) throws Exception;

  @Recover
  public int recover(Exception e);

  void retrySomethings(int num) throws Exception;
}
