package com.dfsoft.handler;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dfsoft.response.MessageCode;
import com.dfsoft.response.WsResponse;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFaillHandler  implements ServerAuthenticationFailureHandler {

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException e) {
		
        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpResponse response = exchange.getResponse();
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        //设置body
        WsResponse<String> wsResponse = WsResponse.failure(MessageCode.COMMON_AUTHORIZED_FAILURE);
        if (e instanceof UsernameNotFoundException) {
        	wsResponse = WsResponse.failure(MessageCode.USER_NOT_EXISTS);
        } else if (e instanceof BadCredentialsException) {
        	wsResponse = WsResponse.failure( MessageCode.USERNAME_PASSWORD_ERROR);
        } else if (e instanceof LockedException) {
        	wsResponse = WsResponse.failure( MessageCode.USER_LOCKED);
        }
        
        byte[]   dataBytes={};
        try {
            ObjectMapper mapper = new ObjectMapper();
            dataBytes=mapper.writeValueAsBytes(wsResponse);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }
}
