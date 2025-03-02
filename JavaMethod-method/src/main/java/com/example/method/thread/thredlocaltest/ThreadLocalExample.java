package com.example.method.thread.thredlocaltest;

/**
 * ThreadLocal示例
 *
 * ThreadLocal是用于存储线程局部变量的工具类，它可以为每个使用它的线程提供独立的变量副本。
 * 每个线程都可以独立地设置和获取自己的变量副本，而不会互相干扰。
 * ThreadLocal通常用于存储线程间的共享数据，但又不想让这些数据互相干扰的场景。
 * 例如，在Web服务器中处理HTTP请求时，每个线程都可能处理不同的用户会话。
 * 在这种情况下，可以使用ThreadLocal来存储每个线程的会话信息，而不会相互干扰。
 *
 * ThreadLocal的典型用法包括：
 * 1. 存储线程的会话信息
 * 2. 存储线程的数据库连接
 * 3. 存储线程的日志信息
 * 4. 存储线程的临时数据
 *
 * 在使用ThreadLocal时，需要注意以下几点：
 * 1. 每个线程都有自己的ThreadLocal变量副本，因此它们不会相互干扰。
 * 2. 当线程结束时，ThreadLocal变量会自动被垃圾回收。
 * 3. 如果ThreadLocal变量不再需要，应该调用remove()方法手动清除它。
 * 4. ThreadLocal变量不应该被设置为null，因为这会导致内存泄漏。
 * 5. ThreadLocal变量应该只在需要时才创建，并在不再使用时及时清除。
 *
 * ThreadLocal的使用示例如下：
 * ThreadLocal<String> threadLocal = new ThreadLocal<>();
 * threadLocal.set("Hello, Thread!");
 * String value = threadLocal.get();
 * threadLocal.remove();// 清除ThreadLocal变量
 * ThreadLocal变量通常用于存储线程的局部状态，例如会话信息、数据库连接等。
 * ThreadLocal变量通常在每个线程的生命周期内使用，并且在不再需要时应该被清除。
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
            for (int i = 0; i < 5; i++){
            System.out.println(Thread.currentThread().getName() + " initial value: " + threadLocalCounter.get());
            threadLocalCounter.set(threadLocalCounter.get() + 1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " updated value: " + threadLocalCounter.get());
            }

            /**
             * `ThreadLocal` 提供了线程局部变量，这意味着每个使用某 `ThreadLocal` 变量的线程都会有这个变量的一个独立的副本。
             * 换句话说，某个线程不能访问另一个线程的 `ThreadLocal` 变量副本。这种机制非常适合于在多线程环境下需要维护线程私有状态的场景。
             *
             * 当你调用 `threadLocalCounter.remove();` 时，这并不会清除整个内存中的缓存，而是从当前线程中移除与该 `ThreadLocal` 变量相关的值。
             * 具体来说，它会删除当前线程所绑定的 `ThreadLocal` 变量的值，使得之后对该变量的访问（在这个线程内）将再次触发 `initialValue()` 方法重新初始化它的值。
             * 这对于避免内存泄漏特别有用，尤其是在使用线程池的情况下，因为如果不手动移除 `ThreadLocal` 的值，由于线程是重复使用的，旧的值可能会一直存在，导致内存无法释放。
             *
             * 因此，执行 `threadLocalCounter.remove();` 操作后，
             * 你所做的只是清除了当前线程局部变量 `threadLocalCounter` 在当前线程中的值，
             * 并没有影响其他线程的 `threadLocalCounter` 值，
             * 也没有进行全局内存缓存的清理。这确保了该线程后续对该 `ThreadLocal` 变量的任何访问都会返回初始值或通过其他逻辑重新设置的值。
             */

            threadLocalCounter.remove();
            System.out.println(Thread.currentThread().getName() + " removed value: " + threadLocalCounter.get());
        };
        Runnable task2 = () -> {
            for (int i = 0; i < 5; i++) {
                System.err.println(Thread.currentThread().getName() + " initial value: " + threadLocalCounter.get());
                threadLocalCounter.set(threadLocalCounter.get() + 5);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.err.println(Thread.currentThread().getName() + " updated value: " + threadLocalCounter.get());
            }
            threadLocalCounter.remove();
        };
        Thread thread1 = new Thread(task1, "Thread-1");
        Thread thread2 = new Thread(task2, "Thread-2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}