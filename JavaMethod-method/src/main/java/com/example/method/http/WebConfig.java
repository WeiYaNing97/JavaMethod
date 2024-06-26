package com.example.method.http;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 重写父类方法，添加自定义拦截器到拦截器注册中心。
     *
     * @param registry 拦截器注册中心
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 将自定义拦截器添加到拦截器注册中心
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/mytest/**");
    }
}
