package com.example.util;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
    public static String isEmpty = "Is_Empty";

    /* ============================== 汉字 开始 ==============================*/
    /**
     * 根据正则表达式判断字符串中是否包含汉字
     *
     * @param str 待检查的字符串
     * @return 如果字符串中包含汉字，则返回true；否则返回false
     */
    public static boolean isContainChinese(String str) {
        // 定义汉字的正则表达式
        String regex = "[\u4e00-\u9fa5]";
        // 编译正则表达式，生成Pattern对象
        Pattern pattern = Pattern.compile(regex);
        // 使用Pattern对象创建Matcher对象，用于匹配字符串
        Matcher match = pattern.matcher(str);
        // 判断字符串中是否存在匹配项，即是否包含汉字
        return match.find();
    }
    /* ============================== 汉字 结束 ==============================*/
    /* ============================== 字母 开始 ==============================*/
    /**
     * @Description 该方法主要使用正则表达式来判断字符串中是否包含字母
     * @author fenggaopan 2015年7月21日 上午9:49:40
     * @param cardNum 待检验的原始卡号
     * @return 返回是否包含
     */
    public static boolean judgeContainsStr(String cardNum) {
        // 定义正则表达式，用于匹配包含字母的字符串
        String regex=".*[a-zA-Z]+.*";
        // 编译正则表达式并创建匹配器
        Matcher m = Pattern.compile(regex).matcher(cardNum);
        // 判断字符串是否匹配正则表达式，即是否包含字母
        return m.matches();
    }
    /**
     * 获取字符串开头字母部分
     *
     * @param str 待处理的字符串
     * @return 返回字符串开头字母部分
     */
    public static String getFirstLetters(String str) {
        // 创建一个StringBuilder对象，用于拼接字符串
        StringBuilder sb = new StringBuilder();
        // 遍历字符串的每个字符
        for (int i = 0; i < str.length(); i++) {
            // 获取当前字符
            char c = str.charAt(i);
            // 判断当前字符是否为字母
            if (Character.isLetter(c)) {
                // 如果是字母，则将其添加到StringBuilder中
                sb.append(c);
            } else {
                // 如果不是字母，则跳出循环
                break;
            }
        }
        // 将StringBuilder对象转换为字符串，并去除字符串首尾的空格后返回
        return sb.toString().trim();
    }
    /* ============================== 字母 结束 ==============================*/
    /* ============================== 数字 开始 ==============================*/
    /**
     * @Description 查询字符串中首个数字出现的位置
     * @param str 查询的字符串
     * @return 若存在，返回位置索引，否则返回-1；
     */
    public static int findFirstIndexNumberOfStr(String str){
        int i = -1;
        // 使用正则表达式匹配字符串中的数字
        Matcher matcher = Pattern.compile("[0-9]").matcher(str);
        // 如果找到了匹配项
        if(matcher.find()) {
            // 将找到的匹配项的起始位置赋值给i
            i = matcher.start();
        }
        // 返回i，即数字在字符串中的位置索引
        return i;
    }
    /**
     * @Description 判断字符串是否包含数字
     * @param str 待判断的字符串
     * @return 如果包含数字则返回true，否则返回false
     */
    public static boolean isNumeric(String str) {
        // 编译正则表达式，匹配包含数字的字符串
        Pattern pattern = Pattern.compile(".*[0-9].*");
        // 使用正则表达式匹配字符串，并判断是否匹配成功
        return pattern.matcher(str).matches();
    }
    /**
     * @Description 判断字符串中是否包含数字
     * @author charles
     * @createTime 2023/12/22 8:45
     * @desc
     * @param source 待判断的字符串
     * @return 如果包含数字则返回true，否则返回false
     */
    public static boolean containDigit(String source) {
        char ch;
        for(int i=0; i<source.length();i++){
            // 获取字符串中的每个字符
            ch = source.charAt(i);
            // 判断字符是否为数字
            if(ch >= '0' && ch <= '9') {
                // 如果字符是数字，则返回true
                return true;
            }
        }
        // 如果循环结束仍未找到数字，则返回false
        return false;
    }
    /**
     * @Description 判断字符串是否为全数字
     * @author charles
     * @createTime 2023/12/22 8:41
     * @desc
     * @param str
     * @return
     */
    public static boolean allIsNumeric(String str){
        //使用正则表达式判断字符串是否全由数字组成
        return str.matches("\\d+");
    }
    /**
     * 判断字符串集合元素是否都包含数字
     * @param stringList
     * @return
     */
    public static boolean thereAreNumbersInTheSet(List<String> stringList) {
        for (String string:stringList){
            // 遍历字符串集合中的每个字符串
            /*如果不包含数字 则返回false */
            if (!containDigit(string)){
                // 如果当前字符串不包含数字，则返回false
                return false;
            }
        }
        // 如果所有字符串都包含数字，则返回true
        return true;
    }
    /* 判断字符串元素是否包含数字*/
    public static boolean thereAreNumbers(String string) {
        /*如果不包含数字 则返回false */
        return containDigit(string);
    }
    /**
     * 判断字符串元素是否为纯数字
     * @param str
     * @return
     */
    public static boolean determineWhetherAStringIsAPureNumber(String str) {
        if (str.matches("\\d+")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 字符串截取 数字字符串
     * @return
     */
    public static String StringTruncationMatcherValue(String args) {
        // 编译正则表达式，匹配0-9的数字
        Pattern pattern = Pattern.compile("[0-9]");
        // 使用正则表达式对输入字符串进行匹配
        Matcher matcher = pattern.matcher(args);
        // 初始化一个空字符串用于保存匹配到的数字
        String str = "";
        // 循环查找匹配项
        while (matcher.find()) {
            // 将匹配到的数字添加到str中
            str += matcher.group();
        }
        // 返回截取到的数字字符串
        return str;
    }
    /**
     * 要在Java中获取字符串开头的数字部分，可以使用正则表达式。
     */
    public static String getNumberPartAtBeginning(String input) {
        // 编译正则表达式，匹配字符串开头的数字部分
        Pattern pattern = Pattern.compile("^\\d+");
        // 创建匹配器对象
        Matcher matcher = pattern.matcher(input);
        // 如果匹配器找到匹配项
        if (matcher.find()) {
            // 返回匹配到的数字部分
            return matcher.group();
        }
        // 如果没有找到匹配项，则返回空字符串
        return "";
    }
    /**
     * 要在Java中获取字符串第一个数值数字部分，可以使用正则表达式。 */
    public static String getEncodingID(String input) {
        String[] split = input.split(",");
        String str = split[0];
        if (str.startsWith("\'") || str.startsWith("\"")){
            str = str.substring(1,str.length());
        }
        if (str.endsWith("\'") || str.endsWith("\"")){
            str = str.substring(0,str.length()-1);
        }
        return str;
    }
    /* ============================== 数字 结束 ==============================*/
    /* ============================== 实体类 开始 ==============================*/
    /**
     * @Description 实体类转化为 字符串
     * @param object
     * @return
     */
    public static String getEntityClassString(Object object) {
        //获取实体类 返回的是一个数组 数组的数据就是实体类中的字段
        Field[] fields = object.getClass().getDeclaredFields();
        String sumString = "";
        for (int i = 0; i < fields.length; i++) {
            //有的字段是用private修饰的 将他设置为可读
            fields[i].setAccessible(true);
            try {
                // 输出属性名和属性值
                String nameKey = fields[i].getName();
                Object value = fields[i].get(object);
                // 跳过serialVersionUID字段
                if (nameKey.equalsIgnoreCase("serialVersionUID")){
                    continue;
                }
                // 拼接字段名和字段值到sumString中
                sumString = sumString  + nameKey + ":\"" + (value == null?isEmpty:value)+"\",";
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // 去除最后一个逗号并返回结果
        return sumString.substring(0,sumString.length()-1);
    }
    /* ============================== 实体类 结束 ==============================*/
    /* ============================== 字符串、文章 开始 ==============================*/
    public static String[] splittingStringsBasedOnMultipleKeywords(String[] keywords,String input) {
        StringBuilder regexBuilder = new StringBuilder();
        for (int i = 0; i < keywords.length; i++) {
            if (i > 0) {
                regexBuilder.append("|");
            }
            regexBuilder.append(Pattern.quote(keywords[i]));
        }
        String regex = regexBuilder.toString();
        String[] parts = input.split(regex);
        /*for (String part : parts) {
            System.out.println(part);
        }*/
        return parts;
    }
    /**
     * @Description 文章字段 换行符 由"\r"  "\n"  "\r\n" 规范成 "\r\n"   并且 连续多空格转化为单空格
     * @param article
     * @return
     */
    public static String trimString(String article) {
        // 读取文章并按行存入数组
        String[] lines = article.split("\\r?\\n|\\r"); // 使用正则表达式按照任意一种换行符进行分割
        /*字符串数组以\r\n拼接成字符串*/
        for (int num = 0 ;num<lines.length;num++){
            /*  为了保存 ^对应命令的位置 因此不能够进行 多空格转变为单空格  */
            if (lines[num].indexOf("^")!=-1 && lines[num].indexOf("^down") ==-1){
                continue;
            }
            lines[num] = repaceWhiteSapce(lines[num]);
        }
        return String.join("\r\n", lines);
    }
    /**
     * @Description 多个连续空格 改为 多个单空格
     * @author charles
     * @createTime 2023/12/22 8:44
     * @desc
     * @param original
     * @return
     */
    public static String repaceWhiteSapce(String original){
        StringBuilder sb = new StringBuilder();
        // 标记是否是第一个空格
        boolean isFirstSpace = false;
        // 如果考虑开头和结尾有空格的情形，可以取消注释以下行
        // original = original.trim();
        char c;
        for(int i = 0; i < original.length(); i++){
            c = original.charAt(i);
            // 遇到空格字符时,先判断是不是第一个空格字符
            if(c == ' ' || c == '\t'){
                if(!isFirstSpace)
                {
                    // 若是第一个空格字符，则添加到StringBuilder中
                    sb.append(c);
                    // 标记为已添加过空格字符
                    isFirstSpace = true;
                }
            }
            // 遇到非空格字符时
            else{
                // 将非空格字符添加到StringBuilder中
                sb.append(c);
                // 标记为非空格字符
                isFirstSpace = false;
            }
        }
        // 返回转换后的字符串
        return sb.toString();
    }
    /**
     * @Description 多个连续的相同字符串，改为单个字符串
     * @desc
     * @param input	 原始字符串      例如："这是  一个  包含EEEEEE多个           连续空格  的字符串";
     * @param string 连续的相同字符串例如：“EEE”；
     * @return  修改后的字符串       例如：“这是  一个  包含EEE多个           连续空格  的字符串”；
     */
    public static String replaceMultipleStringsWithSingleString(String input,String string) {
        return input.replaceAll(string+"+", string);
    }
    /**
     * @Description   去掉字符串首尾的非数字非字母部分。
     * removeNonAlphanumeric方法使用正则表达式^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$
     * @author charles
     * @createTime 2023/12/22 8:48
     * @desc
     * @param str
     * @return
     */
    public static String removeNonAlphanumeric(String str) {
        return str.replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", "");
    }
    /**
     * @Description 子字符串在父字符串中出现的所有位置
     * @author charles
     * @createTime 2023/12/21 19:20
     * @desc
     * @param parent 父字符串
     * @param child 子字符串
     * @return 子字符串在父字符串中出现的所有位置列表
     */
    public static List<Integer> getSubstringPositions(String parent, String child) {
        // 创建一个存储位置的列表
        List<Integer> positions = new ArrayList<>();
        // 查找子字符串在父字符串中首次出现的位置
        int index = parent.indexOf(child);
        // 当子字符串在父字符串中存在时
        while (index != -1) {
            // 将子字符串出现的位置添加到列表中
            positions.add(index);
            // 从上一个出现位置的后一个字符开始，继续查找子字符串的下一个出现位置
            index = parent.indexOf(child, index + 1);
        }
        // 返回子字符串在父字符串中出现的所有位置列表
        return positions;
    }
    /**
     * 根据字符串 根据字符串（忽略大小写）分割为字符串数组
     *
     * "(?i)"表示忽略大小写
     *
     * @param string  被分割字符串
     * @param str   分割关键词
     * @return
     */
    public static String[] splitIgnoreCase(String string,String str){

        return string.split("(?i)"+str);

    }
    /**
     * 判断一个字符串是否包含另一个字符串(忽略大小写)
     * @param primary
     * @param keywords
     * @return
     */
    public static boolean containIgnoreCase(String primary,String keywords) {
        return primary.toUpperCase().indexOf(keywords.toUpperCase())!=-1;
    }
    /* ============================== 字符串、文章 结束 ==============================*/
    /* ============================== 时间 开始 ==============================*/
    /**
     * @Description 获取时间 格式自定
     * @author charles
     * @createTime 2023/12/22 8:42
     *
     * @desc
     * Java时间设为二十四小时制和十二小时制的区别：
     *     1) 二十四小时制： “yyyy-MM-dd HH:mm:ss”
     *     2)十二小时制： “"yyyy-MM-dd hh:mm:ss"”
     *     例如：type  =  "yyyy-MM-dd hh:mm:ss"
     *
     * @return
     */
    public static String getDate(String type){
        String time = new SimpleDateFormat(type).format(new Date());
        return time;
    }
    /**
     * @Description 根据Date 获取时间 yyyy-MM-dd hh:mm:ss 格式字符串*/
    public static String getDatetoString(Date dateTime){
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateTime);
        return time;
    }
    /**
     * @Description 根据yyyy-MM-dd hh:mm:ss 格式字符串 获取时间Date*/
    public static Date getStringtoData(String time){
        Date parse = null;
        try {
            parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(time);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
    /**
     * @Description 时间排序 由大到小
     * @author charles
     * @createTime 2023/12/22 8:43
     * @desc
     * @param dateList
     * @return
     */
    public static List<Date> sortDate(List<Date> dateList){
        dateList.sort((a1,a2) ->{
            return a1.compareTo(a2);
        });
        return dateList;
    }
    /**
     * @Description 时间由大到小排序
     * @author charles
     * @createTime 2023/12/22 8:45
     * @desc
     * @param dateList
     * @return
     */
    public static List<Date> sort(List<Date> dateList){
        dateList.sort((a1,a2) ->{
            return a1.compareTo(a2);
        });
        return dateList;
    }
    /* ============================== 时间 结束 ==============================*/
    /* ============================== 数组 开始 ==============================*/
    /**
     * @Description 删除数组空文本返回新数组
     * @author charles
     * @createTime 2023/12/22 8:45
     * @desc
     * @param strArray
     * @return
     */
    public static String[] removeArrayEmptyTextBackNewArray(String[] strArray) {
        // 将字符串数组转换为列表
        List<String> strList= Arrays.asList(strArray);
        // 创建新的字符串列表
        List<String> strListNew=new ArrayList<>();
        // 遍历原字符串列表
        for (int i = 0; i <strList.size(); i++) {
            // 如果字符串不为空且不等于空字符串
            if (strList.get(i)!=null&&!strList.get(i).equals("")){
                // 将非空字符串添加到新列表中
                strListNew.add(strList.get(i));
            }
        }
        // 将新列表转换为数组
        String[] strNewArray = strListNew.toArray(new String[strListNew.size()]);
        // 返回新数组
        return   strNewArray;
    }
    /**
     * 数组去掉最后一个元素
     * 要去掉Java数组的最后一个元素，可以创建一个新的数组，并将原数组中除最后一个元素之外的所有元素复制到新数组中。
     * @param array
     * @return
     */
    public static String[] RemoveLastElement(String[] array) {
        // 原始数组 array;
        // 创建一个新数组，长度为原数组长度减1
        String[] newArray = new String[array.length - 1];
        // 将原数组中除最后一个元素外的所有元素复制到新数组中
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    /* ============================== 数组 结束 ==============================*/
    /* ============================== 集合 开始 ==============================*/
    /**
     * @Description  查看字符串集合A中存在，但字符串集合B中不存在的部分
     * @author charles
     * @createTime 2024/1/4 10:48
     * @desc
     * @param listA
     * @param listB
     * @return
     */
    public static List<String> findDifference(List<String> listA, List<String> listB) {
        // 将字符串集合A转换为HashSet，以便进行高效的查找和删除操作
        HashSet<String> setA = new HashSet<>(listA);
        // 将字符串集合B也转换为HashSet
        HashSet<String> setB = new HashSet<>(listB);
        // 从setA中移除与setB中相同的元素
        setA.removeAll(setB);
        // 将剩余的setA转换为ArrayList并返回
        return new ArrayList<>(setA);
    }
    /**
     * 判断集合是否为空， 为空返回true
     * 集合判空
     * @param collection
     * @return
     */
    public static boolean isCollectionEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty() || collection.size() == 0;
    }
    /**
     * 判断Map集合是否为空， 为空返回true
     * @param map
     * @return
     */
    public static boolean isMapEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty() || map.size() == 0;
    }
    /* ============================== 集合 结束 ==============================*/
    /* ============================== Double 开始 ==============================*/
    /**
     * 字符串 转化为  Double
     * @param str
     * @return
     */
    public static double stringToDouble(String str) throws NumberFormatException {
        return Double.parseDouble(str);
    }
    /**
     * 字符串截取double值
     *
     * @param input 输入的字符串
     * @return 截取到的double值列表
     */
    public static List<Double> StringTruncationDoubleValue(String input) {
        List<Double> doubleList = new ArrayList<>();

        // 编译正则表达式，匹配整数或小数
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(input);

        // 查找匹配项
        while (matcher.find()) {
            // 将匹配到的字符串转换为double值
            double value = Double.parseDouble(matcher.group());
            // 将double值添加到列表中
            doubleList.add(value);
        }

        // 返回截取到的double值列表
        return doubleList;
    }
    /**
     * 判断 double值 是否在阈值内
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }
    /* ============================== Double 结束 ==============================*/
    /* ============================== Integer 开始 ==============================*/
    /**
     * 字符串截取 Integer 值
     * @return 字符串中的整数列表
     */
    public static List<Integer> extractInts(String str) {
        // 创建一个空的整数列表
        List<Integer> intList = new ArrayList<>();
        // 编译一个正则表达式，匹配一个或多个数字
        Pattern pattern = Pattern.compile("\\d+");
        // 使用正则表达式对字符串进行匹配
        Matcher matcher = pattern.matcher(str);
        // 循环查找匹配项
        while (matcher.find()) {
            // 将匹配到的数字字符串转换为整数，并添加到列表中
            intList.add(Integer.parseInt(matcher.group()));
        }
        // 返回整数列表
        return intList;
    }
    /* ============================== Integer 结束 ==============================*/
    /* ============================== IP 开始 ==============================*/
    /**
     * @Description 判断内容是否存在 IP特征
     * @desc
     * @param input 待判断的内容
     * @return 如果存在IP特征，则返回true；否则返回false
     */
    public static boolean containsIPAddress(String input) {
        // 定义IP地址的正则表达式
        String ipPattern = "(\\d{1,3}\\.){3}\\d{1,3}";
        // 编译正则表达式，生成Pattern对象
        Pattern pattern = Pattern.compile(ipPattern);
        // 使用Pattern对象创建Matcher对象
        Matcher matcher = pattern.matcher(input);
        // 使用Matcher对象的find()方法判断是否存在IP特征
        return matcher.find();
    }
    /* ============================== IP 结束 ==============================*/

}
