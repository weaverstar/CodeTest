package com.uway.common.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * 
 * Description:日期时间操作的工具类
 * 
 * @author 翁涛
 */
public class DateUtils {
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/** 日期格式(yyyy/M/d) */
	public static final String YYYY_M_D_SOLIDUS = "yyyy/M/d";
	
	/** 日期格式(yyyy-MM-dd) */
	public static final String yyyy_MM_dd_EN = "yyyy-MM-dd";

	/** 日期格式(yyyyMMdd) */
	public static final String yyyyMMdd_EN = "yyyyMMdd";

	/** 日期格式(yyyy-MM) */
	public static final String yyyy_MM_EN = "yyyy-MM";

	/** 日期格式(yyyyMM) */
	public static final String yyyyMM_EN = "yyyyMM";

	/** 日期格式(yyyy-MM-dd HH:mm:ss) */
	public static final String yyyy_MM_dd_HH_mm_ss_EN = "yyyy-MM-dd HH:mm:ss";

	/** 日期格式(yyyyMMddHHmmss) */
	public static final String yyyyMMddHHmmss_EN = "yyyyMMddHHmmss";

	/** 日期格式(yyyy年MM月dd日) */
	public static final String yyyy_MM_dd_CN = "yyyy年MM月dd日";

	/** 日期格式(yyyy年MM月dd日HH时mm分ss秒) */
	public static final String yyyy_MM_dd_HH_mm_ss_CN = "yyyy年MM月dd日HH时mm分ss秒";

	/** 日期格式(yyyy年MM月dd日HH时mm分) */
	public static final String yyyy_MM_dd_HH_mm_CN = "yyyy年MM月dd日HH时mm分";

	/** DateFormat缓存 */
	private static Map<String, DateFormat> dateFormatMap = new HashMap<String, DateFormat>();
	
	
	/*
	 * 获取当前时间 dayNum前后的时间  以天为间隔
	 */
	public static Date getTimeByDayGap(Date date,int dayGapNum){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dayGapNum);
		return calendar.getTime();
	
	}
	
	/*
	 * 获取当前时间 dayNum前后的时间  以小时为间隔
	 */
	public static Date getTimeByHourGap(Date date,int hourGapNum){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hourGapNum);
		return calendar.getTime();	
	}
	
	/**
	 * 获取当前时间的前一周
	 * @param date
	 * @return
	 */
	public static Date getWeekBefore(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前时间的前一月
	 * @param date
	 * @return
	 */
	public static Date getMonthBefore(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前时间的前3月
	 * @param date
	 * @return
	 */
	public static Date getThreeMonthBefore(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -3);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前时间的前半年
	 * @param date
	 * @return
	 */
	public static Date getHalfYearBefore(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -6);
		return calendar.getTime();
	}
	
	public static Date getBeforeNDaysDate(int day){
		return getAfterNDaysDate(new Date(),-1*day);
	}
	
	public static Date getAfterNDaysDate(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);

		return cal.getTime();
	}
	
	public static Date addYears(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}
	
	
	/**
	 * 获取DateFormat
	 * 
	 * @param dateTimeStr
	 * @param formatStr
	 * @return
	 */
	public static DateFormat getDateFormat(String formatStr) {
		DateFormat dateFormat = dateFormatMap.get(formatStr);
		if (dateFormat == null) {
			dateFormat = new SimpleDateFormat(formatStr,Locale.CHINA);
			dateFormatMap.put(formatStr, dateFormat);
		}
		return dateFormat;
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */

	public static String DateToStr(Date date, String format) {
		SimpleDateFormat fmt = new SimpleDateFormat(format,Locale.CHINA);
		if (date == null) {
			return null;
		}
		else {		
			return fmt.format(date);
		}
	}
	
	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */

	public static String DateToStr(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		if (date == null) {
			return null;
		}
		else {		
			return fmt.format(date);
		}
	}
	
	
	
	public static Date DTSToDate(long DTSdate) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Long time=new Long(DTSdate);
		String d = format.format(time);
		return format.parse(d);
	}
	
	/**	获取月份起始日期	
	 * @param date	
	 * @return	
	 * @throws ParseException	
	 **/
	public static String getMinMonthDate(String date) throws ParseException{		
		Calendar calendar = Calendar.getInstance();		
		calendar.setTime(dateFormat.parse(date));		
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));		
		return dateFormat.format(calendar.getTime());	
	}
	
	/**	 获取月份最后日期	
	 *  @param date	
	 *  @return	
	 *  @throws ParseException	
	 * */	
	public static String getMaxMonthDate(String date) throws ParseException{	
		Calendar calendar = Calendar.getInstance();	
		calendar.setTime(dateFormat.parse(date));		
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));		
		return dateFormat.format(calendar.getTime());	
	}
	
	public static String getYesterday(){
		Calendar   cal   =   Calendar.getInstance();
		cal.add(Calendar.DATE,   -1);
		String yesterday = new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
		return yesterday;
	}
	
	/**
	 * 获取每月第一天
	 * @return
	 */
	public static Date getFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);              //把当前时间小时变成０
		calendar.set(Calendar.MINUTE, 0);            //把当前时间分钟变成０
		calendar.set(Calendar.SECOND, 0);            //把当前时间秒数变成０
		calendar.set(Calendar.MILLISECOND, 0); 
		return calendar.getTime();
	}
	
	/**
	 * 判断是否为同一天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(final Date date1, final Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        final Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }
	
	 public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
	        if (cal1 == null || cal2 == null) {
	            throw new IllegalArgumentException("The date must not be null");
	        }
	        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
	                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
	                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	 }
	 
	 public static void main(String[] args) {
		 System.out.println(getFirstDay() );
		
		
	}
}
