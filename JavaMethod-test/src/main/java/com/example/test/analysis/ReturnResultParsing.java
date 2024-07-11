package com.example.test.analysis;

import cn.hutool.db.sql.SqlBuilder;
import com.example.test.ip.IPAddressTest;
import com.example.test.pojo.IPBlock;
import com.example.test.pojo.returnInformation;
import com.example.util.MyUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 解析 华为、思科、锐捷、H3C 设备返回结果
 */
public class ReturnResultParsing {


    public static void main(String[] args) {

        List<String> huawei = getIPCIDR(returnInformation.HUAWEI);
        List<String> sike = getIPCIDR(returnInformation.SIKE);
        List<String> ruijie = getIPCIDR(returnInformation.RUIJIE);
        List<String> h3c = getIPCIDR(returnInformation.H3C);

        List<String> all = new ArrayList<>();

        System.err.println("=================huawei======================");
        for (String information:huawei){
            System.err.println(information);

            String[] s = information.split(" ");
            String s1 = MyUtils.convertToCIDR(s[0], s[1]);
            System.err.println(s1);
            all.add(s1);
        }

        System.err.println("==================sike=====================");
        for (String information:sike){
            System.err.println(information);

            String[] s = information.split(" ");
            String s1 = MyUtils.convertToCIDR(s[0], s[1]);
            System.err.println(s1);
            all.add(s1);
        }

        System.err.println("===================ruijie====================");
        for (String information:ruijie){
            System.err.println(information);

            String[] s = information.split(" ");
            String s1 = MyUtils.convertToCIDR(s[0], s[1]);
            System.err.println(s1);
            all.add(s1);
        }

        System.err.println("===================h3c====================");
        for (String information:h3c){
            System.err.println(information);

            String[] s = information.split(" ");
            String s1 = MyUtils.convertToCIDR(s[0], s[1]);
            System.err.println(s1);
            all.add(s1);
        }


        for (int num=30; num >=8; num--){
            List<IPBlock> ipBlockList = all.stream().map(x -> new IPBlock(x)).collect(Collectors.toList());

            // 使用Collections.sort()方法对列表进行排序

            /*Comparator<IPBlock> ipComparator = Comparator.comparing(IPBlock::getIp);
            Collections.sort(ipBlockList, ipComparator);*/

            all = IPAddressTest.getAggregation(ipBlockList,num).stream().collect(Collectors.toList());

            System.err.println("===================/"+num+" 新集合长度 ： ======"+all.size()+"====================");
            all.forEach(x -> System.err.println(x));
        }
    }

    public static List<String> getIPCIDR(String returnInformation) {
        List<String> ipList = new ArrayList<>();
        String[] informationSplit = returnInformation.split("\n");
        for (String information:informationSplit){
            if (information.toLowerCase().indexOf("network".toLowerCase())!=-1){
                List<String> iPs = MyUtils.findIPs(information);
                if (iPs.size()>=2){
                    for (int num = iPs.size()-1 ; num >= 0;num--){
                        List<Integer> collect = Arrays.stream(iPs.get(num).split("\\.")).map(Integer::parseInt).collect(Collectors.toList());
                        if (iPs.size() > 2 && collect.stream().mapToInt(Integer::intValue).sum()==0){
                            iPs.remove(num);
                            continue;
                        }else if (collect.get(0) == 0){
                            collect.set(0,255-collect.get(0));
                            collect.set(1,255-collect.get(1));
                            collect.set(2,255-collect.get(2));
                            collect.set(3,255-collect.get(3));
                        }
                        iPs.set(num,collect.get(0)+"."+collect.get(1)+"."+collect.get(2)+"."+collect.get(3));
                    }

                    String join = String.join(" ", iPs);

                    ipList.add(join);
                }
            }
        }
        return ipList;
    }


}
