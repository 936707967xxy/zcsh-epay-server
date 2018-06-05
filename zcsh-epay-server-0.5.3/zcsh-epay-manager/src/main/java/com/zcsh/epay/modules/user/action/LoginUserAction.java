package com.zcsh.epay.modules.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.zcsh.epay.action.Action;
import com.zcsh.epay.action.BaseAction;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.user.vo.req.UserInfoReq;
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
		return null;
	}

}
