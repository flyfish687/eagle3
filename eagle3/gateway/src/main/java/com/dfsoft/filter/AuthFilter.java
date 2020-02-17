package com.dfsoft.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements WebFilter {
	
	//List<String> ignUrls = new ArrayList<String>();
	

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain){
		String url = exchange.getRequest().getURI().getPath();
		if(!url.contains("/actuator/health")) {
			System.out.println("通过校验的请求路径："+url);
			System.out.println("已经通过security安全校验~~~~~~~~ ");
		}
		return chain.filter(exchange);
	}

}