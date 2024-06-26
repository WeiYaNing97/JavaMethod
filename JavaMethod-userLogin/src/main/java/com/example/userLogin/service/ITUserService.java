package com.example.userLogin.service;


import com.example.userLogin.domain.TUser;

import java.util.List;

/**
 * 用户登录信息表Service接口
 * 
 * @author ruoyi
 * @date 2024-06-16
 */
public interface ITUserService 
{
    /**
     * 查询用户登录信息表
     * 
     * @param id 用户登录信息表主键
     * @return 用户登录信息表
     */
    public TUser selectTUserById(String id);

    /**
     * 查询用户登录信息表列表
     * 
     * @param tUser 用户登录信息表
     * @return 用户登录信息表集合
     */
    public List<TUser> selectTUserList(TUser tUser);

    /**
     * 新增用户登录信息表
     * 
     * @param tUser 用户登录信息表
     * @return 结果
     */
    public int insertTUser(TUser tUser);

    /**
     * 修改用户登录信息表
     * 
     * @param tUser 用户登录信息表
     * @return 结果
     */
    public int updateTUser(TUser tUser);

    /**
     * 批量删除用户登录信息表
     * 
     * @param ids 需要删除的用户登录信息表主键集合
     * @return 结果
     */
    public int deleteTUserByIds(String[] ids);

    /**
     * 删除用户登录信息表信息
     * 
     * @param id 用户登录信息表主键
     * @return 结果
     */
    public int deleteTUserById(String id);

    TUser selectTUserByLoginName(String loginName);
}
