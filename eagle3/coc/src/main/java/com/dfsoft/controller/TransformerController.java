package com.dfsoft.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dfsoft.service.TransformerService;


/**
 * 变电器管理Controller
 * @author swp
 * 2019-11-29 17:26:10
 */
@RestController
public class TransformerController {
	
	@Autowired
	private TransformerService transformerService;
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public List<Map<String, Object>> findTransformer() {
		return transformerService.findTransformer();
	}

	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	public Map<String, Object> findTransformerById(@RequestParam(value = "id", required = true) String id) {
		return transformerService.findTransformerById(id);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public boolean addTransformer(@RequestParam(value = "id") String id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "modelCode", required = true) String modelCode,
			@RequestParam(value = "capacity", required = true) String capacity) {

		return transformerService.addTransformer(id,name,modelCode,capacity);
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public boolean updateTransformer(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "name", required = false) String name, 
			@RequestParam(value = "modelCode", required = false) String modelCode,
			@RequestParam(value = "capacity", required = false) String capacity) {

		return transformerService.updateTransformer(id,name,modelCode,capacity);
	}

	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public boolean delTransformer(@RequestParam(value = "id", required = true) String id) {
		return transformerService.delTransformer(id);
	}
}
