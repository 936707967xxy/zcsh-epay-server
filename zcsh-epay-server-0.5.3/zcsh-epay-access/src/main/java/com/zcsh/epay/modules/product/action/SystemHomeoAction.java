package com.zcsh.epay.modules.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zcsh.epay.action.Action;
import com.zcsh.epay.action.BaseAction;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.product.service.SystemHomeService;
import com.zcsh.epay.modules.product.vo.req.SystemHomeReq;
import com.zcsh.epay.util.RequestUtil;
import com.zcsh.epay.util.ResultCode;
import com.zcsh.epay.utils.SessionUtils;
import com.zcsh.epay.utils.UserSession;
/**
 * @author Administrator
 *商城首页
 */
public class SystemHomeoAction extends BaseAction implements Action{
	
	private SystemHomeService service=new SystemHomeService();

	public ResBody perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		long start=System.currentTimeMillis();
		try {
			LogCvt.info("==============商品首页查询开始===============");
			SystemHomeReq req = (SystemHomeReq) RequestUtil.copyParam(SystemHomeReq.class, request);
			UserSession session=SessionUtils.getLoginSession(request);
			if(session != null){
				if(req == null){
					req = new SystemHomeReq();
				}
				return service.querySystemHomeInfo(req);
			}else{
				return new ResBody(ResultCode.nologin.getCode(),ResultCode.nologin.getMsg());
			}
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("查询异常");
			return new ResBody(ResBody.ERROR_CODE,"查询异常");
		}finally{
			long end=System.currentTimeMillis();
			LogCvt.info("耗时："+(end-start)+"ms");
			LogCvt.info("==============商品首页查询开始结束===============");
		}
	}
}
