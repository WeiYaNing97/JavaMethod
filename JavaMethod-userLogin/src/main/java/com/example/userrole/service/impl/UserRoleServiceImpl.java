package com.example.userrole.service.impl;

import com.example.userrole.entity.UserRole;
import com.example.userrole.mapper.UserRoleMapper;
import com.example.userrole.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> listByRoleIds(List<Integer> roleIds) {
        return userRoleMapper.listByRoleIds(roleIds);
    }
}
