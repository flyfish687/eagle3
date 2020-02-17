//package com.dfsoft.utils;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @Description:消息消费者
// * @author: 史卫鹏
// * @date: 2019年12月4日 下午3:53:36
// * @Copyright:大方软件  
// */
//@Component
//@RabbitListener(queues = "myQueue")
//public class Receiver {
//
//	@RabbitHandler
//	public void process(String msg) {
//		System.out.println("Receiver:" + msg);
//	}
//
//}
