package com.zcsh.epay.modules.user.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zcsh.epay.action.Action;
import com.zcsh.epay.action.BaseAction;
import com.zcsh.epay.db.OracleManager;
import com.zcsh.epay.db.dialet.OracleDialect;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.user.mapper.LoginUserMapper;
import com.zcsh.epay.modules.user.vo.req.UserInfoReq;
import com.zcsh.epay.util.Paging;
import com.zcsh.epay.util.RequestUtil;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月5日 <br>
 * 描述：
 */
public class LoginUserAction extends BaseAction implements Action{

	public ResBody perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserInfoReq req = (UserInfoReq) RequestUtil.copyParam(UserInfoReq.class, request);
		System.out.println(JSON.toJSONString(req));
		SqlSession session = OracleManager.getSession().openSession();
		System.out.println(session);
		LoginUserMapper moreMapper = session.getMapper(LoginUserMapper.class);
		Paging page = new Paging();
		if(req.getPageNumber()!=0){
			page.setPageNumber(req.getPageNumber());
		}
		if(req.getPageSize()!=0){
			page.setPageSize(req.getPageSize());
		}
		List<UserInfoReq>list=moreMapper.queryMerchantList(page, req);
		System.out.println(JSONObject.toJSONString(list));
		return null;
	}

}
