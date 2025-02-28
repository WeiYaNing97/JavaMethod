package com.example.rabbitmq;

import com.example.rabbitmq.pubsub.PUBSUBProducer;
import com.example.rabbitmq.routing.RoutingProducer;
import com.example.rabbitmq.topic.TopicProducer;
import com.example.rabbitmq.work.WorkProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
    public String workMessage() {/*String msg*/

        for (int i = 1; i <= 10; i++) {
            workProducer.sendMessage("msg"+i);
        }

        System.out.println("Message sent!");
        return "Message sent!";
    }

    @GetMapping("/pubsub")
    public String pubsubMessage(String msg) {
        pubsubProducer.sendMessage(msg);
        return "Message sent!";
    }

    @GetMapping("/routing")
    public String routingMessage() {/*String key,String msg*/
        String key ="info";
        for (int i = 1; i <= 100; i++) {
            String msg = "msg"+i;
            routingProducer.sendMessage(key,msg);
        }

        return "Message sent!";
    }

    @GetMapping("/topic")
    public String topicMessage() {/*String key,String msg*/

        String key ="info.aaa";
        for (int i = 1; i <= 100; i++) {
            String msg = "msg"+i;
            topicProducer.sendMessage(key,msg);
        }
        return "Message sent!";
    }
}

