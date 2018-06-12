package com.zcsh.epay.modules.pay.login.service;
import net.sf.json.JSONObject;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.util.JSONUtil;
import com.zcsh.epay.util.http.HttpRequest;
import com.zcsh.epay.utils.wx.WechatConstants;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月11日 <br>
 * 描述：
 */
public class WechatGetSessionService {

	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年6月11日 <br>
	 * 描述： 登录凭证校验
	 * @return
	 */
	public ResBody getWechatSession(){
		ResBody res=null;
		StringBuffer sb =new StringBuffer();
		sb.append("appid=").append(WechatConstants.APPID)
		  .append("&secret=").append(WechatConstants.SECRET)
		  .append("&js_code=").append("")
		  .append("&grant_type=").append(WechatConstants.GRANT_TYPE);
		
		String resultMess=HttpRequest.sendGet(WechatConstants.WX_LOGIN, sb.toString());
		JSONObject ob=JSONObject.fromObject(resultMess);
		if(String.valueOf(ob.get("errcode")) != null){
			res = new ResBody(ResBody.ERROR_CODE,"登录凭证校验失败！");
			return res;
		}
		return null;
	}
	
	public static void main(String[] args) {
		WechatGetSessionService a=new WechatGetSessionService();
		ResBody res=a.getWechatSession();
		System.out.println(JSONUtil.toJSonString(res));
	}
}
