package com.example.test.studentscores.controller;


import com.example.method.log.LogUtils;
import com.example.method.redis.RedisService;
import com.example.test.studentscores.entity.ClassStudentSUM;
import com.example.test.studentscores.entity.ClassSubjectAVG;
import com.example.test.studentscores.service.IScoresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 长城汽车面试题目：
 */
@RestController
@Api(tags = "学生成绩")
@RequestMapping("/studentscores/scores")
public class ScoresController {

    @Autowired
    private IScoresService scoresService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/sum")
    @ApiOperation(value = "获取各班各学生总分"  ,notes = "获取各班各学生总分信息")
    public List<ClassStudentSUM> getSumScores() {
        if (redisService.hasKey("sumScores")) {
            LogUtils.info("从缓存中获取数据");
            return redisService.getCacheObject("sumScores");
        }else {
            LogUtils.info("从数据库中获取数据");
            List<ClassStudentSUM> classStudentSUMList = scoresService.getSumScores();
            redisService.setCacheObject("sumScores", classStudentSUMList, 10, TimeUnit.MINUTES);
            return classStudentSUMList;
        }
    }

    @RequestMapping("/avg")
    @ApiOperation(value = "获取各班各科目平均分"  ,notes = "获取各班各科目平均分信息")
    public List<ClassSubjectAVG> getAvgScores() {
        if (redisService.hasKey("avgScores")) {
            LogUtils.info("从缓存中获取数据");
            return redisService.getCacheObject("avgScores");
        }else {
            LogUtils.info("从数据库中获取数据");
            List<ClassSubjectAVG> classSubjectAVGList = scoresService.getAvgScores();
            redisService.setCacheObject("avgScores", classSubjectAVGList, 10, TimeUnit.MINUTES);
            return classSubjectAVGList;
        }
    }
}
