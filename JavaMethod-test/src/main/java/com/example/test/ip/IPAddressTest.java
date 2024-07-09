package com.example.test.ip;

import com.example.test.pojo.IPAggregationOneMany;
import com.example.test.pojo.IPBlock;
import com.example.test.pojo.IPCalculator;
import com.example.test.pojo.Interval;
import com.example.util.MyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IPAddressTest {
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


        List<IPBlock> ipBlockList = ipBlocks.stream().map(x -> new IPBlock(x)).collect(Collectors.toList());

        for (int num=30; num >=8; num--){
            // 使用Collections.sort()方法对列表进行排序
            Collections.sort(ipBlockList, Comparator.comparing(IPBlock::getIp));

            System.err.println("===================== "+ num +"===========================");
            ipBlockList.stream().forEach(x -> System.err.println(x.toString()));

            ipBlockList = getAggregation(ipBlockList,num);
        }

    }

    public static List<IPBlock> getAggregation(List<IPBlock> ipBlocks,int maskNum) {
        List<IPBlock> returnList = new ArrayList<>();
        while (ipBlocks.size()>=1){
            IPCalculator calculator = IPAddressCalculator.Calculator(ipBlocks.get(0).getIp() + "/" + maskNum);

            String firstAvailable = calculator.getFirstAvailable();
            String finallyAvailable = calculator.getFinallyAvailable();

            List<IPBlock> temporaryPojoList = new ArrayList<>();
            for (int num = 0 ;num<ipBlocks.size(); num++){
                if (MyUtils.compareIP(firstAvailable,ipBlocks.get(num).getIp()) != 1 && MyUtils.compareIP(ipBlocks.get(num).getIp(),finallyAvailable) != 1 ){
                    IPBlock ipBlock = ipBlocks.get(num);
                    if (ipBlock.getPrefix() >= maskNum){
                        temporaryPojoList.add(new IPBlock(ipBlock.getIp()+"/"+maskNum));
                    }else {
                        temporaryPojoList.add(ipBlock);
                    }
                }else {
                    break;
                }
            }
            for (IPBlock temporaryPojo:temporaryPojoList){
                ipBlocks.remove(temporaryPojo);
            }
            returnList.addAll(temporaryPojoList);
        }

        return returnList;
    }
}
