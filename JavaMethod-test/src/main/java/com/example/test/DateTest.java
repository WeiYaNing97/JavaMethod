package com.example.test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTest {

    public static void main(String[] args) {

        Double dataRetentionTime = 3.0;

        // 获取当前日期和时间，考虑系统默认时区
        ZonedDateTime now = ZonedDateTime.now();
        // N个月前的日期和时间
        int monthsBefore = (int) (dataRetentionTime * 12) ; // 例如，获取3个月之前的日期和时间
        ZonedDateTime dateBefore = now.minusMonths(monthsBefore);

        // 如果你想以某个特定时区来表示，可以这样设置：
        // ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        // ZonedDateTime dateBeforeInShanghai = now.withZoneSameInstant(zoneId).minusMonths(monthsBefore);

        // 格式化输出
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateBefore.format(formatter);
        System.out.println("三个月前的日期和时间是: " + formattedDateTime);
    }
}
