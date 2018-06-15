package com.zcsh.epay.modules.product.vo.resp;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月15日 <br>
 * 描述：订单查询返回参数
 */
public class UserOrderResp {

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
}
