package com.elane.learning.retry;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.retry.RetryListener;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@PropertySource("classpath:retryConfig.properties")
@EnableRetry
public class RetryConfig {

  @Bean
  public RetryTemplate retryTemplate() {
    RetryPolicy retryPolicy = new SimpleRetryPolicy(2);

    RetryTemplate retryTemplate = new RetryTemplate();
    retryTemplate.setRetryPolicy(retryPolicy);
//    retryTemplate.setBackOffPolicy();
    retryTemplate.setListeners(new RetryListener[]{new DefaultListenerSupport()});
    return retryTemplate;
  }
}
