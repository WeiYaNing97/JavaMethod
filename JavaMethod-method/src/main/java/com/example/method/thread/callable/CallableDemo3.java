package com.example.method.thread.callable;

import java.util.concurrent.*;

/**
 * Runnable 转 Callable 的三种方式
 *
 * 1. 使用Executors的callable方法
 * 2. 使用FutureTask的方式，传入Runnable和结果
 * 3. 使用线程池, 提交的时候传入Runnable和result
 *
 */
public class CallableDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable r1 = ()->{
            System.out.println("I am Runnable");
        };

        // 转换成Callable:
        Callable<String> c1 = Executors.callable(r1, "result");
        //FutureTask<String> futureTask1 = new FutureTask<>(c1);
        FutureTask futureTask1 = new FutureTask(c1);
        Thread thread1 = new Thread(futureTask1);
        thread1.start();
        System.out.println(futureTask1.get());
        // 然后运行Callable的方式即可：


        // 方式二： 使用FutureTask的方式，传入Runnable和结果
        FutureTask futureTask2 = new FutureTask(r1, "result2");
        Thread thread2 = new Thread((futureTask2));
        thread2.start();
        System.out.println(futureTask2.get());


        // 方式三： 使用线程池, 提交的时候传入Runnable和result
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> result3 = executorService.submit(r1, "result3");
        System.out.println(result3.get());
    }
}
