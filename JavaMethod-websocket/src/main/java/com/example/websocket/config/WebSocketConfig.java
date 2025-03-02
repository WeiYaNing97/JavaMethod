package com.example.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket配置类，用于开启WebSocket支持
 * 通过此类配置，Spring Boot应用程序可以自动注册WebSocket端点，使得使用JSR 356 (Java API for WebSocket) 定义的端点能够被自动发现和使用。
 */
@Configuration
public class WebSocketConfig {

    /**
     * 定义一个Bean，类型为ServerEndpointExporter，用于自动注册实现了WebSocket规范（JSR 356）的@ServerEndpoint注解声明的WebSocket端点。
     *
     * <p>
     * 当在Spring Boot应用程序中使用@ServerEndpoint注解声明WebSocket端点时，需要此Bean来确保端点能够被正确地注册和暴露。
     * </p>
     *
     * @return ServerEndpointExporter的实例，用于自动注册WebSocket端点
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
