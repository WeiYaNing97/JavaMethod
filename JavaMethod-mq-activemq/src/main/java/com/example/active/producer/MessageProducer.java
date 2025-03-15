package com.example.active.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.DeliveryMode;
import javax.jms.TextMessage;

@Service
public class MessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 发送消息到指定的队列或主题
     * @param destination 队列或主题名称
     * @param message 消息内容     */
    public void sendMessage(String destination, String message) {
        // 使用jmsTemplate发送消息，并设置为持久化
        jmsTemplate.send(destination, session -> {
            TextMessage textMessage = session.createTextMessage(message);
            textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT); // 设置消息为持久化模式
            return textMessage;
        });
        // 打印消息发送成功的信息
        System.out.println("Message sent to " + destination + ": " + message);
    }

}
