package com.sinmn.core.utils.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.sinmn.core.utils.util.StringUtil;



/**
 * @author liaohongjun 2012年5月2日 12:04:20
 *
 */
public class TimeUtil {

	public static long MINUTE_MILLISECONDS = 1000 * 60;
	public static long SCAN_LOG_INTERVAL = 1000 * 60;
	public static long HOUR_MILLISECONDS = MINUTE_MILLISECONDS * 60;
	public static long DAY_MILLISECONDS = HOUR_MILLISECONDS * 24;
	private static DateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static DateFormat  fmtDate = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat  fmtSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 时间加上分钟 格式时分
	 * @param s
	 * @return
	 */
	public static String addMinutes(int m) {

		Date date = new Date();
		SimpleDateFormat from = new SimpleDateFormat("HH:mm");
		String times=from.format(new Date(date.getTime() + m * MINUTE_MILLISECONDS));

		return times;
	}

	public static String getCurHour() {
		DateFormat fmt = new SimpleDateFormat("HH:mm");
		try {
			return fmt.format(new Date());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 时间加上分钟 格式时分秒
	 * @param s
	 * @return
	 */
	public static String addMinutesSecond(int m) {

		Date date = new Date();
		SimpleDateFormat from = new SimpleDateFormat("HH:mm:ss");
		String times=from.format(new Date(date.getTime() + m * MINUTE_MILLISECONDS));

		return times;
	}


	/**
	 * 时间加上分钟 格式时分秒
	 * @param s
	 * @return
	 */
	public static Date addMinutesSecondDate(int m) {

		Date date = new Date();
		SimpleDateFormat from = new SimpleDateFormat("HH:mm:ss");
		String times=from.format(new Date(date.getTime() + m * MINUTE_MILLISECONDS));

		try {
			return from.parse(times);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前时间的时分秒数值型
	 * @return
	 */
	public static long getCurrentTime(){
		String s = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		return parseHour(s).getTime();
	}

	public static Date parseHour(String s) {
		try {
			DateFormat fmtTemp = new SimpleDateFormat("HH:mm:ss");
			return fmtTemp.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 *
	 * @Title: parseStringHoursMinute
	 * @author Sunny 15915776364
	 * @date 2016年5月12日 上午9:29:38
	 * @Description: 方法功能描述
	 * @param s
	 * @return
	 * @throws
	 */
	public static Date parseStringHoursMinute(String s){
		try {
			DateFormat fmtTemp = new SimpleDateFormat("HH:mm");
			return fmtTemp.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 获取时间的时分秒数值型
	 * @param s
	 * @return
	 */
	public static long getHourTime(String s){
		return parseHour(s).getTime();
	}
	/**
	 * 获取当前时间的年月日时分秒数值型
	 * @return
	 */
	public static long getYearCurrentTime(){
		String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		return parseMysql(s).getTime();
	}
	/**
	 * 获取零辰1点的失效时间
	 * @return
	 */
	public static int getRedisExpiredTime(){
		Date currDay = new Date();
		String expiredStr = formatMysqlDate(currDay)+" 23:59:59";
		Date expired      = parseMysql(expiredStr);
		long expiredTime  = (expired.getTime()-currDay.getTime())/1000;
		return new Long(expiredTime).intValue();
	}
	public static Date twoHour(Date date){
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 1);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	public static String getCurrOrderNumPre(){
		DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			return fmt.format(new Date());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static Date nextInterval(long interval) {
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		while (c.getTimeInMillis() <= new Date().getTime()) {
			c.setTimeInMillis(c.getTimeInMillis() + interval);
		}
		return c.getTime();
	}

	public static Date beginOfTheWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return beginOfTheDay(c.getTime());
	}

	public static String getYearWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		return c.get(Calendar.YEAR) + "-" + c.get(Calendar.WEEK_OF_YEAR);
	}

	public static String getYearMonth(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1);
	}

	public static boolean isSafe(Date end) {
		return (new Date().getTime() - end.getTime()) >= 2 * 60 * 1000;
	}

	public static int getHour24() {
		Calendar c = new GregorianCalendar();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public static Date parseMysql(String s) {
		try {
			DateFormat fmtTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return fmtTemp.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String getSqlCurDateTime(){
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return fmt.format(new Date());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 获取时间的时间数值型
	 * @param s
	 * @return
	 */
	public static long getTime(String s){
		return parseMysql(s).getTime();
	}

	public static boolean isWeekend(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
				|| c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			return true;
		else
			return false;
	}
	public static Date parseToDay(String str_date) throws Exception{
		return fmtDate.parse(str_date);
	}

	public static Date parseToSecond(String str_date) throws Exception{

		return fmtSecond.parse(str_date);
	}

	public static String sqlDateTime(Date d) {
		return fmt1.format(d);
	}

	public static Date nextDay(Date date) {
		return new Date(beginOfTheDay(date).getTime() + DAY_MILLISECONDS);
	}

	public static Date nextHour(Date date) {
		return new Date(date.getTime() + HOUR_MILLISECONDS);
	}

	public static Date nextDay(Date date, int n) {
		return new Date(beginOfTheDay(date).getTime() + DAY_MILLISECONDS * n);
	}

	public static Date plusDay(Date date, int n) {
		return new Date(date.getTime() + DAY_MILLISECONDS * n);
	}

	public static Date lastDay(Date date, int days) {
		return new Date(date.getTime() - days * DAY_MILLISECONDS);
	}

	public static Date trimToHour(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date nextTime(Date date, int minute) {
		return new Date(date.getTime() + minute * MINUTE_MILLISECONDS);
	}

	public static Date addMinutes(Date date, int m) {
		return new Date(date.getTime() + m * MINUTE_MILLISECONDS);
	}

	public static Date addDays(Date date, int m) {
		return new Date(date.getTime() + m * DAY_MILLISECONDS);
	}

	public static Date timeStampToDate(long ts){
		if(ts == 0){
			return new Date();
		}
		return new Date(ts * 1000);
	}
	
	public static Date formatTimeStampToDate(long ts){
		if(ts == 0){
			return new Date();
		}
		return new Date(ts);
	}

	public static Date addMonth(Date date, int m) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.MONTH, m);
		return c.getTime();
	}

	public static Date addSeconds(Date date, int m) {
		return new Date(date.getTime() + m * 1000);
	}

	public static long getMilliInMonth(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH) * DAY_MILLISECONDS;
	}

	public static Date beginOfTheMonth() {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	public static Date beginOfTheDay(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date beginOfTheHour(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date beginOfThisHour() {
		return beginOfTheHour(new Date());
	}

	public static Date beginOfLastHour() {
		return beginOfTheHour(new Date(new Date().getTime() - HOUR_MILLISECONDS));
	}

	public static Date beginOfLastHour(Date time) {
		return beginOfTheHour(new Date(time.getTime() + HOUR_MILLISECONDS));
	}

	public static Date endOfTheDay(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date standardTime(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		int minute = c.get(Calendar.MINUTE);
		int gewei = minute % 15;
		int shiwei = minute / 15 * 15;
		if (gewei >= 7) {
			gewei = 15;
		} else {
			gewei = 0;
		}
		minute = gewei + shiwei;
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	public static Date alignTime(Date date, int min){
		if(min <= 0){
			return date;
		}
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		int minute = c.get(Calendar.MINUTE);
		int gewei = minute % min;
		int shiwei = minute / min * min;
		if (gewei >= min) {
			gewei = min;
		} else {
			gewei = 0;
		}
		minute = gewei + shiwei;
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	public static Date standardTime(Date date, int min) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		int minute = c.get(Calendar.MINUTE);
		int gewei = minute % min;
		int shiwei = minute / min * min;
		if (gewei >= min / 2) {
			gewei = min;
		} else {
			gewei = 0;
		}
		minute = gewei + shiwei;
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	public static boolean isStandardTime(Date date) {

		Calendar c = new GregorianCalendar();
		c.setTime(date);
		int minute = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		if (sec == 0 && minute % 15 == 0) {
			return true;
		} else {
			return false;
		}

	}

	public static Date standardTime() {
		return standardTime(new Date());

	}

	public static Date standardTime(long interval) {
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		while (Math.abs(c.getTimeInMillis() - new Date().getTime()) > interval / 2d) {
			c.setTimeInMillis(c.getTimeInMillis() + interval);
		}
		return c.getTime();
	}

	public static String getTimestamp() {
		return getTimestamp(new Date());
	}

	public static String getTimestamp(Date date) {
		DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		return fmt.format(date);
	}

	public static Date toDate(String str) {
		if(null == str || str.trim().isEmpty()){
			return null;
		}
		try {
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return fmt.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String formatDateString(Date date, String format) {
		if(date == null){
			return null;
		}

		return new SimpleDateFormat(format).format(date);
	}

	public static Date toDate(String dateString, String format) {
		try {
			DateFormat fmt = new SimpleDateFormat(format);
			return fmt.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * @param date
	 * @return
	 */
	public static String toDateString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}
	/**
	 * @param date
	 * @return
	 */
	public static String toDateTimeString(Date date) {
		String timeStr = "";
		if(date != null){
			timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		}
		return timeStr;
	}
	public static String getYear(Date date) {
		return new SimpleDateFormat("yyyy").format(date);
	}

	public static String getMonth(Date date) {
		return new SimpleDateFormat("MM").format(date);
	}

	public static String getDay(Date date) {
		return new SimpleDateFormat("dd").format(date);
	}
	
	public static String getHour(Date date) {
		return new SimpleDateFormat("HH").format(date);
	}
	
	public static int getMonthCha(Date start, Date end) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(start);
		c2.setTime(end);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int m1 = c1.get(Calendar.MONTH);
		int m2 = c2.get(Calendar.MONTH);
		int monthCha = (y2 - y1) * 12 + m2 - m1;
		return monthCha;
	}

	public static Date nextMonth(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		return c.getTime();
	}

	public static String toTimeStamp(Date date) {
		if(null == date){
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String toDateStamp(Date date) {
		if(null == date){
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static long getTimeStamp()
	{
		return new Date().getTime()/1000;
	}

	public static long getTimeStamp(String date)
	{
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime()/1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

	public static String toMyTimeStamp(Date date) {
		if(null == date){
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	/**
	 * 获取友好显示的日期
	 * @return
	 */
	public static String curView() {
		Date date = new Date();
		return new SimpleDateFormat("yyyy年MM月dd日   HH:mm").format(date);
	}
	public static String getCurDateTime(){
		return toTimeStamp(new Date());
	}
	public static String getMyCurDateTime(){
		return toMyTimeStamp(new Date());
	}
	public static String getHourMinuteSecondStamp(Date date) {
		DateFormat fmt = new SimpleDateFormat("HHmmss");
		return fmt.format(date);
	}
	public static String getMillisecondStamp(Date date) {
		DateFormat fmt = new SimpleDateFormat("HHmmssSSS");
		return fmt.format(date);
	}

	public static String getHourMinuteStamp(Date date) {
		DateFormat fmt = new SimpleDateFormat("HH:mm");
		return fmt.format(date);
	}

	public static Date getDate(Date date2, Date time2) {
		Calendar date = new GregorianCalendar();
		date.setTime(date2);
		Calendar time = new GregorianCalendar();
		time.setTime(time2);
		time.set(Calendar.YEAR, date.get(Calendar.YEAR));
		time.set(Calendar.MONTH, date.get(Calendar.MONTH));
		time.set(Calendar.DATE, date.get(Calendar.DATE));
		return time.getTime();
	}

	public static String formatNianYueRi(Date date) {
		DateFormat fmt = new SimpleDateFormat("yy年M月d日");
		return fmt.format(date);
	}

	/**
	 * 一周之中的第几天
	 * @param date
	 * @return
	 */
	public static int getIntOfWeek(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		switch (c.get(Calendar.DAY_OF_WEEK)){
			case 1:
				return 6;
			case 2:
				return 0;
			case 3:
				return 1;
			case 4:
				return 2;
			case 5:
				return 3;
			case 6:
				return 4;
			case 7:
				return 5;
			default:
				return -1;
		}
	}

	 public static String getDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			return "周日";
		case 2:
			return "周一";
		case 3:
			return "周二";
		case 4:
			return "周三";
		case 5:
			return "周四";
		case 6:
			return "周五";
		case 7:
			return "周六";
		default:
			return "未知日期";
		}
	}

	public static String getDayOfWeekName(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			return "日";
		case 2:
			return "一";
		case 3:
			return "二";
		case 4:
			return "三";
		case 5:
			return "四";
		case 6:
			return "五";
		case 7:
			return "六";
		default:
			return "未知日期";
		}
	}

	public static String formatMysqlDate(Date date) {
		if(date == null){
			return null;
		}
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(date);
	}

	public static String formatMysqlTime(Date date) {
		if(date == null){
			return null;
		}
		DateFormat fmt = new SimpleDateFormat("HH:mm:ss");
		return fmt.format(date);
	}

	public static Date yesterday() {
		return new Date(new Date().getTime() - DAY_MILLISECONDS);
	}

	public static Date pastday(int n) {
		return new Date(new Date().getTime() - n * DAY_MILLISECONDS);
	}

	public static Date pastday(Date d, int n) {
		return new Date(d.getTime() - n * DAY_MILLISECONDS);
	}

	public static Date last5WeekStart() {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(TimeUtil.beginOfTheDay(pastday(30)));
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return c.getTime();
	}

	/**
	 * @param date
	 * @return 统计log库日期
	 */
	public static String toStatisticDate(Date date) {
		DateFormat fmt = new SimpleDateFormat("MMdd");
		return fmt.format(date);
	}

	/**
	 * @param prefix
	 *            with out _
	 * @param date
	 * @return
	 */
	public static String toStatisticTable(String prefix, Date date) {
		DateFormat fmt = new SimpleDateFormat("MMdd");
		return prefix + "_" + fmt.format(date);
	}

	public static String getStatisticDateFull(Date date) {
		DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		return fmt.format(date);
	}

	public static Date fromStatisticDateFull(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Date fromMysqlDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String formatStringTime(String str) throws ParseException{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式

		if(str == null || str ==""){
			return null;
		}
		Date date = format.parse(str);

		String strDate = format.format(date);

		return strDate;
	}

	public static String getCurDate() {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.format(new Date());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getCurTime() {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return fmt.format(new Date());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Date fromMysqlDateTime(String date) {
		try {
			return fmt1.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}


	public static long getDaysCa(Date d1, Date d2) {
		long quot = Math.abs(TimeUtil.beginOfTheDay(d1).getTime()
				- TimeUtil.beginOfTheDay(d2).getTime());
		quot = quot / 1000 / 60 / 60 / 24;
		return quot;
	}

	public static long getMinuteCa(Date d1, Date d2) {
		long quot = Math.abs(d1.getTime() - d2.getTime());
		quot = quot / 1000 / 60;
		return quot;
	}

	public static long getSecondCa(Date d1, Date d2) {
		long quot = Math.abs(d1.getTime() - d2.getTime());
		quot = quot / 1000;
		return quot;
	}
	/**
	 * 获取两个时间的秒间隔差
	 * @param d1
	 * @param d2
	 * @param format
	 * @return
	 */
	public static long getSecondCa(String d1,String d2,String format) {
		return getSecondCa(toDate(d1,format),toDate(d2,format));
	}
	/**
	 * 增加相应的秒间隔
	 * @param d1
	 * @param timeSpan
	 * @param format
	 * @return
	 */
	public static String addSecondCa(String d1,int timeSpan,String format) {
		Date d1Date = toDate(d1,format);
		long timeMili = d1Date.getTime()+timeSpan*1000;
		d1Date.setTime(timeMili);
		return formatDateString(d1Date,format);
	}
	/**
	 * 以当前时间为准，前推指定的天数
	 * @param day 格式：2013-08-09 00:00
	 * @return
	 */
	public static String nextDayFromCur(int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		//cd.add(Calendar.DATE, -7);//前7天
		calendar.add(Calendar.DATE, day);//加一天
		return toTimeStamp(calendar.getTime());
	}

	/**
	 * 以当前时间为准，前推指定的天数
	 * @param day 格式：2013-08-09
	 * @return
	 */
	public static String nextDayTimeFromCur(int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		//cd.add(Calendar.DATE, -7);//前7天
		calendar.add(Calendar.DATE, day);//加一天
		return toDateStamp(calendar.getTime());
	}

	/**
	 * 返回当天的数据格式,格式为2013-08-09 00:00:00
	 * @return
	 */
	public static String getCurDayZero(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(calendar.getTime());
	}
	/**
	 * 获取前一天的时候，格式为2013-08-09 00:00:00
	 * @return
	 */
	public static String getPreDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);//加一天
		return new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(calendar.getTime());
	}
	
	/**
	 * 获取前 day天的时候，格式为2013-08-09 00:00:00
	 * @return
	 */
	public static String getPreDay(Date date,int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1*day);//往前推移 day 天
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}

	/**
	 * 获取前一天的时候，格式为2013-08-09
	 * @return
	 */
	public static String getPreDayTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);//减一天
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	/**
	 * 获取前一天的时候，格式为2013-08-09 00:00:00
	 * @return
	 */
	public static String getPreDay(int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1*day);//加一天
		return new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(calendar.getTime());
	}
	public static boolean sameDay(Date date1, Date date2) {
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		c1.setTime(date1);
		c2.setTime(date2);
		return c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
	}

	public static boolean sameWeek(Date date1, Date date2) {
		return getYearWeek(date1).equals(getYearWeek(date2));
	}

	public static boolean sameTimeInOneMinute(Date date1, Date date2) {
		return Math.abs(getMinuteCa(date1, date2)) <= 1;
	}

	public static Date mid(Date date1, Date date2) {
		return new Date((date1.getTime() + date2.getTime()) / 2);
	}

	public static Date getPrevHour(Date date) {
		return new Date(date.getTime() - 60 * MINUTE_MILLISECONDS);
	}

	public static Date nextTime(int hour) {
		Calendar c = new GregorianCalendar();
		int nowHour = c.get(Calendar.HOUR_OF_DAY);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		if (hour <= nowHour) {
			c.add(Calendar.DAY_OF_YEAR, 1);
		}
		c.set(Calendar.HOUR_OF_DAY, hour);
		return c.getTime();
	}

	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	public static Date getFirstDayOfPastWek(int n) {
		return TimeUtil.beginOfTheDay(getFirstDayOfWeek(pastday(n * 7)));
	}

	public static Date getLastDayOfPastWek(int n) {
		return TimeUtil.endOfTheDay(getLastDayOfWeek(pastday(n * 7)));
	}

	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Monday
		return c.getTime();
	}

	public static boolean futureTime(Date date) {
		Date curTime = new Date();
		return date.after(curTime);
	}

	public static boolean inRange(Date date, Date first, Date second) {
		return date.after(first) && date.before(second);
	}

	public static Date tomorrow() {
		return new Date(new Date().getTime() + TimeUtil.DAY_MILLISECONDS);
	}
	/**
	 * 判断两个日期是否相等
	 * @param d1
	 * @param d2
	 * @param format
	 * @return
	 */
	public static boolean isEqual(String d1,String d2,String format) {
		Date d1Date = toDate(d1,format);
		Date d2Date = toDate(d2,format);
		if(d1Date.getTime()==d2Date.getTime())
			return true;
		return false;
	}

	/**
	 * 判断当前时间是否大于d2时间
	 * @param d2
	 * @return
	 */
	public static boolean isThan(String d2){
		Date d1Date = new Date();
		Date d2Date = toDate(d2,"yyyy-MM-dd HH:mm:ss");
		if(d1Date.getTime()>d2Date.getTime())
			return true;
		return false;
	}
	/**
	 * 判断当前时间是否大于d2时间
	 * @param d2
	 * @return
	 */
	public static boolean isThan(Date d2Date) {
		Date d1Date = new Date();
		if (d1Date.getTime() > d2Date.getTime())
			return true;
		return false;
	}
	/**
	 * 判断D1是否大于D2
	 * @param d1
	 * @param d2
	 * @param format
	 * @return
	 */
	public static boolean isThan(String d1,String d2,String format){
		Date d1Date = toDate(d1,format);
		Date d2Date = toDate(d2,format);
		if(d1Date.getTime()>d2Date.getTime())
			return true;
		return false;
	}
	/**
	 * d1是否小于d2
	 * @param d1
	 * @param d2
	 * @param format
	 * @return
	 */
	public static boolean isLessThan(String d1,String d2,String format){
		Date d1Date = toDate(d1,format);
		Date d2Date = toDate(d2,format);
		if(d1Date.getTime()<d2Date.getTime())
			return true;
		return false;
	}

	/**
	 * 获取两个字符串日期的间隔时间的形式化表达：
	 * <p>xx天xx小时xx分钟xx秒
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	public static String getLastTimestamp(String start_time, String end_time) {
		Date start = toDate(start_time, "yyyy-MM-dd HH:mm");
		Date end = toDate(end_time, "yyyy-MM-dd HH:mm");
		return getLastTimestamp(end.getTime() - start.getTime());
	}

	/**
	 * 获取时间戳所代表的间隔时间的形式化表达：
	 * <p>xx天xx小时xx分钟xx秒
	 * @param stamp
	 * @return
	 */
	public static String getLastTimestamp(long stamp) {
		int day = (int)(stamp / (86400 * 1000L));
		int hour = (int)(stamp / (3600 * 1000L) % 24);
		int minute = (int)(stamp / (60 * 1000L) % 60);
		int second = (int)(stamp / 1000L % 60);
		String s = "";
		if(day > 0) s += day + "天";
		if(hour > 0) s += hour + "小时";
		if(minute > 0) s += minute + "分钟";
		if(second > 0) s += second + "秒";
		return s.length() == 0 ? "0" : s;
	}

	public static boolean isTimeout(Date now,Date dbTime,int timeOut) {
		StringBuilder buff = new StringBuilder(100);
		buff.append("[tools][得到的最后时间]");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(now);
		c2.setTime(dbTime);
//		buff.append("数据库原时间=<"+c2.getTime().toLocaleString()).append(">,");
//		//相差时间，20分钟 ，如果数据库时间加了15分钟与当前时间比较 ，大于或等于当前时间，表示收集数据正常(c2>=c1)
//		c2.add(GregorianCalendar.SECOND, timeOut);
//		buff.append("当前时间 <").append(c1.getTime().toLocaleString()).append(">,数据库时间 =<").append(c2.getTime().toLocaleString()).append(">");
//
//		System.out.println(buff.toString());
		int result = c1.compareTo(c2);
		if (result == 0){
			return false;
		}else if (result < 0){
			return false;
		}else{
			return true;
		}
	}

	//判断时间是否在当天日期之内
	public static boolean isToday(String s){

		String today = getCurDate();

		return isEqual(s,today,"yyyy-MM-dd");

	}

	/**
	 * 判断当前时间（时分秒）是否小于d2时间
	 * @param d2
	 * @return
	 */
	public static boolean isThanTime(String d2){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String today = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
		Date d1Date = toDate(today,"HH:mm:ss");
		Date d2Date = toDate(d2,"HH:mm:ss");
		if(d1Date.getTime()<d2Date.getTime())
			return true;
		return false;
	}

	public static long beginOfToday()
	{
		return beginOfTheDay(new Date()).getTime()/1000;
	}

	public static long endOfToday()
	{
		return endOfTheDay(new Date()).getTime()/1000;
	}

	/**
	 *
	 * @Title: getFormatedDateTime
	 * @author caozj  cao.zhijun@163.com
	 * @date 2016年4月26日 下午4:48:01
	 * @Description: 方法功能描述  格式化时间
	 * @param date
	 * @return
	 * @throws
	 */
	public static String getFormatedDateTime(Date date) {
		if (null == date) {
			return "";
		}

		return fmtSecond.format(date);
	}


	public static Date formarteDate(String date_str){
		if(StringUtil.isEmpty(date_str)){
			return null ;
		}
		Date date = null;
		try {
			date = fmtSecond.parse(date_str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date ;
	}

	public static Date parseYYYYMMDD(String s) {
		try {
			DateFormat fmtTemp = new SimpleDateFormat("yyyy-MM-dd");
			return fmtTemp.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 *
	 * @Title: timeStampToString
	 * @author henser  henser123@163.com
	 * @date 2016年6月2日 下午7:38:35
	 * @Description: long日期时间格式转换成String时间格式
	 * @param ts
	 * @return
	 * @throws
	 */
	public static String timeStampToString(long ts){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(ts * 1000));
		return fmtSecond.format(calendar.getTime());
	}

	/**
	 *
	 * @Title: longDateToString
	 * @author henser  henser123@163.com
	 * @date 2016年6月2日 下午7:38:35
	 * @Description: long日期格式转换成String时间格式
	 * @param ts
	 * @return
	 * @throws
	 */
	public static String longDateToString(long ts){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(ts * 1000));
		return fmtDate.format(calendar.getTime());
	}
	
	/** 
	 * @Title: currentTimeMillisToDateString 
	 * @Description: 时间戳(currentTimeMillis)转为日期(Date)字符串格式。形如yyyy-MM-dd HH:mm:ss
	 * @author: wubo  
	 * @date: 2017年7月20日 上午9:37:42
	 * @param ts 10位长的时间戳
	 * @return 
	 * @return String
	 */  
	public static String currentTimeMillisToDateString(long ts) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(ts * 1000));
	}
	
	/** 
	 * @Title: currentTimeMillisToTimeString 
	 * @Description: 时间戳(currentTimeMillis)转为日期(Date)字符串格式。形如yyyy-MM-dd HH:mm:ss
	 * @author: wubo  
	 * @date: 2017年7月20日 上午11:18:26
	 * @param ts
	 * @return 
	 * @return String
	 */  
	public static String currentTimeMillisToTimeString(long ts){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(ts));
		return fmtDate.format(calendar.getTime());
	}
	

	/**
	 *
	 * @Title: stringToLongDate
	 * @author henser  henser123@163.com
	 * @date 2016年7月2日 下午6:14:32
	 * @Description: 字符串日期 yyyy-MM-dd 转换成 long 类型数据
	 * @param date
	 * @return
	 * @throws
	 */
	public static long stringToLongDate(String date)
	{
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 *
	 * @Title: longToDate
	 * @author henser  henser123@163.com
	 * @date 2016年7月21日 上午10:24:52
	 * @Description: long 类型日期时间 yyyy-MM-dd hh:mm:ss 转换成 dateTime
	 * @param ts
	 * @return
	 * @throws
	 */
	public static Date longToDateTime(long ts){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(ts * 1000));
		try {
			return fmtSecond.parse(fmtSecond.format(calendar.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *
	 * @Title: getDayTime
	 * @author liangqz
	 * @date 2016年9月14日 下午10:58:51
	 * @Description: 获取一天的开始时间我结束时间
	 * @param i
	 * @return
	 * @throws ParseException
	 * @throws
	 */
	public static List<String> getDayTime(int i) throws ParseException{
		List<String> list = new ArrayList<String>();

		Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, - i);
        Date day = c.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String start = sdf.format(day);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        String end = sdf2.format(day);

        list.add(start);
        list.add(end);

        return list;
	}
	
	/**
	 * 
	 * @Title: getSevenDaysTime
	 * @author liangqz
	 * @date 2017年2月20日 上午10:30:20
	 * @Description: 查询最近七天的开始时间和结束时间
	 * @return    
	 * @throws
	 */
	public static List<String> getSevenDaysTime(){
		List<String> list = new ArrayList<String>();

		Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, - 6);
        Date day = c.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String start = sdf.format(day);

        Date day2 = new Date();
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        String end = sdf2.format(day2);

        list.add(start);
        list.add(end);

        return list;
	}
	

	/**
	 *
	 * @Title: getWeekDateTime
	 * @author liangqz
	 * @date 2016年7月5日 下午2:15:46
	 * @Description: 获取最近七天的日期
	 * @return
	 * @throws ParseException
	 * @throws
	 */
	public static List<String> getWeekDateTime() throws ParseException{

		List<String> list = new ArrayList<String>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for(int i = 6; i >= 0; i--){

			Calendar c = Calendar.getInstance();
	        c.add(Calendar.DATE, - i);
	        Date day = c.getTime();
	        String preday = sdf.format(day);

	        list.add(preday);
		}


		return list;
	}

	/**
	 *
	 * @Title: getWeekTime
	 * @author liangqz
	 * @date 2016年9月14日 下午11:28:40
	 * @Description: 获取一周第一天开始时间和最后 一天结束时间
	 * @param i
	 * @return
	 * @throws
	 */
	public static List<String> getWeekTime(int i){
		List<String> list = new ArrayList<String>();

		Calendar cal = Calendar.getInstance();
	    cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
	    cal.add(Calendar.DATE, -i*7);
	    cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
	    String monday = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(cal.getTime());

	    Calendar cal2 = Calendar.getInstance();
	    cal2.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
	    cal2.add(Calendar.DATE, -i*7);
	    cal2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	    String sunday = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(cal2.getTime());

	    list.add(monday);
	    list.add(sunday);

		return list;
	}

	/**
	 *
	 * @Title: getMonthTime
	 * @author liangqz
	 * @date 2016年9月14日 下午11:08:14
	 * @Description: 获取一个月第一天开始时间和最后一天结束时间
	 * @param i
	 * @return
	 * @throws
	 */
	public static List<String> getMonthTime(int i){
		List<String> list = new ArrayList<String>();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

	    //获取前月的第一天
	    Calendar   cal_1=Calendar.getInstance();//获取当前日期
	    cal_1.add(Calendar.MONTH, -i);
	    cal_1.set(Calendar.DAY_OF_MONTH,i);//设置为1号,当前日期既为本月第一天
	    String firstDayTime = format.format(cal_1.getTime());

	    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

		Calendar cale = Calendar.getInstance();
	    cale.set(Calendar.DAY_OF_MONTH,i - 1);//设置为31号,当前日期既为本月第一天
	    String lastDayTime = format2.format(cale.getTime());

	    list.add(firstDayTime);
	    list.add(lastDayTime);

		return list;
	}

	/**
	 *
	 * @Title: getLastMonFirDay
	 * @author liangqz
	 * @date 2016年7月25日 下午5:48:40
	 * @Description: 获取上个月第一天日期
	 * @return
	 * @throws
	 */
	public static String getLastMonFirDay(){

	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	  //获取前月的第一天
      Calendar   cal_1=Calendar.getInstance();//获取当前日期
      cal_1.add(Calendar.MONTH, -1);
      cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
      String firstDay = format.format(cal_1.getTime());

      return firstDay;
	}

	/**
	 *
	 * @Title: getLastMonlastDay
	 * @author liangqz
	 * @date 2016年7月25日 下午5:50:05
	 * @Description: 获取上个月最后一天日期
	 * @return
	 * @throws
	 */
	public static String getLastMonlastDay(){

		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		  Calendar cale = Calendar.getInstance();
	      cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
	      String lastDay = format.format(cale.getTime());

	      return lastDay;
	}

	/**
	 *
	 * @Title: getThisMonlastDay
	 * @author liangqz
	 * @date 2016年7月25日 下午5:51:21
	 * @Description: 获取当月第一天日期
	 * @return
	 * @throws
	 */
	public static String getThisMonlastDay(){

		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		//获取当前月第一天：
	      Calendar c = Calendar.getInstance();
	      c.add(Calendar.MONTH, 0);
	      c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
	      String first = format.format(c.getTime());

	      return first;
	}

	/**
	 *
	 * @Title: getThisMonToDay
	 * @author liangqz
	 * @date 2016年7月25日 下午5:52:57
	 * @Description: 获取当天日期
	 * @return
	 * @throws
	 */
	public static String getThisMonToDay(){

		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		//获取当天日期
	      String today = format.format(new Date());

	      return today;
	}

	/**
	 *
	 * @Title: getYearMonDate
	 * @author liangqz
	 * @date 2016年7月28日 上午11:50:55
	 * @Description: 获取当前年月信息
	 * @return
	 * @throws
	 */
	public static String getYearMonDate(){

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		//获取当天日期
	    String time = format.format(new Date());
	    return time;
	}

	/**
	 *
	 * @Title: getMonthFirDay
	 * @author liangqz
	 * @date 2016年7月29日 上午9:10:19
	 * @Description: 获取月份的第一天
	 * @param month 为月份系数
	 * @return
	 * @throws
	 */
	public static String getMonthFirstDay(int month){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		Calendar   cal_1=Calendar.getInstance();//获取当前日期
	    cal_1.add(Calendar.MONTH, -month);
	    cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
	    String firstDay = format.format(cal_1.getTime()) + " 00:00:00";
	    return firstDay;
	}

	/**
	 *
	 * @Title: getMonthLastDay
	 * @author liangqz
	 * @date 2016年7月29日 上午9:11:17
	 * @Description: 获取月份最后一天
	 * @param month 为月份系数
	 * @return
	 * @throws
	 */
	public static String getMonthLastDay(int month){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		Calendar cale = Calendar.getInstance();
	    cale.add(Calendar.MONTH, -month + 1);
	    cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
	    String lastDay = format.format(cale.getTime()) + " 23:59:59";
	    return lastDay;
	}

	/**
	 *
	 * @Title: getMonthDate
	 * @author liangqz
	 * @date 2016年7月29日 上午10:24:05
	 * @Description: 根据月份系数获取月份日期
	 * @param month
	 * @return
	 * @throws
	 */
	public static String getMonthDate(int month){

		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM"); //设置时间格式
		Calendar   cal_1=Calendar.getInstance();//获取当前日期
	    cal_1.add(Calendar.MONTH, -month);
	    String yearMonth = format.format(cal_1.getTime()) ;

		return yearMonth;
	}

	/**
	 *
	 * @Title: formatDateTime
	 * @author liangqz
	 * @date 2016年7月29日 上午10:06:52
	 * @Description: 格式日期时间
	 * @param str
	 * @return
	 * @throws ParseException
	 * @throws
	 */
	public static String formatDateTime(String str) throws ParseException{

		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM"); //设置时间格式

		if(str == null || str ==""){
			return "00";
		}
		Date date = format.parse(str);

		String strDate = format.format(date);

		return strDate;
	}

	/**
	 *
	 * @Title: dateToString
	 * @author chendt
	 * @date 2016-8-2 下午11:15:06
	 * @Description: 时间转字符串
	 * @param date
	 * @return
	 * @throws
	 */
	public static String dateToString(Date date){
		
		if(date == null){
			return "";
		}
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String strDate = sdf.format(date);
		return strDate;
	}

	/**
	 *
	 * @Title: formatDayTime
	 * @author liangqz
	 * @date 2016年10月28日 下午2:28:18
	 * @Description: 返回天的日期
	 * @param date
	 * @return
	 * @throws
	 */
	public static String formatDayTime(Date date){
		DateFormat sdf = new SimpleDateFormat("dd");
		String strDate = sdf.format(date);
		return strDate;
	}

	/**
	 *
	 * @Title: formatMonthDay
	 * @author liangqz
	 * @date 2016年11月3日 上午10:55:57
	 * @Description: 返回月、日的日期
	 * @param date
	 * @return
	 * @throws
	 */
	public static String formatMonthDay(Date date){
		DateFormat fmt = new SimpleDateFormat("MM-dd");
		return fmt.format(date);
	}

	/**
	 *
	 * @Title: formatDayTimeString
	 * @author liangqz
	 * @date 2016年10月28日 下午4:57:06
	 * @Description: 方法功能描述
	 * @param date
	 * @return
	 * @throws
	 */
	public static String formatDayTimeString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		String strDate = sdf.format(date);
		return strDate;
	}

	/**
	 *
	 * @Title: getFrontBackDate
	 * @author chendt
	 * @date 2016年12月10日 上午12:48:27
	 * @Description: 根据日期字符串，获取前后的日期 ，num = -1 获取前一天，num = 1获取后一天
	 * @param date_str
	 * @param num
	 * @return
	 * @throws
	 */
	public static String getFrontBackDateStr(String date_str , int num){
	    Calendar calendar = Calendar.getInstance();

	    Date date=null;
	    try {
			date = new SimpleDateFormat("yy-MM-dd").parse(date_str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	    calendar.setTime(date);

	    int day=calendar.get(Calendar.DATE);

	    calendar.set(Calendar.DATE,day + num);

	    String front_day=new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

	    return front_day;

	 }

	/**计算两个日期之前的天数**/
	public static int countDays(Date date1,Date date2)  {
		Calendar cal1 = Calendar.getInstance();  cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();  cal2.setTime(date2);
		int day1= cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);
		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if(year1 != year2){//不同年
			int timeDistance = 0;
			for(int i = year1 ; i < year2 ; i ++){
				if(i%4==0 && i%100!=0 || i%400==0){ //闰年
					timeDistance += 366;
				}else{//不是闰年
					timeDistance += 365;
				}
			}
			return timeDistance + (day2-day1) ;
		}else{//同一年
			return day2-day1;
		}
	}

	/**计算距离生日多少天的方法
	 * @throws ParseException */
	public static int countAfterBirthDays(Date birthday_date) throws ParseException{
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date= new java.util.Date();
		String today_md=myFormatter.format(date).substring(4,8);
		String clidate = getStatisticDateFull(birthday_date);
		Long year=Long.parseLong(myFormatter.format(date).substring(0,4));

		String md = clidate.substring(4,8);
		int days= 0;

		if(Long.parseLong(md)<Long.parseLong(today_md)){
			year=year+1;
		}

		java.util.Date mydate= myFormatter.parse(year+md);
		long msInDay = (24*60*60*1000);
		long  day;
		if (md.equals(today_md)){
			days = 0;
		}else {
			day=1+(long)(mydate.getTime() - date.getTime())/msInDay;
			if(day>0){
				days = (int) day;
			}
		}

		return days;
	}

	/**根据生日计算年龄*/
	public static  int getAge(Date birthDay) {
        Date now_date = new Date();
        int age = 0;
        if (now_date.before(birthDay)) {
            return age;
        }

        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        age = yearNow - yearBirth;

        if(age < 0){
        	age = 0;
        }else if (monthNow <= monthBirth){
            if(monthNow == monthBirth){
                if(dayOfMonthNow < dayOfMonthBirth){
                	age--;
                }
            }else{
                age--;
            }
        }
        return age;
    }

	/**查询最近多少天的开始时间和结束时间
	 * @throws ParseException **/
	public static List<String> getDayNumberTime(int day_number) throws ParseException{
		List<String> timeList = new ArrayList<String>();

		List<String> startTimeList = getDayTime(day_number);

		timeList.add(startTimeList.get(0));

		List<String> endTimeList = getDayTime(0);//结束时间为当天
		timeList.add(endTimeList.get(1));

		return timeList;
	}

	public static Date getNextYearOfCurrent(int year){
		Calendar cale = Calendar.getInstance();
	    cale.add(Calendar.YEAR, year);
	    return cale.getTime();
	}

	/**
	 * 获取日期中月份值
	*/
	public static int getMonth(String date_time) {

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
	    Date dt = null;
	    try {
	      dt = sdf.parse(date_time);
	      cal.setTime(dt);
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }

	    int month = cal.get(Calendar.MONTH) + 1;

	    return month;

	}

	/**
	 *
	 * @Title: getMonthFirDay
	 * @author liangqz
	 * @date 2016年7月29日 上午9:10:19
	 * @Description: 获取某年月份前或者后月份的第一天 month = -1 获取前一月，month = 1获取后一月
	 * @param month 为月份系数
	 * @return
	 * @throws
	 */
	public static String getYearMonthFirstDay(String date_time,int month){

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式

		Calendar cal = Calendar.getInstance();

	    Date dt = null;
	    try {
	    	dt = format.parse(date_time);
	    	cal.setTime(dt);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }

	    cal.add(Calendar.MONTH, month);
	    cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
	    String firstDay = format.format(cal.getTime());
	    return firstDay;
	}

	/**
	 *
	 * @Title: getMonthLastDay
	 * @author liangqz
	 * @date 2016年7月29日 上午9:11:17
	 * @Description: 获取某年月份前或者后月份的最后一天 month = -1 获取前一月，month = 1获取后一月
	 * @return
	 * @throws
	 */
	public static String getYearMonthLastDay(String date_time,int month){

		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式

		Calendar cal = Calendar.getInstance();

	    Date dt = null;
	    try {
	    	dt = format.parse(date_time);
	    	cal.setTime(dt);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }

	    cal.add(Calendar.MONTH, month + 1);
	    cal.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
	    String lastDay = format.format(cal.getTime());
	    return lastDay;
	}

	/**
	 * d1是否在d2与d3之间
	 * @param d1
	 * @param d2
	 * @param d3
	 * @param format
	 * @return
	 */
	public static boolean isBetween(String d1,String d2,String d3,String format){
		Date d1Date = toDate(d1,format);
		Date d2Date = toDate(d2,format);
		Date d3Date = toDate(d3,format);

		if(d1Date.getTime()>=d2Date.getTime()
				&& d1Date.getTime()<=d3Date.getTime())
			return true;
		return false;
	}

	/**
	 *
	 * @Title: getWeekFirDay
	 * @author liangqz
	 * @date 2016年7月29日 上午9:10:19
	 * @Description: 获取周前几周的第一天 week = -1 获取前一周
	 * @param week 为周系数
	 * @return
	 * @throws
	 */
	public static String getBeforeWeekFirstDay(String date_time,int week){

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式

		Calendar cal = Calendar.getInstance();

	    Date dt = null;
	    try {
	    	dt = format.parse(date_time);
	    	cal.setTime(dt);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }

	    int day = cal.get(Calendar.DATE);

	    cal.set(Calendar.DATE,day + week*7);

	    String firstDay = format.format(cal.getTime());
	    return firstDay;
	}

	/**
	 *
	 * @Title: getWeekLastDay
	 * @author liangqz
	 * @date 2016年7月29日 上午9:11:17
	 * @Description: 获取某周前几周的最后一天 week = -1 获取前一周
	 * @return
	 * @throws
	 */
	public static String getBeforeWeekLastDay(String date_time,int week){

		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式

		Calendar cal = Calendar.getInstance();

	    Date dt = null;
	    try {
	    	dt = format.parse(date_time);
	    	cal.setTime(dt);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }

	    int day = cal.get(Calendar.DATE);

	    int num = 6*((0-week)-1);

	    cal.set(Calendar.DATE,day + week-num);

	    String lastDay = format.format(cal.getTime());
	    return lastDay;
	}

	/**替换年代**/
	public static List<String> getReplaceYear(String tenyear){

		Integer year_number = Integer.valueOf(tenyear);
		String year_start = "";
		String year_end = "";
		Integer year = 0;

		List<String> yearList = new ArrayList<String>();
		if(year_number >= 50){
			year_start = "19{年代}-01-01 00:00:00";
			year_start = year_start.replace("{年代}", tenyear);
			year = 1900 + IntUtil.toInteger(year_number) + 9;
			year_end = year + "-12-31 23:59:59";
		}else{
			year_start = "20{年代}-01-01 00:00:00";
			year_start = year_start.replace("{年代}", tenyear);
			year = 2000 + IntUtil.toInteger(year_number) + 9;
			year_end = year + "-12-31 23:59:59";
		}

		yearList.add(year_start);
		yearList.add(year_end);

		return yearList;
	}

	/**根据月份间隔查询月份时间，以当天月为第一个月*/
	public static List<String> getTimeByMonth(Integer start_month,Integer end_month){

		List<String> timeList = new ArrayList<String>();

		Date date = new Date();//当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");//格式化对象
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		calendar.add(Calendar.MONTH, -start_month);//月份减相应月数

		timeList.add(sdf.format(calendar.getTime()));

		SimpleDateFormat sdfend = new SimpleDateFormat("yyyy-MM-dd 23:59:59");//格式化对象
		Calendar calendar2 = Calendar.getInstance();//日历对象
		calendar2.setTime(date);//设置当前日期
		calendar2.add(Calendar.MONTH, -end_month);//月份减相应月数

		timeList.add(sdfend.format(calendar2.getTime()));

		return timeList;
	}
	
	/**
	 * 
	 * @Title: weeHours 
	 * @author Sunny 15915776364 
	 * @date 2017年2月7日 上午10:09:24
	 * @Description: 当前时间的00:00:00和23:59:59
	 * @param date
	 * @param flag
	 * @return     
	 * @throws
	 */
	public static Date weeHours(Date date, int flag) {    
	    Calendar cal = Calendar.getInstance();    
	    cal.setTime(date);    
	    int hour = cal.get(Calendar.HOUR_OF_DAY);    
	    int minute = cal.get(Calendar.MINUTE);    
	    int second = cal.get(Calendar.SECOND);    
	        //时分秒（毫秒数）    
	    long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;    
	    
	    //凌晨00:00:00    
	    cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);    
	         
	    if (flag == 0) {    
	        return cal.getTime();    
	    } else if (flag == 1) {    
	        //凌晨23:59:59    
	        cal.setTimeInMillis(cal.getTimeInMillis() + 23*60*60*1000 + 59*60*1000 + 59*1000);    
	    }    
	    return cal.getTime();    
	} 
	
	//根据日期取得当前日期是周几  
    public static String getWeek(Date date){  
        String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;  
        if(week_index<0){  
            week_index = 0;  
        }   
        return weeks[week_index];  
    }  

    public static List<String> getYearTime(String monthOne,String monthTwo){
    	
    	Date date = new Date();//当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");//格式化对象
		String this_year = sdf.format(date);
		
		String yearOne = this_year + "-" + monthOne + " 00:00:00";
		String yearTwo = this_year + "-" + monthTwo + " 00:00:00";
		
		List<String> timeList = new ArrayList<String>();
		
		timeList.add(yearOne);
		if(toDate(yearTwo).getTime() >= toDate(yearOne).getTime()){
			timeList.add(yearTwo);
		}else{
			Calendar curr = Calendar.getInstance();
			curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+1);
			Date dateYear=curr.getTime();
			String next_year = sdf.format(dateYear);
			
			yearTwo = next_year + "-"  + monthTwo + " 00:00:00";
			timeList.add(yearTwo);
		}
    	
		return timeList;
    }
    
    /**计算两个日期月份数
     * @throws ParseException */
    public static int countMonths(String date1,String date2,String pattern) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        
        Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        
        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        
        int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
        
        //开始日期若小月结束日期
        if(year<0){
            year=-year;
            return year*12+c1.get(Calendar.MONTH)-c2.get(Calendar.MONTH);
        }
       
        return year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
    }
    
    /**判断一个时间是否在两个时间之间*/
    public static boolean compareTime(Date start_time,Date end_time,Date this_time){
    	long start_long_time = start_time.getTime();
		long end_long_time = end_time.getTime();
		long this_long_time = this_time.getTime();
		
		if(start_long_time <= this_long_time && this_long_time <= end_long_time){
			return true;
		}
		return false;
	}	
    
    public static boolean compareTimeAfter(Date end_time,Date this_time){
		long end_long_time = end_time.getTime();
		long this_long_time = this_time.getTime();
		
		if(end_long_time <= this_long_time){
			return true;
		}
		return false;
    }
    
    /**判断两个时间是否有交集*/
    public static boolean isOverlap(String startdate1, String enddate1,String startdate2, String enddate2) {    
        Date leftStartDate = null;    
        Date leftEndDate = null;    
        Date rightStartDate = null;    
        Date rightEndDate = null; 
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        try {    
            leftStartDate = format.parse(startdate1);    
            leftEndDate = format.parse(enddate1);    
            rightStartDate = format.parse(startdate2);    
            rightEndDate = format.parse(enddate2);    
        } catch (ParseException e) {    
            return false;    
        }    
            
        return     
            ((leftStartDate.getTime() >= rightStartDate.getTime())     
                    && leftStartDate.getTime() < rightEndDate.getTime())    
            ||    
            ((leftStartDate.getTime() > rightStartDate.getTime())     
                    && leftStartDate.getTime() <= rightEndDate.getTime())    
            ||    
            ((rightStartDate.getTime() >= leftStartDate.getTime())     
                    && rightStartDate.getTime() < leftEndDate.getTime())    
            ||    
            ((rightStartDate.getTime() > leftStartDate.getTime())     
                    && rightStartDate.getTime() <= leftEndDate.getTime());    
                
    }    
    
    public static Date addYear(Date equity_time, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(equity_time);
		cal.add(Calendar.YEAR, i);
		return cal.getTime();
	}
    
	public static void main(String[] args){

		//System.out.println(weeHours(new Date(), 0));  
        //System.out.println(weeHours(new Date(), 1));  
		
        System.out.println(TimeUtil.formatDateString(new Date(TimeUtil.weeHours(new Date(),1).getTime() + LongUtil.toLong(30) * 24 * 3600 * 1000), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(TimeUtil.timeStampToDate(0));
        
        Date date = TimeUtil.formarteDate("2017-02-28 8:19:00");
        
        System.out.println(TimeUtil.getHour(date));
        
        System.out.println(TimeUtil.nextDayFromCur(14));
        
        System.out.println("JHZF" + TimeUtil.getCurrOrderNumPre());
	}
	
	
}
