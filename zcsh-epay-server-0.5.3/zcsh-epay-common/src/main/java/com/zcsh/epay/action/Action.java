package com.zcsh.epay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcsh.epay.message.ResBody;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
public interface Action {
	
	public ResBody perform(HttpServletRequest request, HttpServletResponse response);
}
