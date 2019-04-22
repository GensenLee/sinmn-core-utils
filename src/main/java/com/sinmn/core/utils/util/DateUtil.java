package com.sinmn.core.utils.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 日期工具类
 * 
 * @author lsf
 *
 */
public class DateUtil {

	// 默认日期格式
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	// 默认时间格式
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// excek时间格式
	public static final String EXCEL_DATETIME_FORMAT = "yyyy/MM/dd";
	
	public static final String NEW_EXCEL_DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
	
	// excek时间格式
	public static final String EXCEL_DATETIME_FORMAT_SHORT = "yyyy/MM";

	// 时分秒
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

	// 日期格式化
	private static DateFormat dateFormat = null;

	// 时间格式化
	private static DateFormat dateTimeFormat = null;

	private static DateFormat timeFormat = null;

	private static Calendar gregorianCalendar = null;

	static {
		dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		dateTimeFormat = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
		timeFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
		gregorianCalendar = new GregorianCalendar();
	}
	
	/**
	 * 
	 * @Description 比较天数
	 * @author chensongming
	 * @date 2017年8月3日 下午11:37:55
	 * @param date1
	 * @param date2
	 * @return
	 * @lastModifier
	 */
	public static int compareDays(Date date1, Date date2) {
		if (date1 == null || date2 == null) return 0;
		return (int)((getDate(date2).getTime() - getDate(date1).getTime())/ (24 * 60 * 60 * 1000));
	}

	/**
	 * 日期格式化yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static Date formatDate(String date, String format) {
		if (StringUtil.isEmpty(date) || StringUtil.isEmpty(format)) {
			return null;
		}

		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期格式化yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateFormat(Date date) {
		if (date == null) {
			return null;
		}
		return dateFormat.format(date);
	}

	/**
	 * 日期格式化yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTimeFormat(Date date) {
		if (date == null) {
			return null;
		}
		return dateTimeFormat.format(date);
	}

	/**
	 * 时间格式化
	 * 
	 * @param date
	 * @return HH:mm:ss
	 */
	public static String getTimeFormat(Date date) {
		if (date == null) {
			return null;
		}
		return timeFormat.format(date);
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @param 格式类型
	 * @return
	 */
	public static String getDateFormat(Date date, String formatStr) {
		if (date == null || StringUtil.isEmpty(formatStr)) {
			return null;
		}
		if (StringUtil.isNotEmpty(formatStr)) {
			return new SimpleDateFormat(formatStr).format(date);
		}
		return null;
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateFormat(String date) {
		if (date == null) {
			return null;
		}
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间格式化
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateTimeFormat(String date) {
		if (date == null) {
			return null;
		}
		try {
			return dateTimeFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Description 获取日期（零点）
	 * @author chensongming
	 * @date 2017年8月3日 下午11:48:39
	 * @param date
	 * @return
	 * @lastModifier
	 */
	public static Date getDate(Date date) {
		if (date == null) return null;
		return DateUtil.getDateFormat(dateFormat.format(date));
	}
	
	/**
	 * 获取当前日期(yyyy-MM-dd)
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNowDate() {
		return DateUtil.getDateFormat(dateFormat.format(new Date()));
	}

	/**
	 * 获取当前时间(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNowTime() {
		return DateUtil.getDateTimeFormat(dateTimeFormat.format(new Date()));
	}

	/**
	 * 获取当前日期星期一日期
	 * 
	 * @return date
	 */
	public static Date getFirstDayOfWeek() {
		gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		gregorianCalendar.setTime(new Date());
		gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek()); // Monday
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取当前日期星期日日期
	 * 
	 * @return date
	 */
	public static Date getLastDayOfWeek() {
		gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		gregorianCalendar.setTime(new Date());
		gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek() + 6); // Monday
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取日期星期一日期
	 * 
	 * @param 指定日期
	 * @return date
	 */
	public static Date getFirstDayOfWeek(Date date) {
		if (date == null) {
			return null;
		}
		gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek()); // Monday
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取日期星期一日期
	 * 
	 * @param 指定日期
	 * @return date
	 */
	public static Date getLastDayOfWeek(Date date) {
		if (date == null) {
			return null;
		}
		gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek() + 6); // Monday
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取当前月的第一天
	 * 
	 * @return date
	 */
	public static Date getFirstDayOfMonth() {
		gregorianCalendar.setTime(new Date());
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取当前月的最后一天
	 * 
	 * @return
	 */
	public static Date getLastDayOfMonth() {
		gregorianCalendar.setTime(new Date());
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		gregorianCalendar.add(Calendar.MONTH, 1);
		gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取指定月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取指定月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		if (date == null) {
			return null;
		}
		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		gregorianCalendar.add(Calendar.MONTH, 1);
		gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
		return gregorianCalendar.getTime();
	}

	/**
	 * @Description 获取当年第一天 00:00:00
	 * @author xhz
	 * @date 2017年4月26日 上午9:58:24
	 * @return
	 * @lastModifier
	 */
	public static Date getCurrYearFirstDay() {
		return getYearFirstDay(new Date());
	}

	/**
	 * @Description 获取指定年第一天 00:00:00
	 * @author xhz
	 * @date 2017年4月26日 上午10:01:49
	 * @param date
	 * @return
	 * @lastModifier
	 */
	public static Date getYearFirstDay(Date date) {
		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.MONTH, 1);
		gregorianCalendar.set(Calendar.HOUR, 0);
		gregorianCalendar.set(Calendar.MINUTE, 0);
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		gregorianCalendar.set(Calendar.SECOND, 0);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取日期前一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayBefore(Date date) {
		if (date == null) {
			return null;
		}
		gregorianCalendar.setTime(date);
		int day = gregorianCalendar.get(Calendar.DATE);
		gregorianCalendar.set(Calendar.DATE, day - 1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取日期后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayAfter(Date date) {
		if (date == null) {
			return null;
		}
		gregorianCalendar.setTime(date);
		int day = gregorianCalendar.get(Calendar.DATE);
		gregorianCalendar.set(Calendar.DATE, day + 1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取当前年
	 * 
	 * @return
	 */
	public static int getNowYear() {
		Calendar d = Calendar.getInstance();
		return d.get(Calendar.YEAR);
	}

	/**
	 * @Description 获取年
	 * @author xhz
	 * @date 2017年4月26日 下午2:53:04
	 * @param date
	 * @return
	 * @lastModifier
	 */
	public static int getYear(Date date) {
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		return d.get(Calendar.YEAR);
	}

	/**
	 * @Description 获取年
	 * @author xhz
	 * @date 2017年4月26日 下午2:53:04
	 * @param date
	 * @return
	 * @lastModifier
	 */
	public static int getMonth(Date date) {
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		return d.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前月份
	 * 
	 * @return
	 */
	public static int getNowMonth() {
		Calendar d = Calendar.getInstance();
		return d.get(Calendar.MONTH) + 1;
	}
	

	/**
	 * 获取当月的第几天
	 * 
	 * @return
	 */
	public static int getNowMonDay() {
		Calendar d = Calendar.getInstance();
		return d.get(Calendar.DATE);
	}

	/**
	 * 获取当月天数
	 * 
	 * @return
	 */
	public static int getNowMonthDay() {
		Calendar d = Calendar.getInstance();
		return d.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 获取时间段的每一天
	 * 
	 * @param 开始日期
	 * @param 结算日期
	 * @return 日期列表
	 */
	public static List<Date> getEveryDay(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}
		// 格式化日期(yy-MM-dd)
		startDate = DateUtil.getDateFormat(DateUtil.getDateFormat(startDate));
		endDate = DateUtil.getDateFormat(DateUtil.getDateFormat(endDate));
		List<Date> dates = new ArrayList<Date>();
		gregorianCalendar.setTime(startDate);
		dates.add(gregorianCalendar.getTime());
		while (gregorianCalendar.getTime().compareTo(endDate) < 0) {
			// 加1天
			gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
			dates.add(gregorianCalendar.getTime());
		}
		return dates;
	}

	/**
	 * 获取提前多少个月
	 * 
	 * @param monty
	 * @return
	 */
	public static Date getFirstMonth(int monty) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -monty);
		return c.getTime();
	}

	/**
	 * 增加时间（秒）
	 * 
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date addSeconds(Date date, int seconds) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, seconds);
		return c.getTime();
	}

	/**
	 * 增加时间（分）
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinutes(Date date, int minutes) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}

	/**
	 * 增加时间（时）
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addHours(Date date, int hours) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hours);
		return c.getTime();
	}

	
	/**
	 * 增加时间（日）
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, day);
		return c.getTime();
	}
	
	/**
	 * 增加时间（月）
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addMonth(Date date, int month) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}
	
	/**
	 * @Description 比较2个日期的大小
	 * @author xhz
	 * @date 2017年4月28日 下午4:36:26
	 * @param dateA
	 * @param dateB
	 * @return
	 * @lastModifier
	 */
	public static int compare(Date dateA,Date dateB){
		Calendar calendarA  = Calendar.getInstance();
		calendarA.setTime(dateA);
		Calendar calendarB  = Calendar.getInstance();
		calendarB.setTime(dateB);
		return calendarA.compareTo(calendarB);
	}
	
	/**
	 * 增加时间（天）
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	public static float compareHours(Date date1, Date date2){
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = date1.getTime() - date2.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day * 24 + hour + Float.valueOf(min)/60;
    }
	
	public static void main(String[] args){
//		long ms = 31 * 24 * 60 * 60;
//		System.out.println(ms);
//		
//		Date lastDate = DateUtil.getDate(DateUtil.getDayAfter(DateUtil.getLastDayOfMonth()));
//		long expireTime = (lastDate.getTime() - getDateFormat("2017-08-01 00:00:00").getTime()) / 1000;
//		int n = (int) expireTime;
//		System.out.println(expireTime);
//		System.out.println(n);
//		Date date = new Date();
//		System.out.println(date.getTime());
//		Date date2 = addMinutes(date, -1);
//		System.out.println(date.getTime());
//		System.out.println(date2.getTime());
        System.out.println(compareHours(new Date(), addMinutes(addHours(addDays(new Date(),1),5),23)));
//        System.out.println(getDatePoor(new Date(), addDays(new Date(), 10)));

    }

}
