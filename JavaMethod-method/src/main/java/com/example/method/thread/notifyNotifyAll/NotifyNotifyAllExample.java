package com.example.method.thread.notifyNotifyAll;

/**
 *
 * 问题：为什么线程挂起 lock.wait(); // 等待通知 ，下一个线程还可以获得lock锁，synchronized (lock)？
 * 因为当一个线程调用 `lock.wait()` 时，它会释放该对象上的锁。这意味着其他线程可以进入同一个对象的同步块并获取这个锁。
 * 因此，当一个线程调用 `lock.wait()` 后，其他线程可以进入同一个对象的同步块并获取这个锁。
 *
 * 这是一个非常好的问题！在 Java 中，`synchronized` 和 `wait()` 的工作机制可能会让人感到困惑，但它们的设计是经过深思熟虑的，目的是为了支持线程间的协作。下面我们来详细解释为什么一个线程调用 `lock.wait()` 后会挂起，而其他线程仍然可以获取到 `lock` 锁。
 *
 * ---
 *
 * ### 1. **`synchronized` 的作用**
 * `synchronized` 是用来保证线程同步的关键字。当一个线程进入 `synchronized` 块时，它必须获得与该块关联的对象锁（即这里的 `lock`）。如果锁被其他线程占用，当前线程会被阻塞，直到锁可用。
 *
 * ---
 *
 * ### 2. **`wait()` 的作用**
 * `wait()` 是 `Object` 类中的方法，必须在 `synchronized` 块中调用。它的作用是：
 * - **释放对象锁**：调用 `lock.wait()` 的线程会释放它持有的 `lock` 对象的锁。
 * - **挂起线程**：调用 `wait()` 的线程会进入等待状态，直到其他线程调用 `notify()` 或 `notifyAll()` 来唤醒它。
 * - **重新竞争锁**：当线程被唤醒后，它不会立即继续执行，而是需要重新竞争 `lock` 锁。只有成功获取锁后，才能从 `wait()` 方法返回并继续执行。
 *
 * ---
 *
 * ### 3. **为什么其他线程可以获得锁？**
 * 当你调用 `lock.wait()` 时，线程会释放 `lock` 锁。这意味着其他线程有机会进入 `synchronized (lock)` 块并获取锁。以下是具体的流程：
 *
 * #### 流程分析：
 * 1. **线程 A 进入 `synchronized (lock)` 块**：
 *    - 线程 A 获得了 `lock` 锁。
 *    - 它检查条件 `while (!ready)`，发现条件为 `false`，于是调用 `lock.wait()`。
 *
 * 2. **线程 A 调用 `wait()`**：
 *    - 线程 A 释放了 `lock` 锁，并进入等待状态。
 *    - 此时，`lock` 锁是空闲的，其他线程可以竞争锁。
 *
 * 3. **线程 B 进入 `synchronized (lock)` 块**：
 *    - 线程 B 成功获取了 `lock` 锁。
 *    - 它同样检查条件 `while (!ready)`，发现条件为 `false`，于是也调用 `lock.wait()`。
 *
 * 4. **线程 C 进入 `synchronized (lock)` 块**：
 *    - 线程 C 成功获取了 `lock` 锁。
 *    - 它同样检查条件 `while (!ready)`，发现条件为 `false`，于是也调用 `lock.wait()`。
 *
 * 5. **主线程调用 `notify()` 或 `notifyAll()`**：
 *    - 主线程获取了 `lock` 锁，并将 `ready` 设置为 `true`。
 *    - 如果调用 `notify()`，只有一个等待的线程会被唤醒。
 *    - 如果调用 `notifyAll()`，所有等待的线程都会被唤醒。
 *
 * 6. **被唤醒的线程重新竞争锁**：
 *    - 被唤醒的线程并不会立即继续执行，而是需要重新竞争 `lock` 锁。
 *    - 只有成功获取锁的线程才能从 `wait()` 返回并继续执行。
 *
 * ---
 *
 * ### 4. **总结：为什么其他线程可以获得锁？**
 * - 当一个线程调用 `lock.wait()` 时，它会**主动释放 `lock` 锁**。
 * - 释放锁后，其他线程才有机会进入 `synchronized (lock)` 块并获取锁。
 * - 这是 `wait()` 设计的核心目的之一：它允许线程在等待某个条件满足时，暂时放弃锁，从而让其他线程有机会修改共享资源或通知条件已满足。
 *
 * ---
 *
 * ### 5. **关键点补充**
 * - **`notify()` 和 `notifyAll()` 的区别**：
 *   - `notify()`：只唤醒一个等待的线程，具体哪个线程由 JVM 决定。
 *   - `notifyAll()`：唤醒所有等待的线程，所有线程重新竞争锁。
 *
 * - **`wait()` 必须在 `synchronized` 块中调用**：
 *   - 如果不在 `synchronized` 块中调用 `wait()`，会抛出 `IllegalMonitorStateException`。
 *   - 这是因为 `wait()` 的设计要求线程必须持有锁才能调用它。
 *
 * - **`wait()` 和 `sleep()` 的区别**：
 *   - `wait()`：释放锁，进入等待状态。
 *   - `sleep()`：不释放锁，进入休眠状态。
 *
 * ---
 *
 * ### 6. **代码输出的解释**
 * ```text
 * Waiter-3 is waiting...
 * Waiter-1 is waiting...
 * Waiter-2 is waiting...
 * Notifying threads...
 * Waiter-3 has been notified and is proceeding.
 * ```
 *
 * - `Waiter-1`、`Waiter-2` 和 `Waiter-3` 都进入了 `lock.wait()` 并释放了锁。
 * - 主线程调用 `lock.notify()`，随机唤醒了一个线程（这里是 `Waiter-3`）。
 * - 被唤醒的线程（`Waiter-3`）重新竞争锁，成功获取锁后继续执行。
 *
 * 如果使用 `lock.notifyAll()`，所有线程都会被唤醒，并依次竞争锁，最终输出如下：
 * ```text
 * Waiter-3 is waiting...
 * Waiter-1 is waiting...
 * Waiter-2 is waiting...
 * Notifying threads...
 * Waiter-3 has been notified and is proceeding.
 * Waiter-1 has been notified and is proceeding.
 * Waiter-2 has been notified and is proceeding.
 * ```
 *
 * ---
 *
 * 通过以上解释，你应该能理解为什么 `lock.wait()` 会让线程挂起，而其他线程仍然可以获取锁了！
 */
public class NotifyNotifyAllExample {
    private static final Object lock = new Object();
    private static boolean ready = false;

    public static void main(String[] args) {
        // 创建三个等待线程
        Thread waiter1 = new Thread(new Waiter("Waiter-1"), "Waiter-1");
        Thread waiter2 = new Thread(new Waiter("Waiter-2"), "Waiter-2");
        Thread waiter3 = new Thread(new Waiter("Waiter-3"), "Waiter-3");

        // 启动等待线程
        waiter1.start();
        waiter2.start();
        waiter3.start();

        try {
            // 主线程等待一段时间，确保所有线程都进入等待状态
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 使用 notify() 或 notifyAll()
        synchronized (lock) {
            System.out.println("Notifying threads...");
            ready = true;
            lock.notify(); // 只唤醒一个线程
            //lock.notifyAll(); // 唤醒所有线程
        }
    }

    static class Waiter implements Runnable {
        private String name;

        public Waiter(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (!ready) {
                    System.out.println(name + " is waiting...");
                    try {
                        lock.wait(); // 等待通知
                        /**
                         * wait() 的作用
                         * wait() 是 Object 类中的方法，必须在 synchronized 块中调用。它的作用是：
                         *
                         * 释放对象锁：调用 lock.wait() 的线程会释放它持有的 lock 对象的锁。
                         * 挂起线程：调用 wait() 的线程会进入等待状态，直到其他线程调用 notify() 或 notifyAll() 来唤醒它。
                         * 重新竞争锁：当线程被唤醒后，它不会立即继续执行，而是需要重新竞争 lock 锁。只有成功获取锁后，才能从 wait() 方法返回并继续执行。
                         */
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name + " has been notified and is proceeding.");
            }
        }
    }
}