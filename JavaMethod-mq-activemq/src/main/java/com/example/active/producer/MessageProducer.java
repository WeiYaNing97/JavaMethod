package com.example.active.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.DeliveryMode;
import javax.jms.TextMessage;

/**
 * 在使用 Spring JMS 时，`jmsTemplate.convertAndSend()` 和 `jmsTemplate.send()` 是两种发送消息到 JMS 队列的常见方法。它们的主要区别在于如何处理消息对象以及消息的创建方式。
 *
 * ---
 *
 * ### **1. `jmsTemplate.convertAndSend(queueName, message)`**
 * - **作用**: 自动将传入的对象（比如一个字符串、POJO 等）转换为 JMS 消息。
 * - **特点**:
 *   - 使用 `MessageConverter` 将对象转换为 JMS 消息（通常是 `TextMessage` 或 `ObjectMessage`）。
 *   - 默认情况下，Spring 使用 `SimpleMessageConverter`，它可以处理常见的类型（如 `String`、`byte[]`、`Serializable` 对象等）。
 *   - 如果需要自定义消息转换逻辑，可以提供一个自定义的 `MessageConverter` 实现。
 * - **适用场景**: 当你只需要发送简单的对象（例如字符串或序列化对象）而不需要手动构造复杂的 JMS 消息时。
 * - **示例**:
 *   ```java
 *   jmsTemplate.convertAndSend("myQueue", "Hello, World!");
 *   ```
 *   在这个例子中，`"Hello, World!"` 会被自动转换为一个 `TextMessage`。
 *
 * ---
 *
 * ### **2. `jmsTemplate.send(queueName, messageCreator)`**
 * - **作用**: 允许手动创建和配置 JMS 消息。
 * - **特点**:
 *   - 需要提供一个 `MessageCreator` 实现，用于创建 JMS 消息。
 *   - 可以完全控制消息的内容和属性（例如设置消息头、属性等）。
 *   - 更灵活，但代码更复杂。
 * - **适用场景**: 当你需要对消息进行精细控制（例如添加自定义的消息头或属性）时。
 * - **示例**:
 *   ```java
 *   jmsTemplate.send("myQueue", session -> {
 *       TextMessage message = session.createTextMessage("Hello, World!");
 *       message.setStringProperty("customProperty", "value");
 *       return message;
 *   });
 *   ```
 *   在这个例子中，你可以看到如何通过 `MessageCreator` 手动创建 `TextMessage` 并设置自定义属性。
 *
 * ---
 *
 * ### **主要区别总结**
 *
 * | 特性                     | `convertAndSend`                      | `send`                                |
 * |--------------------------|---------------------------------------|---------------------------------------|
 * | **消息创建方式**         | 自动转换（使用 `MessageConverter`）   | 手动创建（需要实现 `MessageCreator`） |
 * | **灵活性**               | 较低，适合简单场景                   | 较高，适合复杂场景                   |
 * | **是否需要手动配置消息** | 不需要                               | 需要                                 |
 * | **适用场景**             | 发送简单对象（如字符串、POJO）        | 需要定制化消息内容或属性时            |
 *
 * ---
 *
 * ### **选择建议**
 * - 如果只是发送简单的消息（如字符串或 POJO），推荐使用 `convertAndSend`，因为它更简洁。
 * - 如果需要对消息进行更多控制（如设置消息头、属性等），则使用 `send` 方法，并通过 `MessageCreator` 手动创建消息。
 *
 * 希望这能帮助你更好地理解两者之间的区别！
 */
@Service
public class MessageProducer {

    @Autowired
    private JmsTemplate queueJmsTemplate;

    /**
     * 发送消息到指定的队列或主题
     * @param destination 队列或主题名称
     * @param message 消息内容     */
    public void sendMessage(String destination, String message) {
        // 使用jmsTemplate发送消息，并设置为持久化
        queueJmsTemplate.send(destination, session -> {
            TextMessage textMessage = session.createTextMessage(message);
            textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT); // 设置消息为持久化模式
            return textMessage;
        });
        // 打印消息发送成功的信息
        //System.out.println("Message sent to " + destination + ": " + message);
    }

}
