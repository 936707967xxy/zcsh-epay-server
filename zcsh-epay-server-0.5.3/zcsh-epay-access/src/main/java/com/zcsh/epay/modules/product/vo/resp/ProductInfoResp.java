package com.zcsh.epay.modules.product.vo.resp;
/**
 * @author Administrator
 *商品信息
 */
public class ProductInfoResp {

	/**
	 * 商品编号
	 */
	private String productId;
	/**
	 * 商品图片地址（默认）
	 */
	private String productImg;
	/**
	 * 展示类型
	 */
	private String showType;
	/**
	 * 商品名称
	 */
	private String productName;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
}
