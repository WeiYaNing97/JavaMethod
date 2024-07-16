package com.example.test.ip;

import com.example.test.pojo.IPBlock;
import com.example.test.pojo.IPCalculator;
import com.example.test.pojo.IPInformation;
import com.example.util.MyUtils;

/** ip地址计算器
 * 自己根据ip格式编写的 与 GPT查询结果一样 都不能 /31 和 /32*/
public class IPAddressCalculator {



    public static IPCalculator Calculator(String ipCIDR) {

        // 创建IPBlock对象
        IPBlock ipBlock = new IPBlock(ipCIDR);
        // 创建IPCalculator对象
        IPCalculator ipCalculator = new IPCalculator();
        // 设置IPCIDR
        ipCalculator.setIPCIDR(ipCIDR);
        // 将IP地址转换为二进制字符串
        ipCalculator.setIp(MyUtils.getIPBinarySystem(ipBlock.getIp()));

        // 创建子网掩码的前部分，由1组成
        String maskPart1 = MyUtils.splicingStringsWithTheSameCharacter("1", Integer.valueOf(ipBlock.getPrefix()).intValue());

        // 计算主机数
        int hostNumber = 32 - ipBlock.getPrefix();

        // 创建由0组成的主机数部分
        String hostNumber0 = MyUtils.splicingStringsWithTheSameCharacter("0", hostNumber);

        // 创建由1组成的主机数部分
        String hostNumber1 = MyUtils.splicingStringsWithTheSameCharacter("1", hostNumber);

        // 设置子网掩码
        ipCalculator.setMask(maskPart1+hostNumber0);

        // 将主机数部分转换为十进制
        int decimal = Integer.parseInt( hostNumber1==""?"0":hostNumber1, 2);

        // 设置可用地址数（减去网络地址和广播地址）
        ipCalculator.setAvailableAddresses(decimal == 0 ? 0 : decimal-1);

        // 获取网络地址部分
        String iPNetworkSection = ipCalculator.getIp().substring(0, ipBlock.getPrefix());

        // 设置网络号
        ipCalculator.setNetworkNumber(iPNetworkSection + hostNumber0);

        // 设置第一台可用的地址
        ipCalculator.setFirstAvailable(iPNetworkSection + hostNumber0 );

        // 设置最后一台可用的地址
        ipCalculator.setFinallyAvailable(iPNetworkSection + hostNumber1);

        // 设置广播地址
        ipCalculator.setBroadcast(iPNetworkSection + hostNumber1);

        // 将IP地址、子网掩码、网络号、第一个可用的主机号、最后一个可用的主机号、广播地址转换为点分十进制形式
        ipCalculator.setIp(MyUtils.obtainIPBasedOnBinary(ipCalculator.getIp()));
        ipCalculator.setMask(MyUtils.obtainIPBasedOnBinary(ipCalculator.getMask()));
        ipCalculator.setNetworkNumber(MyUtils.obtainIPBasedOnBinary(ipCalculator.getNetworkNumber()));
        ipCalculator.setFirstAvailable(MyUtils.obtainIPBasedOnBinary(ipCalculator.getFirstAvailable()));
        ipCalculator.setFinallyAvailable(MyUtils.obtainIPBasedOnBinary(ipCalculator.getFinallyAvailable()));
        ipCalculator.setBroadcast(MyUtils.obtainIPBasedOnBinary(ipCalculator.getBroadcast()));

        // 返回IPCalculator对象
        return ipCalculator;
    }

}
