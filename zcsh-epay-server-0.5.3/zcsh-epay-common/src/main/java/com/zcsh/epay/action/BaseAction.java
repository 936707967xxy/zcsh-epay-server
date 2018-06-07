package com.zcsh.epay.action;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.zcsh.epay.exceptions.ZcshBusinessException;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.util.AuthCodeReq;
import com.zcsh.epay.util.Checker;
import com.zcsh.epay.util.RequestUtil;
import com.zcsh.epay.util.ResultCode;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月7日 <br>
 * 描述： Action的基础操作类
 *
 */
public class BaseAction {

	/**
     * 检验所有参数的封装方法
     * @param obj
     * @param msg
     * @throws BisException
     */
    protected void checkErrorParam(Object obj, String msg) throws ZcshBusinessException {
        if (Checker.isEmpty(obj)) {
            throw new ZcshBusinessException(ResultCode.notAllParameters.getCode(),msg + ResultCode.notAllParameters.getMsg());
        }
    }
    
    /**
     * @throws FroadBusinessException 
     * 
     */
    protected AuthCodeReq  getAuthCode(HttpServletRequest request) throws ZcshBusinessException  {
    	String authCode =  request.getHeader("authCode");
    	String channel =  request.getHeader("channel");
    	AuthCodeReq authCodeReq = null;
    	if (authCode!=null && channel!=null) {
    		if (authCode.equals("")) {
    			throw new ZcshBusinessException(ResultCode.notAllParameters.getCode(),"authCode" + ResultCode.notAllParameters.getMsg());
			}
    		if (channel.equals("")) {
    			throw new ZcshBusinessException(ResultCode.notAllParameters.getCode(),"channel" + ResultCode.notAllParameters.getMsg());
			}
			authCodeReq = new AuthCodeReq();
			authCodeReq.setAuthCode(authCode);
			authCodeReq.setChannel(channel);
		}else {
			authCodeReq = (AuthCodeReq) RequestUtil.copyParam(AuthCodeReq.class, request);
			if (authCodeReq==null) {
				throw new ZcshBusinessException(ResultCode.notAllParameters.getCode(),"authCode,channel" + ResultCode.notAllParameters.getMsg());
			}
		}
		LogCvt.info("请求头参数"+JSON.toJSONString(authCodeReq));
		return authCodeReq;
    }
}

