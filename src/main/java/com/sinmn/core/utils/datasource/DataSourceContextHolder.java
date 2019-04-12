package com.sinmn.core.utils.datasource;

public class DataSourceContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setDataSourceType(String type){
		if(type == null || "".equals(type.trim())){
			contextHolder.remove();
			return;
		}
		contextHolder.set(type);
	}
	
	public static String getDataSourceType(){
		return contextHolder.get();
	}
}
