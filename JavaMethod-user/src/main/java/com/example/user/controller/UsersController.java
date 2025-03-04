package com.example.user.controller;


import com.example.method.result.AjaxResult;
import com.example.user.entity.Users;
import com.example.user.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Api(tags = "用户信息表")
@RestController
@RequestMapping("/user/users")
public class UsersController {
    @Autowired
    private IUsersService usersService;
    @ApiOperation("获取用户列表信息")
    @GetMapping("/getList")
    public AjaxResult getList() {
        List<Users> list = usersService.list();
        return AjaxResult.success(list);
    }

    @ApiOperation("添加用户信息")
    @GetMapping("/add")
    public AjaxResult add(@RequestBody Users users) {
        boolean save = usersService.add(users);
        return save? AjaxResult.success() : AjaxResult.error();
    }
}
