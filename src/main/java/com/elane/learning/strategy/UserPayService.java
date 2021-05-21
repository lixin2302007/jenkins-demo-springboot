package com.elane.learning.strategy;

import java.math.BigDecimal;

public interface UserPayService {
  /**
   * 计算应付价格
   */
  public BigDecimal quote(BigDecimal orderPrice);
}
