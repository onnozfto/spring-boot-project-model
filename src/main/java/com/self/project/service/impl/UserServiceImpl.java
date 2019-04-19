package com.self.project.service.impl;

import com.self.project.dao.UserMapper;
import com.self.project.model.User;
import com.self.project.service.UserService;
import com.self.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Will on 2019/04/16.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
