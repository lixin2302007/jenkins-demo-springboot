package com.elane.learning.netty.bookticket;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订票人发送查询余票和订票使用的请求信息
 */
@Data
public class BookRequestMsg implements Serializable {

  private static final long serialVersionUID = -9205409955898163549L;

  private User user;//发送订票信息用户
  private String trainNumber;//火车车次
  private int code;//查询命令
  private Date startTime;//开车时间

}
