package com.example.user.service.impl;

import com.example.user.entity.Users;
import com.example.user.mapper.UsersMapper;
import com.example.user.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Override
    public boolean add(Users users) {
        return usersMapper.add(users);
    }
}
