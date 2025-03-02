package com.example.method.thread.callable;

import java.util.concurrent.*;

public class CallableDemo2 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 2. 创建一个Callable 线程任务
        Callable<Integer> c1 = ()->{
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + "| 开始执行任务----");
            return 1;
        };

        // 3. 执行线程任务，获取结果
        Future<Integer> future = executorService.submit(c1);

        // 4.打印结果
        System.out.println(future.get());
    }
}
