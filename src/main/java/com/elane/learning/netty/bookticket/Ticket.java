package com.elane.learning.netty.bookticket;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Ticket implements Serializable {

  private static final long serialVersionUID = -5410308285734934491L;

  private String trainNumber;//火车车次
  private int carriageNumber;//车厢编号
  private String seatNumber;//座位编号
  private String number;//车票编号
  private User user;//订票用户
  private Date bookTime;//订票时间
  private Date startTime;//开车时间

}
