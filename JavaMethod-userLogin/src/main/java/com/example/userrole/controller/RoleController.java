package com.example.userrole.controller;


import com.example.method.result.AjaxResult;
import com.example.userrole.entity.Role;
import com.example.userrole.entity.User;
import com.example.userrole.entity.UserRole;
import com.example.userrole.service.IRoleService;
import com.example.userrole.service.IUserRoleService;
import com.example.userrole.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
@RestController
@RequestMapping("/userrole/role")
@Api(tags = "角色管理")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserRoleService userRoleService;

    @ApiOperation(value = "查询角色", notes = "查询角色列表")
    @GetMapping("/list")
    public AjaxResult getList() {
        List<Role> list = roleService.list();
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "添加角色", notes = "添加一个新角色")
    @PostMapping("/add")
    public AjaxResult addRole(@RequestBody Role role) {
        boolean save = roleService.save(role);
        if (!save) {
            return AjaxResult.error("添加角色失败");
        }
        return AjaxResult.success("添加角色成功");
    }

    @ApiOperation(value = "删除全部角色", notes = "删除全部角色")
    @DeleteMapping("/deleteAll")
    public AjaxResult deleteAll() {
        // 获取所有角色的ID列表
        List<Integer> collect = roleService.list().stream().map(Role::getRoleId).collect(Collectors.toList());

        // 获取与这些角色ID关联的用户角色列表
        List<UserRole> userRoleList = userRoleService.listByRoleIds(collect);

        // 如果存在关联用户，则返回错误信息
        if (userRoleList.size() > 0) {
            return AjaxResult.error("删除角色失败，存在关联用户");
        } else {
            // 如果不存在关联用户，则尝试删除所有角色
            boolean deleteAll = roleService.deleteAll();

            // 如果删除失败，则返回错误信息
            if (!deleteAll) {
                return AjaxResult.error("删除角色失败,不存在关联用户");
            }

            // 如果删除成功，则返回成功信息
            return AjaxResult.success("删除角色成功");
        }
    }


    @ApiOperation(value = "删除角色", notes = "删除一个角色")
    @DeleteMapping("/deleteById/{roleId}")
    public AjaxResult deleteById(@PathVariable Integer roleId) {
        List<Integer> collect = new ArrayList<>();
        collect.add(roleId);
        List<UserRole> userRoleList = userRoleService.listByRoleIds(collect);
        if (userRoleList.size() > 0) {
            return AjaxResult.error("删除角色失败，存在关联用户");
        } else {
            boolean deleteAll = roleService.removeById(roleId);
            if (!deleteAll) {
                return AjaxResult.error("删除角色失败,不存在关联用户");
            }
            return AjaxResult.success("删除角色成功");
        }
    }

}
