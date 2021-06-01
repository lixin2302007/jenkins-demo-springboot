package com.elane.learning.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ChannelRuleEnum {

  TOUTIAO("TOUTIAO", new TouTiaoChannelRule()),
  TENCENT("TENCENT", new TencentChannelRule());

  private String name;
  private GeneralChannelRule channel;

  ChannelRuleEnum(String name, GeneralChannelRule channel) {
    this.name = name;
    this.channel = channel;
  }

  public static ChannelRuleEnum match(String name) {
    ChannelRuleEnum[] values = ChannelRuleEnum.values();
    for (ChannelRuleEnum value : values) {
      if (value.name.equals(name)) {
        return value;
      }
    }
    return null;
  }


}
