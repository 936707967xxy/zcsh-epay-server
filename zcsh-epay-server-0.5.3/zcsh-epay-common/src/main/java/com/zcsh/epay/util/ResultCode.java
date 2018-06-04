/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.util;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月4日 <br>
 * 描述：
 */
public enum ResultCode {
	success("0000","操作成功"),
	failed("9999","操作失败"), //描述的错误信息自定义
	//登录相关
	nologin("9900","未登录或会话超时，请重新登录"),
	nouser("9000","无此用户"),
	errortimesout("9100","已锁定，请30分钟后再试或重置密码后登录"),
	firstlogin("9200","首次登录,请先修改密码"),
	nopermission("9300","无请求权限"),
	badRequest("4000", "无法处理的请求"),
	resetSelf("9400","当前登录用户已重置，请重新登录"),
	userOnline("9500","当前用户已登录，是否强制登录？");
	private String code;
	
	private String msg;
	
	private ResultCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}

