package com.dfsoft.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConsulMainController {

	@Value("${server.port:默认值}")
	private String port;
	@Value("${spring.application.name}")
	private String applicationName;
	
	@RequestMapping("/getConfigInfo")
	public String getConfigInfo() {
		return "我提供服务的端口是" + port+";服务名称是："+applicationName;
	}
}
