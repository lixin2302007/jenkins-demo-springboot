package com.elane.learning.transcation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtil {

  private ThreadPoolUtil() {};

  private volatile static ExecutorService pool = null;

  public static ExecutorService getPool() {
    if (pool == null) {
      synchronized (ThreadPoolUtil.class) {
        if (pool == null) {
          pool = Executors.newFixedThreadPool(50);
        }
      }
    }
    return pool;
  }
}
