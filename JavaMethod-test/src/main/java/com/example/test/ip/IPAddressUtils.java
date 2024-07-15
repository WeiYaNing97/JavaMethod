package com.example.test.ip;
import com.example.test.pojo.IPCalculator;
import com.example.test.pojo.IPInformation;
import com.example.test.pojo.IPInterval;
import com.example.test.pojo.IpComparator;
import com.example.util.MyUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class IPAddressUtils {


    public static List<IPInformation> getIPInformation(String returnInformation) {
        List<IPInformation> ipInformationList = new ArrayList<>();
        // 按照换行符将返回信息分割成数组
        String[] informationSplit = returnInformation.split("\n");

        for (String information:informationSplit){
            // 如果信息中包含"network"字符串
            // todo 获取地址关键字需要确定
            if (information.toLowerCase().indexOf("network".toLowerCase())!=-1){

                IPInformation ipInformation = new IPInformation();

                if (information.toLowerCase().indexOf("area".toLowerCase())!=-1){
                    // 获取地址关键字
                    String[] split = MyUtils.splitIgnoreCase(information,"area");
                    if (split.length == 2){
                        // 将地址关键字添加到结果列表中
                        List<String> iPs = MyUtils.findIPs(split[1]);
                        if (iPs.size() == 1){
                            ipInformation.setArea(iPs.get(0));
                        }
                    }
                    information = split[0];
                }

                // 查找信息中的IP地址
                // 判断字符串中有几个IP特征数据,并返回ip数据
                List<String> iPs = MyUtils.findIPs(information);

                if (iPs.size() == 2){
                    // 从最后一个IP地址开始遍历
                    for (int num = iPs.size()-1 ; num >= 0;num--){
                        // 将IP地址按"."分割成整数列表
                        List<Integer> collect = Arrays.stream(iPs.get(num).split("\\.")).map(Integer::parseInt).collect(Collectors.toList());
                        // 如果第一个整数为0，则将其替换为255减去原值  反子网掩码
                        if (collect.get(0) == 0){
                            collect.set(0,255-collect.get(0));
                            collect.set(1,255-collect.get(1));
                            collect.set(2,255-collect.get(2));
                            collect.set(3,255-collect.get(3));
                        }
                        // 将处理后的整数列表转换成IP地址格式，并替换原IP地址
                        iPs.set(num,collect.get(0)+"."+collect.get(1)+"."+collect.get(2)+"."+collect.get(3));
                    }

                    ipInformation.setIp(iPs.get(0));
                    ipInformation.setMask(iPs.get(1));
                }

                ipInformationList.add(ipInformation);
            }
        }
        return ipInformationList;
    }


    /*实体类根据IP排序*/
    public static List<IPCalculator> sortIPCalculator(List<IPCalculator> ipCalculatorList) {
        ipCalculatorList.sort(new IpComparator());
        return ipCalculatorList;
    }

    public static List<List<IPCalculator>> groupIPCalculator(List<IPCalculator> ipCalculatorList) {
        List<List<IPCalculator>> returnList = new ArrayList<>();

        while ( ipCalculatorList.size() > 0) {

            List<IPCalculator> ipCalculators = new ArrayList<>();

            ipCalculators.add(ipCalculatorList.get(0));

            for (int i = 1; i < ipCalculatorList.size(); i++) {
                boolean isBreak = false;
                if (ipCalculatorList.get(i-1).getIp().equals(ipCalculatorList.get(i).getIp())) {
                    ipCalculators.add(ipCalculatorList.get(i));
                    continue;
                }else if (isIPInRange(ipCalculatorList.get(i).getIp(), ipCalculatorList.get(i-1).getFirstAvailable(), ipCalculatorList.get(i-1).getFinallyAvailable())){
                    ipCalculators.add(ipCalculatorList.get(i));
                    continue;
                }


                String[] previousIpSplit = ipCalculatorList.get(i-1).getIp().split("\\.");
                String[] currentIpSplit = ipCalculatorList.get(i).getIp().split("\\.");
                for (int j = 0; j < 3; j++) {
                    /* 相等则比较下一元素*/
                    if (previousIpSplit[j].equals(currentIpSplit[j])){
                        continue;
                        /* 前一IP比下一IP 的对应元素小一*/
                    }else if (Integer.valueOf(previousIpSplit[j]).intValue() - Integer.valueOf(currentIpSplit[j]) == -1){
                        ipCalculators.add(ipCalculatorList.get(i));
                        break;
                    }else {
                        isBreak = true;
                        break;
                    }
                }

                if (isBreak){
                    break;
                }
            }

            // 使用Lambda表达式和Comparator进行排序
            ipCalculators.sort(Comparator.comparing(IPCalculator::getMask));

            returnList.add(ipCalculators);
            for (IPCalculator pojo:ipCalculators){
                ipCalculatorList.remove(pojo);
            }
        }

        return returnList;

    }

    public static boolean isIPInRange(String ipToCheck, String startIP, String endIP) {
        long start = ipToLong(startIP);
        long end = ipToLong(endIP);
        long check = ipToLong(ipToCheck);

        return check >= start && check <= end;
    }

    private static long ipToLong(String ipAddress) {
        try {
            byte[] bytes = InetAddress.getByName(ipAddress).getAddress();
            long result = 0;
            for (byte b : bytes) {
                result <<= 8;
                result |= (b & 0xFF);
            }
            return result;
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Invalid IP address", e);
        }
    }


    public static List<List<IPCalculator>> getAggregation(List<IPCalculator> ipCalculatorList) {
        List<List<IPCalculator>> returnList = new ArrayList<>();
        while (ipCalculatorList.size() > 0) {
            List<IPCalculator> ipCalculators = new ArrayList<>();
            ipCalculators.add(ipCalculatorList.get(0));

            IPInterval ipInterval = new IPInterval(ipCalculatorList.get(0).getFirstAvailable(), ipCalculatorList.get(0).getFinallyAvailable());
            for (int i = 1; i < ipCalculatorList.size(); i++) {
                IPCalculator ipCalculator = ipCalculatorList.get(i);
                IPInterval forIpInterval = new IPInterval(ipCalculator.getFirstAvailable(), ipCalculator.getFinallyAvailable());
                if (contain(ipInterval,forIpInterval)) {
                    ipCalculators.add(ipCalculator);
                }
            }
            returnList.add(ipCalculators);
            for (IPCalculator pojo:ipCalculators){
                ipCalculatorList.remove(pojo);
            }
        }
        return returnList;
    }


    public static boolean contain(IPInterval Interval1,IPInterval Interval2) {
        String ip1Start = Interval1.getIpStart();
        String ip1End = Interval1.getIpEnd();
        String ip2Start = Interval2.getIpStart();
        String ip2End = Interval2.getIpEnd();

        long start1 = ipToLong(ip1Start);
        long end1 = ipToLong(ip1End);
        long start2 = ipToLong(ip2Start);
        long end2 = ipToLong(ip2End);

        if (isContained(start2, end2, start1, end1)) {
            return true;
        }
        return false;
    }

    private static boolean isContained(long start1, long end1, long start2, long end2) {
        return start1 >= start2 && end1 <= end2;
    }

}
