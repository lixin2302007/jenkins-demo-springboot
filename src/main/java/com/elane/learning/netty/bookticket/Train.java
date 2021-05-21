package com.elane.learning.netty.bookticket;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Train implements Serializable {

  private static final long serialVersionUID = -7276774906480879909L;
  private String number;//火车车次
  private int ticketCounts;//余票数量
}
