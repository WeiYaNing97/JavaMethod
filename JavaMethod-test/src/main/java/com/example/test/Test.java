package com.example.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/TestController")
@Api(value = "TestController接口", tags = {"TestController接口"})
public class Test {
    @ApiOperation("新增用户")
    @GetMapping("/text")
    public static void text() {
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Hello, world!" + new Date());
        });
        thread.run();
    }

}