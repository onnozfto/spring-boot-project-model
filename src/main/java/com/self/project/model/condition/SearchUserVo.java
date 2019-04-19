package com.self.project.model.condition;

import com.self.project.core.BaseSqlCallBack;
import com.self.project.core.base.BaseSearchVo;

/**
 * @Description
 * Created by Will on 2019/04/16.
 */

public class SearchUserVo extends BaseSearchVo implements BaseSqlCallBack {

  @Override
  public String appendCustomSql() {
    return "";
  }
}