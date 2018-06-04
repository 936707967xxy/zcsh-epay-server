/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
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
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ��������ȡ�����ļ�������
 */
public final class ConfigLoad {

	/**
	 * ��ȡ�����ļ���
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
					throw new ZcshBusinessException("û���ҵ������ļ���" + filename);
				}
				in = new FileInputStream(new File(Constants.CONFIG_URI + filename));

			}
		} catch (ZcshBusinessException e) {
			LogCvt.error("�޷���ȡ�����ļ�.", e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return in;
	}
}

