/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.ParseException;
import java.util.Map.Entry;
import com.zcsh.epay.log.LogCvt;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月4日 <br>
 * 描述：
 */
public final class CalendarUtil {
	
	/**
	 * @Fields format1 : yyyy-MM-dd（格式） 
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	/**
	 * @Fields format2 : yyyy/MM/dd（格式） 
	 */
	public static final String YYYYIMMIDD = "yyyy/MM/dd";
	/**
	 * @Fields format3 : yyyy年M月d日（格式） 
	 */
	public static final String YYYY_YEAR_M_MONTH_D_DAY = "yyyy年M月d日";
	/**
	 * @Fields format4 : yyyyMMdd（格式） 
	 */
	public static final String YYYYMMDD = "yyyyMMdd";
	/**
	 * @Fields format5 : yyyy-MM-dd HH:mm:ss（格式） 
	 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * @Fields format6 : HH:mm:ss SSS（格式） 
	 */
	public static final String HHMMSSSSS = "HH:mm:ss SSS";
	/**
	 * @Fields format7 : MMddHHmmss（格式） 
	 */
	public static final String MMDDHHMMSS = "MMddHHmmss";
	/**
	 * @Fields format8 : ddHHmmssSSS（格式） 
	 */
	public static final String DDHHMMSSSSS = "ddHHmmssSSS";
	/**
	 * @Fields format9 : yyyyMMddHHmmssSSS（格式） 
	 */
	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	/**
	 * @Fields format10 : yyyy-MM-dd HH:mm:ss E（格式） 
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_E = "yyyy-MM-dd HH:mm:ss E";
	/**
	 * @Fields format11 : yyyy-MM-dd HH:mm:ss SSS（格式） 
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss SSS";
	/**
	 * @Fields HH : HH（格式） 
	 */
	public static final String HH = "HH";
	/**
	 * @Fields HHmmss : HH:mm:ss（格式） 
	 */
	public static final String HH_MM_SS = "HH:mm:ss";
	/**
	 * @Fields HHmmss1 : HHmmss（格式） 
	 */
	public static final String HHMMSS = "HHmmss"; 
	/**
	 * @Fields HHmm : HHmm（格式） 
	 */
	public static final String HHMM = "HHmm";
	
	/**
	 * @Fields yyyyMMddHHmmss : yyyyMMddHHmmss（格式） 
	 */
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	/**
	 * @Fields YYYYMM : YYYYMM（格式） 
	 */
	public static final String YYYYMM = "yyyyMM";
	/**
	 * @Fields format12 : M月d日HH:mm（格式） 
	 */
	public static final String M_MONTH_D_DAY_HH_MM = "M月d日HH:mm";
	
	public static final String YYYY_MM_DD_HH_MM_SS_DOT_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	
	/**
	  * 创建一个新的实例 CalendarUtil. 
	  * <p>Title: 构造方法</p>
	  * <p>Description: 构造这个日历处理类</p>
	 */
	private CalendarUtil() {
	}
	/**
	  * formatDateByFormat(通过日期对象，按照指定格式格式化日期为String形式)
	  *
	  * @Title: formatDateByFormat
	  * @Description: 通过日期对象，按照指定格式格式化日期为String形式
	  * @Date May 3, 2012 1:23:41 PM
	  * @modifyDate May 3, 2012 1:23:41 PM
	  * @param date 日期对象 java.util.Date
	  * @param format 转换的格式 java.lang.String
	  * @return String 转换后的String日期
	 */
	public static String formatDateByFormat(java.util.Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				LogCvt.error(ex.getMessage(), ex);
			}
		}
		return result;
	}
	/**
	  * addMonth(将String日期中按照格式添加月)
	  * TODO(注意：内部捕捉了异常，用e.printStackTrace()输出了堆栈信息)
	  * TODO(注意：这里的添加是在原来的月份上加上需要添加的月数)
	  *
	  * @Title: addMonth
	  * @Description: 将String日期中按照格式添加月
	  * @Date May 3, 2012 1:26:41 PM
	  * @modifyDate May 3, 2012 1:26:41 PM
	  * @param dateStr 需要添加月份的日期 java.lang.String
	  * @param month 添加月数 int
	  * @param format 格式 java.String
	  * @return String 最后得到的结果 java.lang.String
	 */
	public static String addMonth(String dateStr, int month, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			LogCvt.error(e.getMessage(), e);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		Date nowDate = calendar.getTime();
		return dateFormat.format(nowDate);
	}
	/**
	  * pkDate(获取批扣日期)
	  *
	  * @Title: pkDate
	  * @Description: 获取批扣日期
	  * @Date May 3, 2012 1:34:02 PM
	  * @modifyDate May 3, 2012 1:34:02 PM
	  * @return String 批扣日期 java.lang.String
	 */
	public static String pkDate() {
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DAY_OF_MONTH, -1);
		Date dc = cal1.getTime();
		return CalendarUtil.getDateD(dc.getTime(), CalendarUtil.YYYYMMDD);
	}

	/**
	  * formatDate(格式化日期 格式为 yyyy-MM-dd)
	  *
	  * @Title: formatDate
	  * @Description: 格式化日期 格式为 yyyy-MM-dd
	  * @Date May 3, 2012 1:36:11 PM
	  * @modifyDate May 3, 2012 1:36:11 PM
	  * @param date 需要格式化的时间对象
	  * @return String 格式化后的String java.lang.String
	 */
	public static String formatDate(java.util.Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}
	/**
	  * getDate(通过字符类型的日期跟格式得到该时间的毫秒数)
	  * TODO(注意：如果格式化错误的话，返回的为null，并e.printStackTrace()打印异常堆栈信息)
	  *
	  * @Title: getDate
	  * @Description: 通过字符类型的日期跟格式得到该时间的毫秒数
	  * @Date May 3, 2012 1:37:13 PM
	  * @modifyDate May 3, 2012 1:37:13 PM
	  * @param date String的日期 java.lang.String
	  * @param format 格式 java.lang.String
	  * @return long 时间的毫秒数 long
	 */
	public static Long getDate(String date, String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date dd = sdf.parse(date);
			return new Long(dd.getTime());
		} catch (ParseException e) {
			LogCvt.error(e.getMessage(), e);
			return null;
		}

	}
	/**
	  * getLongD(将date类型的日期转换为yyyyMMdd格式的long类型的时间)
	  * TODO(注意：如果格式化错误的话，返回的为null，并e.printStackTrace()打印异常堆栈信息)
	  *
	  * @Title: getLongD
	  * @Description: 将date类型的日期转换为yyyyMMdd格式的long类型的时间
	  * @Date May 3, 2012 1:39:23 PM
	  * @modifyDate May 3, 2012 1:39:23 PM
	  * @param date 需要转换的日期 java.util.Date
	  * @return Long 得到的long日期 java.lang.Long
	 */
	public static Long getLongD(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String yymmdd = sdf.format(date);
		try {
			long l = sdf.parse(yymmdd).getTime();
			return new Long(l);
		} catch (Exception e) {
			LogCvt.error(e.getMessage(), e);
			return null;
		}
	}
	/**
	  * getLongD_s(根据日期得到毫秒数)
	  *
	  * @Title: getLongD_s
	  * @Description: 根据日期得到毫秒数
	  * @Date May 3, 2012 1:41:49 PM
	  * @modifyDate May 3, 2012 1:41:49 PM
	  * @param date 日期对象 java.util.Date
	  * @return Long 得到的Long日期 java.lang.Long
	 */
	public static Long getLongDs(java.util.Date date) {
		return new Long(date.getTime());
	}
	/**
	  * getLongD_t(将date类型的日期转换为HH:mm:ss SSS格式的long类型的时间)
	  * TODO(注意：如果格式化错误的话，返回的为null，并e.printStackTrace()打印异常堆栈信息)
	  *
	  * @Title: getLongD_t
	  * @Description: 将date类型的日期转换为HH:mm:ss SSS格式的long类型的时间
	  * @Date May 3, 2012 1:39:23 PM
	  * @modifyDate May 3, 2012 1:39:23 PM
	  * @param date 需要转换的日期 java.util.Date
	  * @return Long 得到的long日期 java.lang.Long
	 */
	public static Long getLongDt(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss SSS");
		String time = sdf.format(date);
		try {
			long l = sdf.parse(time).getTime();
			return new Long(l);
		} catch (Exception e) {
			LogCvt.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	  * getDate_d(按照格式将Long类型的日期转换为String为日期)
	  *
	  * @Title: getDate_d
	  * @Description: 按照格式将Long类型的日期转换为String为日期
	  * @Date May 3, 2012 1:43:59 PM
	  * @modifyDate May 3, 2012 1:43:59 PM
	  * @param ld Long类型的日期 java.lang.Long
	  * @param format 格式 java.lang.String
	  * @return String 得到的String日期 java.lang.String
	 */
	public static String getDateD(Long ld, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dd = new Date(ld.longValue());
		return sdf.format(dd);
	}
	/**
	  * getLong_d(根据String日期得到Long类型日期)
	  *
	  * @Title: getLong_d
	  * @Description: 根据String日期得到Long类型日期
	  * @Date May 3, 2012 1:46:12 PM
	  * @modifyDate May 3, 2012 1:46:12 PM
	  * @param date String日期 java.lang.String
	  * @return Long Long类型日期 java.lang.Long
	 */
	public static Long getLongD(String date) {

		String year;
		String month;
		String day;
		String hour = "0";
		String second = "0";
		String minute = "0";

		date = date.replaceAll("/", "");
		date = date.replaceAll("-", "");
		// date = date.replaceAll(".","") ;
		date = date.replaceAll(":", "");
		date = date.replaceAll(" ", "");
		date = date.trim();
		year = date.substring(0, 4);
		month = date.substring(4, 6);
		day = date.substring(6, 8);
		if (date.length() > 8) {
			hour = date.substring(8, 10);
			minute = date.substring(10, 12);
			second = date.substring(12, 14);
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
		cal.set(Calendar.SECOND, Integer.parseInt(second));
		cal.set(Calendar.MINUTE, Integer.parseInt(minute));
		cal.set(Calendar.MILLISECOND, 0);
		Date dc = cal.getTime();

		return new Long(dc.getTime());

	}
	/**
	  * getMonthBegin(根据传入的日期，得到该月的第一天（也就是一号）)
	  *
	  * @Title: getMonthBegin
	  * @Description: 根据传入的日期，得到该月的第一天（也就是一号）
	  * @Date May 3, 2012 1:47:57 PM
	  * @modifyDate May 3, 2012 1:47:57 PM
	  * @param strdate 传入的日期 java.lang.String
	  * @return String 得到的日期 java.lang.String
	 */
	public static String getMonthBegin(String strdate) {
		java.util.Date date = parseFormatDate(strdate);
		return formatDateByFormat(date, "yyyy-MM") + "-01";
	}
	/**
	  * getMonthEnd(根据传入的日期，得到该月的最后一天)
	  *
	  * @Title: getMonthEnd
	  * @Description: 根据传入的日期，得到该月的最后一天
	  * @Date May 3, 2012 1:48:34 PM
	  * @modifyDate May 3, 2012 1:48:34 PM
	  * @param strdate 传入的日期 java.lang.String
	  * @return String 得到的日期 java.lang.String
	 */
	public static String getMonthEnd(String strdate) {
		java.util.Date date = parseFormatDate(getMonthBegin(strdate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}
	
	/**
	 * 根据日期计算所在周的周一和周日
	 * @param time 指定的日期
	 * @return
	 */
	public static String[] convertWeekByDate(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		//判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = calendar.get(Calendar.DAY_OF_WEEK); //获得当前日期是一个星期的第几天
		if (dayWeek == 1){
			calendar.add(Calendar.DAY_OF_MONTH, -1);
		}
		calendar.setFirstDayOfWeek(Calendar.MONDAY); //设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = calendar.get(Calendar.DAY_OF_WEEK); //获得当前日期是一个星期的第几天
		calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek()-day); //根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		Date begin = calendar.getTime();
		calendar.add(Calendar.DATE, 6);
		Date end = calendar.getTime();
		return new String[]{
			CalendarUtil.getDateD(begin.getTime(), CalendarUtil.YYYY_MM_DD),
			CalendarUtil.getDateD(end.getTime(), CalendarUtil.YYYY_MM_DD)
		};
	}
	
	/**
	  * parseFormatDate(将String类型的日期转换成date类型的日期)
	  *
	  * @Title: parseFormatDate
	  * @Description: 将String类型的日期转换成date类型的日期
	  * @Date May 3, 2012 1:49:09 PM
	  * @modifyDate May 3, 2012 1:49:09 PM
	  * @return Date 得到的日期 java.util.Date
	 */
	public static Date parseFormatDate(String strDate) {
		java.util.Date date = null;
		if (strDate.indexOf("-") > 0) {
			date = parseFormatDateBy(strDate, "-");

		} else if (strDate.indexOf(".") > 0) {
			date = parseFormatDateBy(strDate, ".");

		} else if (strDate.indexOf(".") > 0) {
			date = parseFormatDateBy(strDate, ".");

		}
		return date;

	}
	/**
	  * parseFormatDateBy(通过相应的年月日分割符来进行相应的格式化)
	  *
	  * @Title: parseFormatDateBy
	  * @Description: 通过相应的年月日分割符来进行相应的格式化
	  * @Date May 3, 2012 1:50:04 PM
	  * @modifyDate May 3, 2012 1:50:04 PM
	  * @param strDate 传入的日期 java.lang.String
	  * @param division 分隔符 java.lang.String
	  * @return Date 得到的日期 java.util.Date
	 */
	public static java.util.Date parseFormatDateBy(String strDate,
			String division) {
		java.util.Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + division + "MM" + division + "dd");
			date = sdf.parse(strDate);
		} catch (Exception ex) {
			LogCvt.error(ex.getMessage(), ex);
		}
		return date;
	}
	/**
	  * getYear(根据日期得到年)
	  *
	  * @Title: getYear
	  * @Description: 根据日期得到年
	  * @Date May 3, 2012 1:52:03 PM
	  * @modifyDate May 3, 2012 1:52:03 PM
	  * @param date 传入的日期 java.util.Date
	  * @return int 得到的年 int
	 */
	public static int getYear(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}
	/**
	  * getMonth(根据日期得到月份)
	  *
	  * @Title: getMonth
	  * @Description: 根据日期得到月份
	  * @Date May 3, 2012 2:10:15 PM
	  * @modifyDate May 3, 2012 2:10:15 PM
	  * @param date 传入的日期 java.util.Date
	  * @return int 得到的月份 int
	 */
	public static int getMonth(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}
	/**
	  * getDay(根据日期得到天)
	  *
	  * @Title: getDay
	  * @Description: 根据日期得到天
	  * @Date May 3, 2012 2:11:03 PM
	  * @modifyDate May 3, 2012 2:11:03 PM
	  * @param date 传入的日期 java.util.Date
	  * @return int 得到的天 int
	 */
	public static int getDay(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}
	/**
	  * getHour(根据日期得到小时数)
	  *
	  * @Title: getHour
	  * @Description: 根据日期得到小时数
	  * @Date May 3, 2012 2:11:41 PM
	  * @modifyDate May 3, 2012 2:11:41 PM
	  * @param date 传入的日期 java.util.Date
	  * @return int 得到的小时数 int
	 */
	public static int getHour(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}
	/**
	  * getMinute(根据日期得到分钟数)
	  *
	  * @Title: getMinute
	  * @Description: 根据日期得到分钟数
	  * @Date May 3, 2012 2:12:25 PM
	  * @modifyDate May 3, 2012 2:12:25 PM
	  * @param date 传入的日期 java.util.Date
	  * @return int 得到的分钟数 int
	 */
	public static int getMinute(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}
	/**
	  * getSecond(根据日期得到秒数)
	  *
	  * @Title: getSecond
	  * @Description: 根据日期得到秒数
	  * @Date May 3, 2012 2:13:08 PM
	  * @modifyDate May 3, 2012 2:13:08 PM
	  * @param date 传入的日期 java.util.Date
	  * @return int 得到的秒数 int
	 */
	public static int getSecond(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}
	/**
	  * getMillis(根据日期得到毫秒数)
	  *
	  * @Title: getMillis
	  * @Description: 根据日期得到毫秒数
	  * @Date May 3, 2012 2:14:08 PM
	  * @modifyDate May 3, 2012 2:14:08 PM
	  * @param date 传入的日期 java.util.Date
	  * @return int 得到的毫秒数 int
	 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}
	/**
	  * addDate(在date类型的时间上加上给定的天数)
	  *
	  * @Title: addDate
	  * @Description: 在date类型的时间上加上给定的天数
	  * @Date May 3, 2012 2:14:54 PM
	  * @modifyDate May 3, 2012 2:14:54 PM
	  * @param date  传入的日期 java.util.Date
	  * @param day 天数 int
	  * @return Date 得到的日期 java.util.Date
	 */
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}
	/**
	  * addDate(将传入的String日期根据需要添加的类型(type)进行加入(minute)，并按照相应的格式返回String)
	  * TODO(Type的参数通过Calendar的静态变量得到，如：Calendar.DAY_OF_MONTH)
	  *
	  * @Title: addDate
	  * @Description: TODO
	  * @Date May 3, 2012 2:16:19 PM
	  * @modifyDate May 3, 2012 2:16:19 PM
	  * @param dateStr 传入的日期 java.lang.String
	  * @param type 需要添加的类型 int (通过Calendar的静态变量得到，如：Calendar.DAY_OF_MONTH)
	  * @param minute 加入的数值 int
	  * @param format 格式
	  * @return String 得到的日期值 java.lang.String
	 */
	public static String addDate(String dateStr, int type, int minute,
			String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			LogCvt.error(e.getMessage(), e);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, minute);
		Date nowDate = calendar.getTime();
		return dateFormat.format(nowDate);
	}
	/**
	  * subDate(计算2个时间相差的天数)
	  *
	  * @Title: subDate
	  * @Description: 计算2个时间相差的天数
	  * @Date May 3, 2012 2:19:41 PM
	  * @modifyDate May 3, 2012 2:19:41 PM
	  * @param date 第一个日期 java.util.Date
	  * @param date1 第二个日期 java.util.Date
	  * @return int 得到第一个日期-第二个日期的相差天数 int
	 */
	public static int subDate(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}
	/**
	  * addDate(在传入的String日期中加入天数并按照相应的格式得到String日期)
	  *
	  * @Title: addDate
	  * @Description: 在传入的String日期中加入天数并按照相应的格式得到String日期
	  * @Date May 3, 2012 2:21:09 PM
	  * @modifyDate May 3, 2012 2:21:09 PM
	  * @param date 传入的String日期 java.lang.String
	  * @param format 格式 java.lang.String
	  * @param day 天数 java.lang.String
	  * @return String 得到的String日期 java.lang.String
	  * @throws ParseException 转换异常
	 */
	public static String addDate(String date, String format, int day) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		long d1 = sdf.parse(date).getTime() + (long) day * 24 * 3600 * 1000;

		return formatDateByFormat(new Date(d1), format);
	}
	/*
	 * 日期比较函数 传入date1 date2 format return -1 date1<date2 return 0 date1=date2
	 * return 1 date1>date2
	 */
	/**
	  * compareDate(日期比较函数)
	  * TODO(注意：结果为： 传入date1 date2 format return -1 date1<date2 return 0 date1=date2 1 date1>date2)
	  *
	  * @Title: compareDate
	  * @Description: 日期比较函数 传入date1 date2 format return -1 date1<date2 return 0 date1=date2 1 date1>date2
	  * @Date May 3, 2012 2:23:48 PM
	  * @modifyDate May 3, 2012 2:23:48 PM
	  * @param date1 第一个日期 java.lang.String
	  * @param date2 第二个日期 java.lang.String
	  * @param format 两个日期转换的格式 java.lang.String
	  * @return int 得到的比较结果 1 date1>date2 0 date1=date2 -1 date1<date2
	  * @throws Exception 异常
	 */
	public static int compareDate(String date1, String date2, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		long d1 = sdf.parse(date1).getTime();
		long d2 = sdf.parse(date2).getTime();
		if (d1 < d2){
			return -1;
		} else if (d1 == d2){
			return 0;
		} else {
			return 1;
		}
			
	}
	/**
	  * getCurWeek(当前日期的星期)
	  *
	  * @Title: getCurWeek
	  * @Description: 当前日期的星期
	  * @Date May 3, 2012 2:25:52 PM
	  * @modifyDate May 3, 2012 2:25:52 PM
	  * @return String 得到星期 java.lang.String (如:星期日)
	 */
	public static String getCurWeek() {
		int k = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, "星期日");
		map.put(1, "星期一");
		map.put(2, "星期二");
		map.put(3, "星期三");
		map.put(4, "星期四");
		map.put(5, "星期五");
		map.put(6, "星期六");
		
		for (Entry<Integer, String> entry:map.entrySet()){    
			if (entry.getKey()==k){
				return entry.getValue();
			} 
		}
		return "";
	}
	/**
	  * subDateByDays(日期相减)
	  * TODO(返回减去后的日期，格式与原日期的格式相同)
	  *
	  * @Title: subDateByDays
	  * @Description: 日期相减
	  * @Date May 3, 2012 2:26:57 PM
	  * @modifyDate May 3, 2012 2:26:57 PM
	  * @param date 原日期 java.lang.String
	  * @param days 需要减去的天数
	  * @param format 日期的格式
	  * @return String 得到减去的日期 java.lang.String (返回减去后的日期，格式与原日期的格式相同)
	  * @throws Exception 异常
	 */
	public static String subDateByDays(String date, int days, String format)
		throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		// 将原日期转换成毫秒
		long curDateTimes = sdf.parse(date).getTime();
		// 将需要减去的天数转换成毫秒
		long daysTimes = Long.parseLong(String.valueOf(days)) * 24 * 3600 * 1000;
		// 对毫秒进行相减，最后转换成需要的格式
		long result = curDateTimes - daysTimes;
		Date d = new Date();
		d.setTime(result);
		return sdf.format(d).toString();
	}
	/**
	  * subAddDateByDays(日期相加)
	  *
	  * @Title: subAddDateByDays
	  * @Description: 日期相加
	  * @Date Jun 27, 2012 3:19:24 PM
	  * @modifyDate Jun 27, 2012 3:19:24 PM
	  * @param date 原日期
	  * @param days 需要加上的天数
	  * @param format 日期的格式
	  * @return 返回相加后的日期，格式与原日期的格式相同
	  * @throws Exception
	 */
  	public static String subAddDateByDays(String date, int days, String format)
  		throws Exception {
  		SimpleDateFormat sdf = new SimpleDateFormat(format);
  		// 将原日期转换成毫秒
  		long curDateTimes = sdf.parse(date).getTime();
  		// 将需要相加的天数转换成毫秒
  		long daysTimes = Long.parseLong(String.valueOf(days)) * 24 * 3600 * 1000;
  		// 对毫秒进行加，最后转换成需要的格式
  		long result = curDateTimes + daysTimes;
  		Date d = new Date();
  		d.setTime(result);
  		return sdf.format(d).toString();
  	}
  	/**
  	  * subDateGetMonth(两个时间相减，得到月份)
  	  *
  	  * @Title: subDateGetMonth
  	  * @Description: 两个时间相减，得到月份
  	  * @Date Jul 25, 2012 9:54:22 AM
  	  * @modifyDate Jul 25, 2012 9:54:22 AM
  	  * @param maxDate 大的时间对象 java.util.Date
  	  * @param minDate 小的时间对象 java.util.Date
  	  * @return int 得到的月份
  	 */
  	public static int subDateGetMonth(java.util.Date maxDate, java.util.Date minDate) {
  	  
  		int iMaxDateYear=getYear(maxDate);
  		int iMinDateYear=getYear(minDate);
  	  
  		int iMaxDateMonth=getMonth(maxDate);
  		int iMinDateMonth=getMonth(minDate);
  	  
  	  
  		int iSubYear=iMaxDateYear-iMinDateYear;
  	  
  		int iSubMonth=iMaxDateMonth-iMinDateMonth;
  	  
  		int iSubTotalMonth=(iSubYear*12)+iSubMonth;
  	  
        return iSubTotalMonth;
        
    }

  	public static String strDateFormStr(String date, String format){
		if(!StringUtil.isNotEmpty(date)){
			return date;
		}
		if(!StringUtil.isNotEmpty(format)){
			format = CalendarUtil.YYYY_MM_DD; 
		}
        SimpleDateFormat sdf1 = new SimpleDateFormat(format) ;        // 实例化模板对象    
        
		try {
			return sdf1.format(sdf1.parse(date));
		} catch (ParseException e) {
			LogCvt.error(e.getMessage(),e);
			return date;
		}
	}
	public static void main(String[] args) throws Exception {
//		int k = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
//		Map<Integer, String> map = new HashMap<Integer, String>();
//		map.put(0, "星期日");
//		map.put(1, "星期一");
//		map.put(2, "星期二");
//		map.put(3, "星期三");
//		map.put(4, "星期四");
//		map.put(5, "星期五");
//		map.put(6, "星期六");
//		String str="";
//		for (Entry<Integer, String> entry:map.entrySet()){    
//			if (entry.getKey()==k){
//				str = entry.getValue();
//			} 
//		}
	}
}

