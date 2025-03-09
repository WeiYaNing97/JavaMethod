package com.example.method.timer;


import com.example.method.log.LogUtils;
import com.example.utils.util.DateUtils;

import java.util.TimerTask;

public class JobTask extends TimerTask {
    @Override
    public void run() {

        LogUtils.info("[" + DateUtils.getTime() + "] ====== JobTimer 定时任务开启 ： com.example.method.timer.JobTimer ======");

    }
}
