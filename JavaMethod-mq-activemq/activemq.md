# springboot 整合 rabbitmq
## （一）添加依赖
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
    </dependency>
## （二）配置文件
    # ActiveMQ 配置
    spring.activemq.broker-url=tcp://localhost:61616
    spring.activemq.user=admin
    spring.activemq.password=admin
## （三）配置类
    import org.apache.activemq.ActiveMQConnectionFactory;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
    import org.springframework.jms.core.JmsTemplate;
    
    @Configuration
    public class ActiveMQConfig {

        @Bean
        public ActiveMQConnectionFactory connectionFactory() {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
            factory.setBrokerURL("tcp://localhost:61616");
            factory.setUserName("admin");
            factory.setPassword("admin");
            factory.setTrustAllPackages(true); // 允许反序列化所有类
            return factory;
        }
    
        @Bean
        public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory) {
            JmsTemplate template = new JmsTemplate(connectionFactory);
            template.setSessionTransacted(true); // 启用事务
            return template;
        }
    
        @Bean
        public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {
            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
            factory.setConnectionFactory(connectionFactory);
            factory.setConcurrency("1-5"); // 设置并发消费者数量
            return factory;
        }
    }

## 三、实现消息发送与接收
### （一）创建消息生产者
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.jms.core.JmsTemplate;
    import org.springframework.stereotype.Service;
    
    @Service
    public class MessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

        /**     * 发送消息到指定的队列或主题     * @param destination 队列或主题名称     * @param message 消息内容     */
        public void sendMessage(String destination, String message) {
            jmsTemplate.send(destination, session -> session.createTextMessage(message));
            System.out.println("Message sent to " + destination + ": " + message);
        }
    }
### （二）创建消息消费者
    import org.springframework.jms.annotation.JmsListener;
    import org.springframework.stereotype.Service;
    
    @Service
    public class MessageConsumer {

        /**     * 监听指定队列的消息     * @param message 接收到的消息内容     */
        @JmsListener(destination = "myQueue")
        public void receiveMessage(String message) {
            System.out.println("Received message from myQueue: " + message);
        }
    }
### （三）测试消息发送与接收
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    
    @SpringBootTest
    class ActiveMqIntegrationTest {

        @Autowired
        private MessageProducer messageProducer;
    
        @Test
        void testSendAndReceiveMessage() {
            messageProducer.sendMessage("myQueue", "Hello, ActiveMQ!");
            System.out.println("Message sent successfully!");
        }
    }

## 持久化
默认情况下发送不存在的队列或主题，需要设置持久化，当新建该队列的消费者后，就会消费这条信息。
