package com.dfsoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfsoft.entity.AuthUserPoJo;
import com.dfsoft.mapper.UsersMapper;
import com.dfsoft.service.IUsersService;

/**
 * 服务实现类
 */
@Service
public class UsersServiceImpl implements IUsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public AuthUserPoJo findAuthUserByUsername(String username) {
        return usersMapper.findAuthUserByUsername(username);
    }
}
