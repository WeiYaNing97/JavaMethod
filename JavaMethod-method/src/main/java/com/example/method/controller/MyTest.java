package com.example.method.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: JavaMethod
 * @description: 项目启动测试
 * @author:
 * @create: 2024-06-14 15:38
 **/
@RestController
@RequestMapping("/mytest")
public class MyTest {
    @GetMapping("/test")
    public void test() {
        System.err.println("进来了");
    }
}
