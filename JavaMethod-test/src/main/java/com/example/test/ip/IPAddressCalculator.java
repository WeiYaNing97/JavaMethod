package com.example.test.ip;

import com.example.test.address.IPSubnetCalculator;
import com.example.test.pojo.IPBlock;
import com.example.test.pojo.IPCalculator;
import com.example.util.MyUtils;

/** ip 地址计算器 */
public class IPAddressCalculator {

    public static void main(String[] args) {
        String cidr = "192.168.1.0/30";
        IPCalculator calculator = Calculator(cidr);

        System.out.println(calculator.toString());
    }

    /* 计算器 */
    public static IPCalculator Calculator(String ipCIDR) {
        /* 根据 IP的CIDR格式 获取 ip和网络号位数 */
        IPBlock ipBlock = new IPBlock(ipCIDR);

        /* IP信息实体类 */
        IPCalculator ipCalculator = new IPCalculator();
        /* ip CIDR格式*/
        ipCalculator.setIPCIDR(ipCIDR);
        /* ip 解析为32位二进制*/
        ipCalculator.setIp(MyUtils.getIPBinarySystem(ipBlock.getIp()));

        /* 创建 一个 长度与网络号位数相同 字符全为1的字符串*/
        String maskPart1 = MyUtils.splicingStringsWithTheSameCharacter("1", Integer.valueOf(ipBlock.getPrefix()).intValue());
        /* 主机号位数 */
        int hostNumber = 32 - ipBlock.getPrefix();
        /* 创建主机号长度字符全为0的字符串*/
        String hostNumber0 = MyUtils.splicingStringsWithTheSameCharacter("0", hostNumber);
        /* 创建主机号长度字符全为1的字符串 */
        String hostNumber1 = MyUtils.splicingStringsWithTheSameCharacter("1", hostNumber);
        /* 设置 子网掩码： 将网络号长度字符全为1的字符串 与 将主机号长度字符全为0的字符串 拼接*/
        ipCalculator.setMask(maskPart1+hostNumber0);

        /* 并转化为2进制 查看网络号地址段有多少地址*/
        int decimal = Integer.parseInt( hostNumber1==""?"0":hostNumber1, 2);
        /* 因为主机号全为1的情况为广播地址 要 -1*/
        ipCalculator.setAvailableAddresses(decimal == 0 ? 0 : decimal-1);
        /* 截取二进制IP 网络号部分*/
        String iPNetworkSection = ipCalculator.getIp().substring(0, ipBlock.getPrefix());
        /* 二进制IP网络号部分 加 主机号长度字符全为0的字符串*/
        ipCalculator.setNetworkNumber(iPNetworkSection + hostNumber0);
        /* 二进制IP网络号部分 加 主机号长度字符只有最后一位为1其余为0 的字符串*/
        String firstAvailableHostNumber ="";
        if (hostNumber0 != ""){
            firstAvailableHostNumber = hostNumber0.substring(0,hostNumber-1)+"1";
        }
        ipCalculator.setFirstAvailable(iPNetworkSection + firstAvailableHostNumber );

        /* 二进制IP网络号部分 加 主机号长度字符只有最后一位为0其余为1 的字符串  去除了 广播地址*/
        String finallyAvailableHostNumber ="";
        if (hostNumber0 != ""){
            finallyAvailableHostNumber = hostNumber1.substring(0,hostNumber-1)+"0";
        }
        ipCalculator.setFinallyAvailable(iPNetworkSection + finallyAvailableHostNumber);

        /* 二进制IP网络号部分 加 主机号长度字符全为1的字符串 广播地址*/
        ipCalculator.setBroadcast(iPNetworkSection + hostNumber1);

        /* 32位二进制数据 转化为十进制 */
        ipCalculator.setIp(MyUtils.obtainIPBasedOnBinary(ipCalculator.getIp()));
        ipCalculator.setMask(MyUtils.obtainIPBasedOnBinary(ipCalculator.getMask()));
        ipCalculator.setNetworkNumber(MyUtils.obtainIPBasedOnBinary(ipCalculator.getNetworkNumber()));
        ipCalculator.setFirstAvailable(MyUtils.obtainIPBasedOnBinary(ipCalculator.getFirstAvailable()));
        ipCalculator.setFinallyAvailable(MyUtils.obtainIPBasedOnBinary(ipCalculator.getFinallyAvailable()));
        ipCalculator.setBroadcast(MyUtils.obtainIPBasedOnBinary(ipCalculator.getBroadcast()));
        return ipCalculator;
    }
}
