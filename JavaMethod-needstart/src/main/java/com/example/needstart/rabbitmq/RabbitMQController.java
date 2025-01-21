package com.example.needstart.rabbitmq;

import com.example.needstart.rabbitmq.pubsub.PUBSUBProducer;
import com.example.needstart.rabbitmq.routing.RoutingProducer;
import com.example.needstart.rabbitmq.topic.TopicProducer;
import com.example.needstart.rabbitmq.work.WorkProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RabbitMQController")
public class RabbitMQController {

    @Autowired
    private PUBSUBProducer pubsubProducer;
    @Autowired
    private WorkProducer workProducer;
    @Autowired
    private RoutingProducer routingProducer;
    @Autowired
    private TopicProducer topicProducer;

    @GetMapping("/work")
    public String workMessage(String msg) {
        workProducer.sendMessage(msg);
        return "Message sent!";
    }

    @GetMapping("/pubsub")
    public String pubsubMessage(String msg) {
        pubsubProducer.sendMessage(msg);
        return "Message sent!";
    }

    @GetMapping("/routing")
    public String routingMessage(String key,String msg) {
        routingProducer.sendMessage(key,msg);
        return "Message sent!";
    }

    @GetMapping("/topic")
    public String topicMessage(String key,String msg) {
        topicProducer.sendMessage(key,msg);
        return "Message sent!";
    }
}

