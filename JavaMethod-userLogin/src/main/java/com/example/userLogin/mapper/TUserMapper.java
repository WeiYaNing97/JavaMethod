package com.example.userLogin.mapper;


import com.example.userLogin.domain.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户登录信息表Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-16
 */
@Mapper
public interface TUserMapper 
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
     * 删除用户登录信息表
     * 
     * @param id 用户登录信息表主键
     * @return 结果
     */
    public int deleteTUserById(String id);

    /**
     * 批量删除用户登录信息表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTUserByIds(String[] ids);

    TUser selectTUserByLoginName(String loginName);
}
