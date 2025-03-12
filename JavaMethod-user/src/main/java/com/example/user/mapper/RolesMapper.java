package com.example.user.mapper;

import com.example.user.entity.RolePermissionVO;
import com.example.user.entity.Roles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Mapper
public interface RolesMapper extends BaseMapper<Roles> {

    int add(Roles roles);

    List<RolePermissionVO> selectRolesDetailList();
}
