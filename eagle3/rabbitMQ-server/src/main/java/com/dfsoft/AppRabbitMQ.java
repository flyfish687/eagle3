package com.dfsoft;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;




/**
 * 自动配置
 * 1）RabbitAutoConfiguration
 * 2）有自动配置了连接工厂 ConnectionFactory
 * 3）RabbitProperties 封装了 RabbitMQ的配置
 * 4）RabbitTemplate 给RabbitMQ发送和接受消息
 * 5）AmqpAdmin RabbitMQ系统的管理功能组件
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
public class AppRabbitMQ {
	public static void main(String[] args) {
		SpringApplication.run(AppRabbitMQ.class, args);
	}
}
