package com.elane.learning.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MongoLogUtil {

  private static final Logger mongoLog = LogManager.getLogger("mongoLogger");

  public static void printLog(String log) {
    mongoLog.info(log);
  }

}
