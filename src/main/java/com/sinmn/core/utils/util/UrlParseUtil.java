package com.sinmn.core.utils.util;

import java.util.HashMap;
import java.util.Map;

public class UrlParseUtil {

	/**
	 * 给定一个带http的完整的URL，把参数解析出来
	 * @param url
	 * @return
	 */
	public static Map<String,String> urlParse(String url){
		Map<String,String> param = new HashMap<String,String>();
		for(String s : url.split("&")){
			String[] kv = s.split("=");
			if(kv.length == 2){
				param.put(kv[0], kv[1]);
			}
		}
		return param;
	}
}
