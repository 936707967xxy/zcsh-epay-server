package com.zcsh.epay.modules.user.mapper;

import java.util.List;

import com.zcsh.epay.modules.user.vo.req.CustomerInfoReq;
import com.zcsh.epay.modules.user.vo.resp.CustomerInfoResp;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月25日 <br>
 * 描述：会员信息查询
 */
public interface CustomerInfoMapper {

	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年6月25日 <br>
	 * 描述：  会员信息查询
	 * @param req
	 * @return
	 */
	public List<CustomerInfoResp>queryCustomerInfo(CustomerInfoReq req);
}
