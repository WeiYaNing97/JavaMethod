package com.example.test;

import com.example.util.MyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyTest {
    public static void main(String[] args) {
        List<String> ipList = new ArrayList<>();
        ipList.add("192.168.2.1 255.255.255.0");
        ipList.add("192.168.3.1 255.255.255.0");
        ipList.add("192.168.2.0 255.255.254.0");

        String[] keywords = {".", " "};
        List<String[]> stringsArrayList = new ArrayList<>();
        for (String ip:ipList){
            String[] strings = MyUtils.splittingStringsBasedOnMultipleKeywords(keywords, ip);
            stringsArrayList.add(strings);
        }
        for (String[] stringsArray:stringsArrayList){
            Arrays.stream(stringsArray).forEach(name -> System.out.print(name+" "));
            System.out.print("\r\n");
        }
    }
}
