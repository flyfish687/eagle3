package com.dfsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfsoft.mapper.UserMapper;
import com.dfsoft.vo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUserByName(String userName) {
		return userMapper.findUserByName(userName);
	}

	@Override
	public User findUserById(String id) {
		return userMapper.findUserById(id);
	}

	@Override
	public List<User> findUserList() {
		return userMapper.findUserList();
	}

}
