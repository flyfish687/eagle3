package com.dfsoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfsoft.entity.RoleInfo;
import com.dfsoft.mapper.RoleInfoMapper;
import com.dfsoft.service.IRoleInfoService;

@Service
public class RoleInfoServiceImpl implements IRoleInfoService {

    @Autowired
    private RoleInfoMapper roleInfoMapper;
    /**
     *   查询全部角色及对应权限
     **/
    @Override
    public List<RoleInfo> findRoleInfoAndPermission() {
        return roleInfoMapper.findRoleInfoAndPermission();
    }
}
