package com.elane.learning.netty.httpjson;

public class OrderFactory {

  public static Order create(long count) {
    Order order = new Order();
    Customer customer = new Customer();
    order.setCustomer(customer);
    Address billAddress = new Address();
    order.setBillAddress(billAddress);
    order.setCount(123);
    return order;
  }

}
