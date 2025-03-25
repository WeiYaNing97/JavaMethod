package com.example.method.thread.test;

public class synchronizedTest extends Thread {
    @Override
    public void run() {
        synchronized (this) {
            // 获取共享变量 i 的值
            int num = MyTest.j;
            // 对共享变量 i 进行减一操作
            num++;
            // 将更新后的值重新赋值给共享变量 i
            MyTest.j = num;
            // 输出共享变量 i 的值以及当前线程的信息
            System.err.println(MyTest.j + "执行了");
        }
    }
}
