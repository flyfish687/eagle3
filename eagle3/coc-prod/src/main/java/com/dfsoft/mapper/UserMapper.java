package com.dfsoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dfsoft.entity.AuthUserPoJo;
import com.dfsoft.vo.User;


@Mapper
public interface UserMapper {
	
	User findUserByName(@Param("account") String account);
	User findUserById(@Param("id") String id);
	List<User> findUserList();
}
