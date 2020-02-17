package com.dfsoft.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dfsoft.service.IUserService;
import com.dfsoft.vo.User;

@RestController
public class UserController {


	@Resource
	private IUserService userService;

	@RequestMapping("/findUserById")
	public String findUserById(@RequestParam(value = "id", required = true) String id) {
		User u = userService.findUserById(id);
		return u.toString();
	}

	@RequestMapping("/findUserByName")
	public String findUserByName(@RequestParam(value = "name", required = true) String name) {
		User u = userService.findUserByName(name);
		return u.toString();
	}

	@RequestMapping(value = "/findUserList", method = RequestMethod.GET)
	public List<User> findUserList() {
		return userService.findUserList();
	}
	
//	@RequestMapping("/findUserByName")
//	public String findUserByName(String userName) {
//		OutputObject out = new OutputObject("0","成功");
//		
//		User u = userService.findUserByName(userName);
//		out.setObject(u);
//		
//		return out.toJson();
//		//return u.toString();
//	}
}
