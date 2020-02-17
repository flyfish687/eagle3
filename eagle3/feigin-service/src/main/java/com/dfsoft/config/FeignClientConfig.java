package com.dfsoft.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;

/**
 * feign 授权配置
 * @Description:
 * @author: 史卫鹏
 * @date: 2019年12月17日 下午5:14:15
 * @Copyright:大方软件
 */
@Configuration
public class FeignClientConfig {

	/**
	 * 打印feign的所有日志项
	 * @return
	 */
	@Bean
	public Logger.Level getFeignLoggerLevel() {
		return feign.Logger.Level.FULL;
	}

	@Bean
	public BasicAuthRequestInterceptor getBasicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("root", "root");
	}

	/**
	 * 负载规则配置
	 * @return
	 */
	@Bean
	public IRule ribbonRule() {
		// 负载均衡规则，改为随机
		//return new RandomRule();// 负载均衡规则，改为随机
		return new RoundRobinRule();   //配置规则 轮询
		//return new RetryRule(); // 配置规则 重试
		//return new WeightedResponseTimeRule(); // 配置规则 响应时间权重
		//return new BestAvailableRule(); // 配置规则 最空闲连接策略
	}
}
