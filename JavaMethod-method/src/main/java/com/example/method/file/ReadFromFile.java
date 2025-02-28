package com.example.method.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取文件内容，并以字符串列表的形式返回
 */
public class ReadFromFile {

    public static List<String> readContent(String url) {

        // 初始化一个字符串列表用于存储读取的内容
        List<String> stringList = new ArrayList<>();
        // 初始化一个空字符串用于拼接文件内容
        String str = "";
        try {
            // 根据给定的URL创建一个文件对象
            File file = new File(url);
            // 创建一个FileReader对象用于读取文件内容
            FileReader reader = new FileReader(file);
            // 创建一个BufferedReader对象，用于缓冲读取文件内容
            BufferedReader bufferedReader = new BufferedReader(reader);
            // 读取文件内容的每一行
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // 将读取到的每一行添加到字符串列表中
                stringList.add(line);
                // 将读取到的每一行拼接到字符串str中，并用"\r\n"作为换行符
                str = str + line + "\r\n";
            }
            // 关闭BufferedReader对象
            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            // 捕获IOException异常
            if (e.getClass().getSimpleName().equals("FileNotFoundException")){
                // 如果异常类型是FileNotFoundException，则输出错误信息
                System.err.println("系统找不到指定的文件");
            } else {
                // 如果异常类型不是FileNotFoundException，则打印异常堆栈信息
                e.printStackTrace();
            }
        } finally {
            // 在finally块中执行，无论是否发生异常都会执行
            // 将字符串str按"\r\n"进行分割
            String[] split = str.split("\r\n");
            // 遍历分割后的字符串数组
            for (int i = 0; i < split.length - 1; i++) {
                // 如果分割后的字符串与字符串列表中对应位置的字符串（去除首尾空格后）相等
                if (split[i].trim().equals(stringList.get(i).trim())) {
                    // 输出索引、分割后的字符串和字符串列表中的字符串
                    System.out.println(i + " === " + split[i] + " === " + stringList.get(i));
                } else {
                    // 如果不相等，则输出错误信息和分割后的字符串与字符串列表中的字符串
                    System.err.println("文件内容读取错误");
                    System.err.println(split[i] + " === " + stringList.get(i));
                }
            }
            // 输出文件读取成功的消息
            System.out.println("文件读取成功");
            // 返回字符串列表
            return stringList;
        }
    }

}
