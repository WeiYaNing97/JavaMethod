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
        List<List<String>> ipList = collect.stream().filter(s -> howManyIPFeatures(s).size()==2).map(s -> howManyIPFeatures(s)).collect(Collectors.toList());

        List<String> cidrList = new ArrayList<>();
        for (List<String> ips:ipList){
            String CIDR = MyUtils.convertToCIDR(ips.get(0), ips.get(1));
            cidrList.add(CIDR);
        }
        IPBlock ipBlock = IPAggregation.aggregateRoutes(cidrList);
        System.out.println(ipBlock.getCIDR());
    }


    /**
     * 统计字符串中包含的IP地址特征，并将它们存储在一个列表中返回。
     *
     * @param information 包含IP地址特征的字符串
     * @return 包含IP地址特征的列表
     */
    public static List<String> howManyIPFeatures(String information) {
        // 将输入的字符串按照空格进行分割，得到一个字符串数组
        String[] information_split = information.split(" ");
        // 创建一个空的ArrayList，用于存储IP地址特征
        List<String> ip_list = new ArrayList<>();
        // 遍历字符串数组中的每个字符串
        for (String ip:information_split) {
            // 调用MyUtils类的containsIPAddress方法，判断当前字符串是否为IP地址特征
            if (MyUtils.containsIPAddress(ip)) {
                // 如果当前字符串是IP地址特征，则将其添加到ip_list列表中
                ip_list.add(ip);
            }
        }
        // 返回包含IP地址特征的列表
        return ip_list;
    }
}
