package com.example.active.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TopicTestConsumer {

    @JmsListener(destination = "exampleTopic", containerFactory = "topicListenerContainerFactory")
    public void receiveMessage(String message) throws InterruptedException {
        System.out.println("开始");
        Thread.sleep(10000);
        System.out.println("Received message from topic: " + message);
        System.out.println("结束");
    }
    @JmsListener(destination = "exampleTopic", containerFactory = "topicListenerContainerFactory")
    public void receiveMessageTwo(String message) throws InterruptedException {
        System.out.println("开始");
        Thread.sleep(10000);
        System.err.println("Received message from topicTwo: " + message);
        System.out.println("结束");
    }
}