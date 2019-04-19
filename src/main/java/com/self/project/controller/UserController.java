package com.self.project.controller;

import com.self.project.annotation.ReqCondition;
import com.self.project.annotation.RequestPage;
import com.self.project.core.Result;
import com.self.project.core.page.MyPageInfo;
import com.self.project.core.page.PageParam;
import com.self.project.core.page.QueryHelper;
import com.self.project.model.User;
import com.self.project.service.UserService;
import com.self.project.util.ResultUtil;
import com.self.project.model.condition.SearchUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
* @Description
* Created by Will on 2019/04/16.
*/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        this.userService.save(user);
        return ResultUtil.success();
    }

    @PostMapping("/del")
    public Result del(@RequestParam Integer id) {
        this.userService.deleteById(id);
        return ResultUtil.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        this.userService.update(user);
        return ResultUtil.success();
    }

    @GetMapping("/id")
    public Result id(@RequestParam Integer id) {
        return ResultUtil.success(userService.findById(id));
    }

    @PostMapping("/all")
    public Result all(@ReqCondition(entityClass = User.class, searchClass = SearchUserVo.class) Condition condition, @RequestPage PageParam pageParam) {
        MyPageInfo<User> pageInfo = QueryHelper.select(pageParam, () -> userService.findByCondition(condition));
        return ResultUtil.success(pageInfo);
    }

    @PostMapping("/list")
    public Result list(@ReqCondition(entityClass = User.class, searchClass = SearchUserVo.class) Condition condition) {
        List<User> list = this.userService.findByCondition(condition);
        return ResultUtil.success(list);
    }

}