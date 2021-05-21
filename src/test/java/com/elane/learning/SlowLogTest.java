package com.elane.learning;

import com.aliyun.rds20140815.Client;
import com.aliyun.rds20140815.models.DescribeSlowLogRecordsResponseBody.DescribeSlowLogRecordsResponseBodyItemsSQLSlowRecord;
import com.aliyun.rds20140815.models.DescribeSlowLogsResponseBody.DescribeSlowLogsResponseBodyItemsSQLSlowLog;
import com.elane.learning.aliyun.RdsHttpClient;
import com.elane.learning.aliyun.SlowLogCountReq;
import com.elane.learning.aliyun.SlowLogReq;
import java.util.List;
import org.junit.Test;

public class SlowLogTest {

  @Test
  public void test_slowlog() throws Exception {
    String accessKeyId = "";
    String accessKeySecret = "";
    Client client = RdsHttpClient.createClient(accessKeyId, accessKeySecret);
    SlowLogReq slowLogReq = new SlowLogReq();
    slowLogReq.setDBInstanceId("");
    slowLogReq.setDBName("");
    slowLogReq.setPageNumber(1);
    slowLogReq.setPageSize(30);
    slowLogReq.setStartTime("");
    slowLogReq.setEndTime("");
    List<DescribeSlowLogRecordsResponseBodyItemsSQLSlowRecord> SQLSlowRecords = RdsHttpClient
        .callSlowLog(client, slowLogReq);
    SQLSlowRecords.forEach(t->System.out.println(t));
  }

  @Test
  public void test_slowlogCount() throws Exception {
    String accessKeyId = "";
    String accessKeySecret = "";
    Client client = RdsHttpClient.createClient(accessKeyId, accessKeySecret);
    SlowLogCountReq slowLogReq = new SlowLogCountReq();
    slowLogReq.setDBInstanceId("");
    slowLogReq.setDBName("");
    slowLogReq.setPageNumber(1);
    slowLogReq.setPageSize(30);
    slowLogReq.setStartTime("");
    slowLogReq.setEndTime("");
    slowLogReq.setSortKey("");
    List<DescribeSlowLogsResponseBodyItemsSQLSlowLog> SQLSlowRecords = RdsHttpClient
        .callSlowLogCount(client, slowLogReq);
    SQLSlowRecords.forEach(t->System.out.println(t));
  }
}
