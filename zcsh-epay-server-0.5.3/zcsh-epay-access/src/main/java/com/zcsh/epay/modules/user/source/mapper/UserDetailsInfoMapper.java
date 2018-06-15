package com.zcsh.epay.modules.user.source.mapper;

import java.util.List;

import com.zcsh.epay.modules.user.source.vo.req.UserDetailsInfoReq;
import com.zcsh.epay.modules.user.source.vo.resp.UserDetailsInfoResp;
import com.zcsh.epay.modules.user.source.vo.resp.UserDetailsInfoResp.DistributionCustomer;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月15日 <br>
 * 描述：用户详细信息查询
 */
public interface UserDetailsInfoMapper {

	public UserDetailsInfoResp queryUserDetailsInfo(UserDetailsInfoReq req);
	
	public List<DistributionCustomer>queryDistributionInfo(UserDetailsInfoReq req);
}
