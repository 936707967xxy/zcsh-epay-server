/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.modules.user.mapper;

import java.util.List;

import com.zcsh.epay.modules.user.vo.req.UserInfoReq;
import com.zcsh.epay.util.Paging;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��5�� <br>
 * ������
 */
public interface LoginUserMapper {
	List<UserInfoReq> queryMerchantList(Paging page,UserInfoReq req);
}
