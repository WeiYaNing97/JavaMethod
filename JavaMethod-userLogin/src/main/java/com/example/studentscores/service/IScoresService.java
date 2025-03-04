package com.example.studentscores.service;

import com.example.studentscores.entity.ClassStudentSUM;
import com.example.studentscores.entity.ClassSubjectAVG;
import com.example.studentscores.entity.Scores;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-04
 */
public interface IScoresService extends IService<Scores> {

    List<ClassStudentSUM> getSumScores();

    List<ClassSubjectAVG> getAvgScores();
}
