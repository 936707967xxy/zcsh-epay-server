package com.zcsh.epay.modules.user.source.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zcsh.epay.db.OracleManager;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.user.source.mapper.UserDetailsInfoMapper;
import com.zcsh.epay.modules.user.source.vo.req.UserDetailsInfoReq;
import com.zcsh.epay.modules.user.source.vo.resp.UserDetailsInfoResp;
import com.zcsh.epay.modules.user.source.vo.resp.UserDetailsInfoResp.DistributionCustomer;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月15日 <br>
 * 描述：用户详细信息查询
 */
public class UserDetailsInfoService {

	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年6月15日 <br>
	 * 描述： 用户详细信息查询
	 * @param req
	 * @return
	 */
	public ResBody queryUserDetailsInfo(UserDetailsInfoReq req){
		ResBody res=null;
		try {
			SqlSession session = OracleManager.getSession().openSession();
			UserDetailsInfoMapper mapper=session.getMapper(UserDetailsInfoMapper.class);
			List<DistributionCustomer>disList=mapper.queryDistributionInfo(req);
			UserDetailsInfoResp resp=mapper.queryUserDetailsInfo(req);
			resp.setDistributionList(disList);
			
			res=new ResBody(ResBody.SUCCESS_CODE,"查询成功！");
			res.setData(resp);
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("数据库查询异常！");
			res=new ResBody(ResBody.ERROR_CODE,"数据库查询异常！");
		}
		return res;
	}
}
