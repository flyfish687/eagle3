package com.dfsoft.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dfsoft.config.FeignClientConfig;

/**
 * 发电机管理Service
 * @author swp
 * 2019-11-29 17:21:03
 */
@FeignClient(value = "coc-prod",configuration=FeignClientConfig.class)
public interface TransformerService {
	
	/**
	 * 新增
	 * @param userName
	 * @return
	 */
	@RequestMapping("/addTransformer")
	boolean addTransformer(@RequestParam("id") String id,@RequestParam("name") String name,
			@RequestParam("modelCode") String modelCode,@RequestParam("capacity") String capacity);
	
	/**
	 * 删除
	 * @param userName
	 * @return
	 */
	@RequestMapping("/delTransformer")
	boolean delTransformer(@RequestParam("id") String id);
	
	/**
	 * 查询所有
	 * @param userName
	 * @return
	 */
	@RequestMapping("/findTransformer")
	List<Map<String,Object>> findTransformer();
	
	/**
	 * 查询所有 by ID
	 * @param userName
	 * @return
	 */
	@RequestMapping("/findTransformerById")
	Map<String,Object> findTransformerById(@RequestParam("id") String id);
	
	
	/**
	 * 修改
	 * @param userName
	 * @return
	 */
	@RequestMapping("/updateTransformer")
	boolean updateTransformer(@RequestParam("id") String id,@RequestParam("name") String name,
			@RequestParam("modelCode") String modelCode,@RequestParam("capacity") String capacity);
}
