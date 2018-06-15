package com.zcsh.epay.modules.product.vo.req;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月15日 <br>
 * 描述：订单查询请求参数
 */
public class UserOrderReq {

	/**
	 * 查询类型
	 */
	private String queryType;
	
	private Integer pageNumber;
	
	private Integer pageSize;

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
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
