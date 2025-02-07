package com.example.test;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1,张三,28");
        list.add("2,李四,35");
        list.add("3,张三,28");
        list.add("4,王五,35");
        list.add("5,张三,28");
        list.add("6,李四,35");
        list.add("7,赵六,28");
        list.add("8,田七,35");
        List<NameInformation> nameInformations = new ArrayList<>();
        for (String str:list) {
            NameInformation information = new NameInformation().getInformation(str);
            nameInformations.add(information);
        }
        Set<String> collect = nameInformations.stream().map(x -> x.getName()).collect(Collectors.toSet());
        for (String s:collect) {
            List<NameInformation> nameInformationList = new ArrayList<>();
            int num =0;
            for (int i=nameInformations.size()-1;i>=0;i--){
                if (nameInformations.get(i).getName().equals(s)){
                    nameInformationList.add(nameInformations.get(i));
                    num++;
                    nameInformations.remove(nameInformations.get(i));
                }
            }
            for (NameInformation pojo:nameInformationList){
                pojo.setNum(String.valueOf(num));
            }
            nameInformations.addAll(nameInformationList);
        }
        Collections.sort(nameInformations,(o1, o2) -> {
            return o2.getNum().compareTo(o1.getNum());
        });
        System.out.println(nameInformations);
    }
}
@Data
class NameInformation{
    String id;
    String name;
    String age;
    String num;
    public NameInformation getInformation(String str){
        String[] split = str.split(",");
        this.id = split[0];
        this.name = split[1];
        this.age = split[2];
        return this;
    }
}