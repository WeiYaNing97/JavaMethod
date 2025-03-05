package com.example.user.controller;


import com.example.user.entity.RolePermission;
import com.example.user.entity.Users;
import com.example.user.service.IRolePermissionService;
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
 * 角色与权限关联表 前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Api(tags = "角色与权限关联表")
@RestController
@RequestMapping("/user/role-permission")
public class RolePermissionController {
    @Autowired
    private IRolePermissionService rolePermissionService;

    @ApiOperation("获取角色与权限关联表列表")
    @GetMapping("/getList")
    public List<RolePermission> getList() {
        List<RolePermission> list = rolePermissionService.list();
        return list;
    }
}
