package com.zcsh.epay.modules.product.vo.resp;

import java.util.List;

import com.zcsh.epay.utils.vo.ProductTypeVo;

/**
 * @author Administrator
 *系统首页信息展示
 */
public class SystemHomeResp {

	/**
	 * 产品分类
	 */
	private List<ProductTypeVo>productTypeList;
	/**
	 * 精选产品
	 */
	private List<ProductInfoResp>selectProductList;
	/**
	 * 热销产品
	 */
	private List<ProductInfoResp>hostProductList;
	public List<ProductTypeVo> getProductTypeList() {
		return productTypeList;
	}
	public void setProductTypeList(List<ProductTypeVo> productTypeList) {
		this.productTypeList = productTypeList;
	}
	public List<ProductInfoResp> getSelectProductList() {
		return selectProductList;
	}
	public void setSelectProductList(List<ProductInfoResp> selectProductList) {
		this.selectProductList = selectProductList;
	}
	public List<ProductInfoResp> getHostProductList() {
		return hostProductList;
	}
	public void setHostProductList(List<ProductInfoResp> hostProductList) {
		this.hostProductList = hostProductList;
	}
}
