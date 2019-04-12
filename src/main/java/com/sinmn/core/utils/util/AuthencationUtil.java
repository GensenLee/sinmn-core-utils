package com.sinmn.core.utils.util;

/**
 * 权限验证工具类
 *
 */
public class AuthencationUtil {

	/**
	 * 用于加密用户名的 key
	 */
	private static final String HASH_KEY = "$ILKLKOI*-UU&^%GKJ-2EOI-LKKP-JUJKJ9";
	
	/** 一年有多少秒 */
	public static final int YEAR_SECONDS = 365 * 24 * 3600;
	
	/**
	 * 返回一个加密后的字符串
	 * @return
	 */
	public static String getCode(String ... params) {
		String key = "";
		for(String param : params){
			key+="|"+param;
		}
		return EncryptUtil.getSHA1(key + "|" + HASH_KEY);
	}
}
