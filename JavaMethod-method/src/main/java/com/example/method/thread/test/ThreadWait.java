package com.example.method.thread.test;

import java.util.Deque;
import java.util.LinkedList;

public class ThreadWait {
    static Deque<Integer> list = new LinkedList<>();

    public static void main(String[] args) {
        list.offerLast(1);
        list.offerLast(2);
        list.offerLast(3);
        Thread thread2 = new Thread(new runTest(list),"出栈线程");
        thread2.start();
    }
}
class runTest implements Runnable {

    private  Deque<Integer> list = null;

    runTest(Deque<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (list.size() > 0) {
            synchronized (list) {
                System.err.println(Thread.currentThread().getName()+"数量:"+list.pollFirst());
            }
        }
        System.err.println(Thread.currentThread().getName()+"数量:"+list.pollFirst());
    }
}
