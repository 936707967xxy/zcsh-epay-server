/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.zcsh.epay.log.LogCvt;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������Servlet����������-����POST����
 */
public class PostActionFactory extends ActionFactory {

	@Override
	protected Map<String, Class<?>> defaultMap() {
		Iterator<Object> iterator = ActionFactory.prop.keySet().iterator();
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		while(iterator.hasNext()){
			String key = String.valueOf(iterator.next());
			String value = ActionFactory.prop.getProperty(key).trim();
			try {
				map.put(getActionName(key), Class.forName(value));
			} catch (ClassNotFoundException e) {
				LogCvt.error("��װ�ر���"+e.getMessage(),e);
			}
		}

		return map;
	}
}
