package com.elane.learning.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

  @Autowired
  private PayService payService;

  public int dosometing(int num) throws Exception {
    return payService.minGoodssum(num);
  }
}
