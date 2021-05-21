package com.elane.learning.netty.httpjson;

import lombok.Data;

@Data
public class Order {
  private long id;
  private long count;
  private Customer customer;
  private Address billAddress;
  private Shipping shipping;
  private Address deliveryAddress;
}
