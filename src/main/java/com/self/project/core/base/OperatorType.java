package com.self.project.core.base;

/**
 * @Description
 * @Author will
 * @Date 2019/04/12
 */
public enum OperatorType {

  EQUAL(0), NOTEQUAL(1),

  GREATTHAN(2), LESSTHAN(3),

  LIKE(4), NOTLIKE(5),

  BETWEEN(6), IN(7),

  NOTIN(8);

  OperatorType(int value) {
    this.value = value;
  }

  private int value;

  public int getValue() {
    return value;
  }

}
