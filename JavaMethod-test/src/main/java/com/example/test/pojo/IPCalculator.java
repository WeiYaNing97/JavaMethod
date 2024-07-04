package com.example.test.pojo;



public class IPCalculator {
    private String IPCIDR;
    /*ip*/
    private String ip;
    /*掩码*/
    private String mask;
    /*可用地址*/
    private String AvailableAddresses;
    /*网络号*/
    private String networkNumber;
    /*第一个可用:*/
    private String firstAvailable;
    /*最后可用*/
    private String finallyAvailable;
    /*广播*/
    private String broadcast;

    public String getIPCIDR() {
        return IPCIDR;
    }

    public void setIPCIDR(String IPCIDR) {
        this.IPCIDR = IPCIDR;
    }

    public String getAvailableAddresses() {
        return AvailableAddresses;
    }

    public void setAvailableAddresses(String availableAddresses) {
        AvailableAddresses = availableAddresses;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getNetworkNumber() {
        return networkNumber;
    }

    public void setNetworkNumber(String networkNumber) {
        this.networkNumber = networkNumber;
    }

    public String getFirstAvailable() {
        return firstAvailable;
    }

    public void setFirstAvailable(String firstAvailable) {
        this.firstAvailable = firstAvailable;
    }

    public String getFinallyAvailable() {
        return finallyAvailable;
    }

    public void setFinallyAvailable(String finallyAvailable) {
        this.finallyAvailable = finallyAvailable;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    @Override
    public String toString() {
        return "\nIPCalculator{" +
                "\nIPCIDR='" + IPCIDR + '\'' +
                ",\n 地址='" + ip + '\'' +
                ",\n 掩码='" + mask + '\'' +
                ",\n 可用='" + AvailableAddresses + '\'' +
                ",\n 网络='" + networkNumber + '\'' +
                ",\n 第一='" + firstAvailable + '\'' +
                ",\n 最后='" + finallyAvailable + '\'' +
                ",\n 广播='" + broadcast + '\'' +
                '}';
    }
}
