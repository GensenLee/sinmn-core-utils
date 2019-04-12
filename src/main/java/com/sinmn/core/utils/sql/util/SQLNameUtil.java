package com.sinmn.core.utils.sql.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLNameUtil {
	
	/**
	 * @Description 转换成驼峰 abc_efg -> abcEfg
	 * @author xhz
	 * @date 2017年5月26日 下午2:44:53
	 * @param s
	 * @return
	 * @lastModifier
	 */
	public static String H(String s){
		while(true){
			Pattern p = Pattern.compile("(_\\w)");
			Matcher m = p.matcher(s);
			if(m.find()){
				s = s.replace(m.group(1), m.group(1).replace("_", "").toUpperCase());
			}else{
				break;
			}
		}
		return s;
	}
	
	/**
	 * @Description 首字母大写 abc_efg -> Abc_efg
	 * @author xhz
	 * @date 2017年5月26日 下午2:49:09
	 * @param s
	 * @return
	 * @lastModifier
	 */
	public static String FU(String s){
		return s.replaceFirst("^\\w", (s.charAt(0)+"").toUpperCase());
	}
	
	/**
	 * @Description 驼峰并且首字母大写 abc_efg -> AbcEfg
	 * @author xhz
	 * @date 2017年5月26日 下午2:48:39
	 * @param s
	 * @return
	 * @lastModifier
	 */
	public static String U(String s){
		return FU(H(s));
	}
}
