package com.dfsoft.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dfsoft.service.TransformerService;

/**
 * 变压器服务接口
 * @author swp
 */
@RestController
public class TransformerController {

	@Autowired
	private TransformerService transformerService;

	@RequestMapping(value = "/findTransformer", method = RequestMethod.GET)
	public List<Map<String, Object>> findTransformer() {
		return transformerService.findTransformer();
	}

	@RequestMapping(value = "/findTransformerById", method = RequestMethod.GET)
	public Map<String, Object> findTransformerById(@RequestParam(value = "id", required = true) String id) {
		Map<String, Object> ret = transformerService.findTransformerById(id);
		return ret;
	}

	@RequestMapping(value = "/addTransformer", method = RequestMethod.GET)
	public boolean addTransformer(@RequestParam(value = "id") String id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "modelCode", required = true) String modelCode,
			@RequestParam(value = "capacity", required = true) String capacity) {
		Map<String, Object> addMap = new HashMap<String, Object>();
		addMap.put("id", id==null||"".equals(id)?UUID.randomUUID().toString().replace("-", ""):id);
		addMap.put("name", name);
		addMap.put("modelCode", modelCode);
		addMap.put("capacity", capacity);

		return transformerService.addTransformer(addMap);
	}

	@RequestMapping(value = "/updateTransformer", method = RequestMethod.GET)
	public boolean updateTransformer(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "name", required = false) String name, 
			@RequestParam(value = "modelCode", required = false) String modelCode,
			@RequestParam(value = "capacity", required = false) String capacity) {
		Map<String, Object> addMap = new HashMap<String, Object>();
		addMap.put("id", id);
		addMap.put("name", name);
		addMap.put("modelCode", modelCode);
		addMap.put("capacity", capacity);

		return transformerService.updateTransformer(addMap);
	}

	@RequestMapping(value = "/delTransformer", method = RequestMethod.GET)
	public boolean delTransformer(@RequestParam(value = "id", required = true) String id) {
		return transformerService.delTransformer(id);
	}

}
