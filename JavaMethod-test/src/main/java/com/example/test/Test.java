package com.example.test;

import com.example.util.MyUtils;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {


    public static void main(String[] args) {
        String cidr = "192.168.1.0/32";
        IPInfo ipInfo = parseCIDR(cidr);
        System.out.println("Network: " + ipInfo.network);
        System.out.println("Broadcast: " + ipInfo.broadcast);
        System.out.println("First usable: " + ipInfo.firstUsable);
        System.out.println("Last usable: " + ipInfo.lastUsable);
        System.out.println("Available addresses: " + ipInfo.availableAddresses);
    }

    public static IPInfo parseCIDR(String cidr) {
        String[] parts = cidr.split("/");
        String ipStr = parts[0];
        int prefix = Integer.parseInt(parts[1]);

        try {
            InetAddress ip = InetAddress.getByName(ipStr);
            byte[] bytes = ip.getAddress();
            long ipNum = ipToLong(bytes);

            long netMask = -1L << (32 - prefix);
            long network = ipNum & netMask;
            long broadcast = ipNum | (~netMask);

            // Calculate first and last usable IP addresses
            long firstUsable = network + 1;
            long lastUsable = broadcast - 1;

            // Calculate available addresses
            int availableAddresses = (int)(broadcast - network) - 1;

            return new IPInfo(
                    longToIp(network),
                    longToIp(broadcast),
                    longToIp(firstUsable),
                    longToIp(lastUsable),
                    availableAddresses
            );
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private static long ipToLong(byte[] bytes) {
        long result = 0;
        for (byte b : bytes) {
            result <<= 8;
            result |= (b & 0xFF);
        }
        return result;
    }

    private static String longToIp(long ip) {
        return ((ip >> 24) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                (ip & 0xFF);
    }

    static class IPInfo {
        String network;
        String broadcast;
        String firstUsable;
        String lastUsable;
        int availableAddresses;

        public IPInfo(String network, String broadcast, String firstUsable, String lastUsable, int availableAddresses) {
            this.network = network;
            this.broadcast = broadcast;
            this.firstUsable = firstUsable;
            this.lastUsable = lastUsable;
            this.availableAddresses = availableAddresses;
        }
    }
}
