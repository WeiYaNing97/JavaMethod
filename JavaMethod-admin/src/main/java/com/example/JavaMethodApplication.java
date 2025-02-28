package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class JavaMethodApplication {
    /**
     * Java程序的入口方法，用于启动Spring Boot应用
     *
     * @param args 传递给程序的命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(JavaMethodApplication.class, args);
    }
}
