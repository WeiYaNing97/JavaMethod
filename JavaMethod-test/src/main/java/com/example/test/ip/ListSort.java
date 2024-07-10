package com.example.test.ip;

import com.example.test.pojo.IPBlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  实体类聚合 根据某一项排序
*/
public class ListSort {

    public static void main(String[] args) {
        // 示例输入
        List<String> ipBlocks = new ArrayList<>();
        ipBlocks.add("58.33.56.82/25");
        ipBlocks.add("58.33.56.83/25");
        ipBlocks.add("58.35.56.82/23");
        ipBlocks.add("58.36.56.82/23");
        ipBlocks.add("58.62.60.82/23");
        ipBlocks.add("58.62.61.82/22");
        ipBlocks.add("58.62.62.82/22");
        ipBlocks.add("58.33.56.84/25");
        ipBlocks.add("58.33.57.82/24");
        ipBlocks.add("58.33.58.82/25");
        ipBlocks.add("58.33.59.82/24");
        ipBlocks.add("58.34.56.82/24");
        ipBlocks.add("58.62.63.82/22");
        ipBlocks.add("58.62.64.82/26");
        ipBlocks.add("58.62.65.82/26");
        /* 将*/
        List<IPBlock> ipBlockList = ipBlocks.stream().map(x -> new IPBlock(x)).collect(Collectors.toList());
        Collections.sort(ipBlockList, Comparator.comparing(IPBlock::getIp));
        ipBlockList.stream().forEach(x ->System.err.println(x.getIp()+"/"+x.getPrefix()));

        System.err.println("=========================");

        Collections.sort(ipBlockList, Comparator.comparingInt(IPBlock::getPrefix));
        ipBlockList.stream().forEach(x ->System.err.println(x.getIp()+"/"+x.getPrefix()));


    }

}
