package com.example.active.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    /**     * 监听指定队列的消息     * @param message 接收到的消息内容     */
    @JmsListener(destination = "myQueue", containerFactory = "queueListenerContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Received message from 1: " + message);
    }

    @JmsListener(destination = "myQueue", containerFactory = "queueListenerContainerFactory")
    public void receiveMessageTWO(String message) {
        System.err.println("Received message from 2: " + message);
    }

    @JmsListener(destination = "myQueue", containerFactory = "queueListenerContainerFactory")
    public void receiveMessageThree(String message) {
        System.err.println("Received message from 3: " + message);
    }
}
