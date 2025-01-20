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

        List<String> stringList = new ArrayList<>();
        String str = "";
        try {
            File file = new File(url);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringList.add(line);
                str = str + line +"\r\n";
            }
            bufferedReader.close();
        } catch (IOException e) {
            if (e.getClass().getSimpleName().equals("FileNotFoundException")){
                System.err.println("系统找不到指定的文件");
            }else {
                e.printStackTrace();
            }
        } finally {
            String[] split = str.split("\r\n");
            for (int i = 0; i < split.length-1; i++){
                if (split[i].trim().equals(stringList.get(i).trim())){
                    System.out.println(i+" === "+split[i]+" === "+stringList.get(i));
                }else {
                    System.err.println("文件内容读取错误");
                    System.err.println(split[i]+" === "+stringList.get(i));
                }
            }
            System.out.println("文件读取成功");
            return stringList;
        }
    }
}
