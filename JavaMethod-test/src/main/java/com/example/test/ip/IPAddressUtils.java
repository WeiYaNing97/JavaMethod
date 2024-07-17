package com.example.test.ip;
import com.example.test.pojo.*;
import com.example.util.MyUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.stream.Collectors;
public class IPAddressUtils {


    /**
     * 从给定的返回信息中提取IP信息，并返回IP信息列表
     *
     * @param returnInformation 包含IP信息的字符串
     * @return 包含提取出的IP信息的列表
     */
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
    /**
     * 根据IP地址对IPCalculator实体类列表进行排序
     *
     * @param ipCalculatorList IPCalculator实体类列表
     * @return 排序后的IPCalculator实体类列表
     */
    public static List<IPCalculator> sortIPCalculator(List<IPCalculator> ipCalculatorList) {
        // 使用IpComparator对ipCalculatorList进行排序
        ipCalculatorList.sort(new IpComparator());
        // 返回排序后的ipCalculatorList
        return ipCalculatorList;
    }

    /**
     * 判断一个IP地址是否在指定的IP地址范围内
     *
     * @param ipToCheck 要检查的IP地址
     * @param startIP 起始IP地址
     * @param endIP 结束IP地址
     * @return 如果要检查的IP地址在指定的范围内，则返回true；否则返回false
     */
    public static boolean isIPInRange(String ipToCheck, String startIP, String endIP) {
        // 将起始IP地址转换为长整型
        long start = ipToLong(startIP);
        // 将结束IP地址转换为长整型
        long end = ipToLong(endIP);
        // 将要检查的IP地址转换为长整型
        long check = ipToLong(ipToCheck);

        // 判断要检查的IP地址是否在指定范围内
        return check >= start && check <= end;
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
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; ++i) {
            int offset = (3 - i) * 8;
            bytes[i] = (byte) ((ipAsLong >> offset) & 0xFF);
        }
        try {
            return InetAddress.getByAddress(bytes).getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<IPAddresses> splicingAddressRange(List<IPCalculator> ipCalculatorList) {
        List<IPAddresses> ipAddressesList = new ArrayList<>();
        for (IPCalculator ipCalculator : ipCalculatorList) {
            IPAddresses ipAddresses = new IPAddresses();
            ipAddresses.setIpStart(ipCalculator.getFirstAvailable());
            ipAddresses.setIpEnd(ipCalculator.getFinallyAvailable());
            List<IPCalculator> ipCalculators = new ArrayList<>();
            ipCalculators.add(ipCalculator);
            ipAddresses.setIpCalculatorList(ipCalculators);
            ipAddressesList.add(ipAddresses);
        }
        boolean flag = true;
        while (flag){
            flag = false;
            for (int i = ipAddressesList.size()-1; i >= 1; i--){
                if (isIPInRange(ipAddressesList.get(i-1).getIpStart(),
                        ipAddressesList.get(i).getIpStart(), ipAddressesList.get(i).getIpEnd())
                || isIPInRange(ipAddressesList.get(i).getIpStart(),
                        ipAddressesList.get(i-1).getIpStart(), ipAddressesList.get(i-1).getIpEnd())
                || determineIPContinuity(ipAddressesList.get(i-1).getIpEnd(), ipAddressesList.get(i).getIpStart())
                || determineIPContinuity(ipAddressesList.get(i).getIpEnd(), ipAddressesList.get(i-1).getIpStart())) {
                    flag = true;

                    List<String> ipAddresses = new ArrayList<>();
                    ipAddresses.add(ipAddressesList.get(i).getIpStart());
                    ipAddresses.add(ipAddressesList.get(i).getIpEnd());
                    ipAddresses.add(ipAddressesList.get(i-1).getIpStart());
                    ipAddresses.add(ipAddressesList.get(i-1).getIpEnd());

                    String minIp = getMinIP(ipAddresses);
                    String maxIp = getMaxIP(ipAddresses);

                    IPAddresses pojo = new IPAddresses();
                    pojo.setIpStart(minIp);
                    pojo.setIpEnd(maxIp);
                    List<IPCalculator> ipCalculators = new ArrayList<>();
                    ipCalculators.addAll(ipAddressesList.get(i).getIpCalculatorList());
                    ipCalculators.addAll(ipAddressesList.get(i-1).getIpCalculatorList());
                    pojo.setIpCalculatorList(ipCalculators);

                    ipAddressesList.remove(ipAddressesList.get(i));
                    ipAddressesList.remove(ipAddressesList.get(i-1));
                    ipAddressesList.add(pojo);
                }
            }
        }

        return ipAddressesList;
    }

    public static boolean determineIPContinuity(String ip1, String ip2) {
        long l1 = ipToLong(ip1);
        long l2 = ipToLong(ip2);
        return Math.abs(l1 - l2) == 1l;
    }

    /**
     * 从给定的IP地址列表中获取最大的IP地址。
     *
     * @param ipAddresses 包含IP地址的字符串列表
     * @return 列表中的最大IP地址，如果不存在则返回null
     * @throws NullPointerException 如果传入的ipAddresses参数为null
     * @throws ClassCastException   如果ipAddresses列表中的某个元素无法转换为长整型表示的IP地址
     */
    public static String getMaxIP(List<String> ipAddresses) {
        return Collections.max(ipAddresses, Comparator.comparing(IPAddressUtils::toLong));
    }

    /**
     * 从给定的IP地址列表中获取最小的IP地址。
     *
     * @param ipAddresses 包含IP地址的字符串列表
     * @return 列表中最小的IP地址，如果不存在则返回null
     */
    public static String getMinIP(List<String> ipAddresses) {
        return Collections.min(ipAddresses, Comparator.comparing(IPAddressUtils::toLong));
    }

    public static long toLong(String ipAddress) {
        String[] parts = ipAddress.split("\\.");
        long result = 0;
        for (int i = 0; i < parts.length; i++) {
            result |= Long.parseLong(parts[i]) << (24 - (8 * i));
        }
        return result;
    }

    /**
     * todo 待检测
     *
     * 根据IP地址的整数表示形式获取地址子网掩码位数
     *
     * @param num IP地址的整数表示形式
     * @return 返回子网掩码的位数
     * @throws IllegalArgumentException 如果传入的参数不是有效的IP地址整数表示形式（即小于0或大于255.255.255.255对应的整数）
     *
     * 注意：此方法假设IP地址的整数表示形式为无符号整数，并且其值在0到2^32-1之间（包含）。
     * 若输入的整数num为全1的二进制字符串，则视为一个特殊的IP地址（如广播地址），此时子网掩码位数为32减去num的二进制长度；
     * 否则，子网掩码位数为32减去num的二进制长度再加1。
     */
    public static Integer getTheNumberOfMasks(int num) {
        // 将整数num转换为二进制字符串
        String binaryString = Integer.toBinaryString(num);

        return 32 - binaryString.length() + 1;
    }


    /**
     * 计算两个IP地址之间的地址数量（包括起始和结束IP地址）
     *
     * @param ip1 起始IP地址，格式为点分十进制字符串
     * @param ip2 结束IP地址，格式为点分十进制字符串
     * @return 返回起始和结束IP地址之间的地址数量（包括起始和结束IP地址），类型为Integer
     * @throws NumberFormatException 如果起始或结束IP地址的格式不正确
     */
    public static Integer numberOfAddresses(String ip1, String ip2) {
        long l1 = ipToLong(ip1);
        long l2 = ipToLong(ip2);
        return (int) Math.abs(l2 - l1 + 1);
    }

    public static List<String> addressSegmentDecomposition(IPAddresses ipAddress) {
        List<String> ipList = new ArrayList<>();
        String sign = ipAddress.getIpStart();
        String ipEnd = ipAddress.getIpEnd();
        while (ipToLong(sign) <= ipToLong(ipEnd)){

            if (ipToLong(sign) == ipToLong(ipEnd)){
                ipList.add(sign + "/32");

                IPCalculator calculator = IPAddressCalculator.Calculator(sign + "/32");
                System.out.println(sign + "/32"+"范围是: ["+calculator.getFirstAvailable()+","+calculator.getFinallyAvailable()+"]");

                break;
            }

            Integer ipNumber = numberOfAddresses(sign, ipEnd);
            String ip = sign + "/" + getTheNumberOfMasks(ipNumber);
            IPCalculator calculator = IPAddressCalculator.Calculator(ip);

            System.out.println(ip+"范围是: ["+calculator.getFirstAvailable()+","+calculator.getFinallyAvailable()+"]");

            ipList.add(ip);

            /*String finallyAvailable = calculator.getFinallyAvailable();*/
            sign = longToIp(ipToLong(calculator.getFinallyAvailable()) + 1);
        }
        return ipList;
    }


    public static void main1(String[] args) {
        Integer theNumberOfMasks = getTheNumberOfMasks(255);
        System.out.println(theNumberOfMasks);
    }

    public static void main(String[] args) {
        IPAddresses ipAddress =new IPAddresses();
        ipAddress.setIpStart("192.168.1.128");
        ipAddress.setIpEnd("192.168.2.255");
        List<String> stringList = addressSegmentDecomposition(ipAddress);
        System.out.println(stringList);
    }

}
