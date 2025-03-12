package com.example.user.controller;


import com.example.method.result.AjaxResult;
import com.example.user.entity.Departments;
import com.example.user.entity.RolePermissionVO;
import com.example.user.entity.Roles;
import com.example.user.service.IRolesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
    public AjaxResult getList() {
        List<Roles> list = rolesService.list();
        return AjaxResult.success(list);
    }

    @ApiOperation("添加角色信息")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Roles roles) {
        boolean save = rolesService.add(roles);
        return save? AjaxResult.success() : AjaxResult.error();
    }

    @ApiOperation("删除角色信息")
    @DeleteMapping("/delete/{roleId}")
    public AjaxResult delete(@PathVariable Integer roleId) {
        boolean delete = rolesService.removeById(roleId);
        return delete? AjaxResult.success() : AjaxResult.error();
    }

    @ApiOperation("更新角色信息")
    @PutMapping("/update")
    public AjaxResult update(@RequestBody Roles roles) {
        boolean b = rolesService.updateById(roles);
        return b?AjaxResult.success():AjaxResult.error();
    }


    @ApiOperation("获取角色详情信息")
    @GetMapping("/getRolesDetails")
    public List<RolePermissionVO> getRolesDetails() {
        List<RolePermissionVO> rolePermissionVOList = rolesService.getRolesDetails();
        return rolePermissionVOList;
    }
}
