package com.dfsoft.service;

import com.dfsoft.entity.AuthUserPoJo;

/**
 * 用户服务相关接口
 */
public interface IUsersService  {

    /**
     * 通过用户名 查询用户信息 角色列表 权限列表
     **/
    AuthUserPoJo findAuthUserByUsername(String username);

}
