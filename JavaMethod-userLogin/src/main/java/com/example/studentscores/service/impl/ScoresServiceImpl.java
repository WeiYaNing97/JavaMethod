package com.example.studentscores.service.impl;

import com.example.studentscores.entity.ClassStudentSUM;
import com.example.studentscores.entity.ClassSubjectAVG;
import com.example.studentscores.entity.Scores;
import com.example.studentscores.mapper.ScoresMapper;
import com.example.studentscores.service.IScoresService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-04
 */
@Service
public class ScoresServiceImpl extends ServiceImpl<ScoresMapper, Scores> implements IScoresService {
    @Autowired
    private ScoresMapper scoresMapper;

    @Override
    public List<ClassStudentSUM> getSumScores() {
        return scoresMapper.getSumScores();
    }

    @Override
    public List<ClassSubjectAVG> getAvgScores() {
        return scoresMapper.getAvgScores();
    }
}
