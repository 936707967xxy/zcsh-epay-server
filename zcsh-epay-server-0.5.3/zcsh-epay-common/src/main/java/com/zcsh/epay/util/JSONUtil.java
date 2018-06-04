/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
/**
 * JSONUtil �ṩJson�Ͷ���֮���ת����
 *
 */
public class JSONUtil {

	/**
	 * �Ѷ���ת����Json�ַ�����
	 * 
	 * @param obj
	 *            ��Ҫת���Ķ���
	 * @return ת���õ��ַ��������������׳�RuntimeException
	 */
	public static String toJSonString(Object obj) {
		try {
			return JSON.toJSONString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��Json�ַ���ת���ɶ���
	 * 
	 * @param <T>
	 *            ��Ҫת���Ķ�������
	 * @param json
	 *            Json�ַ���
	 * @param clazz
	 *            ת������������
	 * @return ת���õĶ������������׳�RuntimeException
	 */
	public static <T> T toObject(String json, Class<T> clazz) {
		T t = null;
		try {
			t = JSON.parseObject(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return t;
	}
	/**
	 *  ��jsonstr ת����Map����
	  * @Title: toMap
	  * @Description: TODO
	  * @param object
	  * @return
	 */
	public static Map<String, Object> toMap(Object object){
		if(object == null){
			return null;
		}
		return toMap(toJSonString(object));
	}
	
	/**
	 * ��jsonstr ת����Map����
	* @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String json){
		return (Map<String, Object>) JSON.parse(json);
	}

}
