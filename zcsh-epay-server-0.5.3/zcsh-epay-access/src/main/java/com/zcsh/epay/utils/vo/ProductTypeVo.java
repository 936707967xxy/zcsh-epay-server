package com.zcsh.epay.utils.vo;
/**
 * 商品分类
 * @author Administrator
 */
public class ProductTypeVo {

	/**
	 * 产品分类图标地址
	 */
	private String productTypeImg;
	/**
	 * 产品分类名称
	 */
	private String productTypeName;
	/**
	 * 产品分类排序
	 */
	private String productTypeSort;
	/**
	 * 产品分类编号
	 */
	private String productTypeId;
	/**
	 * 分了级别
	 */
	private String typeLevel;
	
	public String getProductTypeImg() {
		return productTypeImg;
	}
	public void setProductTypeImg(String productTypeImg) {
		this.productTypeImg = productTypeImg;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getProductTypeSort() {
		return productTypeSort;
	}
	public void setProductTypeSort(String productTypeSort) {
		this.productTypeSort = productTypeSort;
	}
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getTypeLevel() {
		return typeLevel;
	}
	public void setTypeLevel(String typeLevel) {
		this.typeLevel = typeLevel;
	}
}
