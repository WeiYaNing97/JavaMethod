=============注意：首次打开面板浏览器将提示不安全=================

请选择以下其中一种方式解决不安全提醒
1、下载证书，地址：https://dg2.bt.cn/ssl/baota_root.pfx，双击安装,密码【www.bt.cn】
2、点击【高级】-【继续访问】或【接受风险并继续】访问
教程：https://www.bt.cn/bbs/thread-117246-1-1.html
mac用户请下载使用此证书：https://dg2.bt.cn/ssl/mac.crt

========================面板账户登录信息==========================

【云服务器】请在安全组放行 31127 端口
外网面板地址: https://123.57.51.130:31127/2a7f5cec
内网面板地址: https://172.18.240.222:31127/2a7f5cec
username: t2jol4qc
password: b2b7d543

浏览器访问以下链接，添加宝塔客服
https://www.bt.cn/new/wechat_customer
==================================================================


# 一：创建springboot的关键点
## 父模块
    1:New Project 选择 Spring Initializr
    2:Project Metadata   Type 选择 maven
## 启动模块 admin
    1:New Project 选择 Maven
    2:启动模块 和 子模块名称 注意 "-" 问题
    3:父模块 不依赖 admin，admin 依赖 所有子模块
    4:所有类和方法都应该在启动类相同的包或者子包下。

    5:启动类上添加 @SpringBootApplication，主模块的 pom.xml 添加插件配置：
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>


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
    
# 三：添加 Redis
### 添加 maven 依赖
### yml文件 添加 redis 相关配置
### 添加 RedisConfig 配置类
### 写 RedisTemplate类的增、删、查方法的相应接口类（RedisService）
    
# 四：读取配置文件
    @Value("${token.header}")
    该类应该被注入到需要使用的地方，不能创建该类的实例。否则，该类中的属性值不会生效。
    
# 五：RabbitMQ 相关
    https://blog.csdn.net/qq_25919879/article/details/113055350?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522171947001016800188554588%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=171947001016800188554588&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-113055350-null-null.142^v100^pc_search_result_base7&utm_term=rabbitmq%E5%AE%89%E8%A3%85windows&spm=1018.2226.3001.4187
## 1：安装 Eralng
### 下载Eralng，下面链接已提供otp_win64_20.2.exe
    链接： https://pan.baidu.com/s/1lmvCMPVAV1Ba9UogCdQpZg
    提取码：x9m7
### 配置Eralng 
    配置环境变量
    新建系统变量名为：ERLANG_HOME 变量值为erlang安装地址
    双击系统变量path，点击“新建”，将%ERLANG_HOME%\bin加入到path中。
## 2：安装 RabbitMQ
### 下载rabbitmq，下面链接已提供rabbitmq-server-3.7.4.exe
    链接： https://pan.baidu.com/s/1CPfhg5X1e7UitpgMWIcAEg
    提取码：h4r3
## Springboot 整合 RabbitMQ
### 配置文件
    # rabbitmq 配置
      rabbitmq:
        host: localhost
        port: 5672
        username: guest
        password: guest
        
# 六：多数据源 dynamic-datasource
## 依赖
        <!-- https://mvnrepository.com/artifact/com.baomidou/dynamic-datasource-spring-boot-starter -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
            <version>3.5.1</version>
        </dependency>

## 配置
    spring:
        application:
            name: JavaMethod
        datasource:
            dynamic:
                primary: master
                strict: false
                datasource:
                    master:
                        url: jdbc:mysql://localhost:3306/javamethod?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
                        username: root
                        password: root
                        driver-class-name: com.mysql.cj.jdbc.Driver
                    slave_1:
                        url: jdbc:mysql://localhost:3306/javamethod1?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
                        username: root
                        password: root
                        driver-class-name: com.mysql.cj.jdbc.Driver`

## 切换数据源
    @Service
    @DS("slave")
    public class UserServiceImpl implements UserService {
    
        @Autowired
        private JdbcTemplate jdbcTemplate;
    
        @Override
        @DS("slave_1")
        public List selectByCondition() {
        return jdbcTemplate.queryForList("select * from user where age >10");
        }
    }`

# 七：设置启动项目立即执行的类
## 1：添加注解
    @Component
    @Order(n)//权重值，越小越先执行，默认为Integer.MAX_VALUE
## 2：实现类：
    implements ApplicationListener<ApplicationReadyEvent>
## 3：重写方法
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
    }