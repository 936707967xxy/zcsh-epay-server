/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.zcsh.epay.util.EmptyChecker;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月4日 <br>
 * 描述：Servlet动作工厂类
 */
public class ActionFactory {

	protected Map<String, Class<?>> map = defaultMap();
	public static Properties prop = new Properties();
	public ActionFactory() {
		super();
	}

	public Action create(String actionName) throws InstantiationException, IllegalAccessException {
		Class klass = (Class) map.get(actionName);
		if (klass == null)
			throw new RuntimeException(getClass() + " was unable to find an action named '" + actionName + "'.");

		Action actionInstance = null;
		actionInstance = (Action) klass.newInstance();

		return actionInstance;
	}

	protected Map<String, Class<?>> defaultMap() {
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		return map;
	}
	
	protected String getActionName(String key) {
		if (EmptyChecker.isNotEmpty(key)) {
			return key.substring(key.lastIndexOf("/") + 1, key.length());
		}
		return null;
	}
}
