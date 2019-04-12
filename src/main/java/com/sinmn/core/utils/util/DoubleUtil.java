package com.sinmn.core.utils.util;

import java.math.BigDecimal;

public class DoubleUtil {

	public static String Double2String(Object object) {
		if(object == null || "".equals(object)) {
			return "0.0";
		}
		return object+"";
	}
	
	public static double toDouble(Double d) {
		if(d == null ) {
			return 0.0;
		}
			return d;
	}
	
	public static double toDouble(Object o) {
		if(o == null ) {
			return 0.0;
		}
		try{
			return Double.parseDouble(o.toString());
		}catch(Exception e){
			return 0.0;
		}
	}
	
	public static double BigDecimal2Double(BigDecimal bd) {
		if(bd == null ||"".equals(bd)) {
			return 0.0;
		}
		return bd.doubleValue();
	}
	
	public static boolean isZero(Double d) {
		if(toDouble(d) == 0.0 ) {
			return true;
		}
		return false;
	}
	
	public static boolean isNoZero(Double d) {
		return !isZero(d);
	}
}
