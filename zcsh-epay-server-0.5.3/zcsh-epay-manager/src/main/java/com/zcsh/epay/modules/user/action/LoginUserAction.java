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
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��5�� <br>
 * ������
 */
public class LoginUserAction extends BaseAction implements Action{

	public ResBody perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserInfoReq req = (UserInfoReq) RequestUtil.copyParam(UserInfoReq.class, request);
		System.out.println(JSON.toJSONString(req));
		return null;
	}

}
