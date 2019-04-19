package com.self.project.util;

import com.self.project.core.CodeMessage;
import com.self.project.core.Result;
import com.self.project.util.enumUtil.ResultCode;

/**
 * @Description返回结果封装类
 * @Author will
 * @Date 2019/4/4 0004 上午 10:07
 */
public class ResultUtil {

  private static final String SUCCESS_MSG = "SUCCESS";

  public static Result success() {
    Result result = new Result();
    result.setCode(ResultCode.SUCCESS.getCode()).setMsg(SUCCESS_MSG);
    return result;
  }

  public static <T> Result<T> success(T data) {

    return new Result().setMsg(SUCCESS_MSG).setCode(ResultCode.SUCCESS.getCode()).setData(data);
  }

  public static Result error(String msg) {

    return new Result().setMsg(msg).setCode(ResultCode.FAIL.getCode());
  }

  public static Result error(String code, String msg) {
    return new Result().setCode(code).setMsg(msg);
  }


  public static Result error(CodeMessage codeMessage) {

    return new Result().setCode(codeMessage.getCode()).setMsg(codeMessage.getMsg());
  }

}
