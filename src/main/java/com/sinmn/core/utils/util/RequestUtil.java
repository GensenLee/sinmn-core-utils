package com.sinmn.core.utils.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	/**
	 * 获取域名
	 * @param request
	 * @return
	 */
	public static String getDomain(HttpServletRequest request){
		StringBuffer url = request.getRequestURL();  
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
		return tempContextUrl;
	}
}
