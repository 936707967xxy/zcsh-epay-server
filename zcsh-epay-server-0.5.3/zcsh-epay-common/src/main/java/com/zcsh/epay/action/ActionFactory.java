/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.zcsh.epay.util.EmptyChecker;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������Servlet����������
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
