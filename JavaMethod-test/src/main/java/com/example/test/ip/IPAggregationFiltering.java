package com.example.test.ip;

import com.example.test.pojo.IPBlock;
import com.example.test.pojo.IPCalculator;
import com.example.util.MyUtils;

import java.util.*;

/**
 * 聚合筛选
 */
public class IPAggregationFiltering {
    public static void main(String[] args) {
        // 示例输入
        List<String> ipBlocks = new ArrayList<>();
        ipBlocks.add("58.33.56.82/22");

        // 使用Collections.sort()方法对列表进行排序
        Collections.sort(ipBlocks);

    }


}
