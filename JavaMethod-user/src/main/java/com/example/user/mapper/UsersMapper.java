package com.example.user.mapper;

import com.example.user.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
