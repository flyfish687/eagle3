package com.dfsoft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dfsoft.vo.User;


public interface UserService {

	/**
	 * 获取用户信息
	 * 
	 * @param userName
	 * @return
	 */
	User findUserByName(String userName);

	/**
	 * 获取用户信息
	 * 
	 * @param userName
	 * @return
	 */
	User findUserById(String id);

	List<User> findUserList();

}
