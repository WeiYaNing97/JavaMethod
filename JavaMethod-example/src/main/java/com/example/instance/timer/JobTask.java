package com.example.instance.timer;


import com.example.method.log.LogUtils;
import com.example.util.DateUtils;

import java.util.TimerTask;

public class JobTask extends TimerTask {
    @Override
    public void run() {
        LogUtils.getInfo("[" + DateUtils.getTime() + "] ====== JobTimer 定时任务开启 ======");
    }
}
