package com.dfsoft.utils;



import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class ResponseUtil {

	
    /**
     * $使用response输出JSON
     * @param response
     * @param res      
     */
    public static Mono<Void> out( ServerHttpResponse response, String res) {
        response.setStatusCode(HttpStatus.valueOf(JSONObject.parseObject(res).getInteger("code")));
        response.getHeaders().add("Content-Type", "application/json; charset=UTF-8");
    	response.getHeaders().add("Cache-Control","no-store, max-age=0, no-cache, must-revalidate");
    	response.getHeaders().add("Cache-Control", "post-check=0, pre-check=0");
    	response.getHeaders().add("Pragma", "no-cache");
        
        JsonObject result = new JsonObject();
        result.addProperty("status", JSONObject.parseObject(res).getInteger("code"));
        result.addProperty("message", res);
        byte[] dataBytes=result.toString().getBytes();
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

    /**
     * $网关拒绝，返回401
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

}
