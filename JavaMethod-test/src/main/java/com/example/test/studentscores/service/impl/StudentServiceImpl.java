package com.example.test.studentscores.service.impl;

import com.example.test.studentscores.entity.Student;
import com.example.test.studentscores.mapper.StudentMapper;
import com.example.test.studentscores.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-04
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
