package com.example.user.service;

import com.example.user.entity.RolePermissionVO;
import com.example.user.entity.Roles;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
public interface IRolesService extends IService<Roles> {

    boolean add(Roles roles);

    List<RolePermissionVO> getRolesDetails();
}
