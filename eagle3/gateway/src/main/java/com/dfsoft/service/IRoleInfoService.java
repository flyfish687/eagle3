package com.dfsoft.service;

import java.util.List;

import com.dfsoft.entity.RoleInfo;

public interface IRoleInfoService  {
    public List<RoleInfo> findRoleInfoAndPermission();
}
