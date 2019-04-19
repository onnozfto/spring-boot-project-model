package com.self.project.util.enumUtil;

import com.self.project.core.CodeMessage;

/**
 * @Description
 * @Author will
 * @Date 2019/4/4  上午 10:11
 */
public enum ResultCode implements CodeMessage {
  SUCCESS("200"),//成功
  FAIL("400"),//失败
  UNAUTHORIZED("401"),//未认证（签名错误）
  NOT_FOUND("404"),//接口不存在
  INTERNAL_SERVER_ERROR("500"),//服务器内部错误
  UNKNOW_ERROR("X999");//未知错误

  private final String code;

  ResultCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  @Override
  public String getMsg() {
    return null;
  }

}
