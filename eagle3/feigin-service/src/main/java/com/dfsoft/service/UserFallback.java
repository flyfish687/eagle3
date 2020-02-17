package com.dfsoft.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dfsoft.vo.User;

@Component
public class UserFallback implements IUserService{

	@Override
	public User findUserByName(String userName) {
		User u = new User();
		u.setAccount("100");
		u.setName("fallback回调降级用户");
		return u;
	}

	@Override
	public User findUserById(String id) {
		User u = new User();
		u.setAccount("100");
		u.setName("fallback回调降级用户");
		return u;
	}

	@Override
	public List<User> findUserList() {
		// TODO Auto-generated method stub
		return null;
	}

}
