package com.example.userrole.service.impl;

import com.example.userrole.entity.User;
import com.example.userrole.entity.UserRole;
import com.example.userrole.entity.UserVO;
import com.example.userrole.mapper.UserMapper;
import com.example.userrole.mapper.UserRoleMapper;
import com.example.userrole.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public boolean add(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);

        int insert = userMapper.insert(user);
        if (insert > 0) {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(userVO.getRoleId());
            int insert2 = userRoleMapper.insert(userRole);
            if (insert2 > 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer userId) {
        int i = userRoleMapper.deleteByUserId(userId);
        if (i > 0) {
            int i1 = userMapper.deleteById(userId);
            if (i1 > 0) {
                return true;
            }
            return false;
        }
        return false;
    }
}
