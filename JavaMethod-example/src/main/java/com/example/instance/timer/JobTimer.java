package com.example.instance.timer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Timer;

@Component
@Order(2)
public class JobTimer implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${obtain.ymlfile.time}")
    private int time;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 创建Timer对象
        Timer timer = new Timer();

        // 创建ErrorPackageTimed任务对象
        JobTask task = new JobTask();/*交换机登录方式*/

        // 自动空间管理周期（天）
        /*long delay = 0; // 延迟时间，单位为毫秒
        long period = time * 60 * 1000; // 执行间隔，单位为毫秒*/
        /*time天：  time * 24 * 60 * (60 * 1000) */
        /*time时：  time * 60 * (60 * 1000) */
        /*time分：  time * (60 * 1000) */
        /*time秒：  time * 1000 */
        timer.schedule(task, 0, time * (60 * 1000) );
    }
}
