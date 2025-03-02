package com.example.method.thread.threadpooltest;

import com.example.method.thread.test.MyThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 在Java中，`Executors`类提供了多种创建线程池的方法，每种方法都有其特定的配置和适用场景。以下是您提到的几种线程池的差异及其典型应用场景：
 *
 * 1. **固定大小的线程池 (`newFixedThreadPool(int nThreads)`):**
 *    - **特点**: 创建一个具有固定数量线程的线程池。如果所有线程都处于活动状态时有新的任务提交，则这些任务将等待直到有一个线程空闲出来。
 *    - **应用场景**: 适用于负载较为稳定的场景，可以控制并发线程的数量以避免过多资源消耗。
 *
 * 2. **可缓存的线程池 (`newCachedThreadPool()`):**
 *    - **特点**: 创建一个可根据需要创建新线程的线程池，但会在以前构造的、现在未使用的线程可用时重用它们。如果没有可用的线程，它会创建一个新的线程并添加到池中；当线程空闲时间超过60秒时，它们将被终止并从缓存中移除。
 *    - **应用场景**: 适合执行大量短生命周期的任务的应用程序，特别是在任务量波动较大且不可预测的情况下。
 *
 * 3. **单线程的线程池 (`newSingleThreadExecutor()`):**
 *    - **特点**: 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序（FIFO、LIFO、优先级）执行。
 *    - **应用场景**: 当你需要确保任务按顺序执行并且每个任务完全完成后才开始下一个任务时非常有用。
 *
 * 4. **定时线程池 (`newScheduledThreadPool(int corePoolSize)`):**
 *    - **特点**: 支持定时及周期性任务执行。除了拥有固定大小的线程池特性外，还允许你安排任务延迟执行或定期重复执行。
 *    - **应用场景**: 用于需要计划执行任务的情况，如定时轮询数据库、定时发送心跳包等。
 *
 * 每种线程池都有其独特的优势和适用场景。选择合适的线程池类型取决于具体的需求，例如：
 * - 如果你的应用中有大量的短期任务，使用`newCachedThreadPool()`可以提高性能。
 * - 对于需要严格控制并发级别的场景，`newFixedThreadPool(int nThreads)`是理想的选择。
 * - 当你需要确保任务按序执行时，`newSingleThreadExecutor()`是最好的选择。
 * - 需要调度任务在将来某个时刻执行或者定期执行时，应该使用`newScheduledThreadPool(int corePoolSize)`。
 *
 * 正确地选择线程池类型可以帮助优化应用程序的性能，并确保资源的有效利用。
 */
public class ExecutorsTest {
    public static void main(String[] args) {

        // 创建一个固定大小的线程池，大小为10
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 向线程池中提交一个任务
        executorService.execute(new MyThread());
        // 关闭线程池
        executorService.shutdown();

        // 创建一个可缓存的线程池
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        // 向线程池中提交一个任务
        executorService1.execute(new MyThread());
        // 关闭线程池
        executorService1.shutdown();

        // 创建一个单线程的线程池
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        // 向线程池中提交一个任务
        executorService2.execute(new MyThread());
        // 关闭线程池
        executorService2.shutdown();

        // 创建一个定时线程池，大小为10
        ExecutorService executorService3 = Executors.newScheduledThreadPool(10);
        // 向线程池中提交一个任务
        executorService3.execute(new MyThread());
        // 关闭线程池
        executorService3.shutdown();

    }
}
