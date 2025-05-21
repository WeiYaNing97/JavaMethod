package com.example.utils.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.reflect.Field;
import java.util.List;

public class MybatisPlusUtilt<T> {

    public QueryWrapper<T> getByPojo(T t) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        // 获取对象的所有字段
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field:fields){
            // 设置字段可访问
            field.setAccessible(true);
            try {
                // 获取字段的值
                Object fieldValue = field.get(t);
                if (fieldValue != null) {
                    // 获取数据库字段名
                    String columnName = getColumnName(field);
                    if (columnName != null) {
                        // 将字段名和值添加到查询包装器中
                        queryWrapper.eq(columnName, fieldValue);
                    }
                } else {
                    // 如果字段值为空，则不添加到查询条件中
                }
            } catch (IllegalAccessException e) {
                // 打印非法访问异常
                e.printStackTrace();
            }
        }
        // 执行查询并返回结果
        return queryWrapper;
    }

    /**
     * 获取数据库字段名
     *
     * @param field Java字段对象
     * @return 数据库字段名
     */
    // 辅助方法：获取数据库字段名
    private String getColumnName(Field field) {
        // 检查是否有 @TableField 注解
        // TableField 注解用于指定数据库中的字段名
        TableField tableField = field.getAnnotation(TableField.class);
        if (tableField != null) {
            // 如果存在 @TableField 注解，则返回注解中定义的字段名
            // 返回注解中定义的字段名
            return tableField.value(); // 返回注解中定义的字段名
        }

        // 检查是否有 @TableId 注解
        // TableId 注解用于指定数据库中的主键字段
        TableId tableId = field.getAnnotation(TableId.class);
        if (tableId != null) {
            // 如果存在 @TableId 注解，则返回注解中定义的字段名
            // 返回注解中定义的字段名
            return tableId.value(); // 返回注解中定义的字段名
        }

        // 如果没有注解，默认字段名与 Java 字段名一致
        // 如果字段上没有使用 @TableField 或 @TableId 注解，则默认使用 Java 字段名作为数据库字段名
        // 返回 Java 字段名
        return field.getName();
    }
}
