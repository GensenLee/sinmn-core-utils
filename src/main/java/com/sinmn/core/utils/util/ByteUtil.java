package com.sinmn.core.utils.util;

public class ByteUtil {
	
	public static boolean isZero(Byte value)
	{
		if(toByte(value) == 0){
			return true;
		}
		return false;
	}
	
	public static byte toByte(Byte value)
	{
		if(value == null){
			return 0;
		}
		return value;
	}
	
	public static byte toByte(Object value)
	{
		
		if(value == null || "".equals(value)){
			return 0;
		}
		try{
			return Byte.valueOf(value.toString());
		}catch(Exception e){
			return 0;
		}
	}
	
	public static boolean isNotZero(Byte value)
	{
		return !isZero(value);
	}
}

