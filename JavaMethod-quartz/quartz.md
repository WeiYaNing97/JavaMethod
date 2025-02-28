# Quartz 作业调度问题
## 添加注解
      <!--引入quartz定时框架-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-quartz</artifactId>
        </dependency>
## 创建工具类
      com.example.quartz.utils.QuartzBean

## 参数设置
      在设置 `QuartzBean` 的 `jobClass` 属性时，传递给 `setJobClass` 方法的参数应当是实现了 Quartz `Job` 接口的类的全限定名（即包括包名的类名）。
            例如，在你的代码片段中，你设置了 `"com.example.method.thread.overtime.OverTimeThreadPool"` 作为 jobClass。这意味着以下几点要求：
      1. **实现 Job 接口**：指定的类必须实现 `org.quartz.Job` 接口，并且重写其 `execute(JobExecutionContext context)` 方法。这是 Quartz 框架用来执行定时任务的方法。
      2. **具有无参构造函数**：虽然 Quartz 使用反射来实例化作业类，但是默认情况下，它需要该类提供一个无参构造函数。
         如果你的类没有显式定义任何构造函数，Java 会自动提供一个无参构造函数；但如果你定义了有参构造函数，则需要同时提供一个无参构造函数供 Quartz 使用。
      3. **全限定类名**：由于 `setJobClass` 方法接受的是字符串形式的类名，所以你需要提供目标作业类的完整包路径和类名，确保 Quartz 可以通过这个名称正确地找到并加载对应的类。
      4. **类路径和依赖**：确保该类及其所有依赖都在运行时的类路径上，以便 Quartz 能够成功加载和实例化该类。
         基于上述信息，检查 `"com.example.method.thread.overtime.OverTimeThreadPool"` 是否满足以上条件是非常重要的。
         如果这个类不实现 `Job` 接口或者无法被正确加载，那么调度作业将失败。