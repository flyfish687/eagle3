package com.dfsoft.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dfsoft.service.IConsulService;


@RestController
public class ConsulMainController {

	@Resource
	private IConsulService consulService;
	
	
	@RequestMapping("/getConfigInfo")
	public String getConfigInfo() {
		return consulService.getConfigInfo();
	}
	
}
