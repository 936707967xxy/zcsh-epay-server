package com.zcsh.epay.modules.product.vo.req;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月19日 <br>
 * 描述：支付页面信息查询请求参数
 */
public class OrderPayInfoReq {

	/**
	 * 是否自提
		Y:是
		N：不是
	 */
	private String isSelf;
	/**
	 * 待支付金额
	 */
	private String waitPayAmt;
	/**
	 * 订单号
	 */
	private String orderNo;
	public String getIsSelf() {
		return isSelf;
	}
	public void setIsSelf(String isSelf) {
		this.isSelf = isSelf;
	}
	public String getWaitPayAmt() {
		return waitPayAmt;
	}
	public void setWaitPayAmt(String waitPayAmt) {
		this.waitPayAmt = waitPayAmt;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
