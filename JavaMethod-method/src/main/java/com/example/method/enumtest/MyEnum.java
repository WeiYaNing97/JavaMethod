package com.example.method.enumtest;

/**
 * 枚举测试类
 */
public class MyEnum {
    public static void main(String[] args) {
        direction d = direction.left;
        switch (d){
            case Up:
                System.out.println("上");
                break;
            case down:
                System.out.println("下");
                break;
            case left:
                System.out.println("左");
                break;
            case right:
                System.out.println("右");
                break;
        }
    }
}
enum direction{
    Up, down, left, right
}
