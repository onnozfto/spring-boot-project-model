package com.self.project.core.page;

import com.github.pagehelper.PageHelper;

import java.util.List;
import java.util.Objects;

/**
 * @Description 分页查询帮助类
 * @Author will
 * @Date 2019/04/11
 */
public class QueryHelper {

    public static <T> MyPageInfo<T> select(PageParam pageParam, QueryCallBack callBack) {

        if (pageParam.isNeedPage()) {
            PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), pageParam.isCount());
        }
        if (!Objects.isNull(pageParam.getOrderBy())) {
            PageHelper.orderBy(pageParam.isAsc() ? pageParam.getOrderBy() : pageParam.getOrderBy() + " desc");
        }
        List list = callBack.querty();
        return new MyPageInfo<>(list);
    }
}
