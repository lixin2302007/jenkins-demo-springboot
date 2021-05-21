package com.elane.learning.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyKafkaProducer {

  public static void main(String[] args) {

    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.10.9.211:9092");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
    KafkaProducer producer = new KafkaProducer(properties);

    ProducerRecord record = new ProducerRecord("test", "test", "test");
    CustomCallback customCallback = new CustomCallbackImpl(0, 0);
    producer.send(record, (metadata, exception) -> {
      if (exception == null) {
        customCallback.onSuccess(metadata);
      } else {
        customCallback.onFailure(exception);
      }
    });

  }
}
