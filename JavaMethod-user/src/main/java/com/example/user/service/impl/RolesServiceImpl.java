package com.example.user.service.impl;

import com.example.user.entity.Roles;
import com.example.user.mapper.RolesMapper;
import com.example.user.service.IRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements IRolesService {

    @Autowired
    private RolesMapper rolesMapper;
    /**
     * 添加角色到数据库
     *
     * @param roles 需要添加的角色对象
     * @return 如果角色添加成功返回true，否则返回false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(Roles roles) {
        // 调用rolesMapper的add方法，将roles对象添加到数据库中
        int insert = rolesMapper.add(roles);
        // 判断插入是否成功，如果insert大于0表示插入成功
        return insert > 0;
    }

}
