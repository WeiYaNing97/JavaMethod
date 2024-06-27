package com.example.method.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {

    public static List<String> readContent(String url) {

        List<String> stringList = new ArrayList<>();
        try {
            File file = new File(url);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringList.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            if (e.getClass().getSimpleName().equals("FileNotFoundException")){
                System.err.println("系统找不到指定的文件");
            }else {
                e.printStackTrace();
            }
        } finally {
            return stringList;
        }
    }
}
