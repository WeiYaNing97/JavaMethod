package com.example.test.pojo;



public class IPCalculator {
    private String IPCIDR;
    /*ip*/
    private String ip;
    /*掩码*/
    private String mask;

    private String area;

    /*可用地址*/
    private Integer AvailableAddresses;
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

    public Integer getAvailableAddresses() {
        return AvailableAddresses;
    }

    public void setAvailableAddresses(Integer availableAddresses) {
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "IPCalculator{" +
                "IPCIDR='" + IPCIDR + '\'' +
                ", ip='" + ip + '\'' +
                ", mask='" + mask + '\'' +
                ", area='" + area + '\'' +
                ", AvailableAddresses=" + AvailableAddresses +
                ", networkNumber='" + networkNumber + '\'' +
                ", firstAvailable='" + firstAvailable + '\'' +
                ", finallyAvailable='" + finallyAvailable + '\'' +
                ", broadcast='" + broadcast + '\'' +
                '}';
    }
}
