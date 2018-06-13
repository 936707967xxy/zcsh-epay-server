package com.zcsh.epay.modules.pay.login.service;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.pay.login.vo.req.WechatUserInfoReq;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月11日 <br>
 * 描述：用户登录
 */
public class WechatGetSessionService {

	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年6月11日 <br>
	 * 描述： 校验用户登录状态
	 * @request code
	 * @return
	 */
	public ResBody getWechatSession(WechatUserInfoReq req){
		ResBody res=null;
		try {
			/**
			 * 根据sessionKey查询库，如不存在，则发送第三方登录认证
			 */
			boolean bool=false;
			if(bool){
				//发起第三方认证
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			res = new ResBody(ResBody.ERROR_CODE,"登录异常！");
			return res;
		}
		return res;
	}
	
	
}
