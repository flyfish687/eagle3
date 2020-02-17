package com.dfsoft.handler;



import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.dfsoft.constant.ResultCode;
import com.dfsoft.utils.ResUtil;
import com.dfsoft.utils.ResponseUtil;

import reactor.core.publisher.Mono;

/**
 * @Description:服务器身份验证入口点
 * @author: 史卫鹏
 * @date: 2020年1月13日 下午4:17:29
 * @Copyright:大方软件
 
 */
@Component 
public class EntryPointUnauthorizedHandler implements ServerAuthenticationEntryPoint {

	@Override
	public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
		ServerHttpResponse response = exchange.getResponse();
		return ResponseUtil.out(response, ResUtil.getJsonStr(ResultCode.FORBIDDEN, "请登录后操作"));
	}
}
