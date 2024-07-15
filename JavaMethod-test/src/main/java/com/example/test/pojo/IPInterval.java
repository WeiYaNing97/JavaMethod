package com.example.test.pojo;

public class IPInterval {
    private String ipStart;
    private String ipEnd;

    public IPInterval(String ipStart, String ipEnd) {
        this.ipStart = ipStart;
        this.ipEnd = ipEnd;
    }


    public String getIpStart() {
        return ipStart;
    }

    public void setIpStart(String ipStart) {
        this.ipStart = ipStart;
    }

    public String getIpEnd() {
        return ipEnd;
    }

    public void setIpEnd(String ipEnd) {
        this.ipEnd = ipEnd;
    }


    @Override
    public String toString() {
        return "IPInterval{" +
                "ipStart='" + ipStart + '\'' +
                ", ipEnd='" + ipEnd + '\'' +
                '}';
    }
}
