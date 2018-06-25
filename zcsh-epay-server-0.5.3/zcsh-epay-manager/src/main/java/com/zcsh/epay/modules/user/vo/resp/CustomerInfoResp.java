package com.zcsh.epay.modules.user.vo.resp;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月25日 <br>
 * 描述：会员响应参数信息
 */
public class CustomerInfoResp {

	private String userName;
	private String userId;
	private String userPhone;
	private String userLevel;
	private String userWechatId;
	private String userSex;
	private String amountAvalable;
	private String amountBounds;
	private String amountVoucher;
	private String createTime;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserWechatId() {
		return userWechatId;
	}
	public void setUserWechatId(String userWechatId) {
		this.userWechatId = userWechatId;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getAmountAvalable() {
		return amountAvalable;
	}
	public void setAmountAvalable(String amountAvalable) {
		this.amountAvalable = amountAvalable;
	}
	public String getAmountBounds() {
		return amountBounds;
	}
	public void setAmountBounds(String amountBounds) {
		this.amountBounds = amountBounds;
	}
	public String getAmountVoucher() {
		return amountVoucher;
	}
	public void setAmountVoucher(String amountVoucher) {
		this.amountVoucher = amountVoucher;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
