package com.dfsoft.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 手机号
     */
    private Integer mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 是否生效
     */
    private String status;


}
