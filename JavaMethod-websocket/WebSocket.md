一、WebSocket概述
1、WebSocket简介
WebSocket协议是基于TCP的一种新的网络协议。它实现了浏览器与服务器全双工(full-duplex)通信——允许服务器主动发送信息给客户端。

2、为什么需要WebSocket
HTTP 是基于请求响应式的，即通信只能由客户端发起，服务端做出响应，无状态，无连接。

无状态：每次连接只处理一个请求，请求结束后断开连接。
无连接：对于事务处理没有记忆能力，服务器不知道客户端是什么状态。
通过HTTP实现即时通讯，只能是页面轮询向服务器发出请求，服务器返回查询结果。轮询的效率低，非常浪费资源，因为必须不停连接，或者 HTTP 连接始终打开。

WebSocket的最大特点就是，服务器可以主动向客户端推送信息，客户端也可以主动向服务器发送信息，是真正的双向平等对话。

WebSocket特点：

（1）建立在 TCP 协议之上，服务器端的实现比较容易。
（2）与 HTTP 协议有着良好的兼容性。默认端口也是80和443，并且握手阶段采用 HTTP 协议，因此握手时不容易屏蔽，能通过各种 HTTP 代理服务器。
（3）数据格式比较轻量，性能开销小，通信高效。
（4）可以发送文本，也可以发送二进制数据。
（5）没有同源限制，客户端可以与任意服务器通信。
（6）协议标识符是ws（如果加密，则为wss），服务器网址就是 URL。

二、SpringBoot整合WebSocket
创建 SpringBoot项目，引入 WebSocket依赖，前端这里比较简陋。

<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-websocket</artifactId>
            <version>2.7.12</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
            <version>2.7.12</version>
        </dependency>
application.yml：

server:
port: 8081

spring:
thymeleaf:
mode: HTML
cache: true
prefix: classpath:/templates/
encoding: UTF-8
suffix: .html
check-template-location: true
template-resolver-order: 1
1、WebSocketConfig
启用 WebSocket的支持也是很简单。

/**
* WebSocket配置类。开启WebSocket的支持
  */
  @Configuration
  public class WebSocketConfig {

  /**
    * bean注册：会自动扫描带有@ServerEndpoint注解声明的Websocket Endpoint(端点)，注册成为Websocket bean。
    * 要注意，如果项目使用外置的servlet容器，而不是直接使用springboot内置容器的话，就不要注入ServerEndpointExporter，因为它将由容器自己提供和管理。
      */
      @Bean
      public ServerEndpointExporter serverEndpointExporter() {
      return new ServerEndpointExporter();
      }

}
2、WebSocketServer
这里就是重点了，核心都在这里。

因为WebSocket是类似客户端服务端的形式(采用ws协议)，那么这里的WebSocketServer其实就相当于一个ws协议的Controller
直接@ServerEndpoint("/imserver/{userId}") 、@Component启用即可，然后在里面实现@OnOpen开启连接，@onClose关闭连接，@onMessage接收消息等方法。
新建一个ConcurrentHashMap用于接收当前userId的WebSocket或者Session信息，方便IM之间对userId进行推送消息。单机版实现到这里就可以。
集群版（多个ws节点）还需要借助 MySQL或者 Redis等进行订阅广播方式处理，改造对应的 sendMessage方法即可。

/**
* WebSocket的操作类
  */
  @Component
  @Slf4j
  /**
* html页面与之关联的接口
* var reqUrl = "http://localhost:8081/websocket/" + cid;
* socket = new WebSocket(reqUrl.replace("http", "ws"));
  */
  @ServerEndpoint("/websocket/{sid}")
  public class WebSocketServer {

  /**
    * 静态变量，用来记录当前在线连接数，线程安全的类。
      */
      private static AtomicInteger onlineSessionClientCount = new AtomicInteger(0);

  /**
    * 存放所有在线的客户端
      */
      private static Map<String, Session> onlineSessionClientMap = new ConcurrentHashMap<>();

  /**
    * 连接sid和连接会话
      */
      private String sid;
      private Session session;

  /**
    * 连接建立成功调用的方法。由前端<code>new WebSocket</code>触发
    *
    * @param sid     每次页面建立连接时传入到服务端的id，比如用户id等。可以自定义。
    * @param session 与某个客户端的连接会话，需要通过它来给客户端发送消息
      */
      @OnOpen
      public void onOpen(@PathParam("sid") String sid, Session session) {
      /**
        * session.getId()：当前session会话会自动生成一个id，从0开始累加的。
          */
          log.info("连接建立中 ==> session_id = {}， sid = {}", session.getId(), sid);
          //加入 Map中。将页面的sid和session绑定或者session.getId()与session
          //onlineSessionIdClientMap.put(session.getId(), session);
          onlineSessionClientMap.put(sid, session);

      //在线数加1
      onlineSessionClientCount.incrementAndGet();
      this.sid = sid;
      this.session = session;
      sendToOne(sid, "连接成功");
      log.info("连接建立成功，当前在线数为：{} ==> 开始监听新连接：session_id = {}， sid = {},。", onlineSessionClientCount, session.getId(), sid);
      }

  /**
    * 连接关闭调用的方法。由前端<code>socket.close()</code>触发
    *
    * @param sid
    * @param session
      */
      @OnClose
      public void onClose(@PathParam("sid") String sid, Session session) {
      //onlineSessionIdClientMap.remove(session.getId());
      // 从 Map中移除
      onlineSessionClientMap.remove(sid);

      //在线数减1
      onlineSessionClientCount.decrementAndGet();
      log.info("连接关闭成功，当前在线数为：{} ==> 关闭该连接信息：session_id = {}， sid = {},。", onlineSessionClientCount, session.getId(), sid);
      }

  /**
    * 收到客户端消息后调用的方法。由前端<code>socket.send</code>触发
    * * 当服务端执行toSession.getAsyncRemote().sendText(xxx)后，前端的socket.onmessage得到监听。
    *
    * @param message
    * @param session
      */
      @OnMessage
      public void onMessage(String message, Session session) {
      /**
        * html界面传递来得数据格式，可以自定义.
        * {"sid":"user-1","message":"hello websocket"}
          */
          JSONObject jsonObject = JSON.parseObject(message);
          String toSid = jsonObject.getString("sid");
          String msg = jsonObject.getString("message");
          log.info("服务端收到客户端消息 ==> fromSid = {}, toSid = {}, message = {}", sid, toSid, message);

      /**
        * 模拟约定：如果未指定sid信息，则群发，否则就单独发送
          */
          if (toSid == null || toSid == "" || "".equalsIgnoreCase(toSid)) {
          sendToAll(msg);
          } else {
          sendToOne(toSid, msg);
          }
          }

  /**
    * 发生错误调用的方法
    *
    * @param session
    * @param error
      */
      @OnError
      public void onError(Session session, Throwable error) {
      log.error("WebSocket发生错误，错误信息为：" + error.getMessage());
      error.printStackTrace();
      }

  /**
    * 群发消息
    *
    * @param message 消息
      */
      private void sendToAll(String message) {
      // 遍历在线map集合
      onlineSessionClientMap.forEach((onlineSid, toSession) -> {
      // 排除掉自己
      if (!sid.equalsIgnoreCase(onlineSid)) {
      log.info("服务端给客户端群发消息 ==> sid = {}, toSid = {}, message = {}", sid, onlineSid, message);
      toSession.getAsyncRemote().sendText(message);
      }
      });
      }

  /**
    * 指定发送消息
    *
    * @param toSid
    * @param message
      */
      private void sendToOne(String toSid, String message) {
      // 通过sid查询map中是否存在
      Session toSession = onlineSessionClientMap.get(toSid);
      if (toSession == null) {
      log.error("服务端给客户端发送消息 ==> toSid = {} 不存在, message = {}", toSid, message);
      return;
      }
      // 异步发送
      log.info("服务端给客户端发送消息 ==> toSid = {}, message = {}", toSid, message);
      toSession.getAsyncRemote().sendText(message);
      /*
      // 同步发送
      try {
      toSession.getBasicRemote().sendText(message);
      } catch (IOException e) {
      log.error("发送消息失败，WebSocket IO异常");
      e.printStackTrace();
      }*/
      }

}
3、controller
controller中只有一个简单的界面跳转操作，其他的不需要。

@Controller
@RequestMapping("/demo")
public class DemoController {

    /**
     * 跳转到websocketDemo.html页面，携带自定义的cid信息。
     * http://localhost:8081/demo/toWebSocketDemo/user-1
     *
     * @param cid
     * @param model
     * @return
     */
    @GetMapping("/toWebSocketDemo/{cid}")
    public String toWebSocketDemo(@PathVariable String cid, Model model) {
        model.addAttribute("cid", cid);
        return "websocketDemo";
    }

}
4、websocketDemo.html
新建一个文件，放到 templates目录下面。页面简单使用js代码调用WebSocket。

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>666666</title>
</head>
<body>
    传递来的数据值cid:
    <input type="text" th:value="${cid}" id="cid"/>
    <p>【toUserId】：
    <div><input id="toUserId" name="toUserId" type="text" value="user-1"></div>
    <p>【toUserId】：
    <div><input id="contentText" name="contentText" type="text" value="hello websocket"></div>
    <p>【操作】：
    <div>
        <button type="button" onclick="sendMessage()">发送消息</button>
    </div>
</body>

<script type="text/javascript">
    var socket;
    if (typeof (WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    } else {
        console.log("您的浏览器支持WebSocket");
        //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
 
        var cid = document.getElementById("cid").value;
        console.log("cid-->" + cid);
        var reqUrl = "http://localhost:8081/websocket/" + cid;
        socket = new WebSocket(reqUrl.replace("http", "ws"));
        //打开事件
        socket.onopen = function () {
            console.log("Socket 已打开");
            //socket.send("这是来自客户端的消息" + location.href + new Date());
        };
        //获得消息事件
        socket.onmessage = function (msg) {
            console.log("onmessage--" + msg.data);
            //发现消息进入    开始处理前端触发逻辑
        };
        //关闭事件
        socket.onclose = function () {
            console.log("Socket已关闭");
        };
        //发生了错误事件
        socket.onerror = function () {
            alert("Socket发生了错误");
            //此时可以尝试刷新页面
        }
        //离开页面时，关闭socket
        //jquery1.8中已经被废弃，3.0中已经移除
        // $(window).unload(function(){
        //     socket.close();
        //});
    }
 
    function sendMessage() {
        if (typeof (WebSocket) == "undefined") {
            console.log("您的浏览器不支持WebSocket");
        } else {
            // console.log("您的浏览器支持WebSocket");
            var toUserId = document.getElementById('toUserId').value;
            var contentText = document.getElementById('contentText').value;
            var msg = '{"sid":"' + toUserId + '","message":"' + contentText + '"}';
            console.log(msg);
            socket.send(msg);
        }
    }
 
</script>
</html>

5、测试运行效果
（1）访问页面，建立连接

启动项目，访问 http://localhost:8081/demo/toWebSocketDemo/{cid} 跳转到页面，然后就可以和WebSocket交互了。

这里开启三个浏览器的窗口：

http://localhost:8081/demo/toWebSocketDemo/user-1

此时浏览器的console显示如下：



此时浏览器的network 显示如下：



服务端打印如下图所示内容：


http://localhost:8081/demo/toWebSocketDemo/user-2

此时浏览器的network 显示如下：



此时浏览器的network 显示如下：



服务端打印如下图所示内容：


http://localhost:8081/demo/toWebSocketDemo/user-3

此时浏览器的network 显示如下：



此时浏览器的network 显示如下：



服务端打印如下图所示内容：



(2)、在user-2给user-1发消息



此时user-2的浏览器network里面并没有再请求接口



此时查看服务端的console，截图如下：

查看user-1的console，如下所示：



user-1的network并没有再请求接口。

(3)、给全员发消息

在user-3页面，给所有用户发消息



user-2页面console如下



user-1页面的console如下

此时服务端的console打印如下：