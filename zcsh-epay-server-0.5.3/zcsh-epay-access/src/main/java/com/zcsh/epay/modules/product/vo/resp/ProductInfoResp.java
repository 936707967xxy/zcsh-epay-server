package com.zcsh.epay.modules.product.vo.resp;
/**
 * @author Administrator
 * 商品信息
 */
public class ProductInfoResp {

	private String createTime;
	private String updateTime;
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
	/**
	 * 产品介绍
	 */
	private String productNode;
	/**
	 * 产品原价
	 */
	private String originalPrice;
	/**
	 * 产品进价
	 */
	private String productInPrice;
	/**
	 * 实际价格
	 */
	private String realPrice;
	/**
	 * 产品分类编号
	 */
	private String productTypeId;
	/**
	 * 是否有活动
	 */
	private String isActivity;
	/**
	 * 活动打折
	 */
	private String disCount;
	/**
	 * 产品图1
	 */
	private String productOneImg;
	/**
	 * 产品图2
	 */
	private String productTwoImg;
	/**
	 * 商家时间
	 */
	private String upperDate;
	/**
	 * 下架时间
	 */
	private String lowerDate;
	/**
	 * 入库时间
	 */
	private String warehouseDate;
	/**
	 * 产品状态
	 */
	private String status;
	/**
	 * 商品库存
	 */
	private String stockNum;
	/**
	 * 月销量
	 */
	private String monthSale;
	
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductNode() {
		return productNode;
	}
	public void setProductNode(String productNode) {
		this.productNode = productNode;
	}
	public String getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getProductInPrice() {
		return productInPrice;
	}
	public void setProductInPrice(String productInPrice) {
		this.productInPrice = productInPrice;
	}
	public String getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(String realPrice) {
		this.realPrice = realPrice;
	}
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
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
	public String getProductOneImg() {
		return productOneImg;
	}
	public void setProductOneImg(String productOneImg) {
		this.productOneImg = productOneImg;
	}
	public String getProductTwoImg() {
		return productTwoImg;
	}
	public void setProductTwoImg(String productTwoImg) {
		this.productTwoImg = productTwoImg;
	}
	public String getUpperDate() {
		return upperDate;
	}
	public void setUpperDate(String upperDate) {
		this.upperDate = upperDate;
	}
	public String getLowerDate() {
		return lowerDate;
	}
	public void setLowerDate(String lowerDate) {
		this.lowerDate = lowerDate;
	}
	public String getWarehouseDate() {
		return warehouseDate;
	}
	public void setWarehouseDate(String warehouseDate) {
		this.warehouseDate = warehouseDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStockNum() {
		return stockNum;
	}
	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}
	public String getMonthSale() {
		return monthSale;
	}
	public void setMonthSale(String monthSale) {
		this.monthSale = monthSale;
	}
}
