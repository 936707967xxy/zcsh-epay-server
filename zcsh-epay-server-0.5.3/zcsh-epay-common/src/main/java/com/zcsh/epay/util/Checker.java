package com.zcsh.epay.util;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import java.util.Map;
import com.zcsh.epay.exceptions.ZcshBusinessException;
import com.zcsh.epay.log.LogCvt;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月5日 <br>
 * 描述：
 */
public class Checker {
	// 对于Collection、Dictionary、Map，不深入迭代，判断有没有子元素。
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			obj=((String) obj).trim();
			return ((String) obj) == null || "".equals(obj) ? true : false;
		}
		if (obj instanceof Collection) {
			return ((Collection<?>) obj).isEmpty();
		}
		if (obj instanceof Dictionary) {
			return ((Dictionary<?, ?>) obj).isEmpty();
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).isEmpty();
		}

		return false;
	}

	// 数组里任何一个元素非空，返回false。
	public static boolean isEmpty(Object... array) {
		if (array == null || array.length == 0) {
			return true;
		}

		for (Object o : array) {
			if (isNotEmpty(o)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	// 数组里任何一个元素为空，返回false。
	public static boolean isNotEmpty(Object... array) {
		if (array == null || array.length == 0) {
			return false;
		}

		for (Object o : array) {
			if (isEmpty(o)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isNotEmpty(String... array) {
		if (array == null || array.length == 0) {
			return false;
		}

		for (String o : array) {
			if (isEmpty(o)) {
				return false;
			}
		}

		return true;
	}

	// 为空的默认值转换
	public static String emptyDefaultValue(String obj, String defaultValue) {
		if (isEmpty(obj)) {
			return defaultValue;
		} else {
			return obj;
		}
	}
	
	// 为空的默认值转换
	public static Double emptyDefaultValue(String obj, Double defaultValue) {
		if (isEmpty(obj)) {
			return defaultValue;
		} else {
			return Double.valueOf(obj);
		}
	}
	
	// 为空的默认值转换
	public static double emptyDefaultValue(Double obj, double defaultValue) {
		if (isEmpty(obj)) {
			return defaultValue;
		} else {
			return obj;
		}
	}
	
	// 为空的默认值转换
	public static String emptyDefaultValue(String obj) {
		if (isEmpty(obj)) {
			return null;
		} else {
			return obj;
		}
	}
	
	public static int emptyDefaultValue(Integer value) {
		if (isEmpty(value)) {
			return 0;
		} else {
			return value;
		}
	}
	
	public static Integer emptyDefaultValue(Integer value,Integer defaultValue) {
		if (isEmpty(value)) {
			return defaultValue;
		} else {
			return value;
		}
	}

	public static long emptyDefaultValue(Long value) {
		if (isEmpty(value)) {
			return 0L;
		} else {
			return value;
		}
	}

	public static Long emptyDefaultValue(Long value, Long defaultValue) {
		if (isEmpty(value)) {
			return defaultValue;
		} else {
			return value;
		}
	}

	public static Integer emptyDefaultValue(String obj, Integer defaultValue) {
		if (isEmpty(obj)) {
			return defaultValue;
		} else {
			return Integer.valueOf(obj);
		}
	}

	public static long emptyDefaultValue(String obj, Long defaultValue) {
		if (isEmpty(obj)) {
			return 0L;
		} else {
			return Long.valueOf(obj);
		}
	}

	public static long emptyDefaultValue(Date date) {
		if (isEmpty(date)) {
			return 0L;
		} else {
			return date.getTime();
		}
	}

	public static boolean emptyDefaultValue(Boolean bool) {
		if (isEmpty(bool)) {
			return false;
		} else {
			return bool;
		}
	}
	
	public static double emptyDefaultValue(BigDecimal froadCutMoney,double defaultValue) {
		if (isEmpty(froadCutMoney)) {
			return defaultValue;
		} else {
			return froadCutMoney.doubleValue();
		}
	}
	
	public static long emptyDefaultValue(BigDecimal value,long defaultValue) {
		if (isEmpty(value)) {
			return defaultValue;
		} else {
			return value.longValue();
		}
	}

	public static boolean isUsername(String userName) {
		// return userName.matches("^[a-zA-Z]{1}[a-zA-Z0-9|-|_]{3,19}$");
		return userName.matches("^[a-zA-Z0-9]{4,16}$");
	}

	public static boolean isPayPassword(String password) {
		// ^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$
		// return
		// password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,16})");
		return password.matches("^[a-zA-Z0-9_]{6,16}$");
	}

	public static boolean isLoginPassword(String password) {
		return password.matches("^[a-zA-Z0-9~!@#$%^&*():\";'<>?,./]{6,16}$");
	}

	public static boolean isEmail(String email) {
		return email
				.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}
	
	//将json字符串拆解：去掉“{” 、 “}”
	public static String replaceJsonValue(String obj) {
		if (isEmpty(obj)) {
			return null;
		} else {
			if(obj.contains("{")){
				obj = obj.replaceAll("{", "");
			}
			if(obj.contains("}")){
				obj = obj.replaceAll("}", "");
			}
			return obj;
		}
	}
	
	/**
	 * 检查字段是否为空，为空时抛出异常信息
	 * @param field 字段
	 * @param errorMsg 错误信息
	 * @throws ZcshBusinessException 自定义业务异常信息
	 */
	public static void dataChecker(Object field, ResultCode errorCode) throws ZcshBusinessException {
		//1.请求数据校验
		if(Checker.isEmpty(field)){
			LogCvt.info(errorCode.getMsg());
			throw new ZcshBusinessException(errorCode.getCode(), errorCode.getMsg());
		}
	}
	
	/**
	 * 检查字段是否不为空，不为空时抛出异常信息
	 * @param field 字段
	 * @param errorMsg 错误信息
	 * @throws ZcshBusinessException 自定义业务异常信息
	 */
	public static void dataNotEmptyChecker(Object field, ResultCode errorCode) throws ZcshBusinessException {
		//1.请求数据校验
		if(Checker.isNotEmpty(field)){
			LogCvt.info(errorCode.getMsg());
			throw new ZcshBusinessException(errorCode.getCode(), errorCode.getMsg());
		}
	}
	
	/**
	 * 检查字段是否为空，为空时抛出异常信息
	 * @param field 字段
	 * @param errorMsg 错误信息
	 * @throws ZcshBusinessException 自定义业务异常信息
	 */
	public static void dataChecker(Object field, String errorMsg) throws ZcshBusinessException {
		//1.请求数据校验
		if(Checker.isEmpty(field)){
			LogCvt.info(errorMsg);
			throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
		}
	}
	
	/**
	 * 检查字段<=0时，抛出异常信息
	 * @param field 字段
	 * @param errorMsg 错误信息
	 * @throws ZcshBusinessException 自定义业务异常信息
	 */
	public static void dataLteZero(Integer field, String errorMsg) throws ZcshBusinessException {
		//1.请求数据校验
		if(Checker.isEmpty(field) || field <= 0){
			LogCvt.info(errorMsg);
			throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
		}
	}
	
	/**
	 * 检查字段<=0时，抛出异常信息
	 * @param field 字段
	 * @param errorMsg 错误信息
	 * @throws ZcshBusinessException 自定义业务异常信息
	 */
	public static void dataLteZero(Double field, String errorMsg) throws ZcshBusinessException {
		//1.请求数据校验
		if(Checker.isEmpty(field) || field <= 0){
			LogCvt.info(errorMsg);
			throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
		}
	}
	
	/**
	 * 检查字段<=0时，抛出异常信息
	 * @param field 字段
	 * @param errorMsg 错误信息
	 * @throws ZcshBusinessException 自定义业务异常信息
	 */
	public static void dataLteZero(Long field, String errorMsg) throws ZcshBusinessException {
		//1.请求数据校验
		if(Checker.isEmpty(field) || field <= 0){
			LogCvt.info(errorMsg);
			throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
		}
	}
	
	/**
	 * 检查字段<0时，抛出异常信息
	 * @param field 字段
	 * @param errorMsg 错误信息
	 * @throws ZcshBusinessException 自定义业务异常信息
	 */
	public static void dataLtZero(Long field, String errorMsg) throws ZcshBusinessException {
		//1.请求数据校验
		if(Checker.isEmpty(field) || field < 0){
			LogCvt.info(errorMsg);
			throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
		}
	}
	
	/**
	 * 检查字段<0时，抛出异常信息
	 * @param field 字段
	 * @param errorMsg 错误信息
	 * @throws ZcshBusinessException 自定义业务异常信息
	 */
	public static void dataLtZero(Double field, String errorMsg) throws ZcshBusinessException {
		//1.请求数据校验
		if(Checker.isEmpty(field) || field < 0){
			LogCvt.info(errorMsg);
			throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
		}
	}
	
	/**
	 * 检查字段<0时，抛出异常信息
	 * @param field 字段
	 * @param errorMsg 错误信息
	 * @throws ZcshBusinessException 自定义业务异常信息
	 */
	public static void dataLtZero(Long field, ResultCode errorCode) throws ZcshBusinessException {
		//1.请求数据校验
		if(Checker.isEmpty(field) || field < 0){
			LogCvt.info(errorCode.getMsg());
			throw new ZcshBusinessException(errorCode.getCode(), errorCode.getMsg());
		}
	}
	
	/**
	  * @Description: 检查字段<0时，抛出异常信息
	  * @Author: open
	  * @Date: 2017年1月12日下午8:19:06
	  * @param field
	  * @param errorCode 错误码对象
	  * @param data 携带数据
	  * @param objs 替换数据
	  * @throws ZcshBusinessException
	  */
	public static void dataLtZero(Long field, ResultCode errorCode,Object[] replacement,Object data) throws ZcshBusinessException {
		//1.请求数据校验
		String msg = MessageFormat.format(errorCode.getMsg(),replacement);
		if(Checker.isEmpty(field) || field < 0){
			LogCvt.info(msg);
			throw new ZcshBusinessException(errorCode.getCode(),msg, data);
		}
	}
	
	/**
	 * 检查小数位数
	 * @param field 字段
	 * @param errorMsg 错误信息
	 * @throws ZcshBusinessException 自定义业务异常信息
	 */
	public static void dataPrecisionChecker(double number,int n,String errorMsg) throws ZcshBusinessException {
		//1.请求数据校验
		String s = number + "";
		if(s.contains(".")){
			int position = s.length() - (s.indexOf(".") + 1);
	        if(position > n){
	        	throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
	        }
		}
	}
	
}
