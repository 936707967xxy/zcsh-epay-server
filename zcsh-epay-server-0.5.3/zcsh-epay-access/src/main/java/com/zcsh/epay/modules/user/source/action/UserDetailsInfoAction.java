package com.zcsh.epay.modules.user.source.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcsh.epay.action.Action;
import com.zcsh.epay.action.BaseAction;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.user.source.service.UserDetailsInfoService;
import com.zcsh.epay.modules.user.source.vo.req.UserDetailsInfoReq;
import com.zcsh.epay.util.RequestUtil;
import com.zcsh.epay.util.ResultCode;
import com.zcsh.epay.utils.SessionUtils;
import com.zcsh.epay.utils.UserSession;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月14日 <br>
 * 描述：用户详细信息查询
 */
public class UserDetailsInfoAction extends BaseAction implements Action{

	private UserDetailsInfoService service=new UserDetailsInfoService();
	
	public ResBody perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ResBody res=null;
		long start=System.currentTimeMillis();
		try {
			LogCvt.info("==============个人信息查询开始===============");
			UserDetailsInfoReq req = (UserDetailsInfoReq) RequestUtil.copyParam(UserDetailsInfoReq.class, request);
			UserSession session=SessionUtils.getLoginSession(request);
			if(session!=null){
				if(req == null){
					req=new UserDetailsInfoReq();
				}
				return service.queryUserDetailsInfo(req);
			}else{
				return new ResBody(ResultCode.nologin.getCode(),ResultCode.nologin.getMsg());
			}
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("查询异常！");
			res=new ResBody(ResBody.ERROR_CODE,"查询异常！");
			return res;
		}finally{
			long end=System.currentTimeMillis();
			LogCvt.info("耗时："+(end-start)+"ms");
			LogCvt.info("==============个人信息查询结束===============");
		}
	}

}
