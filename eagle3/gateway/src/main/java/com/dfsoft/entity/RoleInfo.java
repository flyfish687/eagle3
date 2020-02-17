package com.dfsoft.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

@Data
public class RoleInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    //角色id
    private String id;

    //角色名
    private String name;

    private String createBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String updateBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    //角色等级
    private Integer roleLevel;

    //启用  
    private String status;

    //角色说明
    private String description;

    //角色权限
    private List<PermissionInfo> permissionInfos;

}
