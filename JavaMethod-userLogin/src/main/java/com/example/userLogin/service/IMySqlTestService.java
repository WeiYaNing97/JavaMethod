package com.example.userLogin.service;


import com.example.userLogin.domain.MySqlTest;
import com.example.userLogin.domain.TUser;

import java.util.List;

/**
 * 用户登录信息表Service接口
 * 
 * @author ruoyi
 * @date 2024-06-16
 */
public interface IMySqlTestService {

    Integer insertMySqlTest(MySqlTest mySqlTest);

    MySqlTest selectById(Integer integer);

    List<MySqlTest> selectAll();

    List<MySqlTest> selectByIds(int[] a);
}
