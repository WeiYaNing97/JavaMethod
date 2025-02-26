package com.example.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.List;

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