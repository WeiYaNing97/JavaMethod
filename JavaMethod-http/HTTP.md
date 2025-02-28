# HTTP 获取信息
##  自定义拦截器，用于在请求处理之前执行一些操作。
    com.example.http.MyInterceptor implements HandlerInterceptor

    `HandlerInterceptor` 是 Spring MVC 框架中的一个接口，它允许你在一个请求处理的不同阶段插入自定义逻辑。
    这些阶段包括：在控制器（Controller）方法执行之前、在控制器方法执行之后以及在整个请求完成之后。
    通过实现 `HandlerInterceptor` 接口，你可以对请求进行预处理、后处理或者在视图渲染之后执行某些操作。
    `HandlerInterceptor` 接口包含三个主要的方法：

       1. **preHandle()**：在处理器方法（即控制器方法）执行之前被调用。你可以在这个方法中进行一些前置处理，如权限验证、日志记录等。如果该方法返回 `true`，则继续处理当前请求；如果返回 `false`，则中断请求处理链，通常用于拦截不需要进一步处理的请求。
       2. **postHandle()**：在处理器方法执行完毕但还未进行视图渲染时被调用。可以在此方法中修改模型数据或视图对象。注意，如果请求处理过程中抛出了异常，此方法可能不会被调用。
       3. **afterCompletion()**：在整个请求处理（包括视图渲染）完成后被调用。通常用于资源清理工作或记录请求处理完成后的信息。这个方法还会接收一个 `Exception` 参数，因此可以用来处理请求过程中产生的异常。

要使用 `HandlerInterceptor`，你需要创建一个实现了该接口的类，并将其实例注册到 Spring MVC 的拦截器栈中。这可以通过继承 `WebMvcConfigurer` 类并重写 `addInterceptors()` 方法来完成，例如：

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyCustomInterceptor()).addPathPatterns("/**");
    }
}
```

这里，`MyCustomInterceptor` 是你自己实现的 `HandlerInterceptor`。通过这种方式，你可以非常灵活地控制请求处理流程，添加各种横切关注点，比如安全性检查、性能监控等。




# Spring MVC 提供了多个接口，用于不同的目的，帮助开发者构建灵活且功能强大的Web应用程序。以下是几个常用的接口及其用途：

1. **`Controller` 接口**
    - 这是最早的控制器接口，现在大多数情况下使用的是基于注解的控制器（通过 `@Controller` 注解）。它代表了一个基本的MVC控制器，负责处理传入的请求并返回响应。

2. **`@RequestMapping` 注解**
    - 虽然不是一个接口，但它是Spring MVC中最重要的注解之一。它可以标注在类或方法上，用于映射HTTP请求到控制器处理方法上。支持GET、POST等多种HTTP方法，并可以用来匹配URL路径。

3. **`HandlerInterceptor` 接口**
    - 用于定义拦截器，可以在请求被处理器处理之前（`preHandle`）、之后但在视图渲染之前（`postHandle`），以及在整个请求完成之后（`afterCompletion`）插入自定义逻辑。常用于日志记录、权限验证等横切关注点。

4. **`ViewResolver` 接口**
    - 视图解析器接口，用于将逻辑视图名称解析为实际的视图对象。Spring提供了多种实现，如 `InternalResourceViewResolver`（用于JSP）、`ThymeleafViewResolver` 等。

5. **`ModelAndView` 类**
    - 不是一个接口，但它在Spring MVC中非常重要。它封装了模型数据和视图信息，允许控制器方法既指定要显示的数据也指定如何呈现这些数据（即视图）。

6. **`WebMvcConfigurer` 接口**
    - 提供了一种方式来定制Spring MVC应用的配置而不覆盖默认配置。可以通过实现该接口并重写其方法来自定义诸如视图解析器、静态资源处理、拦截器注册等。

7. **`HttpMessageConverter` 接口**
    - 用于转换HTTP请求和响应中的消息内容类型。例如，从JSON格式转换为Java对象或将Java对象转换为XML格式。Spring MVC自动注册了一些默认的消息转换器，但你也可以添加自己的转换器。

8. **`ExceptionHandler` 注解与 `@ControllerAdvice` 注解**
    - 尽管它们不是接口，但它们对于全局异常处理非常有用。`@ControllerAdvice` 可以用于定义全局异常处理器，而 `@ExceptionHandler` 则标注于方法之上，用于处理特定类型的异常。

9. **`AsyncHandlerInterceptor` 接口**
    - 是 `HandlerInterceptor` 的扩展，增加了对异步请求的支持，允许在异步处理的不同阶段执行拦截逻辑。

这些接口和注解共同构成了Spring MVC框架的核心，使得开发人员能够有效地分离关注点、管理依赖关系、处理请求和响应，并创建模块化、可维护的Web应用程序。通过合理利用这些组件，可以显著提高开发效率和代码质量。

# 以下是关于如何使用Spring MVC中一些常用接口和注解的基本指南：

### 1. `Controller` 接口 和 `@Controller`
- **使用方式**：现代Spring MVC开发通常直接使用 `@Controller` 注解，而不是实现 `Controller` 接口。`@Controller` 标注在类上，表明该类是一个控制器。
- **示例**：
  ```java
  @Controller
  public class MyController {
      @RequestMapping("/hello")
      public String hello(Model model) {
          model.addAttribute("message", "Hello, World!");
          return "helloView";
      }
  }
  ```

### 2. `@RequestMapping`
- **使用方式**：可以标注在类或方法级别，用于映射HTTP请求到处理方法。支持指定URL路径、HTTP方法（GET, POST等）、参数等。
- **示例**：
  ```java
  @RequestMapping(value = "/greet", method = RequestMethod.GET)
  public String greet() {
      return "Greetings!";
  }
  ```

### 3. `HandlerInterceptor`
- **使用方式**：实现 `HandlerInterceptor` 接口，并通过配置将其添加到拦截器栈中。
- **示例**：
  ```java
  public class MyInterceptor implements HandlerInterceptor {
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
          // 在处理器执行之前调用
          return true;
      }
  }
  ```
  配置拦截器：
  ```java
  @Configuration
  public class WebConfig implements WebMvcConfigurer {
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
          registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
      }
  }
  ```

### 4. `ViewResolver`
- **使用方式**：配置一个视图解析器，如 `InternalResourceViewResolver` 来解析视图名称到实际的视图资源。
- **示例**：
  ```java
  @Bean
  public ViewResolver viewResolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setPrefix("/WEB-INF/views/");
      resolver.setSuffix(".jsp");
      return resolver;
  }
  ```

### 5. `ModelAndView`
- **使用方式**：在控制器的方法中创建并返回 `ModelAndView` 对象来同时设置模型数据和视图名。
- **示例**：
  ```java
  @RequestMapping("/showMessage")
  public ModelAndView showMessage() {
      ModelAndView modelAndView = new ModelAndView("messageView");
      modelAndView.addObject("message", "This is a message.");
      return modelAndView;
  }
  ```

### 6. `WebMvcConfigurer`
- **使用方式**：实现 `WebMvcConfigurer` 接口以自定义Spring MVC配置而不覆盖默认配置。
- **示例**：
  ```java
  @Configuration
  public class WebConfig implements WebMvcConfigurer {
      @Override
      public void configureViewResolvers(ViewResolverRegistry registry) {
          registry.jsp("/WEB-INF/jsp/", ".jsp");
      }
  }
  ```

### 7. `HttpMessageConverter`
- **使用方式**：自定义消息转换器或将自定义的消息转换器添加到 `HttpMessageConverters` 中。
- **示例**：
  ```java
  @Bean
  public HttpMessageConverters customConverters() {
      HttpMessageConverter<?> additional = ...
      HttpMessageConverter<?> another = ...
      return new HttpMessageConverters(additional, another);
  }
  ```

这些是Spring MVC中一些核心接口和注解的基础使用方法。根据你的具体需求，你可能需要组合使用这些组件来构建功能全面的应用程序。例如，你可以利用 `@Controller` 和 `@RequestMapping` 来定义REST API端点，使用 `HandlerInterceptor` 来进行日志记录或权限验证，或者通过 `ViewResolver` 来管理JSP页面等。