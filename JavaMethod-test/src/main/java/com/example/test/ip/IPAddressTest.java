package com.example.test.ip;

import com.example.test.pojo.IPAggregationOneMany;
import com.example.test.pojo.IPBlock;
import com.example.test.pojo.IPCalculator;
import com.example.test.pojo.Interval;
import com.example.util.MyUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 最终版 测试
 */
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


        for (int num=30; num >=8; num--){

            List<IPBlock> ipBlockList = ipBlocks.stream().map(x -> new IPBlock(x)).collect(Collectors.toList());

            // 使用Collections.sort()方法对列表进行排序
            Collections.sort(ipBlockList, Comparator.comparing(IPBlock::getIp));

            System.err.println("===================== "+ num +"===========================");
            ipBlockList.stream().forEach(x -> System.err.println(x.getIp() + "  " + x.getPrefix()));

            ipBlocks = getAggregation(ipBlockList,num).stream().collect(Collectors.toList());
        }

        for (String pojo:ipBlocks){
            System.err.println(pojo);
        }
    }

    /**
     * 根据给定的IP块列表和掩码位数，对IP块进行聚合。
     *
     * @param ipBlocks 给定的IP块列表
     * @param maskNum 掩码位数
     * @return 聚合后的IP块列表
     */
    public static Set<String> getAggregation(List<IPBlock> ipBlocks,int maskNum) {

        Set<String> returnList = new HashSet<>();

        while (ipBlocks.size()>=1){
            // 创建一个IP计算器对象，用于计算IP地址的起始和结束地址
            IPCalculator calculator = IPAddressCalculator.Calculator(ipBlocks.get(0).getIp() + "/" + maskNum);

            // 获取起始IP地址
            String firstAvailable = calculator.getFirstAvailable();
            // 获取结束IP地址
            String finallyAvailable = calculator.getFinallyAvailable();

            for (int num = 0 ;num<ipBlocks.size(); num++){

                IPCalculator iPBlockCalculator = IPAddressCalculator.Calculator(ipBlocks.get(num).getIp() + "/" + maskNum);/*ipBlocks.get(num).getPrefix()*/

                // [firstAvailable,finallyAvailable] 要在 [iPBlockCalculator.getFirstAvailable(),iPBlockCalculator.getFinallyAvailable()] 内
                /* [firstAvailable,finallyAvailable]  要在 [iPBlockCalculator.getFirstAvailable(),iPBlockCalculator.getFinallyAvailable()] 内*/
                /* 所以 firstAvailable <= iPBlockCalculator.getFirstAvailable()  &&   finallyAvailable >= iPBlockCalculator.getFinallyAvailable()
                * 当 firstAvailable > iPBlockCalculator.getFirstAvailable() 时，==1 所以  firstAvailable <= iPBlockCalculator.getFirstAvailable() 是 !=1
                * 当 finallyAvailable < iPBlockCalculator.getFinallyAvailable() 时， ==-1 所以 finallyAvailable >= iPBlockCalculator.getFinallyAvailable() !=-1 */
                if (MyUtils.compareIP(firstAvailable,iPBlockCalculator.getFirstAvailable()) != 1 && MyUtils.compareIP(finallyAvailable,iPBlockCalculator.getFinallyAvailable()) != -1 ){

                    if (ipBlocks.get(num).getPrefix() < maskNum){
                        // 如果当前IP块的掩码位数小于给定的掩码位数，则使用给定的掩码位数进行聚合
                        returnList.add(calculator.getNetworkNumber() + "/" + ipBlocks.get(num).getPrefix());
                    }else {
                        // 如果当前IP块的掩码位数大于等于给定的掩码位数，则使用给定的掩码位数进行聚合
                        returnList.add(calculator.getNetworkNumber() + "/" + maskNum);
                    }

                    IPBlock ipBlock = ipBlocks.get(num);
                    // 从IP块列表中移除已处理的IP块
                    ipBlocks.removeIf(person -> person.getIp().equals(ipBlock.getIp()));
                }else {
                    // 如果当前IP块地址在起始和结束IP地址之外，则跳出循环
                    break;
                }
            }

        }
        return returnList;
    }


}
