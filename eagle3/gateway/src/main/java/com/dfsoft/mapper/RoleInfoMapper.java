package com.dfsoft.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dfsoft.entity.RoleInfo;



@Mapper
public interface RoleInfoMapper {

    /**
     * @Description 查询全部角色及对应权限
     * @return
     **/
    List<RoleInfo> findRoleInfoAndPermission();
}
