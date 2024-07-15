package com.example.test.ip;

import com.example.test.pojo.IPCalculator;
import com.example.test.pojo.IPInformation;
import com.example.test.pojo.IpComparator;
import com.example.test.returnInformation;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ipTest {
    public static void main(String[] args) {
        // 获取IP信息列表
        List<IPInformation> ipInformationList = IPAddressUtils.getIPInformation(returnInformation.RUIJIE);

        // 将IP信息列表转换为IP计算器列表
        List<IPCalculator> ipCalculatorList = ipInformationList.stream().map(ipInformation -> IPAddressCalculator.Calculator(ipInformation)).collect(Collectors.toList());

        // 对IP计算器列表进行排序
        IPAddressUtils.sortIPCalculator(ipCalculatorList);

        // TODO 临近分组
        List<List<IPCalculator>> lists = IPAddressUtils.groupIPCalculator(ipCalculatorList);

        for (List<IPCalculator> list : lists) {
            System.out.println("1============ 聚合块 开始===============");

            list.stream().forEach(x -> System.out.println(x.getIp() +"["+ x.getFirstAvailable() +","+ x.getFinallyAvailable()+"]" ));

            System.out.println("2============ 聚合块 为===============");

            List<List<IPCalculator>> aggregation = IPAddressUtils.getAggregation(list);

            for (List<IPCalculator> ipCalculators : aggregation) {
                System.out.println("\r");

                for (IPCalculator ipCalculator : ipCalculators) {
                    System.out.print(ipCalculator.getIp() +"["+ ipCalculator.getFirstAvailable() +","+ ipCalculator.getFinallyAvailable()+"]" );

                }
                System.out.println("\r");

            }

            System.out.println("3============ 聚合块 结束===============\r\n\r\n");
        }

    }


}
