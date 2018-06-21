package com.zcsh.epay.modules.product.mapper;

import com.zcsh.epay.modules.product.vo.resp.SystemHomeResp;
import com.zcsh.epay.modules.user.vo.req.UserInfoReq;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月21日 <br>
 * 描述：产品信息
 */
public interface ProductInfoMapper {

	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年6月21日 <br>
	 * 描述： 系统首页信息统计
	 * @param req
	 * @return
	 */
	public SystemHomeResp queryStatisticsData(UserInfoReq req);
}
