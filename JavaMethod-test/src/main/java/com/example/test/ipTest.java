package com.example.test;

import com.example.util.MyUtils;

public class ipTest {
    public static void main(String[] args) {
        int i = MyUtils.compareIP("192.168.2.0", "192.168.0.1");
        System.out.println(i);
    }
}
