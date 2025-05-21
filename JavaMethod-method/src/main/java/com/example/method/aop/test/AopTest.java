package com.example.method.aop.test;

import com.example.method.aop.test.impl.CalcServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "AopTest")
@RestController
@RequestMapping("/AopTest")
public class AopTest {
    @Autowired
    private CalcService calcService;

    @PostMapping("/test")
    @ApiOperation(value = "测试除法")
    public void test() {
        int result = calcService.div(10, 2);
        System.out.println("最终结果: " + result);
    }
}
