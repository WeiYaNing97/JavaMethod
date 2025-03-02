package com.example.userrole.controller;


import com.example.method.result.AjaxResult;
import com.example.userrole.entity.User;
import com.example.userrole.entity.UserVO;
import com.example.userrole.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
@RestController
@RequestMapping("/userrole/user")
@Api(tags = "用户角色管理")
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody UserVO user) {
        boolean save = userService.add(user);
        if (save) {
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @DeleteMapping("/delete/{userId}")
    public AjaxResult delete(@PathVariable Integer userId) {
        boolean save = userService.deleteById(userId);
        if (save) {
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }
}
