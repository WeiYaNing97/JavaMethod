package com.example.method.thread.overtime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程超时处理示例
 *
 */
@RestController
@RequestMapping("/overTimeThreadPool")
public class OverTimeThreadPool {

    /**
     * 处理超时测试
     *
     * @RequestMapping("/timeoutTests") 注解用于将HTTP GET请求映射到timeoutTests方法上
     */
    @RequestMapping("/timeoutTests")
    public void timeoutTests() {
        // 创建一个存储时间的列表
        List<Integer> timeList = new ArrayList<>();
        // 循环生成时间列表
        for (int time = 1000; time <= 10000; time = time + 1000) {
            // 将时间添加到列表中
            timeList.add(time);
        }
        try {
            // 调用切换登录信息的方法
            switchLoginInformations(timeList);
        } catch (InterruptedException e) {
            // 捕获中断异常并打印堆栈跟踪
            e.printStackTrace();
        }
    }


    /**
     * 切换登录信息（模拟操作），根据给定的时间列表，创建多个定时任务，并等待它们完成。
     *
     * @param timeList 包含每个任务需要等待的时间（秒）的列表
     * @throws InterruptedException 如果等待时发生中断
     */
    public static void switchLoginInformations(List<Integer> timeList) throws InterruptedException {
        // 计数线程是否执行完成的计数器
        CountDownLatch countDownLatch = new CountDownLatch(timeList.size());
        int i = 1;
        for (Integer time : timeList) {
            // 创建一个只包含一个线程的新定时任务执行服务
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
            // 提交一个定时任务
            ScheduledFuture<?> schedule = scheduledExecutorService.schedule(
                    // 要监控的线程
                    () -> {
                        // 线程休眠指定的时间
                        try {
                            Thread.sleep(time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // 输出正常结束信息
                        System.out.println("正常结束：" + time);
                    },
                    // 初始延迟为0秒
                    0,
                    // 时间单位为秒
                    TimeUnit.SECONDS);

            try {
                // 等待任务执行结果，同时设置超时时间为6秒
                // 时间单位为秒
                schedule.get(6, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                // 超时要执行逻辑
                // 输出超时信息
                System.err.println("超时：" + time);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                /**
                 * 在Java的try-catch-finally语句中，如果try或catch块中存在return语句，会先执行finally块中的代码，然后再返回到调用的地方。
                 * 无论try或catch块中是否存在return语句，finally块中的代码都会被执行。
                 * finally块通常用于清理资源、关闭文件或释放锁等操作，以确保在程序执行过程中不论是否发生异常都能正确地执行这些操作。
                 * 需要注意的是，如果finally块中也存在return语句，它将会覆盖try或catch块中的return语句，即最终返回的结果是finally块中的return语句的返回值。
                 */
                // 关闭定时任务执行服务
                scheduledExecutorService.shutdown();
            }
        }
        // 等待所有线程执行完成
        countDownLatch.await();
    }
}
