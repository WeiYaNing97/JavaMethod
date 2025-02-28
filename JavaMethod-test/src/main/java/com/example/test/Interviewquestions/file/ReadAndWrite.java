package com.example.test.Interviewquestions.file;
import com.example.method.file.ReadFromFile;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class ReadAndWrite {
    public static void main(String[] args) throws Exception{


        FileManager a = new FileManager("F:\\1.txt",new char[]{'\n'});
        FileManager b = new FileManager("F:\\2.txt",new char[]{'\n',' '});
        List<String> stringList = ReadFromFile.readContent("F:\\1.txt");

        FileWriter c = new FileWriter("F:\\3.txt");
        String aWord = null;
        String bWord = null;
        while((aWord = a.nextWord()) !=null ){
            c.write(aWord + "\n");
            bWord = b.nextWord();
            if(bWord != null)
                c.write(bWord + "\n");
        }

        while((bWord = b.nextWord()) != null){
            c.write(bWord + "\n");
        }
        c.close();
    }
}
class FileManager{

    String[] words = null;
    int pos = 0;
    public FileManager(String filename, char[] seperators) throws Exception {
        // 创建一个File对象，指定文件名
        File f = new File(filename);
        // 创建一个FileReader对象，读取文件内容
        FileReader reader = new FileReader(f);
        // 创建一个字符数组，长度等于文件长度
        char[] buf = new char[(int) f.length()];
        // 读取文件内容到字符数组中
        int len = reader.read(buf);
        // 将字符数组转换为字符串
        String results = new String(buf, 0, len);
        // 正则表达式初始化为null
        String regex = null;
        // 如果分隔符数组长度大于1
        if (seperators.length > 1) {
            // 构建正则表达式，用|连接两个分隔符
            regex = "" + seperators[0] + "|" + seperators[1];
        } else {
            // 分隔符数组长度为1，直接使用该分隔符作为正则表达式
            regex = "" + seperators[0];
        }
        // 使用正则表达式将字符串按分隔符分割成单词数组
        words = results.split(regex);
    }


    public String nextWord(){
        if(pos == words.length)
            return null;
        return words[pos++];
    }

}
