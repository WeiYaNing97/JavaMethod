package com.example.method.log;

import java.util.logging.Logger;

/**
 * 处理并记录日志文件
 * 
 * @author ruoyi
 */
public class LogUtils {
    private static final Logger LOGGER = Logger.getLogger(LogUtils.class.getName());
    /*LOGGER.info("这是一条信息级别的日志");
      LOGGER.warning("这是一条警告级别的日志");
      LOGGER.severe("这是一条严重级别的日志");*/

    public static String getInfo(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        String log = "[" + msg.toString() + "]";
        LOGGER.info(log);
        return log;
    }

    public static String getWarning(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        String log = "[" + msg.toString() + "]";
        LOGGER.warning(log);
        return log;
    }

    public static String getSevere(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        String log = "[" + msg.toString() + "]";
        LOGGER.severe(log);
        return log;
    }
}
