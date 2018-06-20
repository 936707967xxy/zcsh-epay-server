package com.zcsh.epay.modules.pay.wechat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zcsh.epay.action.Action;
import com.zcsh.epay.action.BaseAction;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.pay.wechat.service.WechatLoginService;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月20日 <br>
 * 描述：支付成功后的微信服务器回调
 */
public class WechatNotifyAction extends BaseAction implements Action{

	private WechatLoginService service=new WechatLoginService();
	
	public ResBody perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		long start=System.currentTimeMillis();
		try {
			LogCvt.info("==============微信服务回调开始===============");
				service.wechatNotify(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("回调异常");
			return new ResBody(ResBody.ERROR_CODE,"回调异常");
		}finally{
			long end=System.currentTimeMillis();
			LogCvt.info("耗时："+(end-start)+"ms");
			LogCvt.info("==============微信服务回调结束===============");
		}
		return null;
	}
}
