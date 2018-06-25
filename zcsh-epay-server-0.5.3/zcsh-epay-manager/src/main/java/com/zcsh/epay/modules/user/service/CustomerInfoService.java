package com.zcsh.epay.modules.user.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.zcsh.epay.db.OracleManager;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.user.mapper.CustomerInfoMapper;
import com.zcsh.epay.modules.user.vo.req.CustomerInfoReq;
import com.zcsh.epay.modules.user.vo.resp.CustomerInfoResp;
import com.zcsh.epay.util.Paging;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月25日 <br>
 * 描述：会员信息查询
 */
public class CustomerInfoService {

	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年6月25日 <br>
	 * 描述： 会员信息查询
	 * @return
	 */
	public ResBody queryCustomerInfo(CustomerInfoReq req){
		SqlSession session = OracleManager.getSession().openSession();
		List<CustomerInfoResp>list=null;
		ResBody res=null;
		try {
			Paging page = new Paging();
			if(req.getPageNumber()!=0){
				page.setPageNumber(req.getPageNumber());
			}
			if(req.getPageSize()!=0){
				page.setPageSize(req.getPageSize());
			}
			CustomerInfoMapper moreMapper = session.getMapper(CustomerInfoMapper.class);
			list=moreMapper.queryCustomerInfo(req);
			res = new ResBody(ResBody.SUCCESS_CODE, "查询成功");
			res.setData(list);
			res.setPage(page);
			//session.commit(true);
		} catch (Exception e) {
			// TODO: handle exception
			//session.rollback(true);
			res = new ResBody(ResBody.ERROR_CODE, "查询失败");
			LogCvt.error("信息查询异常：" + e.getMessage(),e);
		}finally {
			if(null != session)  
				session.close(); 
		}
		return res;
	}
}
