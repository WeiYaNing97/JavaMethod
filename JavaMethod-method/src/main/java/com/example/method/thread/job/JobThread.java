package com.example.method.thread.job;

import java.util.concurrent.CountDownLatch;

public class JobThread extends Thread {

    String threadName = null;
    String value = null;
    CountDownLatch countDownLatch = null;

    // 为线程命名
    public JobThread(String threadName,
                     String value,
                     CountDownLatch countDownLatch) {
        this.threadName =threadName;
        this.value = value;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        // 逻辑代码
        System.err.println("====== 线程开始 ======");
        System.err.println("线程名："+ threadName);
        System.err.println("参数值："+ value);
        System.err.println("countDownLatch值："+ countDownLatch.getCount());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        countDownLatch.countDown();
            System.err.println("====== 线程结束 ======");
    }
}
