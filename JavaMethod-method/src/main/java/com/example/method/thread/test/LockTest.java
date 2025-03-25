package com.example.method.thread.test;

import lombok.Synchronized;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest implements Runnable {
    static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        // 加锁 要用静态变量 lock，否则每次都会创建一个新的锁对象
        lock.lock();
        // 获取共享变量 i 的值
        int num = MyTest.i;
        // 对共享变量 i 进行减一操作
        num--;
        // 将更新后的值重新赋值给共享变量 i
        MyTest.i = num;
        // 输出共享变量 i 的值以及当前线程的信息
        System.out.println(MyTest.i + "执行了");
        // 解锁
        lock.unlock();
    }

}
