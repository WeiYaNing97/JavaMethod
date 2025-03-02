package com.example.userrole.service;

import com.example.userrole.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
public interface IUserRoleService extends IService<UserRole> {

    List<UserRole> listByRoleIds(List<Integer> collect);
}
