package com.dfsoft.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dfsoft.entity.AuthUserPoJo;


@Mapper
public interface UsersMapper  {

    /**
     * @Description 通过用户名 查询用户信息 角色列表 权限列表
     * @Param [username]
     **/
    AuthUserPoJo findAuthUserByUsername(@Param("username") String username);
}
