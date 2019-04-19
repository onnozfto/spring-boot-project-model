package ${basePackage}.model.condition;

import ${basePackage}.core.${BaseSqlCallBack};
import ${basePackage}.core.base.${BaseSearchVo};

/**
 * @Description
 * Created by ${author} on ${date}.
 */

public class Search${modelNameUpperCamel}Vo extends ${BaseSearchVo} implements ${BaseSqlCallBack} {

  @Override
  public String appendCustomSql() {
    return null;
  }
}