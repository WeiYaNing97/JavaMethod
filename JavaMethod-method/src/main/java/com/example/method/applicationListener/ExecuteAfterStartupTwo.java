package com.example.method.applicationListener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;


/**
 * 启动后立即执行类 (一)。
 * <p>该方法会在项目启动后立即执行，可以用于执行一些需要在应用程序启动后立即执行的初始化操作。
 *
 * "@Component 注解是 Spring 中用来声明一个类为 Bean 的基础注解.
 * 它让 Spring 容器能够识别、管理并自动装配这些类的实例。
 * 通过合理使用 @Component 及其相关注解，可以极大地简化应用程序的配置，促进代码的模块化和松耦合。
 */
@Component
@Order(2)
public class ExecuteAfterStartupTwo implements ApplicationListener<ApplicationReadyEvent> {


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
        System.err.println("=============================================");
        // 获取第一个时间点
        Instant start = Instant.now();


        System.err.println("第2个项目启动后立即执行类（获取时间差）");
        try {
            Thread.sleep(1024);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 获取第二个时间点
        Instant end = Instant.now();
        // 计算时间差
        Duration duration = Duration.between(start, end);
        // 输出结果，格式化为秒和纳秒
        System.err.printf("获取时间差 Time difference: %d.%09d seconds%n",
                duration.getSeconds(), duration.getNano());
        System.err.println("=============================================");
    }

}
