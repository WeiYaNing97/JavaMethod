package com.example.method.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/delete/{key}")
    public boolean delete(@PathVariable("key") String key) {
        return redisService.deleteObject(key);
    }
}
