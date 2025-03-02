package com.example.userrole.service;

import com.example.userrole.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.userrole.entity.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
public interface IUserService extends IService<User> {

    boolean add(UserVO user);

    boolean deleteById(Integer userId);
}
