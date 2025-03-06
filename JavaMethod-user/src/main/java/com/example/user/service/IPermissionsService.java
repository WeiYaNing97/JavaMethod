package com.example.user.service;

import com.example.user.entity.Permissions;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 权限信息表 服务类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
public interface IPermissionsService extends IService<Permissions> {

    boolean add(Permissions permissions);
}
