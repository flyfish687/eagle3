package com.dfsoft.service;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dfsoft.config.FeignClientConfig;
import com.dfsoft.vo.User;
/**
 * 
 * @Description:
 * @author: 史卫鹏
 * @date: 2019年12月17日 下午5:16:18
 * @Copyright:大方软件
 */
@FeignClient(value = "coc-prod",configuration=FeignClientConfig.class,fallback = UserFallback.class)
public interface IUserService{
	@RequestMapping("/findUserByName")
	User findUserByName(@RequestParam("userName") String userName);
	
	@RequestMapping("/findUserById")
	User findUserById(@RequestParam("id") String id);
	
	@RequestMapping("/findUserList")
	List<User> findUserList();
	
}
