package com.example.method.controller;

import com.example.method.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisController")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/set/{key}/{value}")
    public String set(@PathVariable("key") String key, @PathVariable("value") String value) {

        Object object = redisService.getCacheObject(key);
        if (object == null){
            redisService.setCacheObject(key, value);
            return "存储成功";
        }else {
            return key+"值存在："+object.toString();
        }
    }

    @GetMapping("/get/{key}")
    public Object get(@PathVariable("key") String key) {
        return redisService.getCacheObject(key);
    }
}
