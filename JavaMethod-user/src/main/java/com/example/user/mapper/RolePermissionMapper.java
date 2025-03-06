package com.example.user.mapper;

import com.example.user.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 角色与权限关联表 Mapper 接口
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    int add(RolePermission rolePermission);

    int deleteByRoleId(@Param("roleId") Integer id);

    int updateByRoleId(RolePermission rolePermission);
}
