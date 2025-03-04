package com.example.studentscores.controller;


import com.example.studentscores.entity.ClassStudentSUM;
import com.example.studentscores.entity.ClassSubjectAVG;
import com.example.studentscores.service.IScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author springBoot-Learning
 * @since 2025-03-04
 */
@RestController
@RequestMapping("/studentscores/scores")
public class ScoresController {

    @Autowired
    private IScoresService scoresService;

    @RequestMapping("/sum")
    public List<ClassStudentSUM> getSumScores() {
        List<ClassStudentSUM> classStudentSUMList = scoresService.getSumScores();
        return classStudentSUMList;
    }

    @RequestMapping("/avg")
    public List<ClassSubjectAVG> getAvgScores() {
        List<ClassSubjectAVG> classSubjectAVGList = scoresService.getAvgScores();
        return classSubjectAVGList;
    }
}
