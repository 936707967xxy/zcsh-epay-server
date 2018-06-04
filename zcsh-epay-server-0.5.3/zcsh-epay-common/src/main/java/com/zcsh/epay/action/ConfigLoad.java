/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.zcsh.epay.exceptions.ZcshBusinessException;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.util.Constants;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月4日 <br>
 * 描述：读取配置文件工具类
 */
public final class ConfigLoad {

	/**
	 * 读取配置文件流
	 * 
	 * @param filename
	 * @return
	 */
	public static InputStream loadAsStream(String filename) {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		try {
			if (null == in) {
				File file = new File(Constants.CONFIG_URI + filename);
				if (!file.exists()) {
					throw new ZcshBusinessException("没有找到配置文件：" + filename);
				}
				in = new FileInputStream(new File(Constants.CONFIG_URI + filename));

			}
		} catch (ZcshBusinessException e) {
			LogCvt.error("无法获取配置文件.", e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return in;
	}
}

