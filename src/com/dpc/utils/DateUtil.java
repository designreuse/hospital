package com.dpc.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * 日期转换模式 年-月-日
	 */
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	
	/**
	 * 日期转换模式 年-月-日 时:分:秒
	 */
	public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期转换模式 年-月-日 时:分
	 */
	public static final String DATETIME_M_PATTERN = "yyyy-MM-dd HH:mm";
	/**
	 * 日期转换模式 年-月-日 时
	 */
	public static final String DATETIME_H_PATTERN = "yyyy-MM-dd HH";
	
	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong   = "yyyyMMddHHmmss";
    
    /**
     * 日期转换模式 年-月
     */
    public static final String DATEPATTERN = "yyyy-MM";
    
	/**
	 * 将日期字符串转换为日期类型
	 * @param value		日期字符串
	 * @param pattern   日期转换模式
	 * @return
	 */
	public static Date parse(String value, String pattern){
		DateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将日期字符串转换为日期类型
	 * @param value  日期字符串
	 * @return
	 */
	public static Date parse(String value){
		return parse(value, DATE_PATTERN);
	}
	
	/**
	 * 获取当前日期和时间的英文显示
	 * 
	 * @return
	 */
	public static String nowEn() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	public static String nowEn(String datePattern) {
		return new SimpleDateFormat(datePattern).format(new Date());
	}
	
	
	public static String nowDateEn() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	/**
	 * 指定日期加上指定天数的日期
	 * 
	 * @return
	 */
	public static String dateAdd(String startDate,int day,String fromDatePattern,String toDatePattern) {
		Date date;
		try {
			date = (new SimpleDateFormat(fromDatePattern)).parse(startDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE,day);
			date =cal.getTime();
			startDate = (new SimpleDateFormat(toDatePattern)).format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startDate;
	}
	
	public static Date dateAddMinute(String startDate,int minute,String datePattern) {
		Date date;
		try {
			date = (new SimpleDateFormat(datePattern)).parse(startDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MINUTE,minute);
			date =cal.getTime();
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static String dateAddMinuteStr(String startDate,int minute,String datePattern) {
		Date date;
		try {
			date = (new SimpleDateFormat(datePattern)).parse(startDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MINUTE,minute);
			date =cal.getTime();
			return (new SimpleDateFormat(datePattern)).format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public static String timestamp2Str(Timestamp time,String datePattern) {
		if(null != time && !"".equals(time)){
			Date date = new Date(time.getTime());
			if (null != date && !"".equals(date)) {
				SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
				return sdf.format(date);
			}
		}
  
		return null;
	}
	/**
	 * 将日期格式转字符串
	 * @param date
	 * @param datePattern
	 * @return
	 */
	public static String date2Str(Date date,String datePattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		return sdf.format(date);
	}
	
	/**
	 * 计算时间差
	 * @param beforDate
	 * @param afterDate
	 * @return
	 */
	public static String timeDiffer(Date beforDate, Date afterDate){
		Long minute = (afterDate.getTime() - beforDate.getTime())/(1000*60);
		if(minute < 60L){
			return (minute + "分钟前");
		}else if(minute > 60*24L){
			return (new SimpleDateFormat(DateUtil.DATE_PATTERN).format(beforDate));
		}else{
			return (minute /60L + "小时前");
		}
	}
	
}
