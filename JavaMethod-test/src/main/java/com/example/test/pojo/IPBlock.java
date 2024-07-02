package com.example.test.pojo;

public class IPBlock {
    String ip;
    int prefix;

    public IPBlock() {}

    public IPBlock(String ipAndPrefix) {
        String[] parts = ipAndPrefix.split("/");
        this.ip = parts[0];
        this.prefix = Integer.parseInt(parts[1]);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    public String getCIDR() {
        return ip+"/"+prefix;
    }
}
