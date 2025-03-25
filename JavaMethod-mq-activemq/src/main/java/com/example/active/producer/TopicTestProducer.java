package com.example.active.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class TopicTestProducer {

    @Autowired
    private JmsTemplate topicJmsTemplate;

    public void sendMessage(String topicName, String message) {
        System.out.println("Sending message to topic: " + topicName);
        topicJmsTemplate.convertAndSend(topicName, message);
    }
}