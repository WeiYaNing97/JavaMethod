package com.example.method.applicationListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 启动后立即执行类 (一)。
 * <p>该方法会在项目启动后立即执行，可以用于执行一些需要在应用程序启动后立即执行的初始化操作。
 *
 *
 * "@Component 注解是 Spring 中用来声明一个类为 Bean 的基础注解.
 * 它让 Spring 容器能够识别、管理并自动装配这些类的实例。
 * 通过合理使用 @Component 及其相关注解，可以极大地简化应用程序的配置，促进代码的模块化和松耦合。
 */
@Component
@Order(1)
public class ExecuteAfterStartup implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * 使用注解获取yml文件参数值。
     * 注解：@Value("${一级属性名.二级属性名.三级属性名}"),直接将注解应用到需要该参数的类的对象上.
     * 也可以使用单独的配置类，但是需要特定方法。*/
    @Value("${obtain.ymlfile.values}")
    private String obtainymlfilevalues;

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
        System.err.println("第1个项目启动后立即执行类（获取.yml配置文件数据示例）");
        System.err.println(obtainymlfilevalues);
        System.err.println("=============================================");
    }

}
