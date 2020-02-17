package com.dfsoft;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import com.dfsoft.utils.SpringUtil;





@SpringBootApplication
@EnableDiscoveryClient
public class AppGateway  {
    public static void main( String[] args ) {
    	SpringApplication springApplication = new SpringApplication(AppGateway.class);
    	ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
    	// 将Context设置到SpringUtil中
    	SpringUtil.setApplicationContext(configurableApplicationContext);
    }
}
