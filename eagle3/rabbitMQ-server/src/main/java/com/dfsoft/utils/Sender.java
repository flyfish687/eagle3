//package com.dfsoft.utils;
//
//import java.util.Date;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.log4j.Log4j2;
//
///**
// * 发送消息类
// * @author swp
// * 2019-12-4 15:32:03
// */
//@Component
//@Log4j2
//public class Sender {
//	
//	@Autowired
//	private AmqpTemplate amqp;
//	
//    // 没有交换机，直接到queue 的
//	public void send() {
//		String msg = "msg :" + new Date();
//		log.error(msg);
//		this.amqp.convertAndSend("myQueue", msg);
//	}
//	
//	//type： Direct 处理路由键
//	public void send2() {
//		String smg = "direct 的消息" + new Date();
//		System.out.println("发送消息："+smg);
//		this.amqp.convertAndSend("exchange1","Email", smg);
//	}
//	
//	//有交换机。 扇形分发
//	public void send3() {
//		
//	}
//	
//}
