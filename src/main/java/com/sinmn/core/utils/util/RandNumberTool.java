package com.sinmn.core.utils.util;

import java.util.Random;

import org.joda.time.DateTime;


public class RandNumberTool {

	private static final int    four_num_lenth = 4;
	
	private static final int    six_num_lenth = 6;
	
	public static String getFourRandNumber(){
		Random random = new Random(new DateTime().getMillis());
		return StringUtil.getAddZerro(Math.abs(random.nextInt())%1000000, four_num_lenth);
	}
	
	public static String getSixRandNumber(){

		Random random = new Random(new DateTime().getMillis());
		return StringUtil.getAddZerro(Math.abs(random.nextInt())%1000000, six_num_lenth);

	}

	/**
	 * 生成一个11位的唯一字符串
	 *
	 * @return 生成的字符串
	 */
	public static String createNo() {
		long val = new DateTime().getMillis() + Integer.parseInt(getSixRandNumber());
		return Long.toHexString(val).substring(1);
	}

	public static void main(String[] args) {
		String no = createNo();
		System.out.println(no);
	}
}
