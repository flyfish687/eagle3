package com.dfsoft.filter;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @Description:进行授权访问处理
 * @author: 史卫鹏
 * @date: 2019年12月18日 上午9:46:39
 * @Copyright:大方软件
 */
@Component
public class AuthorizedRequestFilter implements GlobalFilter, Ordered{

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
	        
		String auth = "croot:croot";// 认证的原始信息
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));// 进行加密处理
		// 在进行授权的头信息内容配置的时候加密的信息一定要“Basic”之间保持一致
		String authHeader = "Basic " + new String(encodedAuth);
		//将现在的request 变成 change对象 
		ServerHttpRequest host = exchange.getRequest().mutate().header("Authorization", authHeader).build();
        ServerWebExchange build = exchange.mutate().request(host).build();
		return chain.filter(build);
	}
}
