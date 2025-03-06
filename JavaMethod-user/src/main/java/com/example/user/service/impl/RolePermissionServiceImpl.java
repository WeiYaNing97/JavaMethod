package com.example.user.service.impl;

import com.example.user.entity.RolePermission;
import com.example.user.mapper.RolePermissionMapper;
import com.example.user.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(RolePermission rolePermission) {
        int insert = rolePermissionMapper.add(rolePermission);
        return  insert > 0;
    }

    @Override
    public boolean removeByRoleId(Integer id) {
        int delete = rolePermissionMapper.deleteByRoleId(id);
        return delete > 0;
    }

    @Override
    public boolean updateByRoleId(RolePermission rolePermission) {
        int update = rolePermissionMapper.updateByRoleId(rolePermission);
        return update > 0;
    }
}
