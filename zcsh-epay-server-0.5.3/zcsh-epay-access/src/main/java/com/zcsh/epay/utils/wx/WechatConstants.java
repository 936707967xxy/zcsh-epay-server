/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.utils.wx;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月7日 <br>
 * 描述：常量
 */
public class WechatConstants {

	/*====================================================*/
	//微信支付常量参数
	/*====================================================*/
	/**
	 * 微信小程序appid
	 */
	public static final String APPID = "";
	/**
	 * 微信小程序密钥
	 */
	public static final String SECRET = "";
	/**
	 * 
	 */
	public static final String GRANT_TYPE = "authorization_code";
	/**
	 * 微信支付的商户id
	 */
    public static final String MCH_ID = "";
    /**
     * 微信支付的商户密钥
     */
    public static final String KEY = "";
    /**
     * 支付成功后的服务器回调url
     */
    public static final String NOTIFY_URL = "https://??/weixin/wxNotify";
    /**
     * 签名方式
     */
    public static final String SIGNTYPE = "MD5";
    /**
     * 交易类型
     */
    public static final String TRADETYPE = "JSAPI";
    /**
     * 微信统一下单接口地址
     */
    public static final String WX_PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
	 * 登录凭证校验
	 */
	public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";
    
    
}
