package com.dfsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import feign.Logger;

@SpringBootApplication
//开启服务发现，如果不开启进行负载均衡的时候就会报错
@EnableDiscoveryClient
@ComponentScan("com.dfsoft")
@EnableFeignClients(basePackages={"com.dfsoft.service"})
public class AppCoc  {
	
    public static void main( String[] args ) {
        SpringApplication.run(AppCoc.class, args);
    }
}
