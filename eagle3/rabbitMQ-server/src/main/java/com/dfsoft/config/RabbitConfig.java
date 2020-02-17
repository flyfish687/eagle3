//package com.dfsoft.config;
//
//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Exchange;
//import org.springframework.amqp.core.ExchangeBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import lombok.extern.log4j.Log4j2;
//
///**
// * @Description: rabbitMq 配置类
// * @author: 史卫鹏
// * @date: 2019年12月4日 下午3:59:49
// * @Copyright:大方软件  
// */
//@Configuration
//@Log4j2
//public class RabbitConfig {
//
//	/**
//	 * 创建 RabbitMQ 连接工厂
//	 * 
//	 * @return
//	 */
//	@Bean
//	public ConnectionFactory connectionFactory() {
//		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//		// rabbitmq 服务地址
//		connectionFactory.setAddresses("192.168.72.138:5672");
//		// 用户名
//		connectionFactory.setUsername("admin");
//		// 密码
//		connectionFactory.setPassword("admin");
//		// 虚拟机路径
//		connectionFactory.setVirtualHost("/");
//
//		return connectionFactory;
//	}
//
//	/**
//	 * 创建 RabbitAdmin 类，这个类封装了对 RabbitMQ 管理端的操作！
//	 *
//	 * 比如：Exchange 操作，Queue 操作，Binding 绑定 等
//	 *
//	 * @param connectionFactory
//	 * @return
//	 */
//	@Bean
//	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
//		// 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
//		rabbitAdmin.setAutoStartup(true);
//		return rabbitAdmin;
//	}
//
//
//	/**
//	 * 交换机类型： FanoutExchange 将消息分发到所有的绑定队列，无 routingKey 的概念。 
//	 * HeadersExchange 通过添加属性
//	 * key-value 匹配 DirectExchange 按照 routingKey 分发到知道队列 TopicExchange 多关键字匹配
//	 */
//
//	/**
//	 * 创建 一个 topic 的交换机
//	 * @return
//	 */
//	@Bean
//	public TopicExchange topicExchange() {
//		// 创建交换机，类型为 topic
//		// durable 参数表示是否持久化
//		return new TopicExchange("test.topic", true, false);
//	}
//
//	/**
//	 * 创建一个 队列
//	 * 
//	 * @return
//	 */
//	@Bean
//	public Queue queue() {
//		// 创建队列
//		// durable 参数表示是否持久化
//		return new Queue("test.topic.queue", true);
//	}
//
//	/**
//	 * 创建一个 绑定操作 ，绑定 test.topic 交换机 和 test.topic.queue 队列
//	 * 
//	 * @return
//	 */
//	@Bean
//	public Binding binding() {
//		return BindingBuilder.bind(queue()) // 直接创建队列
//				.to(topicExchange()) // 直接创建交换机，并建立关联关系
//				.with("routing_topic"); // 指定路由 key
//	}
//}