package com.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringBufferTest {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append("Hello");
        sb.append("\r\n");
        sb.append("World");
        sb.append("\r\n");
        System.out.println(sb);
        String str = sb.toString();
        str= str.substring(0, str.length()-2);
        System.out.println(str);
        System.out.println(str.length());
    }

}
