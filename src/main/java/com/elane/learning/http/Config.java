package com.elane.learning.http;

public class Config {

  private final static int httpConnectTimeout = 3000;

  private final static int httpSocketTimeout = 3000;

  private final static int httpMaxPoolSize = 100;

  private final static int httpMonitorInterval = 3000;

  private final static int httpIdelTimeout = 2000;

  public static int getHttpIdelTimeout() {
    return httpIdelTimeout;
  }

  public static int getHttpSocketTimeout() {
    return httpSocketTimeout;
  }

  public static int getHttpMaxPoolSize() {
    return httpMaxPoolSize;
  }

  public static int getHttpMonitorInterval() {
    return httpMonitorInterval;
  }

  public static int getHttpConnectTimeout() {
    return httpConnectTimeout;
  }
}
