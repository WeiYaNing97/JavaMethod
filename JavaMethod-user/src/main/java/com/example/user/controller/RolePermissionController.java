package com.example.user.controller;


import com.example.method.result.AjaxResult;
import com.example.user.entity.RolePermission;
import com.example.user.entity.Users;
import com.example.user.service.IRolePermissionService;
import com.example.user.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
    public AjaxResult getList() {
        List<RolePermission> list = rolePermissionService.list();
        return AjaxResult.success(list);
    }

    @ApiOperation("添加角色与权限关联表")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody RolePermission rolePermission){
        boolean insert = rolePermissionService.add(rolePermission);
        return insert ? AjaxResult.success() : AjaxResult.error();
    }

    @ApiOperation("删除角色与权限关联表")
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete( @PathVariable Integer id){
        boolean delete = rolePermissionService.removeByRoleId(id);
        return delete ? AjaxResult.success() : AjaxResult.error();
    }

    @ApiOperation("更新角色与权限关联表")
    @PutMapping("/update")
    public AjaxResult update(@RequestBody RolePermission rolePermission){
        boolean update = rolePermissionService.updateByRoleId(rolePermission);
        return update ? AjaxResult.success() : AjaxResult.error();
    }
}
