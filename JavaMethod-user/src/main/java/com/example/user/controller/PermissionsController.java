package com.example.user.controller;


import com.example.user.entity.Permissions;
import com.example.user.entity.Users;
import com.example.user.service.IPermissionsService;
import com.example.user.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 权限信息表 前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Api(tags = "权限信息表")
@RestController
@RequestMapping("/user/permissions")
public class PermissionsController {
    @Autowired
    private IPermissionsService permissionsService;
    @ApiOperation("获取权限信息列表")
    @GetMapping("/getList")
    public List<Permissions> getList() {
        List<Permissions> list = permissionsService.list();
        return list;
    }
}
