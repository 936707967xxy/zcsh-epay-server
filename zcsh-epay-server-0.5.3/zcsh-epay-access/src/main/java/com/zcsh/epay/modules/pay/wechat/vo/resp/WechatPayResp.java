/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.modules.pay.wechat.vo.resp;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月13日 <br>
 * 描述：小程序返回给前端支付参数
 */
public class WechatPayResp {

	/**
	 * 随机字符串，长度为32个字符以下。
	 */
	private String nonceStr;
	/**
	 * 统一下单接口返回的 prepay_id 参数值，提交格式如：prepay_id=*
	 */
	private String prepay_id;
	/**
	 * 时间戳从1970年1月1日00:00:00至今的秒数,即当前的时间
	 */
	private String timeStamp;
	/**
	 * 签名
	 */
	private String paySign;
	/**
	 * 签名算法，暂支持 MD5
	 */
	private String signType;
	/**
	 * 小程序唯一标识
	 */
	private String appid;
	
	
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
}
