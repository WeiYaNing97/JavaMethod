package com.example.http;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HttpTest")
public class HttpTest {

    // SpingBoot获取cookie
    @RequestMapping("/getCookie2")/*发送请求，要有cookie2*/
    public String getCookie2(@CookieValue String userId, @CookieValue String goodId){
        return "cookie存取的值userId:" + userId + ",gooId:" + goodId;
    }

    @RequestMapping("/test")/*发送请求，要有cookie2*/
    public String test(){
        return "test";
    }
}
