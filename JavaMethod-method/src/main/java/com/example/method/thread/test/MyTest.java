package com.example.method.thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程命名
 */
public class MyTest {
    public static void main(String[] args) {
        // 创建一个新的线程，并设置线程名称为"MyTest测试"
        Thread t1 = new Thread(new MyThread(),"MyTest测试");
        // 启动线程
        t1.start();
    }
}
