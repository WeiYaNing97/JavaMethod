package com.example.test.pojo;

public class Interval {
    private int start;
    private int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
    /*仅适用于闭区间*/
    public boolean contains(Interval other) {
        return this.start <= other.start && this.end >= other.end;
    }
}