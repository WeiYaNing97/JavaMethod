package com.example.utils.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * GPT查询结果
 * 计算IP地址和子网掩码
 */
public class IPSubnetCalculator {

    public static void main(String[] args) {
        String cidr = "192.168.1.0/24";
        IPDetails details = calculateSubnetDetails(cidr);
        System.err.println(details);
    }

    /**
     * 根据CIDR字符串计算子网详细信息
     *
     * @param cidr CIDR字符串，格式为"IP地址/前缀长度"
     * @return IPDetails对象，包含IP地址、子网掩码、网络地址、第一个可用IP地址、最后一个可用IP地址、广播地址和总主机数
     * @throws RuntimeException 当IP地址格式无效时抛出，包含原始异常信息
     */
    public static IPDetails calculateSubnetDetails(String cidr) {
        // 将CIDR字符串按"/"分割成两部分
        String[] parts = cidr.split("/");
        // 提取IP地址字符串
        String ipStr = parts[0];
        // 提取前缀长度
        int prefixLength = Integer.parseInt(parts[1]);

        try {
            // 将IP地址字符串转换成InetAddress对象
            InetAddress ipAddress = InetAddress.getByName(ipStr);
            // 获取IP地址的字节数组
            byte[] addressBytes = ipAddress.getAddress();
            // 根据前缀长度生成子网掩码字节数组
            byte[] maskBytes = generateMask(prefixLength);
            // 将IP地址字节数组与子网掩码字节数组进行与运算，得到网络地址字节数组
            byte[] networkAddressBytes = applyMask(addressBytes, maskBytes);
            // 根据IP地址字节数组和子网掩码字节数组计算广播地址字节数组
            byte[] broadcastAddressBytes = calculateBroadcastAddress(addressBytes, maskBytes);

            // 计算可用主机数量，减去网络地址和广播地址
            int totalHosts = (1 << (32 - prefixLength)) - 2; // Subtracting the network and broadcast addresses
            // 将网络地址字节数组加1，转换为可用IP地址字符串
            String firstUsableIp = bytesToIpString(incrementIpAddress(networkAddressBytes));
            // 将广播地址字节数组减1，转换为可用IP地址字符串
            String lastUsableIp = bytesToIpString(decrementIpAddress(broadcastAddressBytes));

            // 封装IP详细信息对象并返回
            return new IPDetails(
                    ipStr,
                    bytesToIpString(maskBytes),
                    bytesToIpString(networkAddressBytes),
                    firstUsableIp,
                    lastUsableIp,
                    bytesToIpString(broadcastAddressBytes),
                    totalHosts
            );
        } catch (UnknownHostException e) {
            // 捕获UnknownHostException异常，并抛出运行时异常，提示无效的IP地址格式
            throw new RuntimeException("Invalid IP address format", e);
        }
    }


    /**
     * 生成子网掩码
     *
     * @param prefixLength 前缀长度，表示子网掩码中1的位数
     * @return 生成的子网掩码，以字节数组形式返回
     */
    private static byte[] generateMask(int prefixLength) {
        byte[] mask = new byte[4];
        for (int i = 0; i < 4; i++) {
            // 如果前缀长度大于等于8
            if (prefixLength >= 8) {
                // 将mask[i]设置为0xFF，即二进制全为1
                mask[i] = (byte) 0xFF;
                // 前缀长度减去8
                prefixLength -= 8;
            } else {
                // 如果前缀长度小于8，则将mask[i]设置为左移(8 - prefixLength)位的0xFF与0xFF进行按位与运算的结果
                mask[i] = (byte) ((0xFF << (8 - prefixLength)) & 0xFF);
                // 跳出循环
                break;
            }
        }
        // 返回生成的子网掩码
        return mask;
    }


    /**
     * 将IP地址和子网掩码进行按位与运算，生成网络地址
     *
     * @param ipAddress IP地址的字节数组
     * @param mask 子网掩码的字节数组
     * @return 运算后的网络地址的字节数组
     */
    private static byte[] applyMask(byte[] ipAddress, byte[] mask) {
        byte[] result = new byte[4];
        // 遍历IP地址和子网掩码的每个字节
        for (int i = 0; i < 4; i++) {
            // 对IP地址和子网掩码的对应字节进行按位与运算，并将结果保存到result数组中
            result[i] = (byte) (ipAddress[i] & mask[i]);
        }
        // 返回计算得到的网络地址的字节数组
        return result;
    }


    /**
     * 计算广播地址
     *
     * @param ipAddress IP地址的字节数组
     * @param mask 子网掩码的字节数组
     * @return 广播地址的字节数组
     */
    private static byte[] calculateBroadcastAddress(byte[] ipAddress, byte[] mask) {
        // 反转子网掩码
        byte[] invertedMask = invertMask(mask);

        // 创建广播地址的字节数组
        byte[] broadcastAddress = new byte[4];

        // 遍历IP地址和反转后的子网掩码
        for (int i = 0; i < 4; i++) {
            // 将IP地址的对应字节与反转后的子网掩码的对应字节进行按位或运算，得到广播地址的对应字节
            broadcastAddress[i] = (byte) (ipAddress[i] | invertedMask[i]);
        }

        // 返回广播地址的字节数组
        return broadcastAddress;
    }


    /**
     * 将给定的子网掩码进行反转操作，即将其每个字节中的每一位取反
     *
     * @param mask 子网掩码的字节数组
     * @return 反转后的子网掩码的字节数组
     */
    private static byte[] invertMask(byte[] mask) {
        // 创建一个长度为4的字节数组，用于存储反转后的子网掩码
        byte[] invertedMask = new byte[4];
        // 遍历子网掩码的每个字节
        for (int i = 0; i < 4; i++) {
            // 对当前字节进行按位取反操作，并与0xFF进行按位与运算，确保结果为正数
            invertedMask[i] = (byte) (~mask[i] & 0xFF);
        }
        // 返回反转后的子网掩码的字节数组
        return invertedMask;
    }


    /**
     * 递增IP地址的字节数组表示。
     *
     * @param ipAddress 要递增的IP地址的字节数组表示
     * @return 递增后的IP地址的字节数组表示
     *
     * 注意：此方法仅递增IP地址的最后一个部分（即最后一个字节），如果最后一个字节为255，则将其变为0，并尝试递增前一个字节。
     * 如果所有字节都为255（即IPv4地址的广播地址），则此方法返回的结果可能不是有效的IP地址。
     */
    private static byte[] incrementIpAddress(byte[] ipAddress) {
        byte[] incrementedIp = new byte[4];
        for (int i = 3; i >= 0; i--) {
            // 将当前位置的IP地址加1
            incrementedIp[i] = (byte) (ipAddress[i] + 1);

            // 如果加1后的结果不为0，则将原IP地址的前i个字节复制到incrementedIp中，并跳出循环
            if (incrementedIp[i] != 0) {
                System.arraycopy(ipAddress, 0, incrementedIp, 0, i);
                break;
            }
        }
        return incrementedIp;
    }


    /**
     * 将给定的IP地址字节数组进行递减操作，并返回递减后的IP地址字节数组。
     *
     * @param ipAddress 给定的IP地址字节数组
     * @return 递减后的IP地址字节数组
     */
    private static byte[] decrementIpAddress(byte[] ipAddress) {
        byte[] decrementedIp = new byte[4];
        // 从最后一个字节开始遍历
        for (int i = 3; i >= 0; i--) {
            // 当前字节减1
            decrementedIp[i] = (byte) (ipAddress[i] - 1);
            // 如果当前字节减1后不为-1，则复制前面的字节到decrementedIp中，并跳出循环
            if (decrementedIp[i] != -1) {
                System.arraycopy(ipAddress, 0, decrementedIp, 0, i);
                break;
            }
        }
        // 返回修改后的IP地址
        return decrementedIp;
    }


    /**
     * 将字节数组转换为IP地址格式的字符串
     *
     * @param bytes 字节数组，包含四个字节，表示一个IPv4地址
     * @return 返回IP地址格式的字符串，例如："192.168.1.1"
     */
    private static String bytesToIpString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            // 将字节转换为无符号整数（0-255）并添加到字符串构建器中
            sb.append(bytes[i] & 0xFF);

            // 如果当前字节不是最后一个字节，则在字符串构建器中添加点号分隔符
            if (i < 3) {
                sb.append(".");
            }
        }
        // 将字符串构建器转换为字符串并返回
        return sb.toString();
    }


    static class IPDetails {
        String ip;
        String mask;
        String networkAddress;
        String firstUsableIp;
        String lastUsableIp;
        String broadcastAddress;
        int totalHosts;

        /**
         * IPDetails类的构造函数，用于创建一个包含IP地址、子网掩码、网络地址、第一个可用IP地址、最后一个可用IP地址、广播地址和总主机数的IPDetails对象。
         *
         * @param ip IP地址
         * @param mask 子网掩码
         * @param networkAddress 网络地址
         * @param firstUsableIp 第一个可用IP地址
         * @param lastUsableIp 最后一个可用IP地址
         * @param broadcastAddress 广播地址
         * @param totalHosts 总主机数
         */
        public IPDetails(String ip, String mask, String networkAddress, String firstUsableIp, String lastUsableIp, String broadcastAddress, int totalHosts) {
            // 设置IP地址
            this.ip = ip;
            // 设置子网掩码
            this.mask = mask;
            // 设置网络地址
            this.networkAddress = networkAddress;
            // 设置第一个可用IP地址
            this.firstUsableIp = firstUsableIp;
            // 设置最后一个可用IP地址
            this.lastUsableIp = lastUsableIp;
            // 设置广播地址
            this.broadcastAddress = broadcastAddress;
            // 设置总主机数
            this.totalHosts = totalHosts;
        }


        @Override
        public String toString() {
            return "IPDetails{" +
                    "\r\n ip='" + ip + '\'' +
                    ",\r\n mask='" + mask + '\'' +
                    ",\r\n networkAddress='" + networkAddress + '\'' +
                    ",\r\n firstUsableIp='" + firstUsableIp + '\'' +
                    ",\r\n lastUsableIp='" + lastUsableIp + '\'' +
                    ",\r\n broadcastAddress='" + broadcastAddress + '\'' +
                    ",\r\n totalHosts=" + totalHosts +
                    '}';
        }
    }
}