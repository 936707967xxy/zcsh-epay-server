/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.zcsh.epay.log.LogCvt;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月4日 <br>
 * 描述：Servlet动作工厂类-处理POST请求
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
				LogCvt.error("类装载报错："+e.getMessage(),e);
			}
		}

		return map;
	}
}
