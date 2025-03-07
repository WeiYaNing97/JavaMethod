package com.example.utils.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;

@Component
public class PasswordUtil {

    /**
     * 对密码进行加密并生成哈希密码
     *
     * @param password 用户输入的密码
     * @return 组合后的哈希密码和盐值字符串
     * @throws NoSuchAlgorithmException 如果指定的哈希算法不可用，则抛出此异常
     */
    public String encryptedHashPassword(String password) throws NoSuchAlgorithmException {
        // 生成盐值
        String salt = generateSalt();
        // 计算带盐的哈希值
        String hashedPassword = getSecurePassword(password, salt);
        // 将哈希密码与盐值组合成新字符串
        return combination(hashedPassword, salt);
    }


    /**
     * 验证密码是否正确
     *
     * @param password       原始密码
     * @param hashedPassword 组合后的密码（哈希密码+盐值）
     * @return 如果密码正确返回true，否则返回false
     * @throws NoSuchAlgorithmException 如果指定的哈希算法不可用，则抛出此异常
     */
    public boolean resolveHashPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        // 调用verifyPassword函数，传入组合密码字符串，获取包含密码和盐的HashMap
        HashMap<String, String> stringStringHashMap = verifyPassword(hashedPassword);
        // 调用verifyPassword函数，传入原始密码、提取出的密码和盐，进行密码验证
        return verifyPassword(password,
                stringStringHashMap.get("password"), stringStringHashMap.get("salt"));
    }


    /**
     * 将哈希密码与盐值进行特定规则的组合并返回新字符串
     *
     * @param hashedPassword 哈希密码字符串
     * @param salt           盐值字符串
     * @return               组合后的新字符串
     */
    private String combination(String hashedPassword, String salt){
        // 获取哈希密码的前8个字符作为起始部分
        String startPassword = hashedPassword.substring(0, 8);
        // 获取哈希密码的剩余部分作为结束部分
        String endPassword = hashedPassword.substring(8);
        // 获取盐值的前8个字符作为起始部分
        String startSalt = salt.substring(0, 8);
        // 获取盐值的剩余部分作为结束部分
        String endSalt = salt.substring(8);
        // 将哈希密码的起始部分、盐值的起始部分、哈希密码的结束部分和盐值的结束部分组合成新字符串并返回
        return startPassword + startSalt + endPassword + endSalt;
    }

    /**
     * 验证组合密码并提取出密码和盐
     *
     * @param combinationPassword 组合密码字符串，格式为密码前8位 + 盐前8位 + 密码剩余部分 + 盐剩余部分
     * @return 存储了密码和盐的HashMap，键分别为"password"和"salt"
     */
    private HashMap<String,String> verifyPassword(String combinationPassword) {
        // 获取组合密码的前8个字符作为密码的起始部分
        String startPassword = combinationPassword.substring(0, 8);
        // 获取组合密码的第9到第16个字符作为盐的起始部分
        String startSalt = combinationPassword.substring(8, 16);
        // 获取组合密码的第17到第72个字符作为密码的结束部分
        String endPassword = combinationPassword.substring(16, 72);
        // 获取组合密码的剩余部分作为盐的结束部分
        String endSalt = combinationPassword.substring(72);
        // 创建一个HashMap对象用于存储密码和盐
        HashMap<String,String> map = new HashMap<>();
        // 将密码的起始部分和结束部分拼接后存入HashMap中，键为"password"
        map.put("password", startPassword + endPassword);
        // 将盐的起始部分和结束部分拼接后存入HashMap中，键为"salt"
        map.put("salt", startSalt + endSalt);
        // 返回存储了密码和盐的HashMap
        return map;
    }

    /**
     * 生成盐值
     *
     * @return 使用Base64编码后的盐值字符串
     */
    // 生成盐值
    private String generateSalt() {
        // 创建一个SecureRandom实例
        SecureRandom random = new SecureRandom();
        // 创建一个16字节长度的字节数组
        byte[] salt = new byte[16];
        // 使用SecureRandom实例填充盐值数组
        random.nextBytes(salt);
        // 使用Base64编码将盐值转换为字符串，以便于存储
        return Base64.getEncoder().encodeToString(salt); // 使用Base64编码以便于存储
    }

    /**
     * 计算带盐的哈希值
     *
     * @param password 密码字符串
     * @param salt     经过Base64编码的盐值字符串
     * @return         哈希值对应的十六进制字符串
     * @throws NoSuchAlgorithmException 如果指定的哈希算法不可用，则抛出此异常
     */
    // 计算带盐的哈希值
    private String getSecurePassword(String password, String salt) throws NoSuchAlgorithmException {
        // 可根据需求选择不同的哈希算法
        String algorithm = "SHA-256";
        // 获取指定哈希算法的 MessageDigest 实例
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        // 将salt解码为字节数组
        digest.update(Base64.getDecoder().decode(salt)); // 将salt解码为字节数组
        // 计算哈希值
        byte[] hash = digest.digest(password.getBytes());
        // 创建一个StringBuilder对象，用于拼接生成的十六进制字符串
        StringBuilder hexString = new StringBuilder();
        // 遍历哈希值字节数组
        for (byte b : hash) {
            // 将字节b转换为十六进制字符串
            String hex = Integer.toHexString(0xff & b);
            // 如果转换后的十六进制字符串长度为1，则在前面补0
            if(hex.length() == 1) hexString.append('0');
            // 将转换后的十六进制字符串拼接到hexString中
            hexString.append(hex);
        }
        // 返回拼接后的十六进制字符串
        return hexString.toString();
    }

    /**
     * 验证密码
     *
     * @param originalPassword 原始密码
     * @param storedPasswordHash 存储的密码哈希值
     * @param salt             盐值
     * @return 如果输入的密码与存储的密码哈希值匹配，则返回true，否则返回false
     * @throws NoSuchAlgorithmException 如果指定的哈希算法不可用，则抛出此异常
     */
    // 验证密码
    private boolean verifyPassword(String originalPassword, String storedPasswordHash, String salt) throws NoSuchAlgorithmException {
        // 计算输入密码的哈希值
        String hashOfInput = getSecurePassword(originalPassword, salt);
        // 比较输入密码的哈希值与存储的哈希值是否相等
        return hashOfInput.equals(storedPasswordHash);
    }

}