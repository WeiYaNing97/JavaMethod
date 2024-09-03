package com.example.userLogin.controller;

import com.example.method.file.ReadFromFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/file")
public class MyfileText {
    @RequestMapping("/getText")
    public void getText() {
        String url = "E:\\ideaProject\\jhnw\\doc\\外部路由聚合\\vpn路由HW.txt";
        List<String> stringList = ReadFromFile.readContent(url);
    }

    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        while (true) {
            a = a+b;
            System.out.println( a.length());
        }
    }
}
