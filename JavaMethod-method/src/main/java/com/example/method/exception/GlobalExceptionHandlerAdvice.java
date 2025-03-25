package com.example.method.exception;

import com.example.method.result.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 */
@RestControllerAdvice //统一处理
public class GlobalExceptionHandlerAdvice{

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);
    @ExceptionHandler({Exception.class})// 什么异常的统一处理
    public AjaxResult handlerException(Exception e, HttpServletRequest request, HttpServletResponse response){

        // 记录日志
        //logger.error("全局异常处理：{}",e);
        return AjaxResult.error("返回错误:"+e);
    }

}
