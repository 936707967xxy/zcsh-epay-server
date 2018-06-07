package com.zcsh.epay.modules.user.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import com.zcsh.epay.action.Action;
import com.zcsh.epay.action.BaseAction;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.user.service.LoginUserService;
import com.zcsh.epay.modules.user.vo.req.UserInfoReq;
import com.zcsh.epay.util.RequestUtil;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月5日 <br>
 * 描述：
 */
public class LoginUserAction extends BaseAction implements Action{

	private LoginUserService service=new LoginUserService();
	
	public ResBody perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		LogCvt.info("==============用户信息查询开始===============");
		long start=System.currentTimeMillis();
		try {
			UserInfoReq req = (UserInfoReq) RequestUtil.copyParam(UserInfoReq.class, request);
			LogCvt.info("请求参数："+JSON.toJSONString(req));
			return service.queryUserInfo(req);
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("查询异常："+e.getMessage(),e);
			return new ResBody(ResBody.ERROR_CODE, "查询异常");
		}finally{
			long end=System.currentTimeMillis();
			LogCvt.info("耗时："+(end-start)+"ms");
			LogCvt.info("==============用户信息查询结束===============");
		}
	}

}
