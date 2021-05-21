package com.elane.learning.aliyun;

import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
public class SlowLogReq {

  private String DBInstanceId; //实例ID
  private String EndTime;  //yyyy-MM-ddTHH:mmZ (UTC)
  private String StartTime; //yyyy-MM-ddTHH:mmZ (UTC)
  private String SQLHASH;
  private String DBName;
  private Integer PageSize; //30~100。默认值：30
  private Integer PageNumber;

  public static Map<String, Object> toMap(SlowLogReq slowLogReq) {
    Map<String, Object> map = new HashMap<>();
    map.put("DBInstanceId", slowLogReq.getDBInstanceId());
    map.put("EndTime", slowLogReq.getEndTime());
    map.put("StartTime", slowLogReq.getStartTime());
    map.put("SQLHASH", slowLogReq.getSQLHASH());
    map.put("DBName", slowLogReq.getDBName());
    map.put("PageSize", slowLogReq.getPageSize());
    map.put("PageNumber",slowLogReq.getPageNumber());
    return map;
  }
}
