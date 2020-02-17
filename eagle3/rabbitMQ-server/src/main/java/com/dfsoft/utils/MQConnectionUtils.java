package com.dfsoft.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * rabbitMQ 工具类
 * @author swp
 * 2019-12-4 10:53:26
 */
public class MQConnectionUtils {
	
	
	/**
	 * 创建消息队列连接
	 * @return
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public static Connection newConnection() throws IOException, TimeoutException {
		//1.创建连接工厂
		ConnectionFactory fatory = new ConnectionFactory();
		//2.设置连接
		fatory.setHost("127.0.0.1");
		//3.设置用户名
		fatory.setUsername("guest");
		//4.设置用户密码
		fatory.setPassword("guest");
		//5.设置amqp协议端口号
		fatory.setPort(5672);
		//6.设置VirtualHost地址
		fatory.setVirtualHost("/");
		
		return fatory.newConnection();
	}
	
	
	
}
