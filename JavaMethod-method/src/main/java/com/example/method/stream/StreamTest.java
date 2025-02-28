package com.example.method.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 流式编程测试类
 */
public class StreamTest {


    public static void main(String[] args) {
        getEntityList();/* 获取实体类集合 用于模拟实体类数据 */
        System.out.println("-------------------------------------------------");
        forEach();/* 遍历 */
        System.out.println("-------------------------------------------------");
        filter();/* 过滤 */
        System.out.println("-------------------------------------------------");
        map();/* 映射 */
        System.out.println("-------------------------------------------------");
        sorted();/* 排序 */
        System.out.println("-------------------------------------------------");
        distinct();/* 去重 */
        System.out.println("-------------------------------------------------");
        reduce();/* 规约 */
        System.out.println("-----------------------------------------------");
        match();/* 匹配 */
        System.out.println("-----------------------------------------------");
    }

    /**
     * 获取实体列表
     *
     * @return 包含StreamEntity对象的列表
     */
    public static List<StreamEntity> getEntityList() {
        // 创建一个空的ArrayList，用于存储StreamEntity对象
        List<StreamEntity> entityList = new ArrayList<>();
        // 循环10次，从1到10
        for (int i = 1; i <= 10; i++) {
            // 创建一个新的StreamEntity对象，并设置其属性，然后添加到entityList列表中
            entityList.add(new StreamEntity(i,"姓名:"+i+"先生",i*i*i,""));
        }
        // 返回包含StreamEntity对象的列表
        return entityList;
    }

    /**
     * 遍历并打印实体列表及其用户名
     *
     * <p>
     * 该方法首先通过调用 {@link #getEntityList()} 方法获取一个 {@link StreamEntity} 类型的列表，
     * 然后使用 {@link List#forEach(Consumer)} 方法两次遍历列表。
     * 第一次遍历会打印整个实体对象，第二次遍历仅打印实体对象的用户名。
     * </p>
     */
    public static void forEach() {
        // 调用getEntityList()方法获取StreamEntity类型的列表
        List<StreamEntity> entityList = getEntityList();

        // 第一次遍历列表，打印整个实体对象
        entityList.forEach(System.out::println);

        // 第二次遍历列表，仅打印实体对象的用户名
        entityList.forEach(x -> System.out.println(x.getUserName()));
    }


    /**
     * 过滤StreamEntity列表并打印结果
     *
     * <p>
     * 此方法首先获取一个StreamEntity对象的列表，然后通过Java 8的Stream API进行过滤操作，
     * 并将过滤结果打印到控制台。具体过滤操作包括：
     * <ul>
     *     <li>过滤出id为偶数的StreamEntity对象并打印</li>
     *     <li>过滤出id为奇数的StreamEntity对象并打印</li>
     *     <li>过滤出id大于5的StreamEntity对象并打印</li>
     * </ul>
     * </p>
     *
     * @return 无返回值，此方法主要用于打印过滤后的结果
     */
    public static void filter() {
        List<StreamEntity> entityList = getEntityList();
        // 过滤出 id 偶数
        List<StreamEntity> evenNums = entityList.stream().filter(x -> x.getId() % 2 == 0).collect(Collectors.toList());
        evenNums.stream().forEach(System.out::println);
        // 过滤出 id 奇数
        List<StreamEntity> oddNums = entityList.stream().filter(x -> x.getId() % 2 != 0).collect(Collectors.toList());
        oddNums.stream().forEach(System.out::println);
        // 过滤出 id 大于5的数
        List<StreamEntity> gtFiveNums = entityList.stream().filter(x -> x.getId() > 5).collect(Collectors.toList());
        gtFiveNums.stream().forEach(System.out::println);
    }

    /**
     * 遍历实体列表，并分别打印每个实体的ID和用户名
     *
     * <p>
     * 该方法首先通过调用{@link #getEntityList()}方法获取一个StreamEntity类型的列表，
     * 然后使用Java 8的Stream API的map方法将每个实体对象映射为其ID和用户名，
     * 最后使用forEach方法将映射结果打印到控制台。
     * </p>
     *
     * @return 无返回值，此方法是一个void方法，主要用于打印操作
     */
    public static void map() {
        // 获取实体列表
        List<StreamEntity> entityList = getEntityList();

        // 遍历实体列表，将每个实体的ID映射出来并打印
        entityList.stream().map(x -> x.getId()).forEach(System.out::println);

        // 遍历实体列表，将每个实体的用户名映射出来并打印
        // 遍历实体列表，并分别打印每个实体的ID和用户名
        entityList.stream().map(x -> x.getUserName()).forEach(System.out::println);
    }

    /**
     * 对实体列表中的ID进行排序并打印
     *
     * <p>该方法首先获取一个StreamEntity类型的实体列表，然后将其ID属性提取出来形成一个Integer类型的流，
     * 接着对提取出的ID进行排序，并分别打印出排序前后的ID。</p>
     *
     * @return 无返回值，该方法主要用于打印操作
     */
    public static void sorted() {
        // 获取实体列表
        List<StreamEntity> entityList = getEntityList();

        // 反转实体列表的顺序
        List<StreamEntity> entitys = new ArrayList<>();
        for (int i=entityList.size()-1; i>=0; i--) {
            entitys.add(entityList.get(i));
        }

        // 将ID流转换为List
        List<Integer> collect = entitys.stream().map(x -> x.getId()).collect(Collectors.toList());

        // 打印排序前的ID
        collect.stream().forEach(System.out::println);

        // 对List中的ID进行排序，并转换为流
        Stream<Integer> sortedStream = collect.stream().sorted();

        // 打印排序后的ID
        sortedStream.forEach(System.out::println);

    }

    /**
     * 演示从实体列表中获取并打印所有用户名，并去除重复项后再次打印
     *
     * <p>
     * 该方法首先通过调用{@link #getEntityList()}方法获取一个StreamEntity类型的列表，
     * 然后将相同的列表再次添加到该列表中，以模拟存在重复项的情况。
     * 接着，它使用Java 8的Stream API的map方法将列表中的每个实体映射为其用户名，
     * 并将结果打印到控制台。之后，它使用distinct方法去除用户名列表中的重复项，
     * 并再次将结果打印到控制台。
     * </p>
     *
     * @return 无返回值，该方法主要用于演示和打印操作
     */
    public static void distinct() {
        // 获取实体列表
        List<StreamEntity> entityList = getEntityList();

        // 模拟存在重复项的情况，将相同的列表再次添加到该列表中
        entityList.addAll(getEntityList());

        // 将列表中的每个实体映射为其用户名，并将结果打印到控制台
        // 获取所有用户名
        entityList.stream().map(x -> x.getUserName()).forEach(System.out::println);

        // 去除用户名列表中的重复项，并再次将结果打印到控制台
        // 去除重复项
        entityList.stream().distinct().forEach(System.out::println);
    }

    /**
     * 静态方法，用于对实体列表进行操作，包括求和、求最大值以及使用mapToInt和max求最大值。
     *
     * <p>首先，获取实体列表，然后使用Java 8的Stream API对列表中的实体进行处理。</p>
     *
     * <p>求和操作将实体列表中的ID属性提取出来，并计算它们的和。如果和大于0，则打印和。</p>
     *
     * <p>求最大值操作同样将实体列表中的ID属性提取出来，并找出最大值。如果最大值大于0，则打印最大值。</p>
     *
     * <p>另外，使用mapToInt方法将实体列表中的用户密码转换为IntStream，并使用max方法找出最大值。如果流为空，则返回Integer.MIN_VALUE或其他默认值。最后将最大值打印到控制台。</p>
     *
     * @return 无返回值，该方法主要用于打印操作。
     */
    public static void reduce() {
        // 获取实体列表
        List<StreamEntity> entityList = getEntityList();

        // 求和
        /*Optional<Integer> sum = entityList.stream().map(x -> x.getId()).reduce((x, y) -> x + y);*/
        Optional<Integer> sum = entityList.stream().map(x -> x.getId()).reduce(Integer::sum);
        // 因为 reduce 可能返回 Optional（如果没有元素），所以我们需要检查
        // 如果有最大值，则打印
        sum.filter(integer -> 0 < integer).ifPresent(System.out::println);

        // 求最大值
        Optional<Integer> max = entityList.stream().map(x -> x.getId()).reduce(Integer::max);
        max.filter(integer -> 0 < integer).ifPresent(System.out::println);

        // 更简洁的方式（使用 mapToInt 转换为 IntStream）
        int maxValue = entityList.stream()
                .map(streamEntity -> streamEntity.getUserPassword())
                // 将用户密码转换为 IntStream
                .mapToInt(Integer::intValue) // 或者直接使用 IntStream.of(numbers.toArray())
                // 找出最大值
                .max()
                .orElse(Integer.MIN_VALUE); // 如果流为空，则返回 Integer.MIN_VALUE 或其他默认值

        System.out.println("Max value using mapToInt and max: " + maxValue);

    }

    /**
     * 使用Stream API的匹配方法对实体列表进行匹配操作，并输出结果。
     *
     * <p>
     * 该方法首先通过调用{@link #getEntityList()}方法获取一个StreamEntity类型的列表，
     * 并将相同的列表再次添加到该列表中，以模拟存在重复项的情况。
     * 然后使用Stream API的匹配方法anyMatch、allMatch、noneMatch对列表中的元素进行匹配，
     * 并打印匹配结果。
     * </p>
     *
     * <p>
     * 具体匹配操作包括：
     * <ul>
     * <li>判断列表中是否存在用户名为"姓名:1先生"的实体（使用anyMatch方法）</li>
     * <li>判断列表中所有实体的Match属性是否为空字符串（使用allMatch方法）</li>
     * <li>判断列表中所有实体的用户名是否都为"姓名:1先生"（使用allMatch方法）</li>
     * <li>判断列表中是否不存在用户名为"姓名:1先生"的实体（使用noneMatch方法）</li>
     * </ul>
     * </p>
     *
     * @return 无返回值，该方法主要用于演示和打印操作
     */
    public static void match() {
        // 获取实体列表
        List<StreamEntity> entityList = getEntityList();
        entityList.addAll(getEntityList());

        // 判断列表中是否存在用户名为"姓名:1先生"的实体（使用anyMatch方法）
        boolean hasA = entityList.stream().anyMatch(s -> s.getUserName().equals("姓名:1先生"));
        System.out.println(hasA);

        // 判断列表中所有实体的Match属性是否为空字符串（使用allMatch方法）
        // allMatchA
        boolean allMatchA = entityList.stream().allMatch(s -> s.getMatch().equals(""));
        System.out.println(allMatchA);

        // 判断列表中所有实体的用户名是否都为"姓名:1先生"（使用allMatch方法）
        // allMatchB
        boolean allMatchB = entityList.stream().allMatch(s -> s.getUserName().equals("姓名:1先生"));
        System.out.println(allMatchB);

        // 判断列表中是否不存在用户名为"姓名:1先生"的实体（使用noneMatch方法）
        // noA
        boolean noA = entityList.stream().noneMatch(s -> s.getUserName().equals("姓名:1先生"));
        System.out.println(noA);
    }
}
