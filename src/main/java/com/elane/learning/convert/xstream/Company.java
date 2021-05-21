package com.elane.learning.convert.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("head")
public class Company {

  private String tran_id;
  private String tran_seq;
  private String xtbm;
  private String xtmc;
  private String tran_date;
  private String tran_time;
}
