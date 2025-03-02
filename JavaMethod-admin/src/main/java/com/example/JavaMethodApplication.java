package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * 添加 @EnableWebSocket 注解的目的是启用 Spring Boot 中的 WebSocket 功能。
 * 通过添加这个注解，Spring Boot 会自动配置和启用与 WebSocket 相关的基础设施和处理机制，
 * 以便能够处理 WebSocket 连接、接收和发送消息等操作。如果不添加这个注解，可能会导致无法正常接收 WebSocket 请求，
 * 因为相关的配置和功能没有被启用。
 */
@EnableWebSocket
/**
 * @SpringBootApplication 是一个组合注解，
 * 它包含了 @Configuration、@EnableAutoConfiguration 和 @ComponentScan 三个注解。
 * @Configuration 注解表明该类是一个配置类，它可以包含一些特定的方法，这些方法会被 Spring 容器识别为 Bean。
 * @EnableAutoConfiguration 注解告诉 Spring Boot 根据添加的依赖自动配置应用。
 * @ComponentScan 注解用于指定 Spring Boot 应用中组件扫描的路径。
 * 通过使用 @SpringBootApplication，可以简化配置过程，
 */
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
