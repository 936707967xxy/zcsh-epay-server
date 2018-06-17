package com.zcsh.epay.modules.product.mapper;

import java.util.List;
import com.zcsh.epay.modules.product.vo.req.ProductInfoReq;
import com.zcsh.epay.modules.product.vo.resp.ProductInfoResp;
import com.zcsh.epay.util.Paging;

/**
 * 产品列表信息
 * @author Administrator
 */
public interface ProductInfoMapper {

	/**
	 * 产品列表页面信息查询
	 * @param req
	 * @return
	 */
	List<ProductInfoResp>queryProductInfoList(Paging page,ProductInfoReq req);
	/**
	 * 商品详细信息查询
	 * @param req
	 * @return
	 */
	ProductInfoResp queryProductDetailsInfo(ProductInfoReq req);
	/**
	 * 购物车列表查询
	 * @param page
	 * @param req
	 * @return
	 */
	List<ProductInfoResp>queryProductCartList(Paging page,ProductInfoReq req);
}
