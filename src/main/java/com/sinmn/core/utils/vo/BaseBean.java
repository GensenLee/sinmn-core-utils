package com.sinmn.core.utils.vo;

import java.io.Serializable;
import java.util.Map;

import com.sinmn.core.utils.util.BeanUtil;
import com.sinmn.core.utils.util.FastJsonUtils;

public class BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BaseBean(){
		
	}
	
	/**
	 * 初始化json
	 */
	public BaseBean(String jsonString){
		BeanUtil.copy(FastJsonUtils.getBean(jsonString, this.getClass()), this);
	}
	
	/**
	 * @deception 转换为json字符串
	 * @return
	 */
	public String toJsonString(){
		return FastJsonUtils.toJsonString(this);
	}
	
	/**
	 * @deception 转换为map
	 * @return
	 */
	public Map<String,Object> toMap(){
		return FastJsonUtils.getMap(this);
	}

}
