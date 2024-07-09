package com.example.test.address;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class IPSubnetCalculator {

    public static void main(String[] args) {
        String cidr = "192.168.1.0/24";
        IPDetails details = calculateSubnetDetails(cidr);
        System.out.println(details);
    }

    public static IPDetails calculateSubnetDetails(String cidr) {
        String[] parts = cidr.split("/");
        String ipStr = parts[0];
        int prefixLength = Integer.parseInt(parts[1]);

        try {
            InetAddress ipAddress = InetAddress.getByName(ipStr);
            byte[] addressBytes = ipAddress.getAddress();
            byte[] maskBytes = generateMask(prefixLength);
            byte[] networkAddressBytes = applyMask(addressBytes, maskBytes);
            byte[] broadcastAddressBytes = calculateBroadcastAddress(addressBytes, maskBytes);

            int totalHosts = (1 << (32 - prefixLength)) - 2; // Subtracting the network and broadcast addresses
            String firstUsableIp = bytesToIpString(incrementIpAddress(networkAddressBytes));
            String lastUsableIp = bytesToIpString(decrementIpAddress(broadcastAddressBytes));

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
            throw new RuntimeException("Invalid IP address format", e);
        }
    }

    private static byte[] generateMask(int prefixLength) {
        byte[] mask = new byte[4];
        for (int i = 0; i < 4; i++) {
            if (prefixLength >= 8) {
                mask[i] = (byte) 0xFF;
                prefixLength -= 8;
            } else {
                mask[i] = (byte) ((0xFF << (8 - prefixLength)) & 0xFF);
                break;
            }
        }
        return mask;
    }

    private static byte[] applyMask(byte[] ipAddress, byte[] mask) {
        byte[] result = new byte[4];
        for (int i = 0; i < 4; i++) {
            result[i] = (byte) (ipAddress[i] & mask[i]);
        }
        return result;
    }

    private static byte[] calculateBroadcastAddress(byte[] ipAddress, byte[] mask) {
        byte[] invertedMask = invertMask(mask);
        byte[] broadcastAddress = new byte[4];
        for (int i = 0; i < 4; i++) {
            broadcastAddress[i] = (byte) (ipAddress[i] | invertedMask[i]);
        }
        return broadcastAddress;
    }

    private static byte[] invertMask(byte[] mask) {
        byte[] invertedMask = new byte[4];
        for (int i = 0; i < 4; i++) {
            invertedMask[i] = (byte) (~mask[i] & 0xFF);
        }
        return invertedMask;
    }

    private static byte[] incrementIpAddress(byte[] ipAddress) {
        byte[] incrementedIp = new byte[4];
        for (int i = 3; i >= 0; i--) {
            incrementedIp[i] = (byte) (ipAddress[i] + 1);
            if (incrementedIp[i] != 0) {
                System.arraycopy(ipAddress, 0, incrementedIp, 0, i);
                break;
            }
        }
        return incrementedIp;
    }

    private static byte[] decrementIpAddress(byte[] ipAddress) {
        byte[] decrementedIp = new byte[4];
        for (int i = 3; i >= 0; i--) {
            decrementedIp[i] = (byte) (ipAddress[i] - 1);
            if (decrementedIp[i] != -1) {
                System.arraycopy(ipAddress, 0, decrementedIp, 0, i);
                break;
            }
        }
        return decrementedIp;
    }

    private static String bytesToIpString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(bytes[i] & 0xFF);
            if (i < 3) {
                sb.append(".");
            }
        }
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

        public IPDetails(String ip, String mask, String networkAddress, String firstUsableIp, String lastUsableIp, String broadcastAddress, int totalHosts) {
            this.ip = ip;
            this.mask = mask;
            this.networkAddress = networkAddress;
            this.firstUsableIp = firstUsableIp;
            this.lastUsableIp = lastUsableIp;
            this.broadcastAddress = broadcastAddress;
            this.totalHosts = totalHosts;
        }

        @Override
        public String toString() {
            return "IPDetails{" +
                    "ip='" + ip + '\'' +
                    ", mask='" + mask + '\'' +
                    ", networkAddress='" + networkAddress + '\'' +
                    ", firstUsableIp='" + firstUsableIp + '\'' +
                    ", lastUsableIp='" + lastUsableIp + '\'' +
                    ", broadcastAddress='" + broadcastAddress + '\'' +
                    ", totalHosts=" + totalHosts +
                    '}';
        }
    }
}