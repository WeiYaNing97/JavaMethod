package com.example.test.determineequality;

import java.util.ArrayList;

public class EqualTest {
    public static void main(String[] args) {
        int a = 10;
        long b = 10L;
        double c = 10.0;
        Integer d = 10;
        Integer e = 100;
        Integer f = 100;
        Integer g = 200;
        Integer h = 200;
        System.out.println(a == b);
        System.out.println(b == c);
        System.out.println(c == a);
        System.out.println(a == d);
        System.out.println(e == f);
        // 装箱后的Integer对象在-128到127之间时，会直接使用这个范围内的值作为对象的引用，而不是重新创建对象
        System.out.println(g == h);

        ArrayList list1 = new ArrayList();
        for (int i = 0; i < 100; i++) {
            list1.add(i);
        }
    }
}
