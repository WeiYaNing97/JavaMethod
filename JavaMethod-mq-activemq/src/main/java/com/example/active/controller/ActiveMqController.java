package com.example.active.controller;

import com.example.active.producer.MessageProducer;
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


    @ApiOperation(value = "发送消息并接收")
    @GetMapping("/sendAndReceive")
    public AjaxResult testSendAndReceiveMessage(String destination, String message) {
        messageProducer.sendMessage(destination, message);
        return AjaxResult.success("消息发送成功");
    }
}
