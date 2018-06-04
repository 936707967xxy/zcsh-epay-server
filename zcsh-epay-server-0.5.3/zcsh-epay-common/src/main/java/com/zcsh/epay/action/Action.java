package com.zcsh.epay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcsh.epay.message.ResBody;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月4日 <br>
 * 描述：
 */
public interface Action {
	
	public ResBody perform(HttpServletRequest request, HttpServletResponse response);
}
