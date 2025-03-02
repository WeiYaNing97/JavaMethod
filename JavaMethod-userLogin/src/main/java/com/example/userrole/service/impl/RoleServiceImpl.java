package com.example.userrole.service.impl;

import com.example.userrole.entity.Role;
import com.example.userrole.mapper.RoleMapper;
import com.example.userrole.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    RoleMapper roleMapper;
    @Override
    public boolean deleteAll() {
        return roleMapper.deleteAll();
    }
}
