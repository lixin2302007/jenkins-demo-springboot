package com.elane.learning.netty.bookticket;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订票成功与否反馈信息
 */
@Data
public class BookResponseMsg implements Serializable {

  private static final long serialVersionUID = -3104812324244377898L;

  private boolean success;//是否操作成功
  private User user;//请求用户
  private String msg;//反馈信息
  private int code;//请求指令
  private Train train;//火车车次
  private Date startTime;//出发时间
  private Ticket ticket;//订票成功后具体出票票据
}
