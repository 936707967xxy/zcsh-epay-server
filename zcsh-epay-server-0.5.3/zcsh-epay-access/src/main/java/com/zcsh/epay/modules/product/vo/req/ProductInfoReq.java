package com.zcsh.epay.modules.product.vo.req;
/**
 * @author Administrator
 * 产品信息请求参数
 */
public class ProductInfoReq {

	/**
	 * 商品编号
	 */
	private String productId;
	/**
	 * 展示类型
	 */
	private String showType;
	/**
	 * 产品分类编号
	 */
	private String productTypeId;
	/**
	 * 产品状态
	 */
	private String status;
	
    private Integer pageNumber;
	
	private Integer pageSize;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
