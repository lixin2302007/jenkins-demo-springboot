package com.elane.learning.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;

public interface CustomCallback {

  void onSuccess(RecordMetadata metadata);

  void onFailure(Throwable t);
}
