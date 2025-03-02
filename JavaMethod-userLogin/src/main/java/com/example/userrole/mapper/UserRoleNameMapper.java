package com.example.userrole.mapper;

import com.example.userrole.entity.UserRoleName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
@Mapper
public interface UserRoleNameMapper extends BaseMapper<UserRoleName> {

    String obtainEmployeeRoles(String userName);

    List<String> employeesWhoHaveObtainedRoles(String roleName);

    List<UserRoleName> getPojo(UserRoleName userRoleName);
}
