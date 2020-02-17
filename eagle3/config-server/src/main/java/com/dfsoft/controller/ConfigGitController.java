package com.dfsoft.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/** 
 * @Description:
 * @author: 史卫鹏
 * @date: 2019年12月10日 上午10:19:36
 * @Copyright:大方软件
 */
@RestController
//@RefreshScope
public class ConfigGitController {

	@Value("${server.port}")
	private String port = "123";
	@Value("${spring.application.name}")
	private String applicationName;
	@Value("${spring.datasource.url:默认值1}")
	private String level;
	@RequestMapping("/getconfig")
	public String say(String name) {
		return "我是提供者:"+name+"，我提供服务的端口是" + port
				+";服务名称是："+applicationName + "  log-level:"+level;
	}
}
