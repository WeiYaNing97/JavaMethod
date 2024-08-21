package com.example.method.http;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpTest {

    // SpingBoot获取cookie
    @RequestMapping("/getCookie2")
    public String getCookie2(@CookieValue String userId, @CookieValue String goodId){
        return "cookie存取的值userId:" + userId + ",gooId:" + goodId;
        // 出现错误
        /*
        Whitelabel Error Page
        This application has no explicit mapping for /error, so you are seeing this as a fallback.

        Wed Aug 21 20:27:28 CST 2024
        There was an unexpected error (type=Bad Request, status=400).
        */
    }
}
