package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * JMS（Java Message Service）是一个用于在应用程序之间交换消息的Java API，
 * 它允许应用程序通过消息队列或主题订阅的方式进行异步通信。
 * JMS主要应用于需要可靠的消息传递、系统解耦、提高可伸缩性和支持最终一致性等场景。
 *
 * JMS的主要用途包括：
 * 异步通信：JMS允许发送者和接收者不需要同时在线即可进行通信，这非常适合于那些对响应时间要求不高的场景。
 * 系统解耦：通过使用消息队列，可以降低系统各组件之间的直接依赖性，从而使得系统更加灵活和易于维护。
 * 负载均衡与扩展性：消息中间件能够平衡不同消费者之间的负载，并且支持系统的水平扩展。
 * 确保消息传递：JMS提供了多种消息传递模式（如持久化消息），以确保即使在网络故障或其他异常情况下，消息也能被成功传递。
 * 事务支持：可以在一个事务中发送或接收多条消息，保证数据的一致性。
 */
//@EnableJms // 开启JMS
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
