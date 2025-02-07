package com.example.method.file;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 写入文件内容，
 */
public class WriteToFile {

    public static void writeString(String value, String url) {
        try {
            // 创建一个File对象，指定文件路径
            File file = new File(url);
            // 获取文件的父目录
            File parentDir = file.getParentFile();
            // 如果父目录不存在
            if (!parentDir.exists()) {
                // 创建所有不存在的父目录
                parentDir.mkdirs(); // 创建所有不存在的父目录
            }
            // 如果文件不存在
            if (!file.exists()) {
                // 创建文件
                file.createNewFile(); // 创建文件
            }

            // 创建一个FileWriter对象，指定文件路径，并设置追加模式为true
            FileWriter writer = new FileWriter(file, true);

            // 创建一个BufferedWriter对象，包装FileWriter对象
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            // 将要写入的内容写入到文件中
            bufferedWriter.write(value);
            // 添加换行符
            bufferedWriter.newLine(); // 添加换行符
            // 关闭BufferedWriter对象
            bufferedWriter.close();
        } catch (IOException e) {
            // 捕获IOException异常
            if (e.getClass().getSimpleName().equals("FileNotFoundException")){
                // 如果异常类型是FileNotFoundException，则输出错误信息
                System.err.println("系统找不到指定的文件");
            } else {
                // 如果异常类型不是FileNotFoundException，则打印异常堆栈信息
                e.printStackTrace();
            }
        }
    }


    public static void writeStringList(List<String> values, String url) {
        try {
            // 创建一个File对象，指定文件路径
            File file = new File(url);

            // 获取文件的父目录
            File parentDir = file.getParentFile();
            // 如果父目录不存在
            if (!parentDir.exists()) {
                // 创建所有不存在的父目录
                parentDir.mkdirs(); // 创建所有不存在的父目录
            }
            // 如果文件不存在
            if (!file.exists()) {
                // 创建文件
                file.createNewFile(); // 创建文件
            }

            // 创建一个FileWriter对象，指定文件路径，并设置追加模式为true
            FileWriter writer = new FileWriter(file, true);

            // 创建一个BufferedWriter对象，包装FileWriter对象
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            // 遍历字符串列表，将每个字符串写入文件
            for (String value : values) {
                // 将字符串写入文件
                bufferedWriter.write(value);
                // 添加换行符
                bufferedWriter.newLine(); // 添加换行符
            }
            // 关闭BufferedWriter对象
            bufferedWriter.close();

        } catch (IOException e) {
            // 捕获IOException异常
            if (e.getClass().getSimpleName().equals("FileNotFoundException")) {
                // 如果异常类型是FileNotFoundException，则输出错误信息
                System.err.println("系统找不到指定的文件");
            } else {
                // 如果异常类型不是FileNotFoundException，则打印异常堆栈信息
                e.printStackTrace();
            }
        }
    }


}
