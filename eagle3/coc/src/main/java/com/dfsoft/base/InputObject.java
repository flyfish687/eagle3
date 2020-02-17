package com.dfsoft.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:接口请求基础封装类（感觉暂时这个不是太需要，可以不用）
 * @author: 史卫鹏
 * @date: 2020年2月9日 下午7:35:59
 * @Copyright:大方软件
 */
public class InputObject implements Serializable{

	/**
	 * @Field long serialVersionUID
	 * @Description TODO
	 */
	private static final long serialVersionUID = -4199155442921126322L;

	private Map<String,Object> params = new HashMap();
	
	private List<Map<String,Object>> beans = new ArrayList();
	
	//个性数据可以再添加
	
	
	
}
