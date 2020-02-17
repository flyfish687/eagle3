package com.dfsoft.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * rabbitTemplate工具类
 **/
@RestController
public class RabbitTemplateUtils {

	@Autowired
	RabbitTemplate rabbitTemplate; // 使用RabbitTemplate,这提供了接收/发送等等方法

	/**
	 * 
	 * @param exchangeName
	 * @param routingKey  如果交换机是Fanout类型的，routingKey可以不传
	 * @param msg
	 * @return
	 */
	@GetMapping("/sendToExchangeByRoutingKey")
	public String sendToExchangeByRoutingKey(@RequestParam(value = "exchangeName", required = true) String exchangeName,
			@RequestParam(value = "routingKey", required = false) String routingKey,
			@RequestParam(value = "msg", required = true) Object msg) {
		String messageId = String.valueOf(UUID.randomUUID());
		String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Map<String, Object> map = new HashMap<>();
		map.put("messageId", messageId);
		map.put("messageData", msg);
		map.put("createTime", createTime);
		// 将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
		rabbitTemplate.convertAndSend(exchangeName, routingKey==null?"":routingKey, map);
		return "ok";
	}

	@GetMapping("/sendToQueue")
	public String sendToQueue(@RequestParam(value = "queueName", required = true) String queueName,
			@RequestParam(value = "msg", required = true) Object msg) {

		String messageId = String.valueOf(UUID.randomUUID());
		String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		Map<String, Object> map = new HashMap<>();
		map.put("messageId", messageId);
		map.put("messageData", msg);
		map.put("createTime", createTime);
		rabbitTemplate.convertAndSend(queueName, map);
		return "ok";
	}

	/**
	 * 读取队列消息 （直接应答） queueName 队列名称
	 */
	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public String receive(@RequestParam(value = "queueName", required = true) String queueName) {
		return String.valueOf(rabbitTemplate.receiveAndConvert(queueName));
	}

	/**
	 * 读取队列消息 queueName 队列名称 timeoutMillis
	 * 要等多久才会放弃。零值表示如果没有可用的消息，方法将立即返回空值。负值使方法无限期地等待消息
	 */
	@RequestMapping(value = "/receive2", method = RequestMethod.GET)
	public String receive2(@RequestParam(value = "queueName", required = true) String queueName,
			@RequestParam(value = "timeoutMillis", required = true) long timeoutMillis) {
		return String.valueOf(rabbitTemplate.receiveAndConvert(queueName, timeoutMillis));
	}
}
