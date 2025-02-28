package com.example.test.Interviewquestions.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteTwo {
    public static void main(String[] args) {
        String url1 = "F:\\1.txt" ;
        List<String> information1 = Read.getInformation(url1);
        String url2 = "F:\\2.txt" ;
        List<String> information2 = Read.getInformation(url2);
        List<String> information = new ArrayList<>();
        for (int i = 0;
             i < (information1.size() > information2.size()?information1.size():information2.size());
             i++) {

            if (i < information1.size())
                information.add(information1.get(i));
            if (i < information2.size())
                information.add(information2.get(i));
        }
        write.setInformation(information,"F:\\3.txt");
    }
}

class Read {
    public static List<String> getInformation(String url) {
        List<String> list = new ArrayList<>();
        File file = new File(url);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine() )!= null) {
                list.add(line);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
class write{
    public static void setInformation(List<String> informationList,String url) {
        File file = new File(url);
        boolean mkdirs = file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String information : informationList) {
                bufferedWriter.write(information);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}