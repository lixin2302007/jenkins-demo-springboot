package com.elane.learning.strategy;

import java.math.BigDecimal;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class ParticularlyVipPayService implements UserPayService, InitializingBean {

  @Override
  public BigDecimal quote(BigDecimal orderPrice) {
//    if (消费金额大于30元) {
//      return 7折价格;
//    }
    return BigDecimal.ZERO;
  }


  @Override
  public void afterPropertiesSet() throws Exception {
    UserPayServiceStrategyFactory.register("ParticularlyVip",this);
  }
}
