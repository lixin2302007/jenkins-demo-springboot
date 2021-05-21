package com.elane.learning.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

//状态机，在一个请假单的审批过程中肯定有这几种状态<发起审批，组长审批，经理审批，人事备案>
@Getter
@AllArgsConstructor
public enum  ApprovalStatusEnum {

  START(1,"开始审批"){
    @Override
    ApprovalStatusEnum getNextStatus ()
    {
      return first_leader;
    }
  },
  first_leader(2,"第一个领导审批"){
    @Override
    ApprovalStatusEnum getNextStatus ()
    {
      return second_leader;
    }
  },
  second_leader(3,"第二个领导审批"){
    @Override
    ApprovalStatusEnum getNextStatus ()
    {
      return backups;
    }
  },
  backups(4,"备案"){
    @Override
    ApprovalStatusEnum getNextStatus ()
    {
      return null;
    }
  };

  private Integer code;
  private String msg;

  abstract ApprovalStatusEnum getNextStatus();
}
