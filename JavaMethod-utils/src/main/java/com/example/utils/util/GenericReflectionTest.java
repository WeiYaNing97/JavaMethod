package com.example.utils.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GenericReflectionTest {
    public static void main(String[] args) {
        Text<pojo> textpojo = new Text<>();
        textpojo.Text(new pojo("pojo"));

        Text<methodOne> textmethodOne = new Text<>();
        textmethodOne.Text(new methodOne());

        Text<methodTwo> textmethodTwo = new Text<>();
        textmethodTwo.Text(new methodTwo());
    }
}
class pojo{
    private String name;

    public pojo(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return "pojo{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Text<T>{

    private String type;
    public void Text(T t){
        if (t instanceof pojo){
            type = "pojo";
        }else if (t instanceof methodOne){
            type = "methodOne";
        }else if (t instanceof methodTwo){
            type = "methodTwo";
        }
        Method[] methods = t.getClass().getMethods();
        for (Method method : methods) {
            // 获取方法名
            String methodName = method.getName();
            if (methodName.equals("TextOne")){
                try {
                    method.invoke(t, "TextOne");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (type.equals("pojo")){
            pojo pojo = (pojo) t;
            System.out.println(pojo + "类");
        }else if (type.equals("methodOne")){
            methodOne methodOne = (methodOne) t;
            System.out.println(methodOne + "类");
        }else if (type.equals("methodTwo")){
            methodTwo methodTwo = (methodTwo) t;
            System.out.println(methodTwo + "类");
        }
    }
}

class methodOne{
    public void TextOne(String t) {
        System.err.println(t.toString() + "方法一");
    }
}
class methodTwo{
    public void TextTwo(String t) {
        System.out.println(t.toString() + "方法二");
    }
}