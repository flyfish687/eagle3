package com.dfsoft.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dfsoft.config.FeignClientConfig;

/**
 * @Description: 测试 consul 负载均衡能力
 * @author: 史卫鹏
 * @date: 2019年12月31日 上午10:58:35
 * @Copyright:大方软件  
 */
@FeignClient(value = "coc-prod", configuration = FeignClientConfig.class)
public interface IConsulService{
	
	@RequestMapping("/getConfigInfo")
	String getConfigInfo();
	
}
