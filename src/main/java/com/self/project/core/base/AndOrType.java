package com.self.project.core.base;

/**
 * @Description 查询拼接类型
 * @Author will
 * @Date 2019/04/12
 */
public enum AndOrType {
  AND(0), OR(1);

  AndOrType(int value) {
    this.value = value;
  }

  private int value;

  public int getValue() {
    return value;
  }

}
