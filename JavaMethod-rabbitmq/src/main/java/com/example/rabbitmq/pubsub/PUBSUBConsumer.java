package com.example.rabbitmq.pubsub;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PUBSUBConsumer {


    @RabbitListener(
            // 使用RabbitListener注解，指定消息监听器的配置
            bindings = @QueueBinding(
                    // 队列绑定配置
                    value = @Queue,
                    // 交换机绑定配置
                    exchange = @Exchange(name = "boot_pubsub",type = "fanout")))// 交换机名 交换机类型
    public void handleMessage(Object message) {
        // 输出接收到的消息
        System.err.println("handleMessageReceived message: " + message);
    }


    @RabbitListener(
            // 使用RabbitListener注解，指定消息监听器的配置
            bindings = @QueueBinding(
                    // 队列绑定配置
                    value = @Queue,
                    // 交换机绑定配置
                    exchange = @Exchange(name = "boot_pubsub",type = "fanout")))// 交换机名 交换机类型
    public void handleMessagetTwo(Object message) {
        // 输出接收到的消息
        System.out.println("handleMessagetTwo Received message: " + message);
    }
}
