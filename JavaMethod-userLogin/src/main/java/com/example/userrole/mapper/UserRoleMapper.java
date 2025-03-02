package com.example.userrole.mapper;

import com.example.userrole.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserRole> listByRoleIds(List<Integer> roleIds);

    int deleteByUserId(Integer userId);
}
