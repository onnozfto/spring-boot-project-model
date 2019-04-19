package com.self.project.core.base;

/**
 * @Description
 * @Author will
 * @Date 2019/04/12
 */
public class FieldInfo {

  private String name;

  private String value;

  private AndOrType andOr = AndOrType.AND;

  private OperatorType operator = OperatorType.EQUAL;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public AndOrType getAndOr() {
    return andOr;
  }

  public void setAndOr(AndOrType andOr) {
    this.andOr = andOr;
  }

  public OperatorType getOperator() {
    return operator;
  }

  public void setOperator(OperatorType operator) {
    this.operator = operator;
  }
}