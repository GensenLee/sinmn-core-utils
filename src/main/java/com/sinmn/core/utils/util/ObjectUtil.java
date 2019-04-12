package com.sinmn.core.utils.util;

public class ObjectUtil {

	public static String object2String(Object value) {
		if(value == null) {
			return "";
		}
		return value.toString();
	}
}
