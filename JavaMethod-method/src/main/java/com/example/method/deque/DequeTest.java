package com.example.method.deque;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 队列示例
 *
 * 队列是一种先进先出（FIFO）的数据结构，
 * 它只允许在表的前端进行删除操作，而在表的后端进行插入操作。
 * 队列的实现通常有两种方式：一种是使用链表实现的，另一种是使用数组实现的。
 * 在Java中，可以使用java.util包下的Deque接口和LinkedList类来实现队列。
 *
 * Deque接口是双端队列的抽象，它继承自Queue接口。
 * LinkedList类实现了Deque接口，因此可以使用它来实现队列。
 * 下面是一个使用LinkedList实现队列的示例：
 * 在上面的示例中，我们首先创建了一个LinkedList对象list，然后使用offerLast()方法向队列的末尾添加了三个元素。
 * 然后，我们创建了一个线程thread2，并启动它。
 * 在线程中，我们使用pollFirst()方法从队列的头部取出元素并打印出来。
 * 最后，我们再次尝试从队列的头部取出元素并打印出来。
 * 需要注意的是，在上面的示例中，我们使用了synchronized关键字对list对象加锁，以确保线程安全。
 * 这是因为当多个线程同时访问同一个队列时，如果不加锁，可能会出现数据不一致的问题。
 * 因此，在多线程环境下使用队列时，通常需要对队列对象加锁。
 * 另外，需要注意的是，在上面的示例中，我们使用了pollFirst()方法从队列的头部取出元素。
 * 这是因为pollFirst()方法会返回队列的第一个元素，并将其从队列中移除。
 * 如果想要从队列的末尾取出元素，可以使用pollLast()方法。
 * 总之，在Java中可以使用LinkedList类来实现队列，并通过Deque接口提供的方法来操作队列。
 */

@RestController
@RequestMapping("/dequeTest")
public class DequeTest {


    /**
     * 测试接口方法
     *
     * @RequestMapping("/test") 注解表示该方法对应的请求路径为 /test
     */
    @RequestMapping("/test")
    public void interfaceTest() {
        // 创建一个双端队列
        Deque<Integer> list = new LinkedList<>();

        /**
         * 实现队列还是堆的区别在于 offerFirst和offerLast的区别
         * 在从顶端取值的前体现，offerLast是尾部添加，pollFirst是头部取出，就是队列，先进先出
         * 在从顶端取值的前体现，offerFirst是头部添加，pollFirst是头部取出，就是堆，先进后出
         */
        // 向队列末尾添加元素
        list.offerLast(1);
        list.offerLast(2);
        list.offerLast(3);

        // 创建一个新线程，线程任务为执行runTest类的run方法，线程名为"出栈线程"
        Thread thread2 = new Thread(new runTest(list),"出栈线程");
        // 启动线程
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
        // 当list不为空时循环
        while (list.size() > 0) {
            // 对list加锁，确保线程安全
            synchronized (list) {
                // 打印当前线程名及list中第一个元素的值
                System.err.println(Thread.currentThread().getName()+"数量:"+list.pollFirst());
            }
        }
        // 当list为空时，尝试获取第一个元素，此时应为null
        System.err.println(Thread.currentThread().getName()+"数量:"+list.pollFirst());
    }

}
