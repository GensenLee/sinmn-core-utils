package com.sinmn.core.utils.sql.vo;

import com.sinmn.core.utils.vo.BaseBean;

public class OperatorVO extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 操作符
	 */
	private String operator;
	
	/**
	 * 说明
	 */
	private String desc;
	
	public OperatorVO(String operator,String desc){
		this.operator = operator;
		this.desc = desc;
	}
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
