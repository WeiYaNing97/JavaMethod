package com.example.utils.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {
    // 令牌自定义标识
    @Value("${obtain.ymlfile.logPath}")
    private String logPath;
}
