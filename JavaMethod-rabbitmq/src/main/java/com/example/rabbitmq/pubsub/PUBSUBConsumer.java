package com.example.rabbitmq.pubsub;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PUBSUBConsumer {

    /**
     * 使用RabbitMQ的监听器注解，监听来自"boot_pubsub"交换机并广播到队列的消息。
     * 当消息到达时，会调用此方法进行处理。
     *
     * @param message 接收到的消息对象
     */

    @RabbitListener(
            // 使用RabbitListener注解，指定消息监听器的配置
            bindings = @QueueBinding(
                    // 队列绑定配置
                    value = @Queue,
                    // 交换机绑定配置
                    exchange = @Exchange(name = "boot_pubsub",type = "fanout")))// 交换机名 交换机类型
    public void handleMessage(Object message) {
        // 输出接收到的消息
        System.err.println("Received message: " + message);
    }
}
