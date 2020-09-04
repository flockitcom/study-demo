package com.zq.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zq.bean.User;
import com.zq.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 * @author ${author}
 * @since 2020-09-03
 */
@Controller
@RequestMapping("/user")
public class UserController {

//    @Resource
//    private UserMapper userMapper;
//
//    @RequestMapping(value = "get",method = RequestMethod.GET)
//    public Page<User> get() {
//        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class);
//        wrapper.like(User::getName, "张");
//        Page<User> page = new Page<>(1, 2);
//        return userMapper.selectPage(page, wrapper);
//    }
}

