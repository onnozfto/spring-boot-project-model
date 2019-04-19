package com.self.project.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Author will
 * @Date 2019/04/11
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestPage {

    String PAGENUM = "pageNum";

    String PAGESIZE = "pageSize";

    int defaultPageSize() default 10;

    int defaultPageNum() default 1;

    boolean requireTotal() default true;

    boolean needPage() default true;
}
