package com.dfsoft.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/** 
 * @Description:
 * @author: 史卫鹏
 * @date: 2019年12月10日 下午1:54:34
 * @Copyright:大方软件
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class User implements Serializable{
	private String id;
	private String name;
	private String sex;
	private String account;
	private String password;
	
	public String toString() {
		return " ID: "+this.id+" name: "+this.name+" sex: "+this.sex+
				" account: "+this.account+" pwd: "+this.password;
	}
}

