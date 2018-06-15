package com.zcsh.epay.modules.user.source.vo.resp;

import java.util.List;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月15日 <br>
 * 描述：用户详细信息查询响应参数
 */
public class UserDetailsInfoResp {

	/**
	 * 用户微信名称
	 */
	private String userName;
	/**
	 * 用户微信头像
	 */
	private String userImg;
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
	 * 用户二维码地址
	 */
	private String userQrCode;
	/**
	 * 子分销客户
	 */
	private List<DistributionCustomer>distributionList;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
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
	public String getUserQrCode() {
		return userQrCode;
	}
	public void setUserQrCode(String userQrCode) {
		this.userQrCode = userQrCode;
	}
	public List<DistributionCustomer> getDistributionList() {
		return distributionList;
	}
	public void setDistributionList(List<DistributionCustomer> distributionList) {
		this.distributionList = distributionList;
	}


    public class DistributionCustomer{
	/**
	 * 子分销客户编号
	 */
	private String distributionUserId;
	/**
	 * 子分销客户名称
	 */
	private String distributionUserName;
	/**
	 * 子分销客户头像地址
	 */
	private String distributionUserImg;
	public String getDistributionUserId() {
		return distributionUserId;
	}
	public void setDistributionUserId(String distributionUserId) {
		this.distributionUserId = distributionUserId;
	}
	public String getDistributionUserName() {
		return distributionUserName;
	}
	public void setDistributionUserName(String distributionUserName) {
		this.distributionUserName = distributionUserName;
	}
	public String getDistributionUserImg() {
		return distributionUserImg;
	}
	public void setDistributionUserImg(String distributionUserImg) {
		this.distributionUserImg = distributionUserImg;
	}
  }
}
