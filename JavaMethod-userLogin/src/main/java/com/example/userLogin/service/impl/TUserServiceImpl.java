package com.example.userLogin.service.impl;

import com.example.userLogin.domain.MySqlTest;
import com.example.userLogin.domain.TUser;
import com.example.userLogin.mapper.TMySqlTestMapper;
import com.example.userLogin.mapper.TUserMapper;
import com.example.userLogin.service.ITUserService;
import com.example.utils.uuid.IdUtils;
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
public class TUserServiceImpl implements ITUserService
{
    @Autowired
    private TUserMapper tUserMapper;

    /**
     * 查询用户登录信息表
     * 
     * @param id 用户登录信息表主键
     * @return 用户登录信息表
     */
    @Override
    public TUser selectTUserById(String id)
    {
        return tUserMapper.selectTUserById(id);
    }

    /**
     * 查询用户登录信息表列表
     * 
     * @param tUser 用户登录信息表
     * @return 用户登录信息表
     */
    @Override
    public List<TUser> selectTUserList(TUser tUser)
    {
        return tUserMapper.selectTUserList(tUser);
    }

    /**
     * 新增用户登录信息表
     * 
     * @param tUser 用户登录信息表
     * @return 结果
     */
    @Override
    public int insertTUser(TUser tUser)
    {
        tUser.setId(IdUtils.fastSimpleUUID());
        return tUserMapper.insertTUser(tUser);
    }

    /**
     * 修改用户登录信息表
     * 
     * @param tUser 用户登录信息表
     * @return 结果
     */
    @Override
    public int updateTUser(TUser tUser)
    {
        return tUserMapper.updateTUser(tUser);
    }

    /**
     * 批量删除用户登录信息表
     * 
     * @param ids 需要删除的用户登录信息表主键
     * @return 结果
     */
    @Override
    public int deleteTUserByIds(String[] ids)
    {
        return tUserMapper.deleteTUserByIds(ids);
    }

    /**
     * 删除用户登录信息表信息
     * 
     * @param id 用户登录信息表主键
     * @return 结果
     */
    @Override
    public int deleteTUserById(String id)
    {
        return tUserMapper.deleteTUserById(id);
    }

    @Override
    public TUser selectTUserByLoginName(String loginName) {
        TUser tUser = tUserMapper.selectTUserByLoginName(loginName);
        return tUser;
    }
}
