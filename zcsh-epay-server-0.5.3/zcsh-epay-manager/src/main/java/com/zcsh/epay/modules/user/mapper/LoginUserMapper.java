/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.modules.user.mapper;

import java.util.List;

import com.zcsh.epay.modules.user.vo.req.UserInfoReq;
import com.zcsh.epay.util.Paging;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月5日 <br>
 * 描述：
 */
public interface LoginUserMapper {
	List<UserInfoReq> queryMerchantList(Paging page,UserInfoReq req);
}
