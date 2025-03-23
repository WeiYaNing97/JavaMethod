package com.example.quartz.controller;

import com.example.quartz.bean.QuartzBean;
import com.example.quartz.utils.QuartzUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "定时任务管理")
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private Scheduler scheduler;

    /**
     * 创建定时任务
     *
     * @return 创建成功返回"创建成功"，创建失败返回"创建失败"
     * @throws Exception 异常信息
     */
    @RequestMapping("/createJob")
    @ResponseBody
    @ApiOperation(value = "创建定时任务")
    public String createJob()  {
        try {
            // 创建QuartzBean对象
            QuartzBean quartzBean = new QuartzBean();
            // 设置Job类
            quartzBean.setJobClass("com.example.quartz.job.jobTest");
            // 设置Job名称
            quartzBean.setJobName("test");
            // 设置Cron表达式
            quartzBean.setCronExpression("*/10 * * * * ?");
            // 创建调度任务
            QuartzUtils.createScheduleJob(scheduler, quartzBean);
        } catch (Exception e) {
            // 捕获异常，返回创建失败信息
            return "创建失败";
        }
        // 返回创建成功信息
        return "创建成功";
    }


    @RequestMapping("/pauseJob")
    @ResponseBody
    @ApiOperation(value = "暂停定时任务")
    public String  pauseJob()  {
        try {
            QuartzUtils.pauseScheduleJob (scheduler,"test");
        } catch (Exception e) {
            return "暂停失败";
        }
        return "暂停成功";
    }

    @RequestMapping("/runOnce")
    @ResponseBody
    @ApiOperation(value = "运行一次")
    public String  runOnce()  {
        try {
            QuartzUtils.runOnce (scheduler,"test");
        } catch (Exception e) {
            return "运行一次失败";
        }
        return "运行一次成功";
    }

    @RequestMapping("/resume")
    @ResponseBody
    @ApiOperation(value = "启动定时任务")
    public String  resume()  {
        try {
            QuartzUtils.resumeScheduleJob(scheduler,"test");
        } catch (Exception e) {
            return "启动失败";
        }
        return "启动成功";
    }

    @RequestMapping("/update")
    @ResponseBody
    @ApiOperation(value = "更新定时任务")
    public String  update(QuartzBean quartzBean)  {
        try {
            quartzBean.setJobClass("com.hjljy.blog.Quartz.TaskForEvery");
            quartzBean.setJobName("test");
            quartzBean.setCronExpression("10 * * * * ?");
            QuartzUtils.updateScheduleJob(scheduler,quartzBean);
        } catch (Exception e) {
            return "启动失败";
        }
        return "启动成功";
    }
}
