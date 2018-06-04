package com.zcsh.epay.util;
 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zcsh.epay.log.LogCvt;

public class DateUtil {

    /**
     * <p>��׼�����ڸ�ʽyyyyMMdd</p>
     */
    public static final String standardFormat = "yyyyMMdd";

    /**
     * <p>8λ���ڸ�ʽyy-MM-dd</p>
     */
    public static final String byte8Format = "yy-MM-dd";

    /**
     * <p>8λ���ڸ�ʽyy/MM/dd</p>
     */
    public static final String anotherByte8Format = "yy/MM/dd";

    /**
     * <p>6λ���ڸ�ʽyyMMdd</p>
     */
    public static final String byte6Format = "yyMMdd";

    /**
     * <p>10λ���ڸ�ʽyyyy-MM-dd</p>
     */
    public static final String byte10Format = "yyyy-MM-dd";

    /**
     * <p>17λʱ���ʽyyyyMMdd HH:mm:ss</p>
     */
    public static final String byte17Format = "yyyyMMdd HH:mm:ss";

    /**
     * <p>20λʱ���ʽyyyy-MM-dd HH:mm:ss</p>
     */
    public static final String anotherByte20Format = "yyyy-MM-dd HH:mm:ss";

    /**
     * <p>20λʱ���ʽyyyy-MM-dd HH:mm:ss</p>
     */
    public static final String anotherByte14Format = "yyyyMMddHHmmss";
    
    /**
     * <p>10λ���ڸ�ʽyyyy/MM/dd</p>
     */
    public static final String anotherByte10Format = "yyyy/MM/dd";

    /**
     * <p>get Date from a formated string</p>
     * <p/>
     * <pre>
     * getDateFromString("031009")
     * getDateFromString("20031009")
     * </pre>
     *
     * @param strDate the formated data String
     * @return a date associate with the parameter strDate
     * @throws Exception
     */
    public static Date getDateFromString(String strDate) throws Exception {
        int length = strDate.length();
        if (length == 6) {
            return getDateFromString(strDate, "yyMMdd");
        } else if (length == 8) {
            //����yy/MM/dd��yy-MM-dd��ʽ
            if ("/".equals(strDate.substring(2, 3)) && "/".equals(strDate.substring(5, 6))) {
                //strDate = strDate.replace('/','-');
                return getDateFromString(strDate, "yy/MM/dd");
            } else if ("-".equals(strDate.substring(2, 3)) && "-".equals(strDate.substring(5, 6))) {
                return getDateFromString(strDate, "yy-MM-dd");
            } else {
                //yyMMdd���͵�
                return getDateFromString(strDate, "yyyyMMdd");
            }

        } else if (length == 10) {
            if ("/".equals(strDate.substring(4, 5)) && "/".equals(strDate.substring(7, 8))) {
                return getDateFromString(strDate, "yyyy/MM/dd");
            } else if ("-".equals(strDate.substring(4, 5)) && "-".equals(strDate.substring(7, 8))) {
                return getDateFromString(strDate, "yyyy-MM-dd");
            }
        }
        return null;
    }

    /**
     * <p>get Date from a formated string</p>
     * ������ʵ�������ڸ�ʽ������������һ�µ�����£����ظ��������͵�date
     * ������쳣����Ϊ���Ͳ�ƥ���쳣
     * <pre>
     * getDateFromString("031009", "yyMMdd")
     * getDateFromString("20031009", "yyyyMMdd")
     * </pre>
     *
     * @param strDate the formated data String
     * @param format  the format of the strDate
     * @return a date associate with the parameter strDate
     * @throws Exception
     * @throws Exception
     */
    public static Date getDateFromString(String strDate, String format) throws Exception {
        int length = strDate.length();
        boolean isByte6FormatVolidate = false;
        boolean isByte8FormatVolidate = false;
        boolean isByte10FormatVolidate = false;

        if (length == 6) {
            if ("yyMMdd".equals(format)) {
                //���strDate��060417��format��yyMMdd
                isByte6FormatVolidate = true;
            }
        } else if (length == 8) {
            if ("/".equals(strDate.substring(2, 3)) && "/".equals(strDate.substring(5, 6))) {
                //��formatΪyy/MM/dd���ʱ
                if ("yy/MM/dd".equals(format)) {
                    isByte8FormatVolidate = true;
                }

            } else if ("-".equals(strDate.substring(2, 3)) && "-".equals(strDate.substring(5, 6))) {
                //��formatΪyy-MM-dd���ʱ
                if ("yy-MM-dd".equals(format)) {
                    isByte8FormatVolidate = true;
                }
            } else if ("yyyyMMdd".equals(format)) {
                isByte8FormatVolidate = true;
            }
        } else if (length == 10) {
            if ("/".equals(strDate.substring(4, 5)) && "/".equals(strDate.substring(7, 8))) {
                //��formatΪyyyy/MM/dd���ʱ
                if ("yyyy/MM/dd".equals(format)) {
                    isByte10FormatVolidate = true;
                }
            } else if ("-".equals(strDate.substring(4, 5)) && "-".equals(strDate.substring(7, 8))) {
                //��formatΪyyyy-MM-dd���ʱ
                if ("yyyy-MM-dd".equals(format)) {
                    isByte10FormatVolidate = true;
                }
            }
        }else {
            if (DateUtil.anotherByte20Format.equals( format )) {
                            isByte10FormatVolidate = true;
                        }
        }

        if (isByte6FormatVolidate || isByte8FormatVolidate || isByte10FormatVolidate) {

            try {
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                ParsePosition pos = new ParsePosition(0);

                return formatter.parse(strDate, pos);
            } catch (Exception e) {

            	LogCvt.error("������������[" + strDate + "]�Լ���ʽ [" + format + "] �����������ʹ���", e);

                throw new Exception("�����������ʹ���", e);
            }
        } else {
            throw new Exception("�����������ʽ��ƥ��");
        }

    }
 	/**
	 * ��ʽ������
	 *
	 * @param date
	 *
	 */
	public static String dateToString_5(Date date) {
		if (date == null) return "";
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			return formatter.format(date);
		} catch (Exception e) {
			return String.valueOf(date);
		}
	}
    /**
     * <p>date String from format 1 to format 2</p>
     * ������ʵ�ִӱ�׼���ڸ�ʽyyyyMMddת��Ϊformat���͵ĸ�ʽ
     * <pre>
     * getFormat2FromStd("20030908", "yyyy-MM-dd") = "2003-09-08"
     * </pre>
     *
     * @param dt the data to be convert
     * @return a String associate with the parameter dt
     * @throws Exception
     */
    public static String getFormat2FromStd(String date, String newFormat) throws Exception {
        //return getNewFormatFromStandardFormat(date, "yyyyMMdd", format);
        return getStringFromDate(getDateFromStringUsedForStdFrt(date, "yyyyMMdd"), newFormat);
    }

    /**
     * <p>date String from format 1 to format 2</p>
     * ������ʵ�ִ�һ�����ڸ�ʽdate�����Ӧ����������format1��ת��Ϊ��һ����������format2�����ڱ�ʾ
     * <pre>
     * getFormat2FromFormat1("03-09-08", "yy-MM-dd", "yyyyMMdd") = "20030908"
     * </pre>
     *
     * @param dt the data to be convert
     * @return a String associate with the parameter dt
     * @throws Exception
     */
    public static String getNewFormatFromOldFormat(String date, String format1, String format2) throws Exception {
        return getStringFromDate(getDateFromString(date, format1), format2);
        //ע�⣺�˴�����format2�ĸ�ʽ�޷�����У��

    }


    /**
     * ������ʵ�������ڸ�ʽ������������һ�µ�����£����ظ��������͵�date
     * ������쳣����Ϊ��׼���ڸ�ʽyyyyMMddת��Ϊ������ʽ(yyMMdd,yy-MM-dd,yy/MM/dd,yyyy-MM-dd,yyyy/MM/dd)�쳣
     *
     * @param strDate
     * @param format
     * @return
     * @throws Exception
     */
    private static Date getDateFromStringUsedForStdFrt(String strDate, String format) throws Exception {
        int length = strDate.length();
        boolean isByte8FormatVolidate = false;
        if ((strDate.indexOf("-") == -1) && (strDate.indexOf("/") == -1)) {
            isByte8FormatVolidate = true;
        }

        if (length == 8 && isByte8FormatVolidate) {

            try {
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                ParsePosition pos = new ParsePosition(0);
                return formatter.parse(strDate, pos);
            } catch (Exception e) {

            	LogCvt.error("������������[" + strDate + "]�Լ���ʽ [" + format + "] �����������ʹ���", e);

                throw new Exception("�����������ʹ���", e);
            }
        } else {
            throw new Exception("�������Ͳ��Ǳ�׼��ʽyyyyMMdd");
        }

    }

    /**
     * <p>get String from a Date</p>
     * <p/>
     * <pre>
     * getDateFromString(new Date(System.currentTimeMillis()))
     * </pre>
     *
     * @param dt the data to be convert
     * @return a String associate with the parameter dt
     * @throws Exception
     */
    public static String getStringFromDate(Date dt) throws Exception {
        return getStringFromDate(dt, "yyyyMMdd");
    }
    
    public static String getTodayDate() throws Exception {
        return getStringFromDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * <p>get String from a Date</p>
     * <p/>
     * <pre>
     * getDateFromString(new Date(System.currentTimeMillis()), "yyMMdd HH:mm:ss") = "030910 12:23:30"
     * </pre>
     * <p/>
     * ע�⣺����format��ʽ�ж���(�������͡��������� + ʱ�����͡�ʱ�����ͻ��߿����������ַ���)����˱�������format�ĸ�ʽ��û������У��
     *
     * @param dt     the data to be convert
     * @param format the format to be returned
     * @return a String associate with the parameter dt
     */
    public static String getStringFromDate(Date dt, String format) throws Exception {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(dt);
        } catch (Exception e) {

        	LogCvt.error("������������[" + dt + "]�Լ���ʽ [" + format + "] �����������ʹ���", e);

            throw new Exception("�����������ʹ���", e);
        }

    }


    /**
     * <p>get the interval days between two date</p>
     * ������ʵ�ֻ�ȡ������ͬ��date��������֮��ļ��
     * <pre>
     * diffDate(dt1, dt2) = 2 // suppose dt1="030902" & dt2="030831"
     * </pre>
     *
     * @param dt1 the first data
     * @param dt2 the second data
     * @return the interval days between dt1 and dt2
     */
    public static int diffDate(Date dt1, Date dt2) throws Exception {
        try {
            long date1 = dt1.getTime();
            long date2 = dt2.getTime();
            if (date1 > date2)
                return (int) ((date1 - date2) / (24 * 3600 * 1000.0) + 0.5);

            return (int) ((date2 - date1) / (24 * 3600 * 1000.0) + 0.5);
        } catch (Exception e) {

        	LogCvt.error("������������ [" + dt1 + "]�Լ����� [" + dt2 + "] �������ڼ������");
            throw new Exception("�������ڼ������,[ " + "�������������ڸ�ʽ��ƥ�� ]", e);
        }
    }

    /**
     * <p>get the interval days between two date described by formater "yy-MM-dd"</p>
     * ������ʵ�ֻ�ȡ������ͬ��String���͵����ڼ����ֻ��ʵ�ֶԱ�׼���ڸ�ʽyyyyMMdd�ļ����ȡ
     * <pre>
     * diffDate("20030902", "20030904") = 2
     * </pre>
     *
     * @param dt1 the first data
     * @param dt2 the second data
     * @return the interval days between dt1 and dt2
     * @throws Exception
     */
    public static int diffDate(String dt1, String dt2) throws Exception {
        return diffDate(dt1, dt2, "yyyyMMdd");
    }


    /**
     * <p>get the interval days between two date described by desired formater</p>
     * ������ʵ�ֻ�ȡ��������֮��ļ�������ڸ�ʽ����format�����Ͷ���
     * <pre>
     * diffDate("03-09-02", "03-09-04", "yy-MM-dd") = 2
     * </pre>
     *
     * @param dt1    the first date
     * @param dt2    the second date
     * @param format the formater of the dt1 and dt2
     * @return the interval days between dt1 and dt2
     * @throws Exception
     */
    public static int diffDate(String dt1, String dt2, String format) throws Exception {
        return diffDate(getDateFromString(dt1, format), getDateFromString(dt2, format));
    }

    /**
     * <p>Adds the specified (signed) amount of day to the given date, based on the calendar's rules</p>
     * ������ʵ�ֶ����ڵ����ӣ�����Ĳ���ΪDate�͵ĺ�int�͵ı���
     * <pre>
     * addDaysToDate(dt1, 2) = dt // if dt1="03-09-02" then dt="03-09-04"
     * </pre>
     *
     * @param dt1  the base data
     * @param days days to be added
     * @return the new date
     */
    public static Date addDaysToDate(Date dt1, int days) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(dt1);
        cale.add(Calendar.DATE, days);
        return cale.getTime();
    }

    /**
     * <p>Adds the specified (signed) amount of day to the given date, based on the calendar's rules</p>
     * ������ʵ�����ڵ����ӣ�����Ĳ���Ϊһ����׼��������(yyyyMMdd)��String������һ��int�Ͳ���
     * <pre>
     * addDaysToDate("20030902", 2) = dt // dt="20030904"
     * </pre>
     *
     * @param dt1  the base data
     * @param days days to be added
     * @return the new date
     * @throws Exception
     */
    public static Date addDaysToDate(String dt1, int days) throws Exception {
        return addDaysToDate(dt1, "yyyyMMdd", days);
    }

    /**
     * <p>Adds the specified (signed) amount of day to the given date, based on the calendar's rules</p>
     * ������ʵ�ֶ����ڵ����ӣ��������ڸ�ʽҪ��format���������һ��
     * <pre>
     * addDaysToDate("03-09-02", "yy-MM-dd", 2) = dt // dt="03-09-04"
     * </pre>
     *
     * @param dt1    the base data
     * @param format the format of the dt1
     * @param days   days to be added
     * @return the new date
     * @throws Exception
     */
    public static Date addDaysToDate(String dt1, String format, int days) throws Exception {
        return addDaysToDate(getDateFromString(dt1, format), days);
    }

    /**
     * <p>get the system current date</p>
     * ������ʵ�ֻ�ȡһ����׼����yyyyMMdd
     * <pre>
     * getCurrentDate() = "20030908"
     * </pre>
     *
     * @return the current date
     * @throws Exception
     */
    public static String getCurrentDate() throws Exception {
        return getCurrentDateTime("yyyyMMdd");
    }

    /**
     * <p>get the system current date</p>
     * ������ʵ�ֻ�ȡһ����׼����yyyy-MM-dd
     * <pre>
     * getCurrentDate() = "20030908"
     * </pre>
     *
     * @return the current date
     * @throws Exception
     */
    public static String getCurrentDate1() throws Exception {
        return getCurrentDateTime("yyyy-MM-dd");
    }

    /**
     * <p>get the system current time</p>
     * ������ʵ�ֻ�ȡһ����׼��ʽ��ʱ��HHmmss
     * <pre>
     * getCurrentTime() = "121208"
     * </pre>
     *
     * @return the current time
     * @throws Exception
     */
    public static String getCurrentTime() throws Exception {
        return getCurrentDateTime("HHmmss");

    }
    /**
     * ��������ʱ����λminute
     * @param startDate
     * @param endDate
     * @param type
     * @return
     */
    public static long getDiffTime(String startDate,String endDate){
    	long minutes = 0;
        try  
        {  
        	SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date begin=dfs.parse(startDate);
            Date end = dfs.parse(endDate);
            long between=(end.getTime()-begin.getTime())/1000;//����1000��Ϊ��ת������
            minutes=between/60;
        }catch (Exception e){
        	LogCvt.error(e.getMessage(),e);
        }
        return minutes;
    }
    /**
     * <p>get the system current date and time</p>
     * ������ʵ�ֻ�ȡһ����׼��ʽ�����ں�ʱ��yyyyMMdd HHmmss(������ʱ���м��пո����)
     * <pre>
     * getCurrentTime() = "20030912 121208"
     * </pre>
     *
     * @return the current date and time
     * @throws Exception
     */
    public static String getCurrentDateTime() throws Exception {
        return getCurrentDateTime("yyyyMMdd HHmmss");
    }

    /**
     * <p>get the system current date and time</p>
     * <p/>
     * ˵��: format��ʽ���������¼��ָ�ʽ: yyyyMMdd,yyMMdd,yy-MM-dd,yy/MM/dd,yyyy-MM-dd,yyyy/MM/dd,
     * yyyyMMdd HHmmss���ڸ�ʽ,yyyyMMddHHmmss���ڸ�ʽ,HHmmssʱ���ʽ,HH:mm:ssʱ���ʽ
     * <p/>
     * <pre>
     * getCurrentTime("yy-MM-dd HH:mm:ss") = "03-09-12 12:12:08"
     * </pre>
     *
     * @return the current date and time
     * @throws Exception
     */
    public static String getCurrentDateTime(String format) throws Exception {
        return getStringFromDate(new Date(System.currentTimeMillis()), format);
    }

    public static Date getNow(){
        return  Calendar.getInstance().getTime();
    }
    
    public static int skipDateTime(Date d1, Date d2, int skipMinute)
    {
        if (d1 == null || d2 == null)
            return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d1);
        calendar.add(Calendar.MINUTE, skipMinute);

        return calendar.getTime().compareTo(d2);
    }
    
    public static Date skipDateByMinute(Date d , int skipMinute)
    {
        if (d == null  )
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.MINUTE, skipMinute);

        return  calendar.getTime();
    }
    
    /**
	 * �������
	 * 
	 * @param date
	 *            ԭ����
	 * @param days
	 *            ��Ҫ��ȥ������
	 * @param format
	 *            ���ڵĸ�ʽ
	 * @return ���ؼ�ȥ������ڣ���ʽ��ԭ���ڵĸ�ʽ��ͬ
	 * @throws Exception
	 */
    public static String subDateByDays(String date, int days, String format)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		long curDateTimes = sdf.parse(date).getTime();
		long daysTimes = Long.parseLong(String.valueOf(days)) * 24 * 3600 * 1000;
		long result = (curDateTimes - daysTimes);
		Date d = new Date();
		d.setTime(result);
		return sdf.format(d).toString();
	}
    
    /**
	 * ��ȡǰ���������
	 *
	 * @param count
	 * @param format
	 * @return
	 */
	public static String getPrefixDate(int count, String format) {
		Calendar cal = Calendar.getInstance();
		int day = 0 - count;
		cal.add(5, day);
		Date datNew = cal.getTime();
		SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(datNew);
	}
	
	/**
	 * У�������Ƿ���ĩ
	 * @Title: CheckWeekend
	 * @author: yangchao 2017��4��27��
	 * @modify: yangchao 2017��4��27��
	 * @param date
	 * @param format
	 * @return
	 * @throws Exception 
	 */
	public static boolean CheckIsWeekend(String date,String format) throws Exception{
		DateFormat format1 = new SimpleDateFormat(format);
		Date bdate = format1.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ��ȡ��������֮�������list
	 * @Title: getCoverDates
	 * @author: yangchao 2017��4��29��
	 * @modify: yangchao 2017��4��29��
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getCoverDates(String startDate, String endDate) {
		DateFormat FORMATTER = new SimpleDateFormat("yyyyMMdd");
		Calendar startDay = Calendar.getInstance();
		Calendar endDay = Calendar.getInstance();
		try {
			startDay.setTime(FORMATTER.parse(startDate));
			endDay.setTime(FORMATTER.parse(endDate));
		} catch (ParseException e) {
			LogCvt.error(e.getMessage(), e);
			return new ArrayList<String>();
		}
		return getCoverDates(startDay, endDay, true, true);
	}
	/***
	 * ��ȡ��������֮������ڼ���
	  * @Title: getCoverDates
	  * @Description: TODO
	  * @author: froad-duandewei 2017��8��8��
	  * @modify: froad-duandewei 2017��8��8��
	  * @param startDate
	  * @param endDate
	  * @return
	 */
	public static List<String> getDateList(String startDate, String endDate,boolean isIncludeStart, boolean isIncludeEnd) {
		DateFormat FORMATTER = new SimpleDateFormat("yyyyMMdd");
		Calendar startDay = Calendar.getInstance();
		Calendar endDay = Calendar.getInstance();
		try {
			startDay.setTime(FORMATTER.parse(startDate));
			endDay.setTime(FORMATTER.parse(endDate));
		} catch (ParseException e) {
			LogCvt.error(e.getMessage(), e);
			return new ArrayList<String>();
		}
		return getCoverDates(startDay, endDay, isIncludeStart, isIncludeEnd);
	}
	
	public static List<String> getCoverDates(Calendar startDate,
			Calendar endDate, boolean isIncludeStart, boolean isIncludeEnd) {
		DateFormat FORMATTER = new SimpleDateFormat("yyyyMMdd");
		List<String> coverDates = new ArrayList<String>();
		if (startDate.compareTo(endDate) == 0
				&& (isIncludeStart || isIncludeEnd)) {
			coverDates.add(FORMATTER.format(startDate.getTime()));
		} else if (startDate.compareTo(endDate) < 0) {
			Calendar currentPrintDay = startDate;
			if (isIncludeStart) {
				coverDates.add(FORMATTER.format(currentPrintDay.getTime()));
			}
			while (true) {
				currentPrintDay.add(Calendar.DATE, 1);
				if (currentPrintDay.compareTo(endDate) > 0
						|| (currentPrintDay.compareTo(endDate) == 0 && !isIncludeEnd)) {
					break;
				}
				coverDates.add(FORMATTER.format(currentPrintDay.getTime()));
			}
		}
		return coverDates;
	}
	
	/**
	 * ���ڱȽ�
	 * <li>date1С��date2�� -1  date1����date2�� 1 ��ȷ��� 0
	 * @Title: compareToDates
	 * @author: yangchao 2017��4��30��
	 * @modify: yangchao 2017��4��30��
	 * @param date1
	 * @param date2
	 * @return 
	 */
	public static int compareToDates(String date1, String date2) {
		DateFormat FORMATTER = new SimpleDateFormat("yyyyMMdd");
		Calendar startDay = Calendar.getInstance();
		Calendar endDay = Calendar.getInstance();
		try {
			startDay.setTime(FORMATTER.parse(date1));
			endDay.setTime(FORMATTER.parse(date2));
		} catch (ParseException e) {
			LogCvt.error(e.getMessage(), e);
			return -2;
		}
		return startDay.compareTo(endDay);
	}
	
	/**
	 * ��ȡ�����ַ�����ǰһ������
	 * @Title: getUpDate
	 * @author: yangchao 2017��5��3��
	 * @modify: yangchao 2017��5��3��
	 * @param cleardate
	 * @return
	 * @throws Exception
	 */
	public static String getUpDate(String cleardate) throws Exception {
		SimpleDateFormat da = new SimpleDateFormat("yyyyMMdd");
		Date date = da.parse(cleardate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return da.format(date);
	}

	/**
	 * ��ȡ�����ַ����ĺ�һ������
	 * @Title: getDownDate
	 * @author: yangchao 2017��5��3��
	 * @modify: yangchao 2017��5��3��
	 * @param settleDate
	 * @return
	 * @throws Exception
	 */
	public static String getDownDate(String settleDate) throws Exception {
		SimpleDateFormat da = new SimpleDateFormat("yyyyMMdd");
		Date date = da.parse(settleDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		date = calendar.getTime();
		return da.format(date);
	}
	/**
	 * ���ڸ�ʽת��
	 * @param date
	 			��Ҫת��������
	 * @param srcFormat
	 			Ϊԭ�������ڸ�ʽ
	 * @param format
	 			ת��������ڸ�ʽ
	 * @return
	 * @throws Exception
	 */
	public static String convertDateFormat(String date, String srcFormat,
			String format) throws Exception {
		SimpleDateFormat srcSdf = new SimpleDateFormat(srcFormat);
		// ��ԭ����ת���ɺ���
		long srcDateTimes = srcSdf.parse(date).getTime();
		Date d = new Date();
		d.setTime(srcDateTimes);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d).toString();
	}
	/***
 	 * ���ڸ�ʽת��+ʱ��׷��(�������ݵ���)
 	  * @Title: exportDatePare
 	  * @Description: TODO
 	  * @author: froad-duandewei 2017��9��16��
 	  * @modify: froad-duandewei 2017��9��16��
 	  * @param tranDate��yyyymmdd��
 	  * @return
 	 */
 	public static String exportBeginDatePare(String tranDate,String addTime){
 		String beginDate = "";
 		try {
 			beginDate = convertDateFormat(tranDate, "yyyyMMdd", "yyyy-MM-dd");
 			beginDate = beginDate + addTime;
 		} catch (Exception e) {
 			LogCvt.error("���ڸ�ʽת������",e);
 		}
 		return beginDate;
 	}
	
    public static void main(String[] args) throws ParseException {
    	/*Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸� 
    	int month = c.get(Calendar.MONTH); 
    	int date = c.get(Calendar.DATE); 
    	 System.out.println("ssssssssssssssss"+date);*/
//    	System.out.println(DateUtil.getPrefixDate(-1,"yyyyMMdd"));
	}
}

