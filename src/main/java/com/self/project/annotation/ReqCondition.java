package com.self.project.annotation;

import com.self.project.core.base.BaseSearchVo;

import java.lang.annotation.*;

/**
 * @Description
 * @Author will
 * @Date 2019/04/12
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReqCondition {

    Class<?> entityClass();//数据表实体类

    Class<? extends BaseSearchVo> searchClass();//查询条件类
}
