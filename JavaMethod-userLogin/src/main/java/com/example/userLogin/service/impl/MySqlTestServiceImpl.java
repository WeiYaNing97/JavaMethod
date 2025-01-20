package com.example.userLogin.service.impl;

import com.example.userLogin.domain.MySqlTest;
import com.example.userLogin.mapper.TMySqlTestMapper;
import com.example.userLogin.service.IMySqlTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户登录信息表Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-16
 */
@Service
public class MySqlTestServiceImpl implements IMySqlTestService
{
    @Autowired
    private TMySqlTestMapper tMySqlTestMapper;

    @Override
    public Integer insertMySqlTest(MySqlTest mySqlTest) {
        int i = tMySqlTestMapper.insertMySqlTest(mySqlTest);
        return i;
    }

    @Override
    public MySqlTest selectById(Integer integer) {
        MySqlTest mySqlTest = tMySqlTestMapper.selectById(integer);
        return mySqlTest;
    }

    @Override
    public List<MySqlTest> selectAll() {
        return tMySqlTestMapper.selectAll();
    }

    @Override
    public List<MySqlTest> selectByIds(int[] a) {
        return tMySqlTestMapper.selectByIds(a);
    }
}
