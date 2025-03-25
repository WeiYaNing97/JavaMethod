package com.example.active.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import java.util.UUID;

@Configuration
public class ActiveMQConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;
    @Value("${spring.activemq.user}")
    private String user;
    @Value("${spring.activemq.password}")
    private String password;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        factory.setUserName(user);
        factory.setPassword(password);
        factory.setTrustAllPackages(true); // 允许反序列化所有类
        //factory.setClientID( UUID.randomUUID().toString()); // 设置客户端 ID
        return factory;
    }

    // 队列模式的 JmsTemplate
    @Bean
    public JmsTemplate queueJmsTemplate(ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setSessionTransacted(true); // 启用事务
        jmsTemplate.setPubSubDomain(false); // 设置为队列模式
        return jmsTemplate;
    }

    // 主题模式的 JmsTemplate
    @Bean
    public JmsTemplate topicJmsTemplate(ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setSessionTransacted(true); // 启用事务
        jmsTemplate.setPubSubDomain(true); // 设置为主题模式
        return jmsTemplate;
    }

    // 队列模式的监听器容器工厂
    @Bean
    public DefaultJmsListenerContainerFactory queueListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false); // 设置为队列模式
        return factory;
    }

    // 主题模式的监听器容器工厂
    @Bean
    public DefaultJmsListenerContainerFactory topicListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true); // 设置为主题模式
        factory.setConcurrency("1"); // 设置并发消费者数量
        return factory;
    }
}
