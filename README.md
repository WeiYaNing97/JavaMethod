# 一：创建springboot的关键点
## 父模块
    1:New Project 选择 Spring Initializr
    2:Project Metadata   Type 选择 maven
## 启动模块 admin
    1:New Project 选择 Maven
    2:启动模块 和 子模块名称 注意 "-" 问题
    3:父模块 不依赖 admin，admin 依赖 所有子模块
    *4:所有类和方法都应该在启动类相同的包或者子包下。


# 二：Springboot 整合 MyBatis 
## 添加依赖
    <!-- Spring Boot Web Starter -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>2.2.2</version>
            </dependency>

## 添加配置文件信息
    # MyBatis配置
    mybatis:
      # 搜索指定包别名
      typeAliasesPackage: com.example.**.domain
      # 配置mapper的扫描，找到所有的mapper.xml映射文件
      mapperLocations: classpath*:mapper/**/*Mapper.xml
      # 加载全局的配置文件
      configLocation: classpath:mybatis/mybatis-config.xml
## 添加 mybatis-config.xml 文件
    mybatis/mybatis-config.xml
    
# 三：读取配置文件
    @Value("${token.header}")
    该类应该被注入到需要使用的地方，不能创建该类的实例。否则，该类中的属性值不会生效。
    
