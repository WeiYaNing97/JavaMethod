package com.example.method.thread.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程命名
 */
public class MyTest {

    static  int i = 10000;
    static  int j = 0;
    public static void main(String[] args) {
        // 创建一个新的线程，并设置线程名称为"MyTest测试"
        //Thread t1 = new Thread(new MyThread(),"MyTest测试");
        // 启动线程
        //t1.start();
        ExecutorService executor = Executors.newFixedThreadPool(10000);
        for (int i = 0; i < 10000; i++) {
            executor.execute(new LockTest());
            executor.execute(new synchronizedTest());
        }
    }
}
