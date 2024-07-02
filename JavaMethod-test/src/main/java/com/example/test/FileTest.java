package com.example.test;

import com.example.util.VerifyCodeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileTest {

    /**
     * 根据给定的时间获取文件信息，保留过期时间之后的日志并保存到原文件。
     *
     * @param time 字符串类型，表示过期时间，格式为"yyyy-MM-dd HH:mm:ss"
     * @throws IOException 如果在读取或写入文件时发生错误
     */
    @GetMapping("/getFileInformation")
    public static String getFileInformation(String time) {
        // 日志文件路径
        String logFilePath = "D:\\home\\JavaMethod\\logs\\log.txt"; // 日志文件路径
        // 过期时间戳
        long expirationTimeInMillis = calculateExpirationTime(time); // 过期时间戳

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter("temp_log.log", false))) {

            String line;
            // 判断是否已经遍历到过期时间
            boolean isExpirationTime = false;
            // 遍历日志文件，保留过期时间之后的日志
            while ((line = reader.readLine()) != null) {
                // 解析每行的日志时间戳
                //long logTimeInMillis = parseLogTimestamp(line);

                // 如果日志时间戳小于过期时间，跳过该行,如果日志时间戳大于过期时间，保留该日志
                if (isExpirationTime || parseLogTimestamp(line) > expirationTimeInMillis) {
                    System.err.println(line);
                    isExpirationTime = true;
                    writer.write(line);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 删除原文件并重命名临时文件
        try {
            Files.delete(Paths.get(logFilePath));
            Files.move(Paths.get("temp_log.log"), Paths.get(logFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String s = VerifyCodeUtils.generateVerifyCode(5);
        return s;
    }

    /**
     * 计算过期时间的时间戳
     *
     * @param expireTimeString 过期时间的字符串表示，格式为 "yyyy-MM-dd HH:mm:ss"
     * @return 返回过期时间的时间戳（毫秒）
     * @throws RuntimeException 如果解析过期时间字符串失败，则抛出此异常
     */
    private static long calculateExpirationTime(String expireTimeString) {
        try {
            // 创建一个SimpleDateFormat对象，指定日期时间格式为"yyyy-MM-dd HH:mm:ss"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 使用SimpleDateFormat对象的parse方法将字符串解析为Date对象
            Date expireDate = sdf.parse(expireTimeString);
            // 调用Date对象的getTime方法获取时间戳（毫秒）
            return expireDate.getTime();
        } catch (Exception e) {
            // 解析过期时间字符串失败时，抛出RuntimeException异常，并附带异常信息"Failed to parse expiration time"
            throw new RuntimeException("Failed to parse expiration time", e);
        }
    }

    /**
     * 将日志行中的时间戳字符串解析为长整型时间戳。
     *
     * @param logLine 日志行字符串，其中时间戳应包含在 [ ] 之间
     * @return 解析得到的长整型时间戳
     * @throws RuntimeException 如果解析日志时间戳失败，则抛出此异常
     */
    private static long parseLogTimestamp(String logLine) {
        try {
            // 提取时间戳字符串
            // 假设日志时间格式固定为 [yyyy-MM-dd HH:mm:ss]
            String timestampStr = logLine.substring(logLine.indexOf('[') + 1, logLine.indexOf(']'));
            // 创建SimpleDateFormat对象，设置时间格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 将时间戳字符串解析为Date对象
            Date logDate = sdf.parse(timestampStr);
            // 将Date对象转换为长整型时间戳
            return logDate.getTime();
        } catch (Exception e) {
            // 解析失败时抛出RuntimeException异常
            throw new RuntimeException("Failed to parse log timestamp", e);
        }
    }
}