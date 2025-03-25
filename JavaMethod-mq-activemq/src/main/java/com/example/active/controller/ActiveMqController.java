package com.example.active.controller;

import com.example.active.producer.MessageProducer;
import com.example.active.producer.TopicTestProducer;
import com.example.method.result.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "ActiveMQ测试接口类")
@RestController
@RequestMapping("/activemq")
class ActiveMqController {

    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    TopicTestProducer topicTestProducer;


    @ApiOperation(value = "发送消息到队列")
    @GetMapping("/Queue/sendAndReceive")
    public AjaxResult QueueSendAndReceiveMessage(String destination, String message) {
        // 发送消息
        for (int i = 0; i < 10; i++) {
            messageProducer.sendMessage(destination, message);
        }
        return AjaxResult.success("消息发送成功");
    }

    @ApiOperation(value = "发送消息到主题")
    @GetMapping("/Topic/sendAndReceive")
    public AjaxResult TopicSendAndReceiveMessage() {
        // 发送消息
        for (int i = 0; i < 10; i++) {
            topicTestProducer.sendMessage("exampleTopic", i + " 发送的消息");
        }
        return AjaxResult.success("消息发送成功");
    }
}
