/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
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
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
public final class CalendarUtil {
	
	/**
	 * @Fields format1 : yyyy-MM-dd����ʽ�� 
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	/**
	 * @Fields format2 : yyyy/MM/dd����ʽ�� 
	 */
	public static final String YYYYIMMIDD = "yyyy/MM/dd";
	/**
	 * @Fields format3 : yyyy��M��d�գ���ʽ�� 
	 */
	public static final String YYYY_YEAR_M_MONTH_D_DAY = "yyyy��M��d��";
	/**
	 * @Fields format4 : yyyyMMdd����ʽ�� 
	 */
	public static final String YYYYMMDD = "yyyyMMdd";
	/**
	 * @Fields format5 : yyyy-MM-dd HH:mm:ss����ʽ�� 
	 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * @Fields format6 : HH:mm:ss SSS����ʽ�� 
	 */
	public static final String HHMMSSSSS = "HH:mm:ss SSS";
	/**
	 * @Fields format7 : MMddHHmmss����ʽ�� 
	 */
	public static final String MMDDHHMMSS = "MMddHHmmss";
	/**
	 * @Fields format8 : ddHHmmssSSS����ʽ�� 
	 */
	public static final String DDHHMMSSSSS = "ddHHmmssSSS";
	/**
	 * @Fields format9 : yyyyMMddHHmmssSSS����ʽ�� 
	 */
	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	/**
	 * @Fields format10 : yyyy-MM-dd HH:mm:ss E����ʽ�� 
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_E = "yyyy-MM-dd HH:mm:ss E";
	/**
	 * @Fields format11 : yyyy-MM-dd HH:mm:ss SSS����ʽ�� 
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss SSS";
	/**
	 * @Fields HH : HH����ʽ�� 
	 */
	public static final String HH = "HH";
	/**
	 * @Fields HHmmss : HH:mm:ss����ʽ�� 
	 */
	public static final String HH_MM_SS = "HH:mm:ss";
	/**
	 * @Fields HHmmss1 : HHmmss����ʽ�� 
	 */
	public static final String HHMMSS = "HHmmss"; 
	/**
	 * @Fields HHmm : HHmm����ʽ�� 
	 */
	public static final String HHMM = "HHmm";
	
	/**
	 * @Fields yyyyMMddHHmmss : yyyyMMddHHmmss����ʽ�� 
	 */
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	/**
	 * @Fields YYYYMM : YYYYMM����ʽ�� 
	 */
	public static final String YYYYMM = "yyyyMM";
	/**
	 * @Fields format12 : M��d��HH:mm����ʽ�� 
	 */
	public static final String M_MONTH_D_DAY_HH_MM = "M��d��HH:mm";
	
	public static final String YYYY_MM_DD_HH_MM_SS_DOT_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	
	/**
	  * ����һ���µ�ʵ�� CalendarUtil. 
	  * <p>Title: ���췽��</p>
	  * <p>Description: �����������������</p>
	 */
	private CalendarUtil() {
	}
	/**
	  * formatDateByFormat(ͨ�����ڶ��󣬰���ָ����ʽ��ʽ������ΪString��ʽ)
	  *
	  * @Title: formatDateByFormat
	  * @Description: ͨ�����ڶ��󣬰���ָ����ʽ��ʽ������ΪString��ʽ
	  * @Date May 3, 2012 1:23:41 PM
	  * @modifyDate May 3, 2012 1:23:41 PM
	  * @param date ���ڶ��� java.util.Date
	  * @param format ת���ĸ�ʽ java.lang.String
	  * @return String ת�����String����
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
	  * addMonth(��String�����а��ո�ʽ�����)
	  * TODO(ע�⣺�ڲ���׽���쳣����e.printStackTrace()����˶�ջ��Ϣ)
	  * TODO(ע�⣺������������ԭ�����·��ϼ�����Ҫ��ӵ�����)
	  *
	  * @Title: addMonth
	  * @Description: ��String�����а��ո�ʽ�����
	  * @Date May 3, 2012 1:26:41 PM
	  * @modifyDate May 3, 2012 1:26:41 PM
	  * @param dateStr ��Ҫ����·ݵ����� java.lang.String
	  * @param month ������� int
	  * @param format ��ʽ java.String
	  * @return String ���õ��Ľ�� java.lang.String
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
	  * pkDate(��ȡ��������)
	  *
	  * @Title: pkDate
	  * @Description: ��ȡ��������
	  * @Date May 3, 2012 1:34:02 PM
	  * @modifyDate May 3, 2012 1:34:02 PM
	  * @return String �������� java.lang.String
	 */
	public static String pkDate() {
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DAY_OF_MONTH, -1);
		Date dc = cal1.getTime();
		return CalendarUtil.getDateD(dc.getTime(), CalendarUtil.YYYYMMDD);
	}

	/**
	  * formatDate(��ʽ������ ��ʽΪ yyyy-MM-dd)
	  *
	  * @Title: formatDate
	  * @Description: ��ʽ������ ��ʽΪ yyyy-MM-dd
	  * @Date May 3, 2012 1:36:11 PM
	  * @modifyDate May 3, 2012 1:36:11 PM
	  * @param date ��Ҫ��ʽ����ʱ�����
	  * @return String ��ʽ�����String java.lang.String
	 */
	public static String formatDate(java.util.Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}
	/**
	  * getDate(ͨ���ַ����͵����ڸ���ʽ�õ���ʱ��ĺ�����)
	  * TODO(ע�⣺�����ʽ������Ļ������ص�Ϊnull����e.printStackTrace()��ӡ�쳣��ջ��Ϣ)
	  *
	  * @Title: getDate
	  * @Description: ͨ���ַ����͵����ڸ���ʽ�õ���ʱ��ĺ�����
	  * @Date May 3, 2012 1:37:13 PM
	  * @modifyDate May 3, 2012 1:37:13 PM
	  * @param date String������ java.lang.String
	  * @param format ��ʽ java.lang.String
	  * @return long ʱ��ĺ����� long
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
	  * getLongD(��date���͵�����ת��ΪyyyyMMdd��ʽ��long���͵�ʱ��)
	  * TODO(ע�⣺�����ʽ������Ļ������ص�Ϊnull����e.printStackTrace()��ӡ�쳣��ջ��Ϣ)
	  *
	  * @Title: getLongD
	  * @Description: ��date���͵�����ת��ΪyyyyMMdd��ʽ��long���͵�ʱ��
	  * @Date May 3, 2012 1:39:23 PM
	  * @modifyDate May 3, 2012 1:39:23 PM
	  * @param date ��Ҫת�������� java.util.Date
	  * @return Long �õ���long���� java.lang.Long
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
	  * getLongD_s(�������ڵõ�������)
	  *
	  * @Title: getLongD_s
	  * @Description: �������ڵõ�������
	  * @Date May 3, 2012 1:41:49 PM
	  * @modifyDate May 3, 2012 1:41:49 PM
	  * @param date ���ڶ��� java.util.Date
	  * @return Long �õ���Long���� java.lang.Long
	 */
	public static Long getLongDs(java.util.Date date) {
		return new Long(date.getTime());
	}
	/**
	  * getLongD_t(��date���͵�����ת��ΪHH:mm:ss SSS��ʽ��long���͵�ʱ��)
	  * TODO(ע�⣺�����ʽ������Ļ������ص�Ϊnull����e.printStackTrace()��ӡ�쳣��ջ��Ϣ)
	  *
	  * @Title: getLongD_t
	  * @Description: ��date���͵�����ת��ΪHH:mm:ss SSS��ʽ��long���͵�ʱ��
	  * @Date May 3, 2012 1:39:23 PM
	  * @modifyDate May 3, 2012 1:39:23 PM
	  * @param date ��Ҫת�������� java.util.Date
	  * @return Long �õ���long���� java.lang.Long
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
	  * getDate_d(���ո�ʽ��Long���͵�����ת��ΪStringΪ����)
	  *
	  * @Title: getDate_d
	  * @Description: ���ո�ʽ��Long���͵�����ת��ΪStringΪ����
	  * @Date May 3, 2012 1:43:59 PM
	  * @modifyDate May 3, 2012 1:43:59 PM
	  * @param ld Long���͵����� java.lang.Long
	  * @param format ��ʽ java.lang.String
	  * @return String �õ���String���� java.lang.String
	 */
	public static String getDateD(Long ld, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dd = new Date(ld.longValue());
		return sdf.format(dd);
	}
	/**
	  * getLong_d(����String���ڵõ�Long��������)
	  *
	  * @Title: getLong_d
	  * @Description: ����String���ڵõ�Long��������
	  * @Date May 3, 2012 1:46:12 PM
	  * @modifyDate May 3, 2012 1:46:12 PM
	  * @param date String���� java.lang.String
	  * @return Long Long�������� java.lang.Long
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
	  * getMonthBegin(���ݴ�������ڣ��õ����µĵ�һ�죨Ҳ����һ�ţ�)
	  *
	  * @Title: getMonthBegin
	  * @Description: ���ݴ�������ڣ��õ����µĵ�һ�죨Ҳ����һ�ţ�
	  * @Date May 3, 2012 1:47:57 PM
	  * @modifyDate May 3, 2012 1:47:57 PM
	  * @param strdate ��������� java.lang.String
	  * @return String �õ������� java.lang.String
	 */
	public static String getMonthBegin(String strdate) {
		java.util.Date date = parseFormatDate(strdate);
		return formatDateByFormat(date, "yyyy-MM") + "-01";
	}
	/**
	  * getMonthEnd(���ݴ�������ڣ��õ����µ����һ��)
	  *
	  * @Title: getMonthEnd
	  * @Description: ���ݴ�������ڣ��õ����µ����һ��
	  * @Date May 3, 2012 1:48:34 PM
	  * @modifyDate May 3, 2012 1:48:34 PM
	  * @param strdate ��������� java.lang.String
	  * @return String �õ������� java.lang.String
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
	 * �������ڼ��������ܵ���һ������
	 * @param time ָ��������
	 * @return
	 */
	public static String[] convertWeekByDate(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		//�ж�Ҫ����������Ƿ������գ���������һ����������ģ����������⣬���㵽��һ��ȥ��
		int dayWeek = calendar.get(Calendar.DAY_OF_WEEK); //��õ�ǰ������һ�����ڵĵڼ���
		if (dayWeek == 1){
			calendar.add(Calendar.DAY_OF_MONTH, -1);
		}
		calendar.setFirstDayOfWeek(Calendar.MONDAY); //����һ�����ڵĵ�һ�죬���й���ϰ��һ�����ڵĵ�һ��������һ
		int day = calendar.get(Calendar.DAY_OF_WEEK); //��õ�ǰ������һ�����ڵĵڼ���
		calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek()-day); //���������Ĺ��򣬸���ǰ���ڼ�ȥ���ڼ���һ�����ڵ�һ��Ĳ�ֵ
		Date begin = calendar.getTime();
		calendar.add(Calendar.DATE, 6);
		Date end = calendar.getTime();
		return new String[]{
			CalendarUtil.getDateD(begin.getTime(), CalendarUtil.YYYY_MM_DD),
			CalendarUtil.getDateD(end.getTime(), CalendarUtil.YYYY_MM_DD)
		};
	}
	
	/**
	  * parseFormatDate(��String���͵�����ת����date���͵�����)
	  *
	  * @Title: parseFormatDate
	  * @Description: ��String���͵�����ת����date���͵�����
	  * @Date May 3, 2012 1:49:09 PM
	  * @modifyDate May 3, 2012 1:49:09 PM
	  * @return Date �õ������� java.util.Date
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
	  * parseFormatDateBy(ͨ����Ӧ�������շָ����������Ӧ�ĸ�ʽ��)
	  *
	  * @Title: parseFormatDateBy
	  * @Description: ͨ����Ӧ�������շָ����������Ӧ�ĸ�ʽ��
	  * @Date May 3, 2012 1:50:04 PM
	  * @modifyDate May 3, 2012 1:50:04 PM
	  * @param strDate ��������� java.lang.String
	  * @param division �ָ��� java.lang.String
	  * @return Date �õ������� java.util.Date
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
	  * getYear(�������ڵõ���)
	  *
	  * @Title: getYear
	  * @Description: �������ڵõ���
	  * @Date May 3, 2012 1:52:03 PM
	  * @modifyDate May 3, 2012 1:52:03 PM
	  * @param date ��������� java.util.Date
	  * @return int �õ����� int
	 */
	public static int getYear(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}
	/**
	  * getMonth(�������ڵõ��·�)
	  *
	  * @Title: getMonth
	  * @Description: �������ڵõ��·�
	  * @Date May 3, 2012 2:10:15 PM
	  * @modifyDate May 3, 2012 2:10:15 PM
	  * @param date ��������� java.util.Date
	  * @return int �õ����·� int
	 */
	public static int getMonth(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}
	/**
	  * getDay(�������ڵõ���)
	  *
	  * @Title: getDay
	  * @Description: �������ڵõ���
	  * @Date May 3, 2012 2:11:03 PM
	  * @modifyDate May 3, 2012 2:11:03 PM
	  * @param date ��������� java.util.Date
	  * @return int �õ����� int
	 */
	public static int getDay(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}
	/**
	  * getHour(�������ڵõ�Сʱ��)
	  *
	  * @Title: getHour
	  * @Description: �������ڵõ�Сʱ��
	  * @Date May 3, 2012 2:11:41 PM
	  * @modifyDate May 3, 2012 2:11:41 PM
	  * @param date ��������� java.util.Date
	  * @return int �õ���Сʱ�� int
	 */
	public static int getHour(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}
	/**
	  * getMinute(�������ڵõ�������)
	  *
	  * @Title: getMinute
	  * @Description: �������ڵõ�������
	  * @Date May 3, 2012 2:12:25 PM
	  * @modifyDate May 3, 2012 2:12:25 PM
	  * @param date ��������� java.util.Date
	  * @return int �õ��ķ����� int
	 */
	public static int getMinute(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}
	/**
	  * getSecond(�������ڵõ�����)
	  *
	  * @Title: getSecond
	  * @Description: �������ڵõ�����
	  * @Date May 3, 2012 2:13:08 PM
	  * @modifyDate May 3, 2012 2:13:08 PM
	  * @param date ��������� java.util.Date
	  * @return int �õ������� int
	 */
	public static int getSecond(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}
	/**
	  * getMillis(�������ڵõ�������)
	  *
	  * @Title: getMillis
	  * @Description: �������ڵõ�������
	  * @Date May 3, 2012 2:14:08 PM
	  * @modifyDate May 3, 2012 2:14:08 PM
	  * @param date ��������� java.util.Date
	  * @return int �õ��ĺ����� int
	 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}
	/**
	  * addDate(��date���͵�ʱ���ϼ��ϸ���������)
	  *
	  * @Title: addDate
	  * @Description: ��date���͵�ʱ���ϼ��ϸ���������
	  * @Date May 3, 2012 2:14:54 PM
	  * @modifyDate May 3, 2012 2:14:54 PM
	  * @param date  ��������� java.util.Date
	  * @param day ���� int
	  * @return Date �õ������� java.util.Date
	 */
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}
	/**
	  * addDate(�������String���ڸ�����Ҫ��ӵ�����(type)���м���(minute)����������Ӧ�ĸ�ʽ����String)
	  * TODO(Type�Ĳ���ͨ��Calendar�ľ�̬�����õ����磺Calendar.DAY_OF_MONTH)
	  *
	  * @Title: addDate
	  * @Description: TODO
	  * @Date May 3, 2012 2:16:19 PM
	  * @modifyDate May 3, 2012 2:16:19 PM
	  * @param dateStr ��������� java.lang.String
	  * @param type ��Ҫ��ӵ����� int (ͨ��Calendar�ľ�̬�����õ����磺Calendar.DAY_OF_MONTH)
	  * @param minute �������ֵ int
	  * @param format ��ʽ
	  * @return String �õ�������ֵ java.lang.String
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
	  * subDate(����2��ʱ����������)
	  *
	  * @Title: subDate
	  * @Description: ����2��ʱ����������
	  * @Date May 3, 2012 2:19:41 PM
	  * @modifyDate May 3, 2012 2:19:41 PM
	  * @param date ��һ������ java.util.Date
	  * @param date1 �ڶ������� java.util.Date
	  * @return int �õ���һ������-�ڶ������ڵ�������� int
	 */
	public static int subDate(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}
	/**
	  * addDate(�ڴ����String�����м���������������Ӧ�ĸ�ʽ�õ�String����)
	  *
	  * @Title: addDate
	  * @Description: �ڴ����String�����м���������������Ӧ�ĸ�ʽ�õ�String����
	  * @Date May 3, 2012 2:21:09 PM
	  * @modifyDate May 3, 2012 2:21:09 PM
	  * @param date �����String���� java.lang.String
	  * @param format ��ʽ java.lang.String
	  * @param day ���� java.lang.String
	  * @return String �õ���String���� java.lang.String
	  * @throws ParseException ת���쳣
	 */
	public static String addDate(String date, String format, int day) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		long d1 = sdf.parse(date).getTime() + (long) day * 24 * 3600 * 1000;

		return formatDateByFormat(new Date(d1), format);
	}
	/*
	 * ���ڱȽϺ��� ����date1 date2 format return -1 date1<date2 return 0 date1=date2
	 * return 1 date1>date2
	 */
	/**
	  * compareDate(���ڱȽϺ���)
	  * TODO(ע�⣺���Ϊ�� ����date1 date2 format return -1 date1<date2 return 0 date1=date2 1 date1>date2)
	  *
	  * @Title: compareDate
	  * @Description: ���ڱȽϺ��� ����date1 date2 format return -1 date1<date2 return 0 date1=date2 1 date1>date2
	  * @Date May 3, 2012 2:23:48 PM
	  * @modifyDate May 3, 2012 2:23:48 PM
	  * @param date1 ��һ������ java.lang.String
	  * @param date2 �ڶ������� java.lang.String
	  * @param format ��������ת���ĸ�ʽ java.lang.String
	  * @return int �õ��ıȽϽ�� 1 date1>date2 0 date1=date2 -1 date1<date2
	  * @throws Exception �쳣
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
	  * getCurWeek(��ǰ���ڵ�����)
	  *
	  * @Title: getCurWeek
	  * @Description: ��ǰ���ڵ�����
	  * @Date May 3, 2012 2:25:52 PM
	  * @modifyDate May 3, 2012 2:25:52 PM
	  * @return String �õ����� java.lang.String (��:������)
	 */
	public static String getCurWeek() {
		int k = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, "������");
		map.put(1, "����һ");
		map.put(2, "���ڶ�");
		map.put(3, "������");
		map.put(4, "������");
		map.put(5, "������");
		map.put(6, "������");
		
		for (Entry<Integer, String> entry:map.entrySet()){    
			if (entry.getKey()==k){
				return entry.getValue();
			} 
		}
		return "";
	}
	/**
	  * subDateByDays(�������)
	  * TODO(���ؼ�ȥ������ڣ���ʽ��ԭ���ڵĸ�ʽ��ͬ)
	  *
	  * @Title: subDateByDays
	  * @Description: �������
	  * @Date May 3, 2012 2:26:57 PM
	  * @modifyDate May 3, 2012 2:26:57 PM
	  * @param date ԭ���� java.lang.String
	  * @param days ��Ҫ��ȥ������
	  * @param format ���ڵĸ�ʽ
	  * @return String �õ���ȥ������ java.lang.String (���ؼ�ȥ������ڣ���ʽ��ԭ���ڵĸ�ʽ��ͬ)
	  * @throws Exception �쳣
	 */
	public static String subDateByDays(String date, int days, String format)
		throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		// ��ԭ����ת���ɺ���
		long curDateTimes = sdf.parse(date).getTime();
		// ����Ҫ��ȥ������ת���ɺ���
		long daysTimes = Long.parseLong(String.valueOf(days)) * 24 * 3600 * 1000;
		// �Ժ��������������ת������Ҫ�ĸ�ʽ
		long result = curDateTimes - daysTimes;
		Date d = new Date();
		d.setTime(result);
		return sdf.format(d).toString();
	}
	/**
	  * subAddDateByDays(�������)
	  *
	  * @Title: subAddDateByDays
	  * @Description: �������
	  * @Date Jun 27, 2012 3:19:24 PM
	  * @modifyDate Jun 27, 2012 3:19:24 PM
	  * @param date ԭ����
	  * @param days ��Ҫ���ϵ�����
	  * @param format ���ڵĸ�ʽ
	  * @return ������Ӻ�����ڣ���ʽ��ԭ���ڵĸ�ʽ��ͬ
	  * @throws Exception
	 */
  	public static String subAddDateByDays(String date, int days, String format)
  		throws Exception {
  		SimpleDateFormat sdf = new SimpleDateFormat(format);
  		// ��ԭ����ת���ɺ���
  		long curDateTimes = sdf.parse(date).getTime();
  		// ����Ҫ��ӵ�����ת���ɺ���
  		long daysTimes = Long.parseLong(String.valueOf(days)) * 24 * 3600 * 1000;
  		// �Ժ�����мӣ����ת������Ҫ�ĸ�ʽ
  		long result = curDateTimes + daysTimes;
  		Date d = new Date();
  		d.setTime(result);
  		return sdf.format(d).toString();
  	}
  	/**
  	  * subDateGetMonth(����ʱ��������õ��·�)
  	  *
  	  * @Title: subDateGetMonth
  	  * @Description: ����ʱ��������õ��·�
  	  * @Date Jul 25, 2012 9:54:22 AM
  	  * @modifyDate Jul 25, 2012 9:54:22 AM
  	  * @param maxDate ���ʱ����� java.util.Date
  	  * @param minDate С��ʱ����� java.util.Date
  	  * @return int �õ����·�
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
        SimpleDateFormat sdf1 = new SimpleDateFormat(format) ;        // ʵ����ģ�����    
        
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
//		map.put(0, "������");
//		map.put(1, "����һ");
//		map.put(2, "���ڶ�");
//		map.put(3, "������");
//		map.put(4, "������");
//		map.put(5, "������");
//		map.put(6, "������");
//		String str="";
//		for (Entry<Integer, String> entry:map.entrySet()){    
//			if (entry.getKey()==k){
//				str = entry.getValue();
//			} 
//		}
	}
}

