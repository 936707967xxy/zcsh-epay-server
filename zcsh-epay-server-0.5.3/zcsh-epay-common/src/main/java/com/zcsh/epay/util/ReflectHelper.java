/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.zcsh.epay.log.LogCvt;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
public final class ReflectHelper {
	
	private ReflectHelper() {}
	
	/**
	 * ��ȡ����ָ��������Ϣ
	* <p>Function: getField</p>
	* <p>Description: ȡ����ȫ�������ƣ������ǰ�಻���ڣ����ȡ����������Ϣ��δ�ҵ��򷵻�NULL</p>
	* @author zhaoxy@thankjava.com
	* @date 2014-12-16 ����11:27:07
	* @version 1.0
	* @param obj
	* @param fieldName
	* @return
	 */
	public static Field getField(Object obj , String fieldName){
		if(obj == null){
			return null;
		}
		Field field = null;
		for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				break;
			} catch (NoSuchFieldException e) {
				continue;//��һ�ν�����쳣����clazzΪobj  
						 //�ڶ��ν�����쳣����clazzΪobj����
						 //����������
			}
		}
		return field;
	}

	/**
	 * ��ȡָ��������������ԣ�������������
	* <p>Function: getFieldArrayExcludeUID</p>
	* <p>Description: ��ץȡserialVersionUID����</p>
	* @author zhaoxy@thankjava.com
	* @date 2014-12-16 ����11:27:44
	* @version 1.0
	* @param clazz
	* @return
	 */
	public static Field[] getFieldArrayExcludeUID(Class<?> clazz){
		Field[] currField = clazz.getDeclaredFields();
		clazz = clazz.getSuperclass();
		Field[] supField = clazz.getDeclaredFields();
		Field[] temp = new Field[currField.length + supField.length];
		int length = 0;
		for (Field curr : currField) {
			if("serialVersionUID".equals(curr.getName())){
				continue;
			}
			temp[length] = curr;
			length ++ ;
		}
		for (Field sup : supField) {
			if("serialVersionUID".equals(sup.getName())){
				continue;
			}
			temp[length] = sup;
			length ++ ;
		}
		Field[] all = new Field[length];
		for (int i = 0 ; i < all.length ; i ++) {
			all[i] = temp[i];
		}
		return all;
	}
	
	/**
	 * ��ȡָ�����ֶ�����ֵ
	* <p>Function: getFieldVal</p>
	* <p>Description: ȡ����ȫ�������ƣ������ǰ�಻���ڣ����ȡ��������ֵ  �����쳣�򷵻�NULL</p>
	* @author zhaoxy@thankjava.com
	* @date 2014-12-16 ����11:28:20
	* @version 1.0
	* @param obj
	* @param fieldName
	* @return
	 */
	public static Object getFieldVal(Object obj , String fieldName){
		Field field;
		if(obj == null){
			return null;
		}
		try {
			field = getField(obj,fieldName);
			field.setAccessible(true);//ȡ����ȫ��������
			return field.get(obj);
		} catch (SecurityException e) {
			LogCvt.error(e.getMessage(),e);
			return null;
		} catch (IllegalArgumentException e) {
			LogCvt.error(e.getMessage(),e);
			return null;
		} catch (IllegalAccessException e) {
			LogCvt.error(e.getMessage(),e);
			return null;
		}
	}
	
	/**
	 * Ϊ�ֶ�������������ֵ
	* <p>Function: setFieldVal</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2014-12-18 ����9:52:18
	* @version 1.0
	* @param obj
	* @param fieldName
	* @param value
	 */
	public static void setFieldVal(Object obj,String fieldName ,Object value){
		Field field;
		if(obj == null){
			return ;
		}
		try {
			field = getField(obj,fieldName);
			field.setAccessible(true);//ȡ����ȫ��������
			field.set(obj, value);
		} catch (SecurityException e) {
			LogCvt.error(e.getMessage(),e);
		} catch (IllegalArgumentException e) {
			LogCvt.error(e.getMessage(),e);
		} catch (IllegalAccessException e) {
			LogCvt.error(e.getMessage(),e);
		}
	}
	
	/**
	 * ��ȡָ������
	* <p>Function: getMethod</p>
	* <p>Description: </p>
	* @date 2014-12-18 ����9:55:54
	* @version 1.0
	* @param obj
	* @param methodName
	* @param parameterTypes
	* @return
	 */
	public static Method getMethod(Object obj,String methodName,Class<?>... parameterTypes){
		if(obj == null){
			return null;
		}
		Class<?> clazz = obj.getClass();
		try {
			return clazz.getMethod(methodName, parameterTypes);
		} catch (SecurityException e) {
			LogCvt.error(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			LogCvt.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * ִ��ָ������
	* <p>Function: invokeMethod</p>
	* <p>Description: </p>
	* @date 2014-12-18 ����1:50:06
	* @version 1.0
	* @param obj
	* @param method
	* @param parameter
	 */
	public static void invokeMethod(Object obj,Method method,Object... parameter){
		try {
			method.invoke(obj, parameter);
		} catch (IllegalArgumentException e) {
			LogCvt.error(e.getMessage(),e);
		} catch (IllegalAccessException e) {
			LogCvt.error(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			LogCvt.error(e.getMessage(),e);
		}
	}
	
	public static Class<?> getSignificantSupperClass(Class<?> classType){
		Class<?> supperClass = classType;
		while(classType != null){
			classType = classType.getSuperclass();
			if(Object.class == classType || null == classType){
				break;
			}
			supperClass = classType;
		}
		return supperClass;
	}
}


