package com.self.project.util.enumUtil;

import com.self.project.core.CodeMessage;
import com.self.project.core.Result;
import com.self.project.util.ResultUtil;

/**
 * @Description
 * @Author will
 * @Date 2019/4/4 0004 下午 17:19
 */
public enum DBError implements CodeMessage {
  DB_TYPE_MISMATCH("DB001", "java类型与db类型不匹配"),//java类型与db类型不匹配
  DB_BAD_SQL("DB002", "SQL语法有问题"),//SQL语句有问题
  DB_INVALID_ACCESS("DB003", "数据操作失败"),//数据资源访问出错，如SQL语句有问题
  DB_ACCESS_FAILURE("DB004", "数据操作失败"),//数据资源访问失败，如数据库连接出错
  DB_INTEGRITY_VIOLATION("DB005", "插入或修改的数据与已有数据冲突"),//数据完整性冲突
  DB_UNKNOW_ERROR("DB999", "未知错误");

  private String code;

  private String msg;

  DBError(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Result result() {
    return ResultUtil.error(this);
  }
}
