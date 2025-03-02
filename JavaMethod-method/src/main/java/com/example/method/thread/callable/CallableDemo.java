package com.example.method.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 创建一个有返回值的线程任务Callable, 返回String
        Callable<String> c = new Callable(){
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "| 正在执行有返回值任务");
                return "success";
            }
        };

        // 2. 创建一个Future用来执行多线程任务,构造方法中传入Callable类型变量
        FutureTask<String> futureTask = new FutureTask<>(c);
        // 创建Thread 线程，传入futureTask
        Thread t1 = new Thread(futureTask);

        // 3. 使用futureTask 执行线程任务
        t1.start();

        // 4. 获取该任务的返回结果：
        System.out.println(futureTask.get());
    }
}
