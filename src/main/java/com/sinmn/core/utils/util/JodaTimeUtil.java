package com.sinmn.core.utils.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * joda-time
 * Joda-Time提供了一组Java类包用于处理包括ISO8601标准在内的date和time
 * 可以利用它把JDK Date和Calendar类完全替换掉
 *
 * @author henser 2015年10月27日
 */
public class JodaTimeUtil {

	//时间格式常量
	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	private static final String YYYY_MM_DD_HH_MM    = "yyyy-MM-dd HH:mm";
	private static final String YYYY_MM_DD          = "yyyy-MM-dd";
	private static final String YYYY_MM          	= "yyyy-MM";
	private static final String HH_MM_SS 			= "HH:mm:ss";
	private static final String HH_MM 			    = "HH:mm";

	private static final String YYMMDD = "yyMMdd";

	private static final String YYMMddHHMMSS = "yyMMddHHMMSS";
	
	private static final String yyMMddHHmmss = "yyMMddHHmmss";

	private static final String YYYYMMDD = "yyyyMMdd";

	/**
	 * 获取6位日期 yyMMdd
	 * @return
	 */
	public static String getYYMMdd() {

		DateTime dateTime = new DateTime();
		return date2String( dateTime,YYMMDD );

	}

	/**
	 * 获取8位日期 yyMMdd
	 * @return
	 */
	public static String getYYYYMMDD() {

		DateTime dateTime = new DateTime();
		return date2String( dateTime,YYYYMMDD );

	}

	/**
	 * 获取12位日期 yyMMddHHMMSS
	 * @return
	 */
	public static String getYYMMddHHMMSS() {

		DateTime dateTime = new DateTime();
		return date2String( dateTime,YYMMddHHMMSS );

	}
	
	public static String getyyMMddHHmms(){
		DateTime dateTime = new DateTime();
		
		return date2String(dateTime,yyMMddHHmmss);
	}

	/**
	 * 获取当天的时分 HH:mm
	 * @return
	 */
	public static String getCurHHmm() {

		DateTime dateTime = new DateTime();
		return date2String( dateTime,HH_MM );

	}

	/**
	 * 获取当天的日期 yyyy-MM-dd
	 * @return
	 */
	public static String getCurDate() {

		DateTime dateTime = new DateTime();
		return date2String( dateTime,YYYY_MM_DD );

	}

	/**
	 * 获取当天的日期时间 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurDateTime() {

		DateTime dateTime = new DateTime();
		return date2String(dateTime,YYYY_MM_DD_HH_MM_SS);

	}

	/**
	 * 获取当天的日期时分 yyyy-MM-dd HH:mm
	 * @return
	 */
	public static String getCurDateHHmm() {

		DateTime dateTime = new DateTime();
		return date2String(dateTime,YYYY_MM_DD_HH_MM);

	}

	/**
	 * 获取当天的时间 HH:mm:ss
	 * @return
	 */
	public static String getCurTime() {

		DateTime dateTime = new DateTime();
		return date2String(dateTime,HH_MM_SS);

	}

	/**
	 * 获取年月 yyyy-MM
	 * @param DateTime
	 * @return
	 */
	public static String getYearMonth(DateTime dateTime){

		return date2String(dateTime,YYYY_MM);

	}

	/**
	 * 获取年月 yyyy-MM
	 * @param
	 * @return
	 */
	public static String getYearMonth(){

		DateTime dateTime = new DateTime();
		return date2String(dateTime,YYYY_MM);

	}

	/**
	 * 时间转换成字符串
	 * @param dateTime
	 * @param formmat
	 * @return
	 */
	private static String date2String(DateTime dateTime,String formmat){
		return dateTime.toString(formmat);
	}

	/**
	 * 获取小时
	 * @param DateTime
	 * @return
	 */
	public static int getHour(){

		DateTime dateTime = new DateTime();
		//点
	    int hour = dateTime.getHourOfDay();
		return hour;

	}

	/**
	 * 两个日期比较前后 yyyy-MM-dd
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	public static boolean betweenDate(String start_time,String end_time){

	    return between(start_time,end_time,YYYY_MM_DD);

	}

	/**
	 * 两个日期时间比较前后 yyyy-MM-dd HH:mm
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	public static boolean betweenDateHHmm(String start_time,String end_time){

	    return between(start_time,end_time,YYYY_MM_DD_HH_MM);

	}

	/**
	 * 两个日期时间比较前后 yyyy-MM-dd HH:mm:ss
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	public static boolean betweenDateTime(String start_time,String end_time){

		if(start_time.indexOf(".0") != -1){
			start_time = start_time.substring(0, start_time.length()-2);
		}

		if(end_time.indexOf(".0") != -1){
			end_time = end_time.substring(0, end_time.length()-2);
		}

	    return between(start_time,end_time,YYYY_MM_DD_HH_MM_SS);

	}

	/**
	 * 两个时间比较前后 HH:mm:ss
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	public static boolean betweenTime(String start_time,String end_time){

	    return between(start_time,end_time,HH_MM_SS);

	}

	/**
	 * 两个日期时分比较前后 HH:mm
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	public static boolean betweenHHmm(String start_time,String end_time){

	    return between(start_time,end_time,HH_MM);

	}

	/**
	 * 比较不同时间格式的时间大小
	 * @param start_time
	 * @param end_time
	 * @param format
	 * @return
	 */
	private static boolean between(String start_time,String end_time,String format){

		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);

		DateTime sd = DateTime.parse(start_time,formatter);
	    DateTime ed = DateTime.parse(end_time,formatter);

	    return sd.isBefore(ed);
	}

	/**
	 * 日期时间是否在今天之内 yyyy-MM-dd HH:mm:ss
	 * @param dateTime
	 * @return
	 */
	public static boolean dateTimeInToday(String dateTime){
		return inToday(dateTime,YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 时间是否在今天之内 HH:mm:ss
	 * @param dateTime
	 * @return
	 */
	public static boolean timeInToday(String time){
		return inToday(time,HH_MM_SS);
	}

	/**
	 * 检查不同格式的时间是否在今天之内
	 * @param dateTime
	 * @param format
	 * @return
	 */
	private static boolean inToday(String dateTime,String format){

		 DateTime nowTime = new DateTime();

		 DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		 //一天开始时间
		 String startOfDay =  nowTime.withTimeAtStartOfDay().toString(formatter);
		 //一天结束时间
		 String endOfDay =  nowTime.millisOfDay().withMaximumValue().toString(formatter);

		 DateTime inOfDay = DateTime.parse(dateTime,formatter);
		 DateTime start = DateTime.parse(startOfDay, formatter);
		 DateTime end = DateTime.parse(endOfDay, formatter);

		 if(inOfDay.isAfter(start) && inOfDay.isBefore(end)){
			 return true;
		 }
		 return false;

	}

	/**
	 * 推算前后天数的日期 yyyy-MM-dd
	 * @param days
	 * @return
	 */
	public static String daysThan2Today(int days){
		return than2Today(YYYY_MM_DD,days,null,null,null);
	}

	/**
	 * 推算前后小时的时间 HH:mm:ss
	 * @param hours
	 * @return
	 */
	public static String hoursThan2Today(int hours){
		return than2Today(HH_MM_SS,null,hours,null,null);
	}

	/**
	 * 推算前后分钟的时间 HH:mm:ss
	 * @param minutes
	 * @return
	 */
	public static String minutesThan2Today(int minutes){
		return than2Today(HH_MM_SS,null,null,minutes,null);
	}

	/**
	 * 推算前后秒的时间 HH:mm:ss
	 * @param seconds
	 * @return
	 */
	public static String secondsThan2Today(int seconds){
		return than2Today(HH_MM_SS,null,null,null,seconds);
	}

	/**
	 * 获取日期前后推算的日期时间，正数是算之后的时间，负数是算之前的时间
	 * @param format
	 * @param days
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @return
	 */
	private static String than2Today(String format,Integer days,Integer hours,Integer minutes,Integer seconds){

		DateTime nowTime = new DateTime();
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);

		String nowOfDay =  nowTime.toString(formatter);

		DateTime dateTime = DateTime.parse(nowOfDay, formatter);

		DateTime dayThanToday = null;
		//查找天数时间
		if(days != null){
			dayThanToday = dateTime.minusDays(0-days);
		}
		//查找小时时间
		if(hours != null){
			dayThanToday = dateTime.minusHours(0-hours);
		}
		//查找分钟时间
		if(minutes != null){
			dayThanToday = dateTime.minusMinutes(0-minutes);
		}
		//查找秒时间
		if(seconds != null){
			dayThanToday = dateTime.minusSeconds(0-seconds);
		}

		return dayThanToday.toString(formatter);
	}

	/**
	 * 星期的特殊处理
	 * @param
	 * @return
	 */
	public static void week(){

	    DateTime dt = new DateTime();

	    //星期
	    switch(dt.getDayOfWeek()) {
	    case DateTimeConstants.SUNDAY:
	        System.out.println("星期日");
	        break;
	    case DateTimeConstants.MONDAY:
	        System.out.println("星期一");
	        break;
	    case DateTimeConstants.TUESDAY:
	        System.out.println("星期二");
	        break;
	    case DateTimeConstants.WEDNESDAY:
	        System.out.println("星期三");
	        break;
	    case DateTimeConstants.THURSDAY:
	        System.out.println("星期四");
	        break;
	    case DateTimeConstants.FRIDAY:
	        System.out.println("星期五");
	        break;
	    case DateTimeConstants.SATURDAY:
	        System.out.println("星期六");
	        break;
	    }

	}

	/**
	 *
	 * @Title: StringToDate
	 * @author henser  henser123@163.com
	 * @date 2016年4月17日 下午11:33:48
	 * @Description: 方法功能描述 字符转日期格式类型 yyyy-MM-dd HH:mm:ss
	 * @param date_time
	 * @return
	 * @throws
	 */
	public static Date StringToDate(String date_time) {

		try{
			DateTimeFormatter formatter = DateTimeFormat.forPattern(YYYY_MM_DD_HH_MM_SS);

			DateTime dataTime = DateTime.parse(date_time,formatter);

			return dataTime.toDate();
		}catch(Exception e){
			return null;
		}

	}
	
	/**
	 * 
	 * @Title: StringToDate
	 * @author luozh
	 * @date 2016年8月15日 上午10:21:46
	 * @Description: 字符转日期格式类型
	 * @param date_time
	 * @param format
	 * @return    
	 * @throws
	 */
	public static Date StringToDate(String date_time, String format){
		try{
			DateTimeFormatter formatter = DateTimeFormat.forPattern(format);

			DateTime dataTime = DateTime.parse(date_time,formatter);

			return dataTime.toDate();
		}catch(Exception e){
			return null;
		}
	}

	@SuppressWarnings("unused")
	public static boolean checkStringToDate(String date_time){
		try{
			DateTimeFormatter formatter = DateTimeFormat.forPattern(YYYY_MM_DD_HH_MM_SS);

			DateTime dataTime = DateTime.parse(date_time,formatter);


		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

		return true;

	}

	/**
	 *
	 * @Title: StringToYYYYMMDD
	 * @author henser  henser123@163.com
	 * @date 2016年4月18日 上午2:32:11
	 * @Description: 方法功能描述 字符转日期格式类型 yyyy-MM-dd
	 * @param date_time
	 * @return
	 * @throws
	 */
	public static Date StringToYYYYMMDD(String date_time) {

		try{
			DateTimeFormatter formatter = DateTimeFormat.forPattern(YYYY_MM_DD);

			DateTime dataTime = DateTime.parse(date_time,formatter);

			return dataTime.toDate();
		}catch(Exception e){
			return null;
		}

	}

	@SuppressWarnings("unused")
	public static boolean checkStringToYYYYMMDD(String date_time){
		try{
			DateTimeFormatter formatter = DateTimeFormat.forPattern(YYYY_MM_DD);

			DateTime dataTime = DateTime.parse(date_time,formatter);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	/**
	 *
	 * @Title: StringToYYYYMM
	 * @author henser  henser123@163.com
	 * @date 2016年4月18日 上午2:32:24
	 * @Description: 方法功能描述 字符转日期格式类型 yyyy-MM
	 * @param date_time
	 * @return
	 * @throws
	 */
	public static Date StringToYYYYMM(String date_time) {

		DateTimeFormatter formatter = DateTimeFormat.forPattern(YYYY_MM);

		DateTime dataTime = DateTime.parse(date_time,formatter);

		return dataTime.toDate();
	}


	/**
	 *
	 * @Title: todayDate
	 * @author henser  henser123@163.com
	 * @date 2016年4月17日 下午11:35:12
	 * @Description: 方法功能描述 获取今天的日期
	 * @param date_time
	 * @return
	 * @throws
	 */
	public static Date todayDate() {

		DateTime dt = new DateTime();

		String date_time = date2String(dt,YYYY_MM);

		DateTimeFormatter formatter = DateTimeFormat.forPattern(YYYY_MM);

		DateTime dataTime = DateTime.parse(date_time,formatter);

		return dataTime.toDate();
	}

	/**
	 * 判断今天时间在两个时间之间
	 * @param args
	 */
	public static boolean todayInStartEnd (String start_time,String end_time) {

		if(start_time.indexOf(".0") != -1){
			start_time = start_time.substring(0, start_time.length()-2);
		}

		if(end_time.indexOf(".0") != -1){
			end_time = end_time.substring(0, end_time.length()-2);
		}

		DateTime dt = new DateTime();

		String date_time = date2String(dt,YYYY_MM_DD_HH_MM_SS);

		boolean greaterThan = betweenDateTime(start_time,date_time);
		boolean lesserThan = betweenDateTime(date_time,end_time);

		if(greaterThan && lesserThan) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 开始时间前多少分钟的时间
	 * @param args
	 */
	public static String minutesThanDateTime (String date_time,int minutes) {

		if(date_time.indexOf(".0") != -1){
			date_time = date_time.substring(0, date_time.length()-2);
		}

		DateTimeFormatter formatter = DateTimeFormat.forPattern(YYYY_MM_DD_HH_MM_SS);

		DateTime dateTime = DateTime.parse(date_time, formatter);

		//查找分钟时间
		DateTime dayThanToday = dateTime.minusMinutes(minutes);

		return dayThanToday.toString(formatter);
	}
	
	/**
	 * 获取两个日期的列表
	 */
	public static List<Date> dateSplit(Date start_date, Date end_date) throws Exception {
	    Long spi = end_date.getTime() - start_date.getTime();
	    Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

	    List<Date> dateList = new ArrayList<Date>();
	    dateList.add(end_date);
	    for (int i = 1; i <= step; i++) {
	        dateList.add(new Date(dateList.get(i - 1).getTime()
	                - (24 * 60 * 60 * 1000)));// 比上一天减一
	    }
	    return dateList;
	}
	
	

	public static void main(String[] args){

		String date_time = "2016-04-20 00:00:00.0";
		String end_time = "2016-04-21 12:00:00";
		if(date_time.indexOf(".0") != -1){
			date_time = date_time.substring(0, date_time.length()-2);
		}
		System.out.println(todayInStartEnd(date_time,end_time));

		System.out.println(minutesThanDateTime(end_time,30));

		System.out.println(daysThan2Today(-1));
		
		Date start_date = JodaTimeUtil.StringToYYYYMMDD("2016-12-07");
		
		Date end_date = JodaTimeUtil.StringToYYYYMMDD("2016-12-12");
		
		try {
			List<Date> listDate = JodaTimeUtil.dateSplit(start_date, end_date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			for(int i= listDate.size()-1 ; i>=0;i--){
				System.out.println(sdf.format(listDate.get(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(getYYMMddHHMMSS());
		
	}

}
