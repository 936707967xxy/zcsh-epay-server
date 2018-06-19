package com.zcsh.epay.modules.product.vo.resp;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月19日 <br>
 * 描述：支付页面信息查询响应参数
 */
public class OrderPayInfoResp {

	/**
	 * 待支付金额
	 */
	private String waitPayAmt;
	/**
	 * 可用余额
	 */
	private String amountAvailable;
	/**
	 * 可用奖金
	 */
	private String amountBonus;
	/**
	 * 可用代金卷
	 */
	private String amountVoucher;
	/**
	 * 代金卷总期次
	 */
	private String voucherTotalPeriod;
	public String getWaitPayAmt() {
		return waitPayAmt;
	}
	public void setWaitPayAmt(String waitPayAmt) {
		this.waitPayAmt = waitPayAmt;
	}
	public String getAmountAvailable() {
		return amountAvailable;
	}
	public void setAmountAvailable(String amountAvailable) {
		this.amountAvailable = amountAvailable;
	}
	public String getAmountBonus() {
		return amountBonus;
	}
	public void setAmountBonus(String amountBonus) {
		this.amountBonus = amountBonus;
	}
	public String getAmountVoucher() {
		return amountVoucher;
	}
	public void setAmountVoucher(String amountVoucher) {
		this.amountVoucher = amountVoucher;
	}
	public String getVoucherTotalPeriod() {
		return voucherTotalPeriod;
	}
	public void setVoucherTotalPeriod(String voucherTotalPeriod) {
		this.voucherTotalPeriod = voucherTotalPeriod;
	}
}
