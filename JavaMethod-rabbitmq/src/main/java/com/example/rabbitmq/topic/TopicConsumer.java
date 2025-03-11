package com.example.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {

    /**
     * 使用RabbitMQ的监听器注解，监听来自名为"boottopicexchange"的topic类型交换机发送的消息，
     * 并将这些消息路由到名为"boottopicqueue01"的队列中。
     *
     * @param message 接收到的消息对象
     *
     * 注意：该监听器绑定的队列仅接收路由键匹配"error.*"或"info.*"模式的消息。
     */
    @RabbitListener(bindings = @QueueBinding(
            // 队列绑定配置
            value = @Queue(name = "boottopicqueue"), // 队列名
            // 交换机绑定配置
            exchange = @Exchange(name = "boottopicexchange",type = "topic"), // 交换机名 交换机类型为topic
            // 路由键配置，匹配"error.*"或"info.*"模式的消息
            key = {"error.*","info.*"}
    ))
    public void handleMessage(Object message) {
        // 输出接收到的消息
        System.err.println("TopicConsumer message01: " + message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 使用RabbitMQ的监听器注解，监听来自名为"boottopicexchange"的topic类型交换机发送的消息，
     * 并将这些消息路由到名为"boottopicqueue01"的队列中。
     *
     * @param message 接收到的消息对象
     *
     * 注意：该监听器绑定的队列仅接收路由键匹配"error.*"或"info.*"模式的消息。
     */
    @RabbitListener(bindings = @QueueBinding(
            // 队列绑定配置
            value = @Queue(name = "boottopicqueue"), // 队列名
            // 交换机绑定配置
            exchange = @Exchange(name = "boottopicexchange",type = "topic"), // 交换机名 交换机类型为topic
            // 路由键配置，匹配"error.*"或"info.*"模式的消息
            key = {"error.*","info.*"}
    ))
    public void handleMessage02(Object message) {
        // 输出接收到的消息
        System.err.println("TopicConsumer message02: " + message);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
