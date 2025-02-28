package com.example.userLogin.controller;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringBufferTypeHandler extends BaseTypeHandler<StringBuffer> {

    /**
     * 设置PreparedStatement的非空参数
     *
     * @param ps        PreparedStatement对象
     * @param i         参数索引
     * @param parameter 要设置的参数，类型为StringBuffer
     * @param jdbcType  JDBC类型
     * @throws SQLException 如果在执行SQL时发生错误，抛出SQLException异常
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, StringBuffer parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    /**
     * 从ResultSet中获取可空结果的StringBuffer表示
     *
     * @param rs ResultSet对象
     * @param columnName 列名
     * @return 如果列值为null，则返回null；否则返回包含列值内容的StringBuffer对象
     * @throws SQLException 如果发生数据库访问错误，则抛出SQLException异常
     */
    @Override
    public StringBuffer getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        return str == null ? null : new StringBuffer(str);
    }

    /**
     * 从ResultSet中获取指定列的可空结果的StringBuffer表示。
     *
     * @param rs ResultSet对象，包含数据库查询结果
     * @param columnIndex 列的索引（从1开始）
     * @return 如果列值为null，则返回null；否则返回包含列值内容的StringBuffer对象
     * @throws SQLException 如果在执行数据库操作时发生错误，则抛出此异常
     */
    @Override
    public StringBuffer getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        return str == null ? null : new StringBuffer(str);
    }

    /**
     * 从CallableStatement中获取指定列的可空结果的StringBuffer表示。
     *
     * @param cs CallableStatement对象，包含存储过程的执行结果
     * @param columnIndex 列的索引（从1开始）
     * @return 如果列值为null，则返回null；否则返回包含列值内容的StringBuffer对象
     * @throws SQLException 如果在执行数据库操作时发生错误，则抛出此异常
     */
    @Override
    public StringBuffer getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        return str == null ? null : new StringBuffer(str);
    }
}