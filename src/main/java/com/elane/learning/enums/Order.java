package com.elane.learning.enums;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Order {

  private OrderStateMachine orderState = OrderStateMachine.DISPATCHING;

  private String name;

  private BigDecimal amount;

  public Order nextState() {
    // todo business
    this.orderState = this.orderState.nextState();
    return this;
  }

  public void log() {
    System.out.println(this.orderState.prevState() + "----->" + this.orderState.name());
  }


  public static void main(String[] args) {
    Order order = new Order();
    order.setAmount(BigDecimal.TEN);
    order.setName("丝滑的肥皂");
    order.nextState();
    order.log();
    order.nextState();
    order.log();
  }
}
