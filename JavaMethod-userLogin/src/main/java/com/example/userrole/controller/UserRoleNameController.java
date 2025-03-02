package com.example.userrole.controller;


import com.example.method.result.AjaxResult;
import com.example.userrole.entity.UserRoleName;
import com.example.userrole.service.IUserRoleNameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
@RestController
@RequestMapping("/userrole/user_role_name")
@Api(tags = "用户角色名称视图")
public class UserRoleNameController {
    @Autowired
    private IUserRoleNameService userRoleNameService;


    @ApiOperation("获得员工角色")
    @GetMapping("/obtainEmployeeRoles")
    public AjaxResult obtainEmployeeRoles(String userName) {
       String roleName= userRoleNameService.obtainEmployeeRoles(userName);
       return AjaxResult.success(roleName);
    }

    @ApiOperation("获得角色的员工")
    @GetMapping("/employeesWhoHaveObtainedRoles")
    public AjaxResult employeesWhoHaveObtainedRoles(String roleName) {
        List<String> userNames= userRoleNameService.employeesWhoHaveObtainedRoles(roleName);
        return AjaxResult.success(userNames);
    }

    @ApiOperation("获得pojo列表")
    @GetMapping("/getPojoLint")
    public AjaxResult getPojoLint(@RequestBody UserRoleName userRoleName) {
        /*
        *
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1); // 假设我们想查找状态为1的用户
        queryWrapper.gt("age", 18); // 并且年龄大于18岁的用户
        * */
        List<UserRoleName> pojoList = userRoleNameService.getPojo(userRoleName);
        return AjaxResult.success(pojoList);
    }
}
