package com.example.user.service.impl;

import com.example.user.entity.Permissions;
import com.example.user.mapper.PermissionsMapper;
import com.example.user.service.IPermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 权限信息表 服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {
    @Autowired
    PermissionsMapper permissionsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(Permissions permissions) {
        int insert = permissionsMapper.add(permissions);
        return insert>0;
    }
}
