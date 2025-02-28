package com.example.method.thread.test;

/**
 * ThreadLocal示例
 */
public class ThreadLocalExample {

    // 创建一个ThreadLocal变量
    private static ThreadLocal<Integer> threadLocalCounter = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0; // 设置初始值
        }
    };

    public static void main(String[] args) throws InterruptedException {
        // 创建并启动多个线程
        Runnable task1 = () -> {
            System.out.println(Thread.currentThread().getName() + " initial value: " + threadLocalCounter.get());
            threadLocalCounter.set(threadLocalCounter.get() + 1);
            System.out.println(Thread.currentThread().getName() + " updated value: " + threadLocalCounter.get());
        };
        Runnable task2 = () -> {
            System.out.println(Thread.currentThread().getName() + " initial value: " + threadLocalCounter.get());
            threadLocalCounter.set(threadLocalCounter.get() + 5);
            System.out.println(Thread.currentThread().getName() + " updated value: " + threadLocalCounter.get());
        };
        Thread thread1 = new Thread(task1, "Thread-1");
        Thread thread2 = new Thread(task2, "Thread-2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}