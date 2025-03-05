package com.example.test.studentscores.mapper;

import com.example.test.studentscores.entity.ClassStudentSUM;
import com.example.test.studentscores.entity.ClassSubjectAVG;
import com.example.test.studentscores.entity.Scores;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-04
 */
@Mapper
public interface ScoresMapper extends BaseMapper<Scores> {

    List<ClassStudentSUM> getSumScores();

    List<ClassSubjectAVG> getAvgScores();
}
