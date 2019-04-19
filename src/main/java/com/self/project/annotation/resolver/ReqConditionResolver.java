package com.self.project.annotation.resolver;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.self.project.annotation.ReqCondition;
import com.self.project.core.ProjectConstant;
import com.self.project.core.base.AndOrType;
import com.self.project.core.base.FieldInfo;
import com.self.project.util.Tools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

/**
 * @Description
 * @Author will
 * @Date 2019/04/12
 */
public class ReqConditionResolver implements HandlerMethodArgumentResolver {

    private static final String BASE_VO_PATH_NAME = ProjectConstant.BASE_PACKAGE + "core.base.BaseSearchVo";

    @Override

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ReqCondition.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        Map<String, Object> parameterMap = getParameterObjectMap(request);
        ReqCondition reqCondition = parameter.getParameterAnnotation(ReqCondition.class);
        Object jsonObj;
        switch (request.getMethod()) {
            case "POST":
                if (Objects.equals("application/json", request.getContentType())) {
                    BufferedReader bis = new BufferedReader(new InputStreamReader(request.getInputStream()));
                    String jsonString = IOUtils.readAll(bis);
                    jsonObj = JSONObject.parseObject(jsonString, Objects.isNull(reqCondition.searchClass()) ?
                            reqCondition.entityClass() :
                            reqCondition.searchClass());

                } else {
                    throw new RuntimeException(Tools.format("注解@ReqCondition只支持Content-Type:{}", "application/json"));
                }
                break;
            case "GET":
                jsonObj = JSONObject.parseObject(JSONObject.toJSONString(parameterMap), Objects.isNull(reqCondition.searchClass()) ?
                        reqCondition.entityClass() :
                        reqCondition.searchClass());
                break;
            default:
                throw new RuntimeException(Tools.format("注解@ReqCondition只支持请求类型:{},{}", "POST", "GET"));
        }
        Condition condition = new Condition(reqCondition.entityClass(), false, false);
        Criteria criteria = condition.createCriteria();
        //没有查询类，精确匹配
        if (Objects.isNull(reqCondition.searchClass())) {
            Field[] fields = reqCondition.entityClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(jsonObj);
                if (!Objects.isNull(value)) {
                    if (value instanceof String && StringUtils.isNotBlank(value.toString())) {
                        criteria.andEqualTo(field.getName(), value);
                    }

                }
            }
        } else {
            if (!Objects.equals("POST", request.getMethod().toUpperCase())) {
                throw new RuntimeException(Tools.format("@ReqCondition实体和查询类不同情况只支持：{}请求", "POST"));
            }
            Field[] fields;
            if (Objects.equals(BASE_VO_PATH_NAME, reqCondition.searchClass().getName())) {
                fields = reqCondition.searchClass().getDeclaredFields();

            } else {
                fields = reqCondition.searchClass().getSuperclass().getDeclaredFields();
                //解析自定义sql
                Method appendMethod = reqCondition.searchClass().getDeclaredMethod("appendCustomSql");
                Object invoke = appendMethod.invoke(jsonObj);
                if (StringUtils.isNotBlank(invoke.toString())) {
                    criteria.andCondition(invoke.toString());
                }
            }
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(jsonObj);
                if (!Objects.isNull(value)) {
                    if (value instanceof List) {
                        for (Object item : (List) value) {
                            FieldInfo info = (FieldInfo) item;
                            resolveFieldInfos(criteria, info);
                        }
                    }
                }
            }
        }
        return condition;
    }

    /**
     * 解析POST请求的查询参数
     */
    private void resolveFieldInfos(Criteria criteria, FieldInfo info) {
        if (Objects.equals(AndOrType.AND.getValue(), info.getAndOr().getValue())) {
            switch (info.getOperator()) {
                case EQUAL:
                    criteria.andEqualTo(info.getName(), info.getValue());
                    break;
                case NOTEQUAL:
                    criteria.andNotEqualTo(info.getName(), info.getValue());
                    break;
                case GREATTHAN:
                    criteria.andGreaterThan(info.getName(), info.getValue());
                    break;
                case LESSTHAN:
                    criteria.andLessThan(info.getName(), info.getValue());
                    break;
                case LIKE:
                    criteria.andLike(info.getName(), info.getValue());
                    break;
                case NOTLIKE:
                    criteria.andNotLike(info.getName(), info.getValue());
                    break;
                case BETWEEN:
                    criteria.andBetween(info.getName(), info.getValue().split(",")[0], info.getValue().split(",")[1]);
                    break;
                case IN:
                    criteria.andIn(info.getName(), Arrays.asList(info.getValue().split(",")));
                    break;
                case NOTIN:
                    criteria.andNotIn(info.getName(), Arrays.asList(info.getValue().split(",")));
                    break;
                default:
                    break;

            }
        } else if (Objects.equals(AndOrType.OR.getValue(), info.getAndOr())) {
            switch (info.getOperator()) {
                case EQUAL:
                    criteria.orEqualTo(info.getName(), info.getValue());
                    break;
                case NOTEQUAL:
                    criteria.orNotEqualTo(info.getName(), info.getValue());
                    break;
                case GREATTHAN:
                    criteria.orGreaterThan(info.getName(), info.getValue());
                    break;
                case LESSTHAN:
                    criteria.orLessThan(info.getName(), info.getValue());
                    break;
                case LIKE:
                    criteria.orLike(info.getName(), info.getValue());
                    break;
                case NOTLIKE:
                    criteria.orNotLike(info.getName(), info.getValue());
                    break;
                case BETWEEN:
                    criteria.orBetween(info.getName(), info.getValue().split(",")[0], info.getValue().split(",")[1]);
                    break;
                case IN:
                    criteria.orIn(info.getName(), Arrays.asList(info.getValue().split(",")));
                    break;
                case NOTIN:
                    criteria.orNotIn(info.getName(), Arrays.asList(info.getValue().split(",")));
                    break;
                default:
                    break;
            }
        } else {
            throw new RuntimeException(Tools.format("参数AndOr只支持:{},{}数字", "0", "1"));
        }
    }

    /**
     * @Description: 解析请求参数的值为Object
     * @auther: will
     * @date: 2019/04/12
     */
    private Map<String, Object> getParameterObjectMap(HttpServletRequest request) {

        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> resultMap = new HashMap<>();
        for (Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (values == null) {
                resultMap.put(key, null);
            } else {
                StringBuilder sb = new StringBuilder();
                for (String value : values) {
                    sb.append(value).append(",");
                }
                sb.replace(sb.length() - 1, sb.length(), "");
                resultMap.put(key, sb.toString());
            }
        }
        return resultMap;
    }
}
