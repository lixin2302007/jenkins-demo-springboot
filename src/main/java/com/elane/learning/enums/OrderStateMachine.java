package com.elane.learning.enums;

public enum OrderStateMachine {

  DISPATCHING {
    @Override
    public OrderStateMachine nextState() {
      return DELIVERING;
    }

    @Override
    public OrderStateMachine prevState() {
      return this;
    }
  },

  DELIVERING {
    @Override
    public OrderStateMachine nextState() {
      return DECEIVED;
    }

    @Override
    public OrderStateMachine prevState() {
      return DISPATCHING;
    }
  },

  DECEIVED {
    @Override
    public OrderStateMachine nextState() {
      return DECEIVED;
    }

    @Override
    public OrderStateMachine prevState() {
      return DELIVERING;
    }
  };


  public abstract OrderStateMachine nextState();

  public abstract OrderStateMachine prevState();

}
