package com.zcsh.epay.modules.user.vo.req;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月5日 <br>
 * 描述：
 */
public class UserInfoReq {

	private String sessionManagerKey;
	
	
	private String userId;
	private String userName;
	private String loginName;
	private String loginPwd;
	
	
	public int  pageNumber;
	public int pageSize;

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

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getSessionManagerKey() {
		return sessionManagerKey;
	}

	public void setSessionManagerKey(String sessionManagerKey) {
		this.sessionManagerKey = sessionManagerKey;
	}
}
