package com.dfsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dfsoft.service.UserService;
import com.dfsoft.vo.User;


@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	/**
	 * 通过名称查找用户
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/findUserByName", method = RequestMethod.GET)
	public User findUserByName(@RequestParam(value = "userName", required = true) String userName) {
		return userService.findUserByName(userName);
	}
	
	/**
	 * 通过ID查找用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findUserById", method = RequestMethod.GET)
	public User findUserById(@RequestParam(value = "id", required = true) String id) {
		User u = userService.findUserById(id);
		return u;
	}
	
	@RequestMapping(value = "/findUserList", method = RequestMethod.GET)
	public List<User> findUserList() {
		return userService.findUserList();
	}
}
