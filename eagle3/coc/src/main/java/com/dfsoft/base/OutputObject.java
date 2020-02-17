package com.dfsoft.base;

import java.io.Serializable;

/**
 * @Description: 接口响应基础封装类
 * @author: 史卫鹏
 * @date: 2020年2月9日 下午7:23:06
 * @Copyright:大方软件
 */
public class OutputObject implements Serializable{

	/**
	 * @Field long serialVersionUID
	 * @Description TODO
	 */
	private static final long serialVersionUID = -769083935130498630L;
	//code 码
	private String returnCode;
	//响应msg
	private String returnMessage;
	//数据信息
	private Object Object;
	//如果细分，可以追加 bean beans
	//Map<String,Object> bean  ...
	//List<Map<String,Object>> beans  ...
	
	
	//这些是具体的个性化属性，具体看需不需要
	//单位 String company  ...
	//String phone ...
	
	public OutputObject() {
		
	}
	
	public OutputObject(String returnCode) {
		this.setReturnCode(returnCode);
	}
	
	public OutputObject(String returnCode,String returnMessage) {
		this.setReturnCode(returnCode);
		this.setReturnMessage(returnMessage);
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public Object getObject() {
		return Object;
	}

	public void setObject(Object object) {
		Object = object;
	}
	
}
