package com.example.user.service;

import com.example.user.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
public interface IUsersService extends IService<Users> {

    boolean add(Users users);
}
