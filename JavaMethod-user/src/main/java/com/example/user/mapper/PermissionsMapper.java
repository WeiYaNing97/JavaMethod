package com.example.user.mapper;

import com.example.user.entity.Permissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限信息表 Mapper 接口
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Mapper
public interface PermissionsMapper extends BaseMapper<Permissions> {

    int add(Permissions permissions);
}
