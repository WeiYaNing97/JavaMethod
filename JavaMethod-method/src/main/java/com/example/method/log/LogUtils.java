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

    /**
     * 将给定的对象作为信息日志输出，并返回该日志的字符串表示（包含方括号）。
     *
     * @param msg 要输出的信息日志消息，如果为null，则默认为空字符串
     * @return 包含方括号的信息日志字符串
     */
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

    /**
     * 将给定的对象作为警告日志输出，并返回该日志的字符串表示（包含方括号）。
     *
     * @param msg 要输出的警告日志消息，如果为null，则默认为空字符串
     * @return 包含方括号的警告日志字符串
     */
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

    /**
     * 将传入的消息以严重级别输出到日志中，并返回该消息字符串（包含方括号）。
     *
     * @param msg 要输出的消息对象，可以为null，但会被转换为空字符串
     * @return 带有方括号的消息字符串
     */
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
