package com.example.method.thread.threadpoolapplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 自定义线程池，在应用启动完成后初始化固定大小的线程池
 * 并通过 @Value 注解从配置文件中读取线程池大小
 * Executors.newFixedThreadPool(nThreads);
 */
@Component
@Order(2)
public class MyThreadPool implements ApplicationListener<ApplicationReadyEvent> {
    public static ExecutorService fixedThreadPool;
    @Value("${nThreads}")
    private int nThreads;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 创建一个固定大小的线程池，线程池中最多有 yml.nThreads 个线程
        // 初始化固定大小的线程池
        fixedThreadPool = Executors.newFixedThreadPool(nThreads);
    }

}
