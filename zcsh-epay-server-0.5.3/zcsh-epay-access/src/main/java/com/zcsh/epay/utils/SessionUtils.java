/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.utils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月15日 <br>
 * 描述：
 */
public class SessionUtils {

	public static UserSession getLoginSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserSession se = (UserSession) session.getAttribute(UserSession.KEY);
		if (se != null) {
			return (UserSession) session.getAttribute(UserSession.KEY);
		}
		return null;
	}
}
