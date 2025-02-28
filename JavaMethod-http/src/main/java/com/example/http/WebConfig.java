package com.example.http;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义拦截器配置类，用于注册自定义的拦截器。
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 重写父类方法，添加自定义拦截器到拦截器注册中心。
     *
     * @param registry 拦截器注册中心
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 将自定义拦截器添加到拦截器注册中心，并设置拦截路径为 /HttpTest/**
        // 将自定义拦截器添加到拦截器注册中心
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/HttpTest/**");

        // 将自定义拦截器添加到拦截器注册中心，并设置拦截路径为 /RabbitMQController/**
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/RabbitMQController/**");

        // 将自定义拦截器添加到拦截器注册中心，并设置拦截路径为 /quartz/**
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/quartz/**");
    }

}
