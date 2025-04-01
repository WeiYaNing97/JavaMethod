package com.example.method.thread.blockingqueue;

import java.util.concurrent.*;

public class DynamicTaskQueueExample {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个阻塞队列存放任务
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        // 创建线程池，持续从队列中获取任务并执行
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        // 创建10个线程，每个线程都从队列中获取任务并执行
        for (int i = 0; i < 10; i++) {
            threadPool.submit(() -> {
                try {
                    // 无限循环，持续从队列中获取任务 不用担心死循环 因为有阻塞队列的存在
                    // 不用担心任务过多导致内存溢出 因为有队列的限制
                    while (true) {
                        // 阻塞等待任务 直到有任务到来 或者线程被中断
                        Runnable task = taskQueue.take();
                        task.run();
                    }
                } catch (InterruptedException e) {
                    // 中断当前线程
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 提交初始任务
        for (int i = 0; i < 100; i++) {
            final int taskId = i;
            taskQueue.offer(() -> {
                System.out.println("Initial Task " + taskId + " is running.");
                try {
                    // 模拟任务执行时间
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 中断当前线程
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 动态添加任务
        Thread.sleep(10000); // 模拟延迟
        taskQueue.offer(() -> {
            System.out.println("Newly Added Task is running.");
        });

        // 关闭线程池（实际应用中需要更复杂的逻辑来优雅关闭）
        threadPool.shutdown();
    }

}