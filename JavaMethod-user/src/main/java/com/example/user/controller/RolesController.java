package com.example.user.controller;


import com.example.method.result.AjaxResult;
import com.example.user.entity.Departments;
import com.example.user.entity.Roles;
import com.example.user.service.IRolesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Api(tags = "角色信息表")
@RestController
@RequestMapping("/user/roles")
public class RolesController {
    @Autowired
    private IRolesService rolesService;
    @ApiOperation("获取角色列表信息")
    @GetMapping("/getList")
    public List<Roles> getList() {
        List<Roles> list = rolesService.list();
        return list;
    }

    @ApiOperation("添加角色信息")
    @GetMapping("/add")
    public AjaxResult add(@RequestBody Roles roles) {
        boolean save = rolesService.add(roles);
        return save? AjaxResult.success() : AjaxResult.error();
    }
}
