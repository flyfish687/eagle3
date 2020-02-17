package com.dfsoft.controller;

import java.util.Map;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AmqpAdminUtils {

	@Autowired
	private AmqpAdmin amqpAdmin;
	
	/**
	 *  declareExchange 创建Exchange操作
		deleteExchange 删除Exchange操作
		declareQueue 创建Queue操作
		deleteQueue 删除Queue操作
		purgeQueue 清空队列
		declareBinding 绑定操作，绑定Queue，Exchange
		removeBinding 删除绑定操作
	 */
	
	

	/**
	 * 创建交换机
	 */
	@RequestMapping(value = "/createExchange", method = RequestMethod.GET)
	public String createExchange(@RequestParam(value = "exchangeName", required = true) String exchangeName,
			@RequestParam(value = "exchangeType", required = true) String exchangeType) {
		
		if(!exchangeName.isEmpty() && !exchangeType.isEmpty()) {
			if("direct".equals(exchangeType)) {
				// 创建交换机, 类型为 direct
		        // durable 参数表示是否持久化
				amqpAdmin.declareExchange(new DirectExchange(exchangeName, false, false));
			}else if("topic".equals(exchangeType)) {
				// 创建交换机，类型为 topic
		        // durable 参数表示是否持久化
				amqpAdmin.declareExchange(new TopicExchange(exchangeName, false, false));
			}else if("fanout".equals(exchangeType)) {
				// 创建交换机，类型为 fanout
		        // durable 参数表示是否持久化
				amqpAdmin.declareExchange(new FanoutExchange(exchangeName, false, false));
			}else {
				return "false";
			}
			return "success";
		}
		return "false";
	}
	
	/**
	 * 删除交换机
	 */
	@RequestMapping(value = "/deleteExchange", method = RequestMethod.GET)
	public boolean deleteExchange(@RequestParam(value = "exchangeName", required = true) String exchangeName) {
		return amqpAdmin.deleteExchange(exchangeName);
	}
	
	/**
     * 创建队列
     */
	@RequestMapping(value = "/createQueue", method = RequestMethod.GET)
	public String createQueue(@RequestParam(value = "queueName", required = true) String queueName) {
		if(!queueName.isEmpty()) {
			// 创建队列
	        // durable 参数表示是否持久化
			amqpAdmin.declareQueue(new Queue(queueName, false));
			return "success";
		}
		return "false";
    }

	/**
     * 删除队列
     */
	@RequestMapping(value = "/deleteQueue", method = RequestMethod.GET)
	public boolean deleteQueue(@RequestParam(value = "queueName", required = true) String queueName) {
		return amqpAdmin.deleteQueue(queueName);
    }
	
	/**
     * 删除队列
     * queueName  队列名称
     * unused  是否在队列只有在不使用时才应删除
     * empty   是否仅当队列为空时才应删除该队列
     */
	@RequestMapping(value = "/deleteQueue2", method = RequestMethod.GET)
	public void deleteQueue2(@RequestParam(value = "queueName", required = true) String queueName,
			@RequestParam(value = "unused", required = true) boolean unused,
			@RequestParam(value = "empty", required = true) boolean empty) {
		
		
		
		amqpAdmin.deleteQueue(queueName, unused, empty); 
    }
	
	/**
     * 绑定
     * destination     队列名称
     * exchange     交换
     * routingKey     路由件   如果绑定的交换机类型为fanout，routingKey 可以随便写个值
     * arguments     参数
     */
	@RequestMapping(value = "/declareBinding", method = RequestMethod.GET)
	public void declareBinding(@RequestParam(value = "destination", required = true) String destination,
			@RequestParam(value = "exchange", required = true) String exchange,
			@RequestParam(value = "routingKey", required = true) String routingKey,
			@RequestParam(value = "arguments", required = false) Map<String, Object> arguments) {
		
		amqpAdmin.declareBinding(new Binding(destination,
				Binding.DestinationType.QUEUE,exchange,routingKey,arguments));
	}
	
	/**
     * 删除绑定操作
     * destination     队列名称
     * exchange     交换
     * routingKey     路由件   如果绑定的交换机类型为fanout，routingKey 可以随便写个值
     * arguments     参数
     */
	@RequestMapping(value = "/removeBinding", method = RequestMethod.GET)
	public void removeBinding(@RequestParam(value = "destination", required = true) String destination,
			@RequestParam(value = "exchange", required = true) String exchange,
			@RequestParam(value = "routingKey", required = true) String routingKey,
			@RequestParam(value = "arguments", required = false) Map<String, Object> arguments) {
		amqpAdmin.removeBinding(new Binding(destination,Binding.DestinationType.QUEUE,exchange,routingKey,null));
	}
	
	
	
	
	/**
     * 清空队列
     * queueName     队列名称
     * noWait     是否等待清除完成
     */
	@RequestMapping(value = "/purgeQueue", method = RequestMethod.GET)
	public void purgeQueue(@RequestParam(value = "queueName", required = true) String queueName,
			@RequestParam(value = "noWait", required = true) boolean noWait) {
		amqpAdmin.purgeQueue(queueName, noWait);
	}
	
	
	
//	public void createExchange() {
//		// 创建交换器
//		// amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//		// 创建队列（如果存在同名，则不创建）
//		amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
//		// 创建绑定规则 new Binding(目的地，目的地类型，交换器名字，路由件，参数头)
//		// amqpAdmin.declareBinding(new Binding("amqpadmin.queue",
//		// Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));
//		// 删除队列
//		// amqpAdmin.deleteQueue("amqpadmin.queue");
//	}

}
