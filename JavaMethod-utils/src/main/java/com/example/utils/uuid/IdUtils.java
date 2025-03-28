package com.example.utils.uuid;

/**
 * ID生成器工具类
 * 
 * @author ruoyi
 */
public class IdUtils
{

    public static void main(String[] args) {
        while (true){
            String fastUUID = fastUUID();
            String fastSimpleUUID = fastSimpleUUID();
            System.err.println(fastUUID.length()==36);
            System.err.println(fastSimpleUUID.length()==32);
        }
    }
    /**
     * 获取随机UUID
     * 
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 随机UUID
     */
    public static String fastUUID()
    {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID    32位
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID()
    {
        return UUID.fastUUID().toString(true);
    }
}
