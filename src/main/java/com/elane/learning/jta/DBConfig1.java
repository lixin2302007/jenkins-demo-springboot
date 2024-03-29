package com.elane.learning.jta;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "mysql.datasource.datasource1")
public class DBConfig1 {
  private String url;
  private String username;
  private String password;
  private int minPoolSize;
  private int maxPoolSize;
  private int maxLifetime;
  private int borrowConnectionTimeout;
  private int loginTimeout;
  private int maintenanceInterval;
  private int maxIdleTime;
  private String testQuery;
}
