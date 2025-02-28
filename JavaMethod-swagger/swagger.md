# swagger 简介
     swagger是当下比较流行的实时接口文文档生成工具。
     接口文档是当前前后端分离项目中必不可少的工具，在前后端开发之前，后端要先出接口文档，前端根据接口文档来进行项目的开发，双方开发结束后在进行联调测试。所以接口文档其实就是开发之前双方之间的一种约定。通常接口文档分为离线的和实时的。离线的接口文档工具有： word(相当于没说)， YAPI, 小幺鸡等，这种文档需要程序员在上面编写，也一般具备接口测试功能。通常是由开发人员先在离线接口文档上编写信息，然后交给前端人员参照开发。最大的弊端是当我们的接口程序发生变动时，需要回过头来维护上面的内容，很麻烦，是真的麻烦。
     实时接口文档就是可以根据我们的代码来自动生成相应的接口文档，优点就是我们的代码发生变化时，生成的接口文档也会自动更新，无需我们关注修改，主需要按时发布即可。但是由于是根据代码自动生成的，所以最大的弊端就是代码侵入性强，需要我们在项目代码中集成生成接口文档的相关代码。
     实时接口文档现在的方案有很多，但是swagger还是其中比较有影响力的一个。
# swagger 集成
## 1. 添加依赖
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
    </dependency>

## 2. 配置swagger
    @Configuration
    @EnableSwagger2
    public class SwaggerConfig {

    /**
     * 设置多个：
     *
     * @Bean
     *     public Docket appApi() {
     *
     *         List<Parameter> pars = new ArrayList<>();
     *         ParameterBuilder token = new ParameterBuilder();
     *         token.name("token").description("用户令牌").modelRef(new ModelRef("string")).parameterType("header").required(false)
     *                 .build();
     *         pars.add(token.build());
     *
     *         return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/app/.*")).build()
     *                 .globalOperationParameters(pars).apiInfo(pdaApiInfo()).useDefaultResponseMessages(false)
     *                 .enable(enableSwagger)
     *                 .groupName("appApi");
     *
     *     }
     *
     *     @Bean
     *     public Docket adminApi() {
     *
     *         List<Parameter> pars = new ArrayList<>();
     *         ParameterBuilder token = new ParameterBuilder();
     *         token.name("token").description("用户令牌").modelRef(new ModelRef("string")).parameterType("header").required(false)
     *                 .build();
     *         pars.add(token.build());
     *         return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/admin/.*")).build()
     *                 .globalOperationParameters(pars).apiInfo(pdaApiInfo()).useDefaultResponseMessages(false)
     *                 .enable(enableSwagger)
     *                 .groupName("adminApi");
     *
     *     }
     *
     *
     * @return
     */


    /**
     * 创建并返回一个配置好的Swagger Docket对象，用于生成API文档。
     *
     * 该方法首先创建一个新的Docket对象，设置文档类型为SWAGGER_2，并通过apiInfo()方法设置API信息。
     *
     * @return 返回配置好的Docket对象
     */
    @Bean
    public Docket createRestApi() {
        // 创建一个新的Docket对象，设置文档类型为SWAGGER_2，并通过apiInfo()方法设置API信息
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                // 选择API的基础包名为"com.lsqingfeng.action.swagger.controller"
                .apis(RequestHandlerSelectors.basePackage("com.example."))
                // 选择所有路径来生成API文档
                .paths(PathSelectors.any())
                .build()
                // 通过globalOperationParameters(setHeaderToken())方法添加一个全局请求头参数
                .globalOperationParameters(setHeaderToken());

    }


    /**
     * 获取Swagger API文档信息
     *
     * @return 返回ApiInfo对象，包含API文档的标题、描述、服务条款URL和版本信息
     */
    private ApiInfo apiInfo() {
        // 创建一个ApiInfoBuilder对象
        return new ApiInfoBuilder()
                // 设置API文档的标题
                .title("action-swagger")
                // 设置API文档的描述
                .description("swagger实战")
                // 设置服务条款的URL（此处为空）
                .termsOfServiceUrl("")
                // 设置API文档的版本信息
                .version("1.0")
                // 构建并返回ApiInfo对象
                .build();
    }


    /**
     * 设置swagger文档中全局参数
     *
     * @Description: 该方法用于设置swagger文档中的全局参数，例如用户令牌（token）。
     * @param: 无参数
     * @Date: 2020/9/11 10:15
     * @return: 返回包含全局参数的列表，类型为java.util.List<springfox.documentation.service.Parameter>
     */
    private List<Parameter> setHeaderToken() {
        // 创建一个空的参数列表
        List<Parameter> pars = new ArrayList<>();

        // 创建一个ParameterBuilder对象，用于构建参数
        ParameterBuilder userId = new ParameterBuilder();

        // 设置参数名称为"token"，描述为"用户TOKEN"，类型为字符串，参数类型为header，且为必填项
        userId.name("token")
              .description("用户TOKEN")
              .modelRef(new ModelRef("string"))
              .parameterType("header")
              .required(true)
              .build();

        // 将构建好的参数添加到参数列表中
        pars.add(userId.build());

        // 返回包含全局参数的列表
        return pars;
    }
    }

## 3. 编写接口

### 添加到实体类上的注解 
    类： @ApiModel("创建Swagger响应结果")
    属性： @ApiModelProperty("id")

### 添加到接口上的注解
    接口： @Api(value = "用户接口", tags = {"用户接口"})
    方法： @ApiOperation("新增用户")

## 4. 编写yml文件
    Swagger引用的Springfox使用的路径匹配是基于AntPathMatcher的。
    所以要想解决，添加配置，将springBoot MVC的路劲匹配模式修改一下即可。
    在springBoot配置文件中添加配置：
    spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER

    swagger:
      enabled: true
      title: 用户接口文档
      description: 用户接口文档
      version: 1.0

## 4. 启动项目，访问swagger页面
访问地址： ip:端口号/swagger-ui.html

