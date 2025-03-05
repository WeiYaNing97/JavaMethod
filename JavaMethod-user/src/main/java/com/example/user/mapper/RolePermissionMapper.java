package com.example.user.mapper;

import com.example.user.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
