package com.sinmn.core.utils.sql.vo;

import com.sinmn.core.utils.vo.BaseBean;

public class ConditionVO extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 条件
	 */
	private String condition;
	
	/**
	 * 中文意思
	 */
	private String desc;
	
	public ConditionVO(String condition,String desc){
		this.condition = condition;
		this.desc = desc;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	

}
