package com.example.studentscores.mapper;

import com.example.studentscores.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-04
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
