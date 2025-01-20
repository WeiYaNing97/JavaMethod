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
            File file = new File(url);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs(); // 创建所有不存在的父目录
            }
            if (!file.exists()) {
                file.createNewFile(); // 创建文件
            }

            FileWriter writer = new FileWriter(file,true);

            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(value);
            bufferedWriter.newLine(); // 添加换行符
            bufferedWriter.close();
        } catch (IOException e) {
            if (e.getClass().getSimpleName().equals("FileNotFoundException")){
                System.err.println("系统找不到指定的文件");
            }else {
                e.printStackTrace();
            }
        }
    }

    public static void writeStringList(List<String> values, String url) {
        try {
            File file = new File(url);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs(); // 创建所有不存在的父目录
            }
            if (!file.exists()) {
                file.createNewFile(); // 创建文件
            }

            FileWriter writer = new FileWriter(file,true);

            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (String value:values){
                bufferedWriter.write(value);
                bufferedWriter.newLine(); // 添加换行符
            }
            bufferedWriter.close();

        } catch (IOException e) {
            if (e.getClass().getSimpleName().equals("FileNotFoundException")){
                System.err.println("系统找不到指定的文件");
            }else {
                e.printStackTrace();
            }
        }
    }

}
