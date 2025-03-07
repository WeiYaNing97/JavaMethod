package com.example.user.mapper;

import com.example.user.entity.UserDetails;
import com.example.user.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    boolean add(Users users);

    List<UserDetails> viewUserDetails();

    Users getByUsername(String username);
}
