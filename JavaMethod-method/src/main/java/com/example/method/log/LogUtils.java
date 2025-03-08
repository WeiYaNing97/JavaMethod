package com.example.method.log;

import com.example.method.file.WriteToFile;
import com.example.utils.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 处理并记录日志文件
 * 
 * @author ruoyi
 */
@Component
public class LogUtils {

    @Value("${obtain.ymlfile.logPath}")
    private String logPath;
    @Autowired
    private WriteToFile writeToFile;

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
        String log = "[" + msg + "]";
        LOGGER.severe(log);
        return log;
    }


    /**
     * 将异常信息记录到日志文件中，并返回异常信息的字符串表示
     *
     * @param exception 异常对象
     * @return 异常信息的字符串表示
     */
    public String setExceptionInLog(Exception exception)
    {
        // 获取异常信息的字符串表示
        String severe = getSevere(exception);

        // 获取当前日期时间字符串
        String date ="["+MyUtils.getDate("yyyy-MM-dd HH:mm:ss")+"] ";

        // 创建一个ArrayList用于存储日志信息
        List<String> information_list = new ArrayList<>();
        // 将日期时间字符串和异常信息拼接后添加到信息列表中
        information_list.add(date + exception);

        // 获取异常的堆栈跟踪信息
        StackTraceElement[] stackTrace = exception.getStackTrace();
        // 将堆栈跟踪信息转换为字符串列表，并将每条信息前添加日期时间字符串
        List<String> collect = Arrays.asList(stackTrace).stream().map(x -> date+x).collect(Collectors.toList());
        // 将转换后的堆栈跟踪信息添加到信息列表中
        information_list.addAll(collect);

        // 将信息列表写入到日志文件中
        // 文件路径为：logPath+当前日期-log.txt
        writeToFile.writeStringList(information_list, logPath + MyUtils.getDate("yyyy-MM-dd") + "-log.txt");

        // 返回异常信息的字符串表示
        return severe;
    }

}
