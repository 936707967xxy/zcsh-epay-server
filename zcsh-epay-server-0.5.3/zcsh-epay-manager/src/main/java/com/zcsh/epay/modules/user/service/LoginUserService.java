/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.modules.user.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zcsh.epay.db.OracleManager;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.user.mapper.LoginUserMapper;
import com.zcsh.epay.modules.user.vo.req.UserInfoReq;
import com.zcsh.epay.util.Paging;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��5�� <br>
 * ������
 */
public class LoginUserService {

	public ResBody queryUserInfo(UserInfoReq req){
		SqlSession session = OracleManager.getSession().openSession();
		List<UserInfoReq>list=null;
		ResBody res=null;
		try {
			Paging page = new Paging();
			if(req.getPageNumber()!=0){
				page.setPageNumber(req.getPageNumber());
			}
			if(req.getPageSize()!=0){
				page.setPageSize(req.getPageSize());
			}
			LoginUserMapper moreMapper = session.getMapper(LoginUserMapper.class);
			list=moreMapper.queryMerchantList(page, req);
			res = new ResBody(ResBody.SUCCESS_CODE, "��ѯ�ɹ�");
			res.setData(list);
			res.setPage(page);
			//session.commit(true);
		} catch (Exception e) {
			// TODO: handle exception
			//session.rollback(true);
			res = new ResBody(ResBody.ERROR_CODE, "��ѯʧ��");
			LogCvt.error("��Ϣ��ѯ�쳣��" + e.getMessage(),e);
		}finally {
			if(null != session)  
				session.close(); 
		}
		return res;
	}
}
