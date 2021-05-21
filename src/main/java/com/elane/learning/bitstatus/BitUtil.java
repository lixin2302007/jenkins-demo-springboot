package com.elane.learning.bitstatus;

public class BitUtil {
  /**
   *  计算状态位
   *  tags: 已有状态位
   *  value: 需要添加的状态值
   */
  public static int addTag(int tags, int... values) {
    for (int value : values) {
      tags |= value;
    }
    return tags;
  }

  /**
   *  是否包含状态位
   *  tags: 已有状态位
   *  value: 需要判断的状态值
   */
  public static boolean hasTag(int tags, int value) {
    return (tags & value) == value;
  }

  /**
   * 移除状态位
   * tags: 已有状态位
   * value: 需要移除的状态值
   */
  public static int delTag(int tags, int value) {
    if ((tags & value) != value) return tags;
    return tags ^ value;
  }
}
