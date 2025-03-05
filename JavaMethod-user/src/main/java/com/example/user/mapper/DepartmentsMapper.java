package com.example.user.mapper;

import com.example.user.entity.Departments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 部门信息表 Mapper 接口
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Mapper
public interface DepartmentsMapper extends BaseMapper<Departments> {

    int add(@Param("departments") Departments departments);
}
