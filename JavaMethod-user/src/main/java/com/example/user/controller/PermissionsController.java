package com.example.user.controller;


import com.example.method.result.AjaxResult;
import com.example.user.entity.Permissions;
import com.example.user.entity.Users;
import com.example.user.service.IPermissionsService;
import com.example.user.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
    public AjaxResult getList() {
        List<Permissions> list = permissionsService.list();
        return AjaxResult.success(list);
    }

    @ApiOperation("添加权限信息")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Permissions permissions) {
        boolean add = permissionsService.add(permissions);
        return add?AjaxResult.success():AjaxResult.error();
    }

    @ApiOperation("删除权限信息")
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Integer id) {
        boolean delete = permissionsService.removeById(id);
        return delete?AjaxResult.success():AjaxResult.error();
    }

    @ApiOperation("更新权限信息")
    @PutMapping("/update")
    public AjaxResult update(@RequestBody Permissions permissions) {
        boolean b = permissionsService.updateById(permissions);
        return b?AjaxResult.success():AjaxResult.error();
    }
}
