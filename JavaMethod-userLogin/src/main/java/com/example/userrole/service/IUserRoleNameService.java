package com.example.userrole.service;

import com.example.userrole.entity.UserRoleName;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
public interface IUserRoleNameService extends IService<UserRoleName> {

    String obtainEmployeeRoles(String userName);

    List<String> employeesWhoHaveObtainedRoles(String roleName);

    List<UserRoleName> getPojo(UserRoleName userRoleName);
}
