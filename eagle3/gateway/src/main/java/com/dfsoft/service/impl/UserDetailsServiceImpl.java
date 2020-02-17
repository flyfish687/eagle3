package com.dfsoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dfsoft.entity.AuthUserDetails;
import com.dfsoft.entity.AuthUserPoJo;
import com.dfsoft.service.IUsersService;

import reactor.core.publisher.Mono;



/** 
 * $实现security提供的 用户信息获取接口  并按照业务增加redis 登陆限制
 **/
@Component
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {
	
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IUsersService usersService;
            
    @Override
    public Mono<UserDetails> findByUsername(String username) {
//        String value = redisTemplate.opsForValue().get("");
    	
    	//从数据库获取 用户信息
    	AuthUserPoJo authUserPoJo = usersService.findAuthUserByUsername(username);
    	
    	
    	// todo 预留调用数据库根据用户名获取用户
    	if(null==authUserPoJo){
            throw new UsernameNotFoundException("当前用户不存在");
        }
    	if(authUserPoJo.getRoleInfos()==null || authUserPoJo.getRoleInfos().isEmpty()){
            throw new UsernameNotFoundException("当前用户无角色");
        }
    	return Mono.just(new AuthUserDetails(authUserPoJo));
    }
    
}
