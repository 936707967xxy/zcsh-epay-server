/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.utils;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月12日 <br>
 * 描述：用户上线文对象
 */
public class UserSession {

	public static final String KEY = "ZCSH_AUDIT_SYSTEM_LOGIN_SESSION_KEY";
	/**
	 * 用户唯一标识
	 */
	private String openid;
	/**
	 * 系统生成的用户状态
	 */
	private String sessionKey;
	/**
	 * 用户编号
	 */
	private String userId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户电话号码
	 */
	private String userPhone;
	/**
	 * 性别
	 */
	private String userSex;
	/**
	 * 最近登录时间
	 */
	private String recentLoginDate;
	/**
	 * 用户头像
	 */
	private String userImg;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getRecentLoginDate() {
		return recentLoginDate;
	}
	public void setRecentLoginDate(String recentLoginDate) {
		this.recentLoginDate = recentLoginDate;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
}