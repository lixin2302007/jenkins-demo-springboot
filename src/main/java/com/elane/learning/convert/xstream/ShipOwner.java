package com.elane.learning.convert.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("djxx")
public class ShipOwner {

  private ShipOwnerInfo sjcryxx;

  @Data
  @XStreamAlias("sjcryxx")
  private static class ShipOwnerInfo {
    private String xm;
    @XStreamAlias("sfzjlx_dm")
    private String sfzjlxDm;
    private String sfzjhm;
    private String lxdh;
    private String dz;
    private String fzjg_dm;
    private String fzjgmc;
    private String djlx;
    private String gbhy;
    private String djzclx;
    private String zsry_dm;
    private String hybh;
  }
}
