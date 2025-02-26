package com.example.quartz.controller;

import com.example.quartz.bean.QuartzBean;
import com.example.quartz.utils.QuartzUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private Scheduler scheduler;

    @RequestMapping("/createJob")
    @ResponseBody
    public String createJob()  {
        try {
            QuartzBean quartzBean = new QuartzBean();
            quartzBean.setJobClass("com.example.userLogin.controller.UserLoginController.getText");
            quartzBean.setJobName("test");
            quartzBean.setCronExpression("*/10 * * * * ?");
            QuartzUtils.createScheduleJob(scheduler,quartzBean);
        } catch (Exception e) {
            return "创建失败";
        }
        return "创建成功";
    }

    @RequestMapping("/pauseJob")
    @ResponseBody
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
