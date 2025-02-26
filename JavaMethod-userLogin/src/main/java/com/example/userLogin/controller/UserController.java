package com.example.userLogin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
@Api(value = "swagger测试接口", tags = {"swagger测试接口"})
public class UserController {

    @ApiOperation("新增用户")
    @GetMapping("/text")
    public String getText(String name) {
        System.out.println("Hello! "+name + new Date().toLocaleString());
        return "Hello " + name;
    }
}
