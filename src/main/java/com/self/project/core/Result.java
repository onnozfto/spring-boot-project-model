package com.self.project.core;

/**
 * @Description
 * @Author will
 * @Date 2019/4/4  上午 10:06
 */
public class Result<T> {

  private String code;

  private String msg;

  private T data;

  public String getCode() {
    return code;
  }

  public Result<T> setCode(String code) {
    this.code = code;
    return this;
  }

  public String getMsg() {
    return msg;
  }

  public Result<T> setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  public void setCodeMsg(CodeMessage codeMessage) {
    this.code = codeMessage.getCode();
    this.msg = codeMessage.getMsg();
  }

  public T getData() {
    return data;
  }

  public Result<T> setData(T data) {
    this.data = data;
    return this;
  }
}
