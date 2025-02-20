package com.example.rabbitmq.work;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WorkConsumer {

    /**
     * @Description
      * @ Component注解是Spring框架中的一个注解，用于标识一个类为一个可被Spring容器管理的组件。
      * 被@Component注解标识的类将会被Spring自动扫描并加载到应用上下文中，可以通过依赖注入等方式进行使用。
     */
    @RabbitListener(queuesToDeclare = @Queue("springbootrabbitMQwork"))
    public void receiveMsg(String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        System.out.println("消费者收到消息:"+ msg);


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*手动签收*/
        /*如果为 false，则只确认当前的 deliveryTag 对应的消息；
        如果为 true，则确认当前 deliveryTag 及之前所有未确认的消息。*/

        /*channel.basicAck(deliveryTag,true);*/
        if (channel.isOpen()) {
            channel.basicAck(deliveryTag, false);
        } else {
            System.out.println("Channel is closed, cannot ack/nack");
        }
    }
}
