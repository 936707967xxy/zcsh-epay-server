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
     * <p>标准的日期格式yyyyMMdd</p>
     */
    public static final String standardFormat = "yyyyMMdd";

    /**
     * <p>8位日期格式yy-MM-dd</p>
     */
    public static final String byte8Format = "yy-MM-dd";

    /**
     * <p>8位日期格式yy/MM/dd</p>
     */
    public static final String anotherByte8Format = "yy/MM/dd";

    /**
     * <p>6位日期格式yyMMdd</p>
     */
    public static final String byte6Format = "yyMMdd";

    /**
     * <p>10位日期格式yyyy-MM-dd</p>
     */
    public static final String byte10Format = "yyyy-MM-dd";

    /**
     * <p>17位时间格式yyyyMMdd HH:mm:ss</p>
     */
    public static final String byte17Format = "yyyyMMdd HH:mm:ss";

    /**
     * <p>20位时间格式yyyy-MM-dd HH:mm:ss</p>
     */
    public static final String anotherByte20Format = "yyyy-MM-dd HH:mm:ss";

    /**
     * <p>20位时间格式yyyy-MM-dd HH:mm:ss</p>
     */
    public static final String anotherByte14Format = "yyyyMMddHHmmss";
    
    /**
     * <p>10位日期格式yyyy/MM/dd</p>
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
            //对于yy/MM/dd和yy-MM-dd格式
            if ("/".equals(strDate.substring(2, 3)) && "/".equals(strDate.substring(5, 6))) {
                //strDate = strDate.replace('/','-');
                return getDateFromString(strDate, "yy/MM/dd");
            } else if ("-".equals(strDate.substring(2, 3)) && "-".equals(strDate.substring(5, 6))) {
                return getDateFromString(strDate, "yy-MM-dd");
            } else {
                //yyMMdd类型的
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
     * 本方法实现在日期格式和日期类型相一致的情况下，返回该日期类型的date
     * 如果有异常，则为类型不匹配异常
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
                //如果strDate是060417，format是yyMMdd
                isByte6FormatVolidate = true;
            }
        } else if (length == 8) {
            if ("/".equals(strDate.substring(2, 3)) && "/".equals(strDate.substring(5, 6))) {
                //当format为yy/MM/dd情况时
                if ("yy/MM/dd".equals(format)) {
                    isByte8FormatVolidate = true;
                }

            } else if ("-".equals(strDate.substring(2, 3)) && "-".equals(strDate.substring(5, 6))) {
                //当format为yy-MM-dd情况时
                if ("yy-MM-dd".equals(format)) {
                    isByte8FormatVolidate = true;
                }
            } else if ("yyyyMMdd".equals(format)) {
                isByte8FormatVolidate = true;
            }
        } else if (length == 10) {
            if ("/".equals(strDate.substring(4, 5)) && "/".equals(strDate.substring(7, 8))) {
                //当format为yyyy/MM/dd情况时
                if ("yyyy/MM/dd".equals(format)) {
                    isByte10FormatVolidate = true;
                }
            } else if ("-".equals(strDate.substring(4, 5)) && "-".equals(strDate.substring(7, 8))) {
                //当format为yyyy-MM-dd情况时
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

            	LogCvt.error("根据输入日期[" + strDate + "]以及格式 [" + format + "] 返回日期类型错误", e);

                throw new Exception("返回日期类型错误", e);
            }
        } else {
            throw new Exception("日期类型与格式不匹配");
        }

    }
 	/**
	 * 格式化日期
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
     * 本方法实现从标准日期格式yyyyMMdd转换为format类型的格式
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
     * 本方法实现从一个日期格式date和其对应的日期类型format1，转换为另一种日期类型format2的日期表示
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
        //注意：此处对于format2的格式无法进行校验

    }


    /**
     * 本方法实现在日期格式和日期类型相一致的情况下，返回该日期类型的date
     * 如果有异常，则为标准日期格式yyyyMMdd转换为其他格式(yyMMdd,yy-MM-dd,yy/MM/dd,yyyy-MM-dd,yyyy/MM/dd)异常
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

            	LogCvt.error("根据输入日期[" + strDate + "]以及格式 [" + format + "] 返回日期类型错误", e);

                throw new Exception("返回日期类型错误", e);
            }
        } else {
            throw new Exception("日期类型不是标准格式yyyyMMdd");
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
     * 注意：由于format格式有多种(日期类型、日期类型 + 时间类型、时间类型或者可能有其他字符串)，因此本方法对format的格式并没有作出校验
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

        	LogCvt.error("根据输入日期[" + dt + "]以及格式 [" + format + "] 返回日期类型错误", e);

            throw new Exception("返回日期类型错误", e);
        }

    }


    /**
     * <p>get the interval days between two date</p>
     * 本方法实现获取两个不同的date类型日期之间的间隔
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

        	LogCvt.error("根据输入日期 [" + dt1 + "]以及日期 [" + dt2 + "] 返回日期间隔错误");
            throw new Exception("返回日期间隔错误,[ " + "可能是两种日期格式不匹配 ]", e);
        }
    }

    /**
     * <p>get the interval days between two date described by formater "yy-MM-dd"</p>
     * 本方法实现获取两个不同的String类型的日期间隔，只能实现对标准日期格式yyyyMMdd的间隔获取
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
     * 本方法实现获取两个日期之间的间隔，日期格式按照format的类型而定
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
     * 本方法实现对日期的增加，传入的参数为Date型的和int型的变量
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
     * 本方法实现日期的增加，传入的参数为一个标准日期类型(yyyyMMdd)的String参数和一个int型参数
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
     * 本方法实现对日期的增加，其中日期格式要与format定义的类型一致
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
     * 本方法实现获取一个标准日期yyyyMMdd
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
     * 本方法实现获取一个标准日期yyyy-MM-dd
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
     * 本方法实现获取一个标准格式的时间HHmmss
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
     * 计算两个时间差；单位minute
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
            long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
            minutes=between/60;
        }catch (Exception e){
        	LogCvt.error(e.getMessage(),e);
        }
        return minutes;
    }
    /**
     * <p>get the system current date and time</p>
     * 本方法实现获取一个标准格式的日期和时间yyyyMMdd HHmmss(日期与时间中间有空格隔开)
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
     * 说明: format格式可以有以下几种格式: yyyyMMdd,yyMMdd,yy-MM-dd,yy/MM/dd,yyyy-MM-dd,yyyy/MM/dd,
     * yyyyMMdd HHmmss日期格式,yyyyMMddHHmmss日期格式,HHmmss时间格式,HH:mm:ss时间格式
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
	 * 日期相减
	 * 
	 * @param date
	 *            原日期
	 * @param days
	 *            需要减去的天数
	 * @param format
	 *            日期的格式
	 * @return 返回减去后的日期，格式与原日期的格式相同
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
	 * 获取前几天的日期
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
	 * 校验日期是否周末
	 * @Title: CheckWeekend
	 * @author: yangchao 2017年4月27日
	 * @modify: yangchao 2017年4月27日
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
	 * 获取两个日期之间的日期list
	 * @Title: getCoverDates
	 * @author: yangchao 2017年4月29日
	 * @modify: yangchao 2017年4月29日
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
	 * 获取两个日期之间的日期集合
	  * @Title: getCoverDates
	  * @Description: TODO
	  * @author: froad-duandewei 2017年8月8日
	  * @modify: froad-duandewei 2017年8月8日
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
	 * 日期比较
	 * <li>date1小于date2则 -1  date1大于date2则 1 相等返回 0
	 * @Title: compareToDates
	 * @author: yangchao 2017年4月30日
	 * @modify: yangchao 2017年4月30日
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
	 * 获取日期字符串的前一天日期
	 * @Title: getUpDate
	 * @author: yangchao 2017年5月3日
	 * @modify: yangchao 2017年5月3日
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
	 * 获取日期字符串的后一天日期
	 * @Title: getDownDate
	 * @author: yangchao 2017年5月3日
	 * @modify: yangchao 2017年5月3日
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
	 * 日期格式转换
	 * @param date
	 			需要转换的日期
	 * @param srcFormat
	 			为原来的日期格式
	 * @param format
	 			转换后的日期格式
	 * @return
	 * @throws Exception
	 */
	public static String convertDateFormat(String date, String srcFormat,
			String format) throws Exception {
		SimpleDateFormat srcSdf = new SimpleDateFormat(srcFormat);
		// 将原日期转换成毫秒
		long srcDateTimes = srcSdf.parse(date).getTime();
		Date d = new Date();
		d.setTime(srcDateTimes);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d).toString();
	}
	/***
 	 * 日期格式转换+时间追加(用于数据导出)
 	  * @Title: exportDatePare
 	  * @Description: TODO
 	  * @author: froad-duandewei 2017年9月16日
 	  * @modify: froad-duandewei 2017年9月16日
 	  * @param tranDate（yyyymmdd）
 	  * @return
 	 */
 	public static String exportBeginDatePare(String tranDate,String addTime){
 		String beginDate = "";
 		try {
 			beginDate = convertDateFormat(tranDate, "yyyyMMdd", "yyyy-MM-dd");
 			beginDate = beginDate + addTime;
 		} catch (Exception e) {
 			LogCvt.error("日期格式转换错误",e);
 		}
 		return beginDate;
 	}
	
    public static void main(String[] args) throws ParseException {
    	/*Calendar c = Calendar.getInstance();//可以对每个时间域单独修改 
    	int month = c.get(Calendar.MONTH); 
    	int date = c.get(Calendar.DATE); 
    	 System.out.println("ssssssssssssssss"+date);*/
//    	System.out.println(DateUtil.getPrefixDate(-1,"yyyyMMdd"));
	}
}

