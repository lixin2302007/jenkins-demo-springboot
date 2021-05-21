package com.elane.learning.aliyun;


import com.aliyun.rds20140815.Client;
import com.aliyun.rds20140815.models.DescribeSlowLogRecordsResponseBody.DescribeSlowLogRecordsResponseBodyItemsSQLSlowRecord;
import com.aliyun.rds20140815.models.DescribeSlowLogsResponseBody.DescribeSlowLogsResponseBodyItemsSQLSlowLog;
import com.aliyun.tea.*;
import com.aliyun.rds20140815.*;
import com.aliyun.rds20140815.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;
import java.util.List;
import java.util.Map;

public class RdsHttpClient {
  /**
   * 使用AK&SK初始化账号Client
   * @param accessKeyId
   * @param accessKeySecret
   * @return Client
   * @throws Exception
   */
  public static com.aliyun.rds20140815.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
    Config config = new Config()
        // 您的AccessKey ID
        .setAccessKeyId(accessKeyId)
        // 您的AccessKey Secret
        .setAccessKeySecret(accessKeySecret);
    // 访问的域名
    config.endpoint = "rds.aliyuncs.com";
    return new com.aliyun.rds20140815.Client(config);
  }

  //查看慢日志明细
  public static List<DescribeSlowLogRecordsResponseBodyItemsSQLSlowRecord> callSlowLog(Client client, SlowLogReq slowLogReq) throws Exception {
    DescribeSlowLogRecordsRequest describeSlowLogRecordsRequest = DescribeSlowLogRecordsRequest
        .build(SlowLogReq.toMap(slowLogReq));
    DescribeSlowLogRecordsResponse describeSlowLogRecordsResponse = client
        .describeSlowLogRecords(describeSlowLogRecordsRequest);
    List<DescribeSlowLogRecordsResponseBodyItemsSQLSlowRecord> sqlSlowRecord = describeSlowLogRecordsResponse
        .getBody().items.getSQLSlowRecord();
    return sqlSlowRecord;
  }

  //查询慢日志统计
  public static List<DescribeSlowLogsResponseBodyItemsSQLSlowLog> callSlowLogCount(Client client, SlowLogCountReq slowLogCountReq)
      throws Exception {
    Map<String, Object> map = SlowLogReq.toMap(slowLogCountReq);
    map.put("SortKey", slowLogCountReq.getSortKey());
    DescribeSlowLogsRequest describeSlowLogsRequest = DescribeSlowLogsRequest.build(map);
    DescribeSlowLogsResponse describeSlowLogsResponse = client
        .describeSlowLogs(describeSlowLogsRequest);
    List<DescribeSlowLogsResponseBodyItemsSQLSlowLog> sqlSlowLog = describeSlowLogsResponse
        .getBody().items.getSQLSlowLog();
    return sqlSlowLog;
  }
}
