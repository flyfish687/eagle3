package com.dfsoft.manange;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dfsoft.constant.SecurityConstant;
import com.dfsoft.entity.RoleInfo;
import com.dfsoft.service.IRoleInfoService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

public class JWTReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext>  {
	
    private IRoleInfoService roleInfoService;
	private StringRedisTemplate redisTemplate;
    private List<RoleInfo> authorities = new ArrayList<>();
    
    //初始化authorities，把所有角色和权限关系存到
    public JWTReactiveAuthorizationManager(IRoleInfoService roleInfoService,StringRedisTemplate redisTemplate) {
    	this.roleInfoService = roleInfoService;
    	this.redisTemplate = redisTemplate;
    	
    	//读取所有角色和权限关系数据
    	authorities = roleInfoService.findRoleInfoAndPermission();
        if(authorities != null) {
            for(RoleInfo roleInfo : authorities) {
            	roleInfo.setName("ROLE_" + roleInfo.getName());
            }
        }
    }
    
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
    	//请求
    	ServerHttpRequest req = object.getExchange().getRequest();
    	return authentication
                .filter(a -> {
                	return a.isAuthenticated();
                })
                .flatMapIterable( a -> {
                	return a.getAuthorities();
                })
                .map( g-> {
                	return g.getAuthority();
                })
                .any(c->{
                	String token = req.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                	System.out.println("认证管理中读取到的token："+token);
                	//请求路径
                	String url = req.getURI().getPath();
                	String method = req.getMethodValue();
                	if (StringUtils.isNotBlank(token)) {
                        // 解析token
                        Claims claims = null;
                        try {
                            claims = Jwts.parser()
                                    .setSigningKey(SecurityConstant.tokenSigningKey)
                                    .parseClaimsJws(token.replace(SecurityConstant.TOKEN_SPLIT, ""))
                                    .getBody();
                        } catch (ExpiredJwtException e) {
                        	return false;
                        } catch (Exception e) {
                        	return false;
                        }

                        //获取用户名
                        String username = claims.getSubject();
                        //判定token
                        String oldToken = redisTemplate.opsForValue().get("token_" + username);

                        if (StringUtils.isBlank(oldToken)){
                        	return false;
                        }
                        if(!oldToken.equals(token.replace(SecurityConstant.TOKEN_SPLIT, ""))) {
                        	return false;
                        }

                        //获取redis中角色与权限关系
                        String roleInfosMapPermission = redisTemplate.opsForValue().get("authentication:roleinfos:permissions");
                        if (StringUtils.isBlank(roleInfosMapPermission)) {
                            return false;
                        }

                        //获取当前用户角色
                        String authority = claims.get(SecurityConstant.AUTHORITIES).toString();

                        Boolean haveRole = false;
                        if (StringUtils.isNotBlank(authority)) {
                            JSONArray list = JSONArray.parseArray(authority);
                            JSONArray redisData = JSONArray.parseArray(roleInfosMapPermission);
                            //循环查找权限
                            for (int i = 0; i < list.size(); i++) {
                                String userRoleId = list.getString(i);//jtw取出的角色id
                                for (int j = 0; j < redisData.size(); j++) {
                                    JSONObject redisArray = redisData.getJSONObject(j);
                                    if (userRoleId.equals(redisArray.getString("id"))) {//对比
                                        JSONArray redisDataPer = redisArray.getJSONArray("permissionInfos");//获取权限
                                        for (int z = 0; z < redisDataPer.size(); z++) {
                                            JSONObject jsonObject = redisDataPer.getJSONObject(z);
                                            if(!haveRole && method.contentEquals(jsonObject.getString("method")) && jsonObject.getString("path").contains(url)) {
                                            	return true;
                                            }
                                        }
                                    }
                                }
                            }
                        } 
                    }
                    return false;
                })
                .map( hasAuthority -> {
                	return new AuthorizationDecision(hasAuthority);
                	})
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}