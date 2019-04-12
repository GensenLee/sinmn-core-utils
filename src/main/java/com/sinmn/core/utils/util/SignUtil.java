package com.sinmn.core.utils.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.sinmn.core.utils.vo.BaseBean;

/**
 * 签名专用神器
 * @author xhz
 *
 */
public class SignUtil {

	public static String getSignString(Object param){
		if(param instanceof Map){
			return getSignString((Map)param);
		}else if(param instanceof String){
			return param.toString();
		}
		return getSignString(FastJsonUtils.getMap(param));
	}
	
	/**
	 * 排序
	 * @param paramMaps
	 * @return
	 */
	public static String getSignString(Map<String, Object> paramMaps){
		SortedMap<String, Object> sortedMap = new TreeMap<String, Object>(paramMaps);
		return getUrlParams(sortedMap);
	}
	
	/**
	 * 获取拼接的字符串  aa=aa&bb=bb&cc=cc&dd=dd
	 * @param map
	 * @return
	 */
	public static String getUrlParams(Map<String, Object> map) {
		
		List<String> result =  new ArrayList<String>();
		
		for(String key : map.keySet()){
			String strValue = "";
			Object value = map.get(key);
			if(value == null){
				strValue = "";
			}else if(value instanceof String[] || value instanceof List || value instanceof BaseBean){
				strValue = FastJsonUtils.toJsonString(value);
			}else{
				strValue = value.toString();
			}
			result.add(key+"="+strValue);
		}
		return ListUtil.join(result,"&");
	}
	
	/**
	 * 利用md5获取签名后的串
	 * @param param
	 * @param suffix
	 * @return
	 */
	public static String getSignByMD5(Object param,String suffix){
		String signStr = getSignString(param)+suffix;
		return MD5.getMD5(signStr).toLowerCase();
	}
}
