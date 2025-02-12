package com.example.method.thread.threadpoolapplication;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Component
@Order(2)
public class MyThreadPool implements ApplicationListener<ApplicationReadyEvent> {
    public static ExecutorService fixedThreadPool;
    @Value("${nThreads}")
    private int nThreads;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 创建一个固定大小的线程池，线程池中最多有 3 个线程
        fixedThreadPool = Executors.newFixedThreadPool(nThreads);
    }
}
