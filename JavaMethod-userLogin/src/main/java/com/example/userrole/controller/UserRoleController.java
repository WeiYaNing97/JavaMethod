package com.example.userrole.controller;


import com.example.method.result.AjaxResult;
import com.example.userrole.service.IUserRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
@RestController
@RequestMapping("/userrole/user_role")
@Api(tags = "用户角色管理")
public class UserRoleController {
}
