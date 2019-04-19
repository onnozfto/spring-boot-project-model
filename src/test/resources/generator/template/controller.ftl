package ${basePackage}.controller;

<#if searchType == 0>
<#else>
import ${basePackage}.annotation.ReqCondition;
</#if>
import ${basePackage}.annotation.RequestPage;
import ${basePackage}.core.Result;
<#if searchType == 2>
import ${basePackage}.core.base.${baseSearchVo};
</#if>
import ${basePackage}.core.page.MyPageInfo;
import ${basePackage}.core.page.PageParam;
import ${basePackage}.core.page.QueryHelper;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.util.ResultUtil;
<#if searchType == 3>
import ${basePackage}.model.condition.${searchVo};
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
<#if searchType == 0 >
<#else>
import tk.mybatis.mapper.entity.Condition;
</#if>

import java.util.List;

/**
* @Description
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {

    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/add")
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        this.${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultUtil.success();
    }

    @PostMapping("/del")
    public Result del(@RequestParam Integer id) {
        this.${modelNameLowerCamel}Service.deleteById(id);
        return ResultUtil.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        this.${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultUtil.success();
    }

    @GetMapping("/id")
    public Result id(@RequestParam Integer id) {
        return ResultUtil.success(${modelNameLowerCamel}Service.findById(id));
    }

<#-- 没有查询条件 -->
<#if searchType == 0 >
    <#if searchMethodType == "GET">
    @GetMapping("/all")
    <#else >
    @PostMapping("/all")
    </#if>
    public Result all(@RequestPage PageParam pageParam) {
    MyPageInfo<${modelNameUpperCamel}> pageInfo = QueryHelper.select(pageParam, () -> ${modelNameLowerCamel}Service.findAll());
        return ResultUtil.success(pageInfo);
    }

    <#if searchMethodType == "GET">
    @GetMapping("/list")
    <#else >
    @PostMapping("/list")
    </#if>
    public Result list() {
        List<${modelNameUpperCamel}> list = this.${modelNameLowerCamel}Service.findAll();
        return ResultUtil.success(list);
    }
<#--精确匹配-->
<#elseif searchType == 1>
    <#if searchMethodType == "GET">
    @GetMapping("/all")
    <#else >
    @PostMapping("/all")
    </#if>
    public Result all(@ReqCondition(entityClass = ${modelNameUpperCamel}.class) Condition condition, @RequestPage PageParam pageParam) {
        MyPageInfo<${modelNameUpperCamel}> pageInfo = QueryHelper.select(pageParam, () -> ${modelNameLowerCamel}Service.findByCondition(condition));
        return ResultUtil.success(pageInfo);
    }

    <#if searchMethodType == "GET">
    @GetMapping("/list")
    <#else >
    @PostMapping("/list")
    </#if>
    public Result list(@ReqCondition(entityClass = ${modelNameUpperCamel}.class) Condition condition) {
        List<${modelNameUpperCamel}> list = this.${modelNameLowerCamel}Service.findByCondition(condition);
        return ResultUtil.success(list);
    }
<#--模糊加精确匹配-->
<#elseif searchType == 2>
    <#if searchMethodType == "GET">
    @GetMapping("/all")
    <#else >
    @PostMapping("/all")
    </#if>
    public Result all(@ReqCondition(entityClass = ${modelNameUpperCamel}.class, searchClass = ${baseSearchVo}.class) Condition condition, @RequestPage PageParam pageParam) {
        MyPageInfo<${modelNameUpperCamel}> pageInfo = QueryHelper.select(pageParam, () -> ${modelNameLowerCamel}Service.findByCondition(condition));
        return ResultUtil.success(pageInfo);
    }

    <#if searchMethodType == "GET">
    @GetMapping("/list")
    <#else >
    @PostMapping("/list")
    </#if>
    public Result list(@ReqCondition(entityClass = ${modelNameUpperCamel}.class, searchClass = ${baseSearchVo}.class) Condition condition) {
        List<${modelNameUpperCamel}> list = this.${modelNameLowerCamel}Service.findByCondition(condition);
        return ResultUtil.success(list);
    }
<#elseif searchType == 3>
    <#if searchMethodType == "GET">
    @GetMapping("/all")
    <#else >
    @PostMapping("/all")
    </#if>
    public Result all(@ReqCondition(entityClass = ${modelNameUpperCamel}.class, searchClass = ${searchVo}.class) Condition condition, @RequestPage PageParam pageParam) {
        MyPageInfo<${modelNameUpperCamel}> pageInfo = QueryHelper.select(pageParam, () -> ${modelNameLowerCamel}Service.findByCondition(condition));
        return ResultUtil.success(pageInfo);
    }

    <#if searchMethodType == "GET">
    @GetMapping("/list")
    <#else >
    @PostMapping("/list")
    </#if>
    public Result list(@ReqCondition(entityClass = ${modelNameUpperCamel}.class, searchClass = ${searchVo}.class) Condition condition) {
        List<${modelNameUpperCamel}> list = this.${modelNameLowerCamel}Service.findByCondition(condition);
        return ResultUtil.success(list);
    }
<#else>
</#if>

}