package com.example.userLogin.mapper;


import com.example.userLogin.domain.MySqlTest;
import com.example.userLogin.domain.TUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户登录信息表Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-16
 */
@Mapper
public interface TMySqlTestMapper
{

    int insertMySqlTest(MySqlTest mySqlTest);



    MySqlTest selectById(Integer id);

    List<MySqlTest> selectAll();

    List<MySqlTest> selectByIds(int[] ids);
}
