/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
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
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��5�� <br>
 * ������
 */
public class Checker {
	// ����Collection��Dictionary��Map��������������ж���û����Ԫ�ء�
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

	// �������κ�һ��Ԫ�طǿգ�����false��
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

	// �������κ�һ��Ԫ��Ϊ�գ�����false��
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

	// Ϊ�յ�Ĭ��ֵת��
	public static String emptyDefaultValue(String obj, String defaultValue) {
		if (isEmpty(obj)) {
			return defaultValue;
		} else {
			return obj;
		}
	}
	
	// Ϊ�յ�Ĭ��ֵת��
	public static Double emptyDefaultValue(String obj, Double defaultValue) {
		if (isEmpty(obj)) {
			return defaultValue;
		} else {
			return Double.valueOf(obj);
		}
	}
	
	// Ϊ�յ�Ĭ��ֵת��
	public static double emptyDefaultValue(Double obj, double defaultValue) {
		if (isEmpty(obj)) {
			return defaultValue;
		} else {
			return obj;
		}
	}
	
	// Ϊ�յ�Ĭ��ֵת��
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
	
	//��json�ַ�����⣺ȥ����{�� �� ��}��
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
	 * ����ֶ��Ƿ�Ϊ�գ�Ϊ��ʱ�׳��쳣��Ϣ
	 * @param field �ֶ�
	 * @param errorMsg ������Ϣ
	 * @throws ZcshBusinessException �Զ���ҵ���쳣��Ϣ
	 */
	public static void dataChecker(Object field, ResultCode errorCode) throws ZcshBusinessException {
		//1.��������У��
		if(Checker.isEmpty(field)){
			LogCvt.info(errorCode.getMsg());
			throw new ZcshBusinessException(errorCode.getCode(), errorCode.getMsg());
		}
	}
	
	/**
	 * ����ֶ��Ƿ�Ϊ�գ���Ϊ��ʱ�׳��쳣��Ϣ
	 * @param field �ֶ�
	 * @param errorMsg ������Ϣ
	 * @throws ZcshBusinessException �Զ���ҵ���쳣��Ϣ
	 */
	public static void dataNotEmptyChecker(Object field, ResultCode errorCode) throws ZcshBusinessException {
		//1.��������У��
		if(Checker.isNotEmpty(field)){
			LogCvt.info(errorCode.getMsg());
			throw new ZcshBusinessException(errorCode.getCode(), errorCode.getMsg());
		}
	}
	
	/**
	 * ����ֶ��Ƿ�Ϊ�գ�Ϊ��ʱ�׳��쳣��Ϣ
	 * @param field �ֶ�
	 * @param errorMsg ������Ϣ
	 * @throws ZcshBusinessException �Զ���ҵ���쳣��Ϣ
	 */
	public static void dataChecker(Object field, String errorMsg) throws ZcshBusinessException {
		//1.��������У��
		if(Checker.isEmpty(field)){
			LogCvt.info(errorMsg);
			throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
		}
	}
	
	/**
	 * ����ֶ�<=0ʱ���׳��쳣��Ϣ
	 * @param field �ֶ�
	 * @param errorMsg ������Ϣ
	 * @throws ZcshBusinessException �Զ���ҵ���쳣��Ϣ
	 */
	public static void dataLteZero(Integer field, String errorMsg) throws ZcshBusinessException {
		//1.��������У��
		if(Checker.isEmpty(field) || field <= 0){
			LogCvt.info(errorMsg);
			throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
		}
	}
	
	/**
	 * ����ֶ�<=0ʱ���׳��쳣��Ϣ
	 * @param field �ֶ�
	 * @param errorMsg ������Ϣ
	 * @throws ZcshBusinessException �Զ���ҵ���쳣��Ϣ
	 */
	public static void dataLteZero(Double field, String errorMsg) throws ZcshBusinessException {
		//1.��������У��
		if(Checker.isEmpty(field) || field <= 0){
			LogCvt.info(errorMsg);
			throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
		}
	}
	
	/**
	 * ����ֶ�<=0ʱ���׳��쳣��Ϣ
	 * @param field �ֶ�
	 * @param errorMsg ������Ϣ
	 * @throws ZcshBusinessException �Զ���ҵ���쳣��Ϣ
	 */
	public static void dataLteZero(Long field, String errorMsg) throws ZcshBusinessException {
		//1.��������У��
		if(Checker.isEmpty(field) || field <= 0){
			LogCvt.info(errorMsg);
			throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
		}
	}
	
	/**
	 * ����ֶ�<0ʱ���׳��쳣��Ϣ
	 * @param field �ֶ�
	 * @param errorMsg ������Ϣ
	 * @throws ZcshBusinessException �Զ���ҵ���쳣��Ϣ
	 */
	public static void dataLtZero(Long field, String errorMsg) throws ZcshBusinessException {
		//1.��������У��
		if(Checker.isEmpty(field) || field < 0){
			LogCvt.info(errorMsg);
			throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
		}
	}
	
	/**
	 * ����ֶ�<0ʱ���׳��쳣��Ϣ
	 * @param field �ֶ�
	 * @param errorMsg ������Ϣ
	 * @throws ZcshBusinessException �Զ���ҵ���쳣��Ϣ
	 */
	public static void dataLtZero(Double field, String errorMsg) throws ZcshBusinessException {
		//1.��������У��
		if(Checker.isEmpty(field) || field < 0){
			LogCvt.info(errorMsg);
			throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
		}
	}
	
	/**
	 * ����ֶ�<0ʱ���׳��쳣��Ϣ
	 * @param field �ֶ�
	 * @param errorMsg ������Ϣ
	 * @throws ZcshBusinessException �Զ���ҵ���쳣��Ϣ
	 */
	public static void dataLtZero(Long field, ResultCode errorCode) throws ZcshBusinessException {
		//1.��������У��
		if(Checker.isEmpty(field) || field < 0){
			LogCvt.info(errorCode.getMsg());
			throw new ZcshBusinessException(errorCode.getCode(), errorCode.getMsg());
		}
	}
	
	/**
	  * @Description: ����ֶ�<0ʱ���׳��쳣��Ϣ
	  * @Author: open
	  * @Date: 2017��1��12������8:19:06
	  * @param field
	  * @param errorCode ���������
	  * @param data Я������
	  * @param objs �滻����
	  * @throws ZcshBusinessException
	  */
	public static void dataLtZero(Long field, ResultCode errorCode,Object[] replacement,Object data) throws ZcshBusinessException {
		//1.��������У��
		String msg = MessageFormat.format(errorCode.getMsg(),replacement);
		if(Checker.isEmpty(field) || field < 0){
			LogCvt.info(msg);
			throw new ZcshBusinessException(errorCode.getCode(),msg, data);
		}
	}
	
	/**
	 * ���С��λ��
	 * @param field �ֶ�
	 * @param errorMsg ������Ϣ
	 * @throws ZcshBusinessException �Զ���ҵ���쳣��Ϣ
	 */
	public static void dataPrecisionChecker(double number,int n,String errorMsg) throws ZcshBusinessException {
		//1.��������У��
		String s = number + "";
		if(s.contains(".")){
			int position = s.length() - (s.indexOf(".") + 1);
	        if(position > n){
	        	throw new ZcshBusinessException(ResultCode.failed.getCode(), errorMsg);
	        }
		}
	}
	
}

