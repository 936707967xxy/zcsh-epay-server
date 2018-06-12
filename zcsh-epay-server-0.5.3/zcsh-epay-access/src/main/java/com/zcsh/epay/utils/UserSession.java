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

	/**
	 * 用户唯一标识
	 */
	private String openid;
	/**
	 * 会话密钥
	 */
	private String session_key;
	/**
	 * 用户在开放平台的唯一标识符
	 */
	private String unionid;
	/**
	 * 会话token
	 */
	private String token;
	/**
	 * 系统生成的用户状态
	 */
	private String threeSessionId; 
	
	
}
