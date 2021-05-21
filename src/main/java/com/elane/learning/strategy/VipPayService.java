package com.elane.learning.strategy;

import java.math.BigDecimal;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class VipPayService implements UserPayService, InitializingBean {

  @Override
  public BigDecimal quote(BigDecimal orderPrice) {
//    if(该用户超级会员刚过期并且尚未使用过临时折扣){
//      临时折扣使用次数更新();
//      returen 8折价格;
//    }
//    return 9折价格;
    return BigDecimal.ZERO;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    UserPayServiceStrategyFactory.register("VipPay",this);
  }
}
