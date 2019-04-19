package com.self.project.annotation.resolver;

import com.self.project.annotation.RequestPage;
import com.self.project.core.page.PageParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description requestPage注解解析器
 * @Author will
 * @Date 2019/04/11
 * @see com.self.project.annotation.RequestPage
 */
public class RequestPageResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestPage.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        RequestPage requestPage = methodParameter.getParameterAnnotation(RequestPage.class);
        int pageNum = stringToIntWithDefault(request.getParameter(RequestPage.PAGENUM), requestPage.defaultPageNum());
        int pageSize = stringToIntWithDefault(request.getParameter(RequestPage.PAGESIZE), requestPage.defaultPageSize());
        boolean count = Objects.isNull(request.getParameter("count")) ? requestPage.requireTotal() : Boolean.getBoolean(request.getParameter("count"));
        String orderBy = request.getParameter("orderBy");
        String sort = request.getParameter("asc");
        boolean isPage = Objects.isNull(request.getParameter("isPage")) ? requestPage.needPage() : Boolean.getBoolean(request.getParameter("isPage"));
        PageParam pageParam = new PageParam(pageNum, pageSize);
        pageParam.setOrderBy(orderBy);
        pageParam.setCount(count);
        pageParam.setAsc(Objects.isNull(sort) || (!Objects.equals("false", sort) || !Objects.equals("0", sort)));
        pageParam.setNeedPage(isPage);
        return pageParam;
    }

    private int stringToIntWithDefault(String str, int defaultValue) {
        if (StringUtils.isBlank(str)) {
            return defaultValue;
        }
        return Integer.parseInt(str);
    }

}
