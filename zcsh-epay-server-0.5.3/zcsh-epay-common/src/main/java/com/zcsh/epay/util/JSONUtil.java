package com.zcsh.epay.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月4日 <br>
 * 描述：JSONUtil 提供Json和对象之间的转换。
 */
public class JSONUtil {

	/**
	 * 把对象转换成Json字符串。
	 * @param obj
	 *            需要转换的对象。
	 * @return 转换好的字符串。如果出错会抛出RuntimeException
	 */
	public static String toJSonString(Object obj) {
		try {
			return JSON.toJSONString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把Json字符串转换成对象
	 * 
	 * @param <T>
	 *            所要转换的对象类型
	 * @param json
	 *            Json字符串
	 * @param clazz
	 *            转换对象有类型
	 * @return 转换好的对象，如果出错会抛出RuntimeException
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
	 *  将jsonstr 转换成Map对象
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
	 * 将jsonstr 转换成Map对象
	* @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String json){
		return (Map<String, Object>) JSON.parse(json);
	}

}