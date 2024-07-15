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

    /**
     * 判断给定的IP地址是否在指定范围内
     *
     * @param ip 待判断的IP地址
     * @return 如果IP地址在指定范围内则返回true，否则返回false
     */
    public boolean isInRange(String ip) {
        // 判断IP地址是否在指定范围内
        if (compareIP(this.firstAvailable,ip) != 1 &&  compareIP(ip,this.finallyAvailable) != -1 ){
            // 如果IP地址不小于第一个可用IP地址且不大于最后一个可用IP地址，则返回true
            return true;
        }
        // 如果不满足上述条件，则返回false
        return false;
    }



    /**
     * 比较两个IP地址的大小。
     * @param ip1 第一个IP地址字符串
     * @param ip2 第二个IP地址字符串
     * @return 如果ip1 > ip2 返回1，如果ip1 < ip2 返回-1，否则返回0
     */
    public static int compareIP(String ip1, String ip2) {
        long ip1Long = ipToLong(ip1);
        long ip2Long = ipToLong(ip2);

        if (ip1Long > ip2Long) {
            return 1;
        } else if (ip1Long < ip2Long) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 将IP地址字符串转换为long值。
     * @param ipAddress IP地址字符串
     * @return 转换后的long值
     */
    private static long ipToLong(String ipAddress) {
        String[] parts = ipAddress.split("\\.");
        return (Long.parseLong(parts[0]) << 24)
                + (Long.parseLong(parts[1]) << 16)
                + (Long.parseLong(parts[2]) << 8)
                + Long.parseLong(parts[3]);
    }
}
