package com.dfsoft.handler;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.dfsoft.constant.ResultCode;
import com.dfsoft.utils.ResUtil;
import com.dfsoft.utils.ResponseUtil;

import reactor.core.publisher.Mono;


@Component
public class RestAccessDeniedHandler implements ServerAccessDeniedHandler {

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
		return ResponseUtil.out(exchange.getResponse(), ResUtil.getJsonStr(ResultCode.FORBIDDEN, "您没有权限"));
	}

}
