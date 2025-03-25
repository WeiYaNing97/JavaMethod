package com.example.method.thread.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println("我正在执行线程");
        System.out.println("线程正在执行");
        System.out.println("线程名称"+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程执行完毕");
    }
}
