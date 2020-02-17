package com.dfsoft.handler;

import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.dfsoft.entity.AuthUserDetails;
import com.dfsoft.entity.AuthUserPoJo;
import com.dfsoft.entity.RoleInfo;
import com.dfsoft.response.MessageCode;
import com.dfsoft.response.WsResponse;
import com.dfsoft.service.impl.RoleInfoServiceImpl;
import com.dfsoft.utils.TokenUtil;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationSuccessHandler extends WebFilterChainServerAuthenticationSuccessHandler {

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private StringRedisTemplate redisTemplate;

    @Autowired
    private RoleInfoServiceImpl roleInfoService;
	

	@Override
	public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {

		ServerWebExchange exchange = webFilterExchange.getExchange();
		ServerHttpResponse response = exchange.getResponse();
		// 设置headers
		HttpHeaders httpHeaders = response.getHeaders();
		httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
		httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
		// 设置body
		WsResponse wsResponse = WsResponse.success();
		byte[] dataBytes = {};
		ObjectMapper mapper = new ObjectMapper();
		try {
			AuthUserPoJo user = (AuthUserPoJo) authentication.getPrincipal();
			AuthUserDetails userDetails = buildUser(user);

			String Username = authentication.getName();

			// 在redis中查询用户之前是否登入
			String oldToken = redisTemplate.opsForValue().get("token_" + Username);
			if (!StringUtils.isBlank(oldToken)) {
				// 清除旧Token
				redisTemplate.delete("token_" + Username);
			}

			String roleInfosMapPermission = redisTemplate.opsForValue().get("authentication:roleinfos:permissions");
			if (StringUtils.isBlank(roleInfosMapPermission)) {
				// 将角色与权限关系存入redis
				List<RoleInfo> roleInfos = roleInfoService.findRoleInfoAndPermission();
				redisTemplate.opsForValue().set("authentication:roleinfos:permissions", JSON.toJSONString(roleInfos),
						480, TimeUnit.MINUTES);
			}

			// 创建token
			String accessToken = tokenUtil.createAccessJwtToken(userDetails);

			// 存入redis
			redisTemplate.opsForValue().set("token_" + Username, accessToken, 480, TimeUnit.MINUTES);
			
			httpHeaders.add(HttpHeaders.AUTHORIZATION, accessToken);
			wsResponse.setResult(userDetails);
			dataBytes = mapper.writeValueAsBytes(wsResponse);
		} catch (Exception ex) {
			ex.printStackTrace();
			JsonObject result = new JsonObject();
			result.addProperty("status", MessageCode.COMMON_FAILURE.getCode());
			result.addProperty("message", "授权异常");
			dataBytes = result.toString().getBytes();
		}
		DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
		return response.writeWith(Mono.just(bodyDataBuffer));
	}

	private AuthUserDetails buildUser(AuthUserPoJo authUserPoJo) {
		return new AuthUserDetails(authUserPoJo);
	}

}