package com.example.method.aop.test.impl;

import com.example.method.aop.test.CalcService;
import org.springframework.stereotype.Service;

@Service
public class CalcServiceImpl implements CalcService {
    @Override
    public int div(int x, int y) {
        int result = x / y;
        System.out.println("CalcServiceImpl计算结果："+result);
        return result;
    }
}
