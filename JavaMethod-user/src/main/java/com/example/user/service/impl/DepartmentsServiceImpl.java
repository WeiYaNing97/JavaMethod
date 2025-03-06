package com.example.user.service.impl;

import com.example.user.entity.Departments;
import com.example.user.mapper.DepartmentsMapper;
import com.example.user.service.IDepartmentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 部门信息表 服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-05
 */
@Service
public class DepartmentsServiceImpl extends ServiceImpl<DepartmentsMapper, Departments> implements IDepartmentsService {

    @Autowired
    private DepartmentsMapper departmentsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(Departments departments) {
        int add = departmentsMapper.add(departments);
        return  add > 0;
    }
}
