package com.example.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list.add("192.168.2.101/24");
        list.add("192.168.3.102/24");
        list.add("192.168.1.106/24");
        list.add("192.168.1.103/24");
        list.add("192.168.5.104/24");
        list.add("192.168.6.105/24");

        list2.add("192.168.2.101/24");
        list2.add("192.168.6.105/24");
        list2.add("192.168.1.106/24");
        list2.add("192.168.3.102/24");
        list2.add("192.168.1.103/24");
        list2.add("192.168.5.104/24");

        List<Long> collect = list.stream().map(ip -> ipToLong(findIPs(ip).get(0))).collect(Collectors.toList());
        List<Long> collect1 = list2.stream().map(ip -> ipToLong(findIPs(ip).get(0))).collect(Collectors.toList());
        // 使用Stream API进行排序
        collect = collect.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        // 使用Stream API进行排序
        collect1 = collect1.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        if (collect.equals(collect1)) {
            System.out.println("两个列表相等");
        }else if(areAllElementsEqualAtSameIndex(list, list2)){
            System.out.println("两个列表不相等，但是相同位置的元素都相等");
        } else {
            System.out.println("两个列表不相等");
        }
    }

    /**
     * 将IP地址字符串转换为长整型
     *
     * @param ipAddress IP地址字符串
     * @return 转换后的长整型值
     * @throws IllegalArgumentException 如果输入的IP地址字符串无效
     */
    private static long ipToLong(String ipAddress) {
        try {
            // 通过InetAddress类的getByName方法将IP地址字符串转换为InetAddress对象，并获取其字节数组
            byte[] bytes = InetAddress.getByName(ipAddress).getAddress();
            long result = 0;
            // 遍历字节数组，将每个字节转换为长整型并拼接起来
            for (byte b : bytes) {
                // 将result左移8位，相当于将result乘以2的8次方
                result <<= 8;
                // 将当前字节b与0xFF进行位与运算，确保只保留低8位，然后与result进行位或运算，将结果保存到result中
                result |= (b & 0xFF);
            }
            // 返回转换后的长整型值
            return result;
        } catch (UnknownHostException e) {
            // 如果输入的IP地址字符串无效，则抛出IllegalArgumentException异常，并携带UnknownHostException异常作为原因
            throw new IllegalArgumentException("Invalid IP address", e);
        }
    }

    /**
     * 将长整型表示的IP地址转换为点分十进制格式的字符串
     *
     * @param ipAsLong 长整型表示的IP地址
     * @return 点分十进制格式的IP地址字符串
     * @throws RuntimeException 如果IP地址转换过程中发生错误
     */
    public static String longToIp(long ipAsLong) {
        // 创建一个长度为4的字节数组用于存储IP地址的各个部分
        byte[] bytes = new byte[4];
        // 遍历字节数组的每一个位置
        for (int i = 0; i < 4; ++i) {
            // 计算当前位置的偏移量
            int offset = (3 - i) * 8;
            // 将长整型表示的IP地址右移对应偏移量，然后与0xFF进行按位与运算，将结果存储在字节数组的当前位置
            bytes[i] = (byte) ((ipAsLong >> offset) & 0xFF);
        }

        try {
            // 将字节数组转换为InetAddress对象，并获取其主机地址（点分十进制格式的IP地址字符串）
            // return InetAddress.getByAddress(bytes).getHostAddress();
            return InetAddress.getByAddress(bytes).getHostAddress();
        } catch (UnknownHostException e) {
            // 如果转换过程中发生异常，则抛出RuntimeException
            throw new RuntimeException(e);
        }
    }

    public static boolean areAllElementsEqualAtSameIndex(List<?>... lists) {
        if (lists == null || lists.length == 0) return true;

        // Check if all lists have the same size.
        int size = -1;
        for (List<?> list : lists) {
            if (size == -1) {
                size = list.size();
            } else if (list.size() != size) {
                return false; // Lists of different sizes.
            }
        }

        // Compare elements at each index.
        for (int i = 0; i < size; i++) {
            Object firstElement = null;
            for (List<?> list : lists) {
                Object currentElement = list.get(i);
                if (firstElement == null) {
                    firstElement = currentElement;
                } else if (!Objects.equals(firstElement, currentElement)) {
                    return false; // Elements at index i are not equal.
                }
            }
        }

        return true; // All elements at the same indexes are equal.
    }

    /**
     *  判断字符串中有几个IP特征数据,并返回ip数据
     *  注意：超过255 不是ip 不会显示。测试时注意
     * @param str
     * @return
     */
    public static List<String> findIPs(String str) {
        List<String> ips = new ArrayList<>();
        //String regex = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        Pattern pattern = Pattern.compile("(\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b)");
        //Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            ips.add(matcher.group());
        }

        return ips;
    }
}
