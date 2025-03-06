package com.example.user.controller;


import com.example.method.result.AjaxResult;
import com.example.user.entity.Roles;
import com.example.user.entity.UserDetails;
import com.example.user.entity.Users;
import com.example.user.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Users users) {
        boolean save = usersService.add(users);
        return save? AjaxResult.success() : AjaxResult.error();
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Integer id) {
        boolean delete = usersService.removeById(id);
        return delete? AjaxResult.success() : AjaxResult.error();
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/update")
    public AjaxResult update(@RequestBody Users users) {
        boolean b = usersService.updateById(users);
        return b?AjaxResult.success():AjaxResult.error();
    }

    @ApiOperation("查看用户详情")
    @GetMapping("/viewUserDetails")
    public AjaxResult viewUserDetails() {
        List<UserDetails> userDetailsList = usersService.viewUserDetails();
        return AjaxResult.success(userDetailsList);
    }
}
