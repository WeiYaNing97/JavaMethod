package com.example.userrole.service.impl;

import com.example.userrole.entity.UserRoleName;
import com.example.userrole.mapper.UserRoleNameMapper;
import com.example.userrole.service.IUserRoleNameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
@Service
public class UserRoleNameServiceImpl extends ServiceImpl<UserRoleNameMapper, UserRoleName> implements IUserRoleNameService {

    @Autowired
    private UserRoleNameMapper userRoleNameMapper;

    @Override
    public String obtainEmployeeRoles(String userName) {
        return userRoleNameMapper.obtainEmployeeRoles(userName);
    }

    @Override
    public List<String> employeesWhoHaveObtainedRoles(String roleName) {
        return userRoleNameMapper.employeesWhoHaveObtainedRoles(roleName);
    }

    @Override
    public List<UserRoleName> getPojo(UserRoleName userRoleName) {
        return userRoleNameMapper.getPojo(userRoleName);
    }
}
