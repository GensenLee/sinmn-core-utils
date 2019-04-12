package com.sinmn.core.utils.apiEnhanced.vo;

import java.util.Map;

import com.sinmn.core.utils.vo.BaseBean;

import lombok.Data;

@Data
public class ApiEnhancedHeaderVO extends BaseBean {

	/**
	 * 根据注解判断是否参与uuid计算
	 */
	private Map<String,Object> auth;
	
	/**
	 * 时间戳，实际上是一个随机数，不参与cacheable计算，但是参与 idempotent 计算
	 */
	private String timestamp;
	
	/**
	 * 数据签名,cacheable会用到
	 */
	private String signature;
	
	/**
	 * 扩展参数，备用
	 */
	private Map<String,Object> ext;
}
