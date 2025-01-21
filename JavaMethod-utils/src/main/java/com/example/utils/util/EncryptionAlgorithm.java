package com.example.utils.util;

import cn.hutool.core.util.ReUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionAlgorithm {
    /**
     * 校验复杂度
     */
    public Boolean checkPWD(String PWD) {
        // 规定的正则表达式
        // (?![a-zA-Z]+$) 表示 字符串不能完全由大小写字母组成
        // (?![A-Z0-9]+$) 表示 字符串不能完全由大写字母和数字组成
        // (?![A-Z\W_]+$) 表示 字符串不能完全由大写字母和特殊字符组成
        // (?![a-z0-9]+$) 表示 字符串不能完全由小写字母和数字组成
        // (?![a-z\W_]+$) 表示 字符串不能完全由小写字母和特殊字符组成
        // (?![0-9\W_]+$) 表示 字符串不能完全由数字和特殊字符组成
        // [a-zA-Z0-9\W_]{8,} 表示 字符串应该匹配大小写字母、数字和特殊字符，至少匹配8次
        String regex = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![a-z0-9]+$)(?![A-Z\\W_]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,}$";
        return ReUtil.isMatch(regex, PWD);
    }

    /**
     * 验证密码并返回其SHA-256哈希值的十六进制字符串表示形式。
     *
     * @param password 要验证的密码字符串
     * @return 密码的SHA-256哈希值的十六进制字符串表示形式
     * @throws NoSuchAlgorithmException 如果无法找到指定的哈希算法（SHA-256）则抛出此异常
     */
    public static String PasswordVerification (String password) throws NoSuchAlgorithmException {
        // 计算输入密码的哈希值
        // 获取SHA-256哈希算法实例
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // 对密码进行哈希计算，并返回字节数组形式的哈希值
        byte[] hashBytes = md.digest(password.getBytes());
        // 将哈希值的字节数组转换为十六进制字符串并返回
        return bytesToHex(hashBytes);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param bytes 要转换的字节数组
     * @return 转换后的十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        // 创建一个StringBuilder对象用于构建十六进制字符串
        StringBuilder hexString = new StringBuilder();
        // 遍历字节数组
        for (byte b : bytes) {
            // 将字节转换为十六进制字符串
            String hex = Integer.toHexString(0xff & b);
            // 如果十六进制字符串的长度为1，则在前面添加一个'0'
            if (hex.length() == 1) {
                hexString.append('0');
            }
            // 将十六进制字符串追加到StringBuilder对象中
            hexString.append(hex);
        }
        // 返回转换后的十六进制字符串
        return hexString.toString();
    }
}
