package com.example.user.service.impl;

import com.example.user.entity.RolePermission;
import com.example.user.mapper.RolePermissionMapper;
import com.example.user.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与权限关联表 服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

}
