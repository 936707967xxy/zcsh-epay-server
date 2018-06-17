package com.zcsh.epay.modules.product.mapper;
import java.util.List;

import com.zcsh.epay.modules.product.vo.resp.ProductInfoResp;
import com.zcsh.epay.utils.vo.ProductTypeVo;

/**
 * 商城首页
 * @author Administrator
 */
public interface SystemHomeMapper {

	/**
	 * 查询商品全部分类
	 * @return
	 */
	List<ProductTypeVo> queryProductType();
	/**
	 * 查询热销产品和精选产品
	 * @param showType 
	 * @return  HOT_SALE:热销 SELECTED:精选
	 */
	List<ProductInfoResp>queryHomeProduct();
}
