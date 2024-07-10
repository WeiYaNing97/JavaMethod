package com.example.test.ip;

import com.example.test.pojo.IPBlock;
import com.example.test.pojo.IPCalculator;
import com.example.test.pojo.Interval;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 第二版
 * 只考虑 ip:x.x.x.x 第三位
 * 1. 排序 Collections.sort(ipBlocks);
 * 2. 合并 相近的IP 组合测试聚合
 * 3. 聚合 获得聚合，查看是否满足条件 满足则聚合，否则去除聚合后不满足的ip、或者聚合后超出范围修改聚合
 */
public class IPAggregationFiltering {
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


        // 使用Collections.sort()方法对列表进行排序
        Collections.sort(ipBlocks);

        List<IPBlock> ipBlockList = ipBlocks.stream().map(x -> new IPBlock(x)).collect(Collectors.toList());

        List<List<IPBlock>> lists = filterGrouping(ipBlockList);

        for (List<IPBlock> list:lists){
            List<String> collect = list.stream().map(x -> x.getIp() + "/" + x.getPrefix()).collect(Collectors.toList());
            if (collect.size() <= 2){
                System.err.println();
            }
            IPBlock ipBlock = IPAggregation.aggregateRoutes(collect);
            System.err.println(ipBlock.getIp()+"/"+ipBlock.getPrefix());
        }
    }

    /**
     * 将IP块列表按照相邻关系进行分组并返回分组后的列表
     *
     * @param ipBlockList 待分组的IP块列表
     * @return 分组后的IP块列表列表，每个内部列表包含一组相邻的IP块
     */
    public static List<List<IPBlock>> filterGrouping(List<IPBlock> ipBlockList) {
        List<List<IPBlock>> pojoLists = new ArrayList<>();

        while (ipBlockList.size() >= 1){
            if (ipBlockList.size() == 1){
                pojoLists.add(ipBlockList);
            }

            List<IPBlock> ipBlocks = new ArrayList<>();
            ipBlocks.add(ipBlockList.get(0));
            for (int i = 1; i < ipBlockList.size(); i++){
                /* 当前便利的前一个*/
                IPBlock frontIpBlock = ipBlockList.get(i-1);
                /* 当前便利的*/
                IPBlock currentIpBlock = ipBlockList.get(i);
                if (judgingAdjacency(frontIpBlock.getIp(),currentIpBlock.getIp())){
                    ipBlocks.add(currentIpBlock);
                }else {
                    break;
                }
            }

            // ================将可以聚合的字段筛选出来=====================
            /* 将可以聚合的字段筛选出来 */
            /*逻辑方法*/
            ipBlocks = minimumAggregation(ipBlocks);
            // ================将可以聚合的字段筛选出来=====================

            if (ipBlocks.size() == 1){
                pojoLists.add(ipBlocks);
                ipBlockList.remove(ipBlocks.get(0));
            }else {
                pojoLists.add(ipBlocks);
                for (IPBlock value:ipBlocks){
                    ipBlockList.remove(value);
                }
            }
        }

        return pojoLists;
    }

    /**
     * 判断两个IP地址是否相邻
     *
     * @param frontIp 前一个IP地址
     * @param currentIp 当前IP地址
     * @return 如果两个IP地址相邻，则返回true；否则返回false
     */
    public static boolean judgingAdjacency(String frontIp,String currentIp) {
        // 将前一个IP地址按照点号分割成字符串数组
        String[] front_split = frontIp.split("\\.");
        // 将当前IP地址按照点号分割成字符串数组
        String[] current_split = currentIp.split("\\.");

        // 判断前两位是否相等
        /*判断前两位是否相等*/
        if ((front_split[0]+"."+front_split[1]).equals((current_split[0]+"."+current_split[1]))){

            // 判断第三位是否相差1或相等
            if ((Integer.valueOf(front_split[2]).intValue() - Integer.valueOf(current_split[2]).intValue() == 1)
                    ||
            (Integer.valueOf(front_split[2]).intValue() - Integer.valueOf(current_split[2]).intValue() == 0)){

                // 如果满足条件，则返回true
                return true;

            }

        }
        // 如果不满足条件，则返回false
        return false;
    }


    /**
     * 将给定IP块列表按照网络地址进行聚合
     *
     * @param ipBlocks IP块列表
     * @return 聚合后的IP块列表
     */
    public static List<IPBlock> minimumAggregation(List<IPBlock> ipBlocks) {
        /*
        1:因为第三位是连续的所以 先获取最小值和最大值 ：ip地址段区间
        */
        /* 获取所有IP地址的列表 */
        List<String> ips = ipBlocks.stream().map(x -> x.getIp()).collect(Collectors.toList());
        /* 提取所有IP地址的第三位并转换为整数列表 */
        List<Integer> ip3s = ips.stream().map(x -> Integer.valueOf( x.split("\\.")[2] ).intValue()).collect(Collectors.toList());

        /* 最大值 */
        int ipmax = Collections.max(ip3s);
        /* 最小值 */
        int ipmin = Collections.min(ip3s);
        /* 创建IP地址段区间对象 */
        Interval ipInterval = new  Interval(ipmin , ipmax);


        int prefix = ipBlocks.get(0).getPrefix();
        boolean contains = false;
        int networkmin = 0;
        int networkmax = 0;
        int num = 0;

        /*
        2:根据第一条ip和 对应的掩码 获取区间，查看是否有包含在内的。包含的 网络号地址段要求在 ip地址段区间内
        3:如果全部在ip地址段内 则   对应的掩码 -1 ，重复第二步，直到 网络号地址段超出ip地址段区间、或者两个区间相等。
        */
        do {
            num++;
            if (contains){
                prefix--;
            }
            /* 根据第一个IP地址和当前prefix计算网络号地址段 */
            IPCalculator calculator = IPAddressCalculator.Calculator(ipBlocks.get(0).getIp() + "/" + prefix);
            networkmin = Integer.valueOf(calculator.getFirstAvailable().split("\\.")[2]).intValue();
            networkmax = Integer.valueOf(calculator.getFinallyAvailable().split("\\.")[2]).intValue();
            /* 创建网络号地址段区间对象 */
            Interval networkInterval = new  Interval(networkmin , networkmax);
            /* 判断网络号地址段是否在IP地址段区间内 */
            contains = ipInterval.contains(networkInterval);
        }while (contains);

        /*
        num > 1 证明多次执行 说明prefix执行过减法 说明跳出循环时多减了一次
        */
        /* 输出聚合后的IP地址和prefix */
        /* num > 1 证明多次执行 说明prefix执行过减法 说明跳出循环时多减了一次*/
        System.err.println("聚合："+ ipBlocks.get(0).getIp() + "/" + (num > 1? prefix+1:prefix));

        List<IPBlock> returnPojoList = new ArrayList<>();
        for (IPBlock pojo:ipBlocks){
            int ip3 = Integer.valueOf(pojo.getIp().split("\\.")[2]).intValue();
            /* 判断当前IP地址的第三位是否在网络号地址段内 */
            if (networkmin <= ip3 && ip3 <= networkmax){
                System.err.println(pojo.getIp()+"/"+pojo.getPrefix());
                /* 将满足条件的IPBlock对象加入结果列表 */
                returnPojoList.add(pojo);
            }
        }

        /* 输出分隔符 */
        System.err.println("============================================================");

        /* 返回结果列表 */
        return returnPojoList;
    }

}
