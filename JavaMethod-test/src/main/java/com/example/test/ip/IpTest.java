package com.example.test.ip;

import com.example.test.pojo.IPBlock;
import com.example.util.MyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IpTest {

    static String returnString = "#\n" +
            " sysname RouterA\n" +
            "#\n" +
            "router id 1.1.1.1  //Router ID，建议配置为LoopBack0的IP地址\n" +
            "#\n" +
            "interface GigabitEthernet1/0/0\n" +
            " ip address 192.168.2.1 255.255.255.0\n" +
            "#\n" +
            "interface GigabitEthernet2/0/0\n" +
            " ip address 192.168.3.1 255.255.255.0\n" +
            "#\n" +
            "interface GigabitEthernet3/0/0\n" +
            " ip address 192.168.0.1 255.255.255.0\n" +
            "#\n" +
            "interface LoopBack0\n" +
            " ip address 1.1.1.1 255.255.255.255\n" +
            "#\n" +
            "ospf 2\n" +
            " area 0.0.0.0\n" +
            "  network 192.168.0.0 0.0.0.255  //指定运行OSPF 2协议的接口的IP地址位于192.168.0.0/24网段，接口所属的区域为area 0\n" +
            "  network 192.168.2.0 0.0.0.255\n" +
            "  network 192.168.3.0 0.0.0.255\n" +
            "#\n"+



            " sysname RouterB\n" +
            "#\n" +
            "router id 2.2.2.2\n" +
            "#\n" +
            "interface GigabitEthernet1/0/0\n" +
            " ip address 192.168.1.2 255.255.255.0\n" +
            "#\n" +
            "interface GigabitEthernet2/0/0\n" +
            " ip address 192.168.0.2 255.255.255.0\n" +
            "#\n" +
            "interface LoopBack0\n" +
            " ip address 2.2.2.2 255.255.255.255\n" +
            "#\n" +
            "ospf 2\n" +
            " area 0.0.0.0\n" +
            "  abr-summary 192.168.2.0 255.255.254.0  //在区域边界路由器（ABR）上配置路由聚合\n" +
            "  network 192.168.0.0 0.0.0.255\n" +
            " area 0.0.0.1\n" +
            "  network 192.168.1.0 0.0.0.255\n" +
            "#\n" +




            " sysname RouterC\n" +
            "#\n" +
            "router id 3.3.3.3\n" +
            "#\n" +
            "interface GigabitEthernet1/0/0\n" +
            " ip address 192.168.1.1 255.255.255.0\n" +
            "#\n" +
            "interface LoopBack0\n" +
            " ip address 3.3.3.3 255.255.255.255\n" +
            "#\n" +
            "ospf 2\n" +
            " area 0.0.0.1\n" +
            "  network 192.168.1.0 0.0.0.255\n" +
            "#";

    public static void main(String[] args) {

        List<String> collect = Arrays.stream( returnString.split("\n") ).collect( Collectors.toList() );
        List<List<String>> ipList = collect.stream().filter(s -> MyUtils.findIPs(s).size()==2).map(s -> MyUtils.findIPs(s)).collect(Collectors.toList());

        List<String> cidrList = new ArrayList<>();
        for (List<String> ips:ipList){
            String CIDR = MyUtils.convertToCIDR(ips.get(0), ips.get(1));
            cidrList.add(CIDR);
        }
        IPBlock ipBlock = IPAggregation.aggregateRoutes(cidrList);
        System.out.println(ipBlock.getCIDR());
    }



}
