package com.example.test.pojo;

import java.util.List;

public class IPAggregationOneMany {
    private IPBlock aggregation;
    private List<IPBlock> ipBlocks;

    public IPBlock getAggregation() {
        return aggregation;
    }

    public void setAggregation(IPBlock aggregation) {
        this.aggregation = aggregation;
    }

    public List<IPBlock> getIpBlocks() {
        return ipBlocks;
    }

    public void setIpBlocks(List<IPBlock> ipBlocks) {
        this.ipBlocks = ipBlocks;
    }
}
