package com.dfsoft.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

import com.dfsoft.filter.AuthorizedRequestFilter;

import reactor.core.publisher.Mono;

/**
 * @Description: zuul的配置类
 * @author: 史卫鹏
 * @date: 2019年12月18日 上午10:24:37
 * @Copyright:大方软件
 */
@Configuration
public class ZuulConfig {

	/**
	 * $配合redis 实现限流
	 * @return
	 */
//	@Bean
//	public KeyResolver uriKeyResolver() {
//		return new KeyResolver() {
//			@Override
//			public Mono<String> resolve(ServerWebExchange exchange) {
//				// TODO Auto-generated method stub
//				System.out.println(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
//				// 获取用户的IP 地址，进行限流
//				return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
//			}
//		};
//	}

	@Bean
	public AuthorizedRequestFilter getAuthorizedRequestFilter() {
		return new AuthorizedRequestFilter();
	}

//	@Bean
//	public TokenFilter tokenFilter() {
//		return new TokenFilter();
//	}

}
