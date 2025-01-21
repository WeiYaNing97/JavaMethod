package com.example.instance.applicationListener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ExecuteAfterStartupThree  implements ApplicationListener<ApplicationReadyEvent> {
    /**
     * 在应用程序准备完毕后执行的方法。
     *
     * <p>该方法会在项目启动后立即执行，可以用于执行一些需要在应用程序启动后立即执行的初始化操作。
     *
     * @param event 应用程序准备完毕的事件对象
     * @throws Exception 如果在执行过程中出现异常，则会抛出异常
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        System.err.println("第三个项目启动后立即执行类");

    }
}
