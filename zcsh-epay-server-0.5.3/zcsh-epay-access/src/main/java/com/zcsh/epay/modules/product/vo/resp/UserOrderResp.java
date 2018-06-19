package com.zcsh.epay.modules.product.vo.resp;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月15日 <br>
 * 描述：订单查询返回参数
 */
public class UserOrderResp {

	/**
	 * 收货人名称
	 */
	private String userName;
	/**
	 * 收货人电话
	 */
	private String userPhone;
	
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品图片地址
	 */
	private String productImg;
	/**
	 * 产品编号
	 */
	private String productId;
	/**
	 * 产品规格
	 */
	private String productSpecification;
	/**
	 * 成交价格
	 */
	private String realPrice;
	/**
	 * 商品原价
	 */
	private String originalPrice;
	/**
	 * 商品数量
	 */
	private String productNum;
	/**
	 * 是否有活动
		1：有
		0：无
	 */
	private String isActivity;
	/**
	 * 打折
	 */
	private String disCount;
	/**
	 * 月销量
	 */
	private String monthSale;
	/**
	 * 默认收货地址
	 */
	private String addressDufault;
	private String addressOne;
	private String addressTwo;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductSpecification() {
		return productSpecification;
	}
	public void setProductSpecification(String productSpecification) {
		this.productSpecification = productSpecification;
	}
	public String getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(String realPrice) {
		this.realPrice = realPrice;
	}
	public String getAddressDufault() {
		return addressDufault;
	}
	public void setAddressDufault(String addressDufault) {
		this.addressDufault = addressDufault;
	}
	public String getAddressOne() {
		return addressOne;
	}
	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}
	public String getAddressTwo() {
		return addressTwo;
	}
	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public String getIsActivity() {
		return isActivity;
	}
	public void setIsActivity(String isActivity) {
		this.isActivity = isActivity;
	}
	public String getDisCount() {
		return disCount;
	}
	public void setDisCount(String disCount) {
		this.disCount = disCount;
	}
	public String getMonthSale() {
		return monthSale;
	}
	public void setMonthSale(String monthSale) {
		this.monthSale = monthSale;
	}
}
