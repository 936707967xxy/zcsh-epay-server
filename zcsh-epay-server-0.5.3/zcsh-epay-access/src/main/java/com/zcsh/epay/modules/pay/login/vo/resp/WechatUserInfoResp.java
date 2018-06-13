/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.modules.pay.login.vo.resp;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月13日 <br>
 * 描述：返回给前端的用户信息
 */
public class WechatUserInfoResp {

	/**
	 * 登录状态key
	 */
	private String sessionKey;
	/**
	 * 用户唯一标识
	 */
	private String openid;
	
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}
