package com.example.test;

import java.math.BigInteger;

public class Test {
    public static void main(String[] args) {
        String binaryString = "111111110";
        int decimal = Integer.parseInt(binaryString, 2);
        System.out.println(decimal);  // 输出: 42

        // 使用BigInteger
        BigInteger decimal2 = new BigInteger(binaryString, 2);
        System.out.println(decimal2);  // 输出: 42
    }
}
