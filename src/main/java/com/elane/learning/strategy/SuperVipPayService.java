package com.elane.learning.strategy;

import java.math.BigDecimal;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class SuperVipPayService implements UserPayService, InitializingBean {

  @Override
  public BigDecimal quote(BigDecimal orderPrice) {
//    return 8折价格;
    return BigDecimal.ZERO;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    UserPayServiceStrategyFactory.register("SuperVip",this);
  }
}
