/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.modules.pay.wechat.vo.req;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月13日 <br>
 * 描述：微信小程序统一下单请求参数
 */
public class WechatPayReq {

	/**
	 * 用户唯一标识
	 */
	private String openid;
	/**
	 * 微信分配的小程序ID
	 */
	private String appid;
	/**
	 * 微信支付分配的商户号
	 */
	private String mch_id;
	/**
	 * 随机字符串，长度要求在32位以内
	 */
	private String nonce_str;
	/**
	 * 通过签名算法计算得出的签名值
	 */
	private String sign;
	/**
	 * 签名类型，默认为MD5，支持HMAC-SHA256和MD5
	 */
	private String sign_type;
	/**
	 * 商品简单描述
	 */
	private String body;
	/**
	 * 商品详情
	 */
	private String detail;
	/**
	 * 商户订单号
	 */
	private String out_trade_no;
	/**
	 * 默认人民币：CNY
	 */
	private String fee_type;
	/**
	 * 订单总金额，单位为分
	 */
	private String total_fee;
	/**
	 * 终端IP
	 */
	private String spbill_create_ip;
	/**
	 * 通知地址
	 */
	private String notify_url;
	/**
	 * 交易类型 JSAPI
	 */
	private String trade_type;
	/**
	 * 指定支付方式
	 * 上传此参数no_credit--可限制用户不能使用信用卡支付
	 */
	private String limit_pay;
	/**
	 * 商品ID
	 */
	private String product_id;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getLimit_pay() {
		return limit_pay;
	}
	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
}
