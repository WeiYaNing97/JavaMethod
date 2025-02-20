package com.example.rabbitmq.routing;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RoutingConsumer {

    /**
     * 使用RabbitMQ的监听器注解，监听来自"bootrountingexchange"交换机的消息，并处理这些消息。
     * 监听器绑定的队列名为"bootrountingqueue01"，交换机类型为direct，并且只处理路由键为"error"、"info"或"zidingyi"的消息。
     *
     * @param message 接收到的消息对象
     */
    @RabbitListener(
            // 使用@QueueBinding注解绑定队列和交换机
            bindings = @QueueBinding(
                    // 定义队列，队列名为"bootrountingqueue01"
                    value = @Queue(name = "bootrountingqueue01"), //队列名
                    // 定义交换机，交换机名为"bootrountingexchange"，类型为direct
                    exchange = @Exchange(name = "bootrountingexchange",type = "direct"), //交换机名 交换机类型为direct
                    // 定义路由键，只处理路由键为"error"、"info"或"zidingyi"的消息
                    key = {"error","info","zidingyi"} //定向参数
            ))
    public void handleMessage(Object message) {
        // 打印接收到的消息
        System.err.println("RoutingConsumer message1: " + message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用RabbitMQ的监听器注解，监听来自"bootrountingexchange"交换机的消息，并处理这些消息。
     * 监听器绑定的队列名为"bootrountingqueue01"，交换机类型为direct，并且只处理路由键为"error"、"info"或"zidingyi"的消息。
     *
     * @param message 接收到的消息对象
     */
    @RabbitListener(
            // 使用@QueueBinding注解绑定队列和交换机
            bindings = @QueueBinding(
                    // 定义队列，队列名为"bootrountingqueue01"
                    value = @Queue(name = "bootrountingqueue02"), //队列名
                    // 定义交换机，交换机名为"bootrountingexchange"，类型为direct
                    exchange = @Exchange(name = "bootrountingexchange",type = "direct"), //交换机名 交换机类型为direct
                    // 定义路由键，只处理路由键为"error"、"info"或"zidingyi"的消息
                    key = {"error","info","zidingyi"} //定向参数
            ))
    public void handleMessage2(Object message) {
        // 打印接收到的消息
        System.err.println("RoutingConsumer message2: " + message);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
