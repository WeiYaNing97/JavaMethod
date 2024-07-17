package com.example.test.ip;

import com.example.test.pojo.IPAddresses;
import com.example.test.pojo.IPCalculator;
import com.example.test.pojo.IPInformation;
import com.example.test.pojo.IpComparator;
import com.example.test.returnInformation;
import com.example.util.MyUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ipTest {
    public static void main(String[] args) {
        // 获取IP信息列表
        List<IPInformation> ipInformationList = IPAddressUtils.getIPInformation(returnInformation.H3C);
        List<String> collect = ipInformationList.stream().map(ipInformation -> MyUtils.convertToCIDR(ipInformation.getIp(), ipInformation.getMask())).collect(Collectors.toList());


        // 将IP信息列表转换为IP计算器列表
        List<IPCalculator> ipCalculatorList = collect.stream().map(ipCIDR -> IPAddressCalculator.Calculator(ipCIDR)).collect(Collectors.toList());

        // 对IP计算器列表进行排序
        IPAddressUtils.sortIPCalculator(ipCalculatorList);

        List<IPAddresses> ipAddresses = IPAddressUtils.splicingAddressRange(ipCalculatorList);

        for (IPAddresses ipAddress : ipAddresses) {
            System.err.println("原始IP:");
            ipAddress.getIpCalculatorList().stream().forEach(x -> System.err.println(x.getIp() + "/" + x.getMask() + "[" + x.getFirstAvailable() + " - " + x.getFinallyAvailable() + "]"));
            System.err.println("聚合为:");
            List<String> stringList = IPAddressUtils.addressSegmentDecomposition(ipAddress);
            stringList.stream().forEach(System.err::println);
            System.err.println("==============================================================");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
