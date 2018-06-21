package com.zcsh.epay.modules.product.vo.resp;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月21日 <br>
 * 描述：系统首页返回参数
 */
public class SystemHomeResp {

	/**
	 * 日交易额
	 */
	private String dayTradingAmount;
	/**
	 * 周交易额
	 */
	private String weekTradingAmount;
	/**
	 * 月交易额
	 */
	private String monthTradingAmount;
	/**
	 * 系统公告
	 */
	private String SystemNotice;
	
	
	public String getDayTradingAmount() {
		return dayTradingAmount;
	}
	public void setDayTradingAmount(String dayTradingAmount) {
		this.dayTradingAmount = dayTradingAmount;
	}
	public String getWeekTradingAmount() {
		return weekTradingAmount;
	}
	public void setWeekTradingAmount(String weekTradingAmount) {
		this.weekTradingAmount = weekTradingAmount;
	}
	public String getMonthTradingAmount() {
		return monthTradingAmount;
	}
	public void setMonthTradingAmount(String monthTradingAmount) {
		this.monthTradingAmount = monthTradingAmount;
	}
	public String getSystemNotice() {
		return SystemNotice;
	}
	public void setSystemNotice(String systemNotice) {
		SystemNotice = systemNotice;
	}
}
