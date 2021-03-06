package com.zcsh.epay.modules.product.mapper;
import java.util.List;

import com.zcsh.epay.modules.product.vo.req.OrderPayInfoReq;
import com.zcsh.epay.modules.product.vo.req.UserOrderReq;
import com.zcsh.epay.modules.product.vo.resp.OrderPayInfoResp;
import com.zcsh.epay.modules.product.vo.resp.UserOrderResp;
import com.zcsh.epay.util.Paging;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月15日 <br>
 * 描述：订单查询
 */
public interface UserOrderMapper {

	List<UserOrderResp> queryUserOrderInfo(Paging page,UserOrderReq req);
	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年6月19日 <br>
	 * 描述： 支付页面查询
	 * @param req
	 * @return
	 */
	OrderPayInfoResp queryOrderPayInfo(OrderPayInfoReq req);
	
}
