package com.self.project.core;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @Description
 * @Author will
 * @Date 2019/4/4  上午 9:55
 */
public interface Mapper<T> extends BaseMapper<T>, ConditionMapper<T>,
    IdsMapper<T>,
    InsertListMapper<T> {

}
