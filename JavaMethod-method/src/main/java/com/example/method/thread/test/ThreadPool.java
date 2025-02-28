package com.example.method.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        for (int i = 1 ; i <= 1000 ; i++){
            stringList.add("value "+i);
        }
        try {
            switchLoginInformations(stringList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 切换登录信息的方法
     *
     * 使用线程池并发处理登录信息切换任务。每个登录信息作为一个任务提交到线程池中执行。
     * 线程池的最大线程数被限制为3，以确保系统资源不会被过度占用。
     *
     * @param stringList 包含登录信息的字符串列表
     * @throws InterruptedException 如果在等待所有线程执行完毕时被中断，则抛出此异常
     */
    public static void switchLoginInformations(List<String> stringList) throws InterruptedException {
        // 用于计数线程是否执行完成
        CountDownLatch countDownLatch = new CountDownLatch(stringList.size());
        // 创建一个固定大小的线程池，线程池中最多有 3 个线程
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        int i = 1;
        for (String string:stringList){

            // 等待线程池中的线程数小于3时再继续执行
            while (((ThreadPoolExecutor) fixedThreadPool).getActiveCount() >= 3){
                System.out.println("最大线程数已达到，等待线程执行完毕再进行下一次操作");
                Thread.sleep(500); // 简单等待策略
            }
            /*获取线程名*/
            String threadName = getThreadName(i);
            i++;
            // 执行线程任务
            fixedThreadPool.execute( new JobThread(threadName,string,countDownLatch));//mode, ip, name, password,configureCiphers, port, loginUser,time

        }

        // 等待所有线程执行完毕
        countDownLatch.await();
        /*关闭线程池*/
        fixedThreadPool.shutdown();
    }


    public static void removeThread(String threadname) {
        System.err.println("删除线程Thread" + threadname);
    }

    /*线程命名*/
    public static String getThreadName(int i) {
        String name = System.currentTimeMillis() + new Random().nextInt(100) +" ";
        return "threadname" + name;
    }

}
