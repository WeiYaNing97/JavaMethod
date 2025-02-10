package com.example.userLogin.controller;

import com.example.method.thread.threadpoolapplication.MyThreadPool;
import com.example.userLogin.domain.MySqlTest;
import com.example.userLogin.domain.TUser;
import com.example.userLogin.service.IMySqlTestService;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * 用户登录信息表Controller
 *
 * @author ruoyi
 * @date 2024-06-16
 */
@RestController
@RequestMapping("/mysqltest")
public class MySqlTestController {
    @Autowired
    private IMySqlTestService iMySqlTestService;

    @Value("${splitKey}")
    private String splitKey;


    @RequestMapping("/add")
    @Transactional
    public int add() {
        MySqlTest mySqlTest = new MySqlTest();
        mySqlTest.setTestInformation("controller测试数据");
        // 获取第一个时间点
        Instant start = Instant.now();

        int j = iMySqlTestService.insertMySqlTest(mySqlTest);

        // 获取第二个时间点
        Instant end = Instant.now();
        // 计算时间差
        Duration duration = Duration.between(start, end);
        // 输出结果，格式化为秒和纳秒
        System.out.printf("插入 Time difference: %d.%09d seconds%n",
                duration.getSeconds(), duration.getNano());
        return j;
    }

    @RequestMapping("/select")
    public String Select() {
        // 获取第一个时间点
        Instant start = Instant.now();
        List<MySqlTest> mySqlTestList = iMySqlTestService.selectAll();
        // 获取第二个时间点
        Instant end = Instant.now();
        // 计算时间差
        Duration duration = Duration.between(start, end);
        // 输出结果，格式化为秒和纳秒
        System.out.printf("读取 Time difference: %d.%09d seconds%n",
                duration.getSeconds(), duration.getNano());
        return null;
    }
}