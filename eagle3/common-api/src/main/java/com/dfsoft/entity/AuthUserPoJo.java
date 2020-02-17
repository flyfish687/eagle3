package com.dfsoft.entity;

import lombok.Data;

import java.util.List;

import com.dfsoft.entity.PermissionInfo;
import com.dfsoft.entity.RoleInfo;

@Data
public class AuthUserPoJo {
    private String userId;
    private String userName;
    private String passWord;
    private String status;
    private List<RoleInfo> roleInfos;
    private List<PermissionInfo> permissionInfos;
    
    public AuthUserPoJo() {
    }
    public AuthUserPoJo(String id, String username, String password, List<RoleInfo> roles) {
        this.userId = id;
        this.userName = username;
        this.passWord = password;
        this.roleInfos = roles;
    }
}
