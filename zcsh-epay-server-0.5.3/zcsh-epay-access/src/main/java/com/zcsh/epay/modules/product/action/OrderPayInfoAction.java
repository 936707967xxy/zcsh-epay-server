package com.zcsh.epay.modules.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcsh.epay.action.Action;
import com.zcsh.epay.action.BaseAction;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.product.service.UserOrderInfoService;
import com.zcsh.epay.modules.product.vo.req.OrderPayInfoReq;
import com.zcsh.epay.util.RequestUtil;
import com.zcsh.epay.util.ResultCode;
import com.zcsh.epay.utils.SessionUtils;
import com.zcsh.epay.utils.UserSession;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月19日 <br>
 * 描述：支付页面信息查询
 */
public class OrderPayInfoAction extends BaseAction implements Action{

	private UserOrderInfoService service=new UserOrderInfoService();
	
	public ResBody perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		long start=System.currentTimeMillis();
		try {
			LogCvt.info("==============支付页面查询开始===============");
			OrderPayInfoReq req = (OrderPayInfoReq) RequestUtil.copyParam(OrderPayInfoReq.class, request);
			UserSession session=SessionUtils.getLoginSession(request);
			if(session != null){
				if(req == null){
					req = new OrderPayInfoReq();
				}
				return service.queryOrderPayInfo(req);
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
			LogCvt.info("==============支付页面查询结束===============");
		}
	}
}
