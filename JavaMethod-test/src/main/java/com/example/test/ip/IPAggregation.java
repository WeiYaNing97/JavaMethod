package com.example.test.ip;
import com.example.test.pojo.IPBlock;
import com.example.util.MyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 第一版
 * 根据给出的地址列表，得出 一个路由聚合结果
 */
public class IPAggregation {

    public static void main(String[] args) {
        // 示例输入
        List<String> ipBlocks = new ArrayList<>();
        ipBlocks.add("212.56.1.0/24");
        ipBlocks.add("212.56.2.0/24");
        ipBlocks.add("212.56.3.0/24");
        ipBlocks.add("212.56.4.0/23");
        ipBlocks.add("212.56.5.0/24");
        ipBlocks.add("212.56.6.0/24");
        ipBlocks.add("212.56.7.0/24");
        ipBlocks.add("212.56.8.0/24");
        ipBlocks.add("212.56.9.0/24");
        ipBlocks.add("212.56.10.0/24");

        IPBlock ipBlock = aggregateRoutes(ipBlocks);
        System.out.println(ipBlock.getCIDR());
    }

    /**
     * 将IP地址块数组进行聚合，返回具有最大相同前缀的IP块
     *
     * @param ipBlocks IP地址块数组，格式为"xxx.xxx.xxx.xxx/xx"的字符串数组
     * @return 聚合后的IPBlock对象，包含具有最大相同前缀的IP地址和前缀长度
     */
    public static IPBlock aggregateRoutes(List<String> ipBlocks) {

        if (ipBlocks.size()==1){
            return new IPBlock(ipBlocks.get(0));
        }

        // 转换输入为IPBlock对象数组，便于处理
        IPBlock[] blocks = new IPBlock[ipBlocks.size()];
        for (int i = 0; i < ipBlocks.size(); i++) {
            // 将每个IP地址块转换为IPBlock对象
            blocks[i] = new IPBlock(ipBlocks.get(i));
            // 将IP地址转换为二进制系统
            blocks[i].setIp(MyUtils.stringInsertionInterval(MyUtils.getIPBinarySystem(blocks[i].getIp()),".",8));
        }

        // 打印转换后的二进制IP地址
        Arrays.stream(blocks).map(x -> x.getIp()).forEach(System.out::println);

        // 收集转换后的二进制IP地址到列表中
        List<String> collect = Arrays.stream(blocks).map(x -> x.getIp()).collect(Collectors.toList());

        // 找出具有最大相同前缀的IP地址
        String maximumSamePrefix = maximumSamePrefix(collect);

        System.out.println(maximumSamePrefix);

        // 打印具有最大相同前缀的IP地址
        int prefix = maximumSamePrefix.replace(".","").length();

        IPBlock ipBlock = new IPBlock();
        ipBlock.setIp(convertBinaryToIP(maximumSamePrefix));
        ipBlock.setPrefix(prefix);

        return ipBlock;
    }


    /**
     * 将二进制字符串转换为IP地址字符串
     *
     * @param maximumSamePrefix 长度小于或等于32位的二进制字符串，表示具有最大相同前缀的二进制IP地址
     * @return 转换后的IP地址字符串
     * @throws NumberFormatException 如果二进制字符串无法转换为整数，则抛出该异常
     */
    public static String convertBinaryToIP(String maximumSamePrefix) {
        // 按照点号分割二进制字符串
        String[] split = maximumSamePrefix.split("\\.");

        // 将分割后的二进制字符串转换为整数，并放入列表中
        List<Integer> ipList = Arrays.stream(split).map(x -> Integer.parseInt(padWithZeros(x), 2)).collect(Collectors.toList());

        // 创建一个新的列表ips，用于存储IP地址的每个部分
        List<Integer> ips = new ArrayList<>();

        // 遍历ipList列表，将其中的元素添加到ips列表中
        // 如果ipList列表的长度小于4，则在ips列表中添加0
        for (int i = 0; i < 4; i++) {
            if (i<ipList.size()) {
                ips.add(ipList.get(i));
            } else {
                ips.add(0);
            }
        }

        // 初始化IP地址字符串
        String ip = "";

        // 遍历ips列表，将每个元素转换为字符串，并添加点号
        for (int i = 0; i < ips.size(); i++) {
            ip += ips.get(i)+".";
        }

        // 去掉最后一个点号，返回最终的IP地址字符串
        return ip.substring(0,ip.length()-1);
    }





    /**
     * 返回给定IP地址列表中具有最大相同前缀的IP地址。
     *
     * @param ips 包含IP地址的字符串列表
     * @return 具有最大相同前缀的IP地址
     */
    public static String maximumSamePrefix(List<String> ips) {
        if (ips.size() == 1) {
            // 如果IP地址列表只有一个元素，直接返回该元素
            return ips.get(0);
        }

        // 对IP地址列表进行排序
        Collections.sort(ips);

        // 获取列表中的第一个和最后一个IP地址
        String first = ips.get(0);
        String last = ips.get(ips.size() - 1);
        int i = 0;

        // 遍历字符串，找到第一个不同的字符
        while (i < first.length() && i < last.length() && first.charAt(i) == last.charAt(i)) {
            i++;
        }

        // 返回第一个IP地址中从开头到第一个不同字符之前的子串
        return first.substring(0, i);
    }

    /**
     * 在输入字符串的左侧用零填充至8位长度
     *
     * @param input 输入字符串
     * @return 填充后的字符串，如果输入字符串长度已经大于等于8位，则返回原字符串
     */
    public static String padWithZeros(String input) {
        int length = input.length();
        // 如果输入字符串长度小于8位
        if (length < 8) {
            // 创建一个StringBuilder对象，并将输入字符串放入其中
            StringBuilder sb = new StringBuilder(input);
            // 循环8-输入字符串长度的次数
            for (int i = 0; i < 8 - length; i++) {
                // 在StringBuilder对象后追加"0"
                sb.append("0");
            }
            // 将StringBuilder对象转换为字符串并返回
            return sb.toString();
        // 如果输入字符串长度已经大于等于8位，则直接返回原字符串
        } else {
            return input;
        }
    }

}
