package com.elane.learning.kafka;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.kafka.clients.producer.RecordMetadata;

public class CustomCallbackImpl implements CustomCallback {

  private final AtomicInteger successCount;
  private final AtomicInteger failureCount;

  public CustomCallbackImpl(int successCount, int failureCount) {
    this.successCount = new AtomicInteger(successCount);
    this.failureCount = new AtomicInteger(failureCount);
  }

  @Override
  public void onSuccess(RecordMetadata metadata) {
    successCount.incrementAndGet();
  }

  @Override
  public void onFailure(Throwable t) {
    failureCount.incrementAndGet();
  }
}
