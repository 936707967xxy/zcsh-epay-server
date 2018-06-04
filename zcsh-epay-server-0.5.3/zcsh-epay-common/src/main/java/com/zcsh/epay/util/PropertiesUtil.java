/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import com.zcsh.epay.action.ConfigLoad;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
public class PropertiesUtil {
	public static final String FILE_NAME = "init.properties";
	public static Properties prop = new Properties();
	static {
		InputStream in = ConfigLoad.loadAsStream(FILE_NAME);
		prop = new Properties();
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(in, "utf-8"));
			prop.load(bf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		if(StringUtils.isNotBlank(prop.getProperty(key))){
			return prop.getProperty(key);
		}
		return "";
	}
	public static void main(String[] args) {
		System.out.println(getValue("password"));
	}
}

