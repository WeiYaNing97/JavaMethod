package com.example.userLogin.controller;
import com.example.userLogin.domain.MySqlTest;
import com.example.userLogin.service.IMySqlTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.Duration;
import java.util.List;

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
        mySqlTest.setTestInformation("123456789012345678901234567890123456789012345678901234567890");

        for (int w = 1; w < 11;w++) {
            Instant start = Instant.now();
            for (int i = 0; i < 10000; i++) {
                // 获取第一个时间点
                int j = iMySqlTestService.insertMySqlTest(mySqlTest);
            }
            // 获取第二个时间点
            Instant end = Instant.now();
            // 计算时间差
            Duration duration = Duration.between(start, end);
            // 输出结果，格式化为秒和纳秒
            System.out.printf(w + "次插入 Time difference: %d.%09d seconds%n",
                    duration.getSeconds(), duration.getNano());
        }
        return 1;
    }

    @RequestMapping("/select")
    public String Select() {
        int startInt = 904221;
        for (int  w = 1; w < 16;w++) {
            Instant start = Instant.now();
            MySqlTest mySqlTestList1 = new MySqlTest();
            int i = startInt;
            for (; i < startInt + 10000; i++) {
                // 获取第一个时间点
                mySqlTestList1 = iMySqlTestService.selectById(i);
            }
            // 获取第二个时间点
            Instant end = Instant.now();
            // 计算时间差
            Duration duration = Duration.between(start, end);
            // 输出结果，格式化为秒和纳秒
            System.out.printf(w+" 次读取 Time difference: %d.%09d seconds%n",
                    duration.getSeconds(), duration.getNano());
            startInt = i;
        }

        return null;
    }

    /**读取文本内容*/
    public static String ReadFileContent(String path) {
        String content = "";
        try (
                FileReader fileReader = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            // 循环读取文件中的每一行内容
            while ((line = bufferedReader.readLine()) != null) {
                // 将读取到的每一行内容添加到列表中
                content += line;
            }
        } catch (IOException e) {
            // 获取异常类型
            String exceptionType = e.getClass().getSimpleName();
            if (exceptionType.equals("FileNotFoundException")) {
                System.out.println("文件不存在！");
            } else {
                // 捕获IO异常，并打印堆栈信息和错误信息
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        // 返回存储文本内容的列表
        return content;
    }
}