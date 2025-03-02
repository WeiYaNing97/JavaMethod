package com.example.userrole.service;

import com.example.userrole.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
public interface IRoleService extends IService<Role> {


    boolean deleteAll();
}
