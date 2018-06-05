/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
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
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��5�� <br>
 * ������
 */
public class BaseAction {

	/**
     * �������в����ķ�װ����
     * 
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
		LogCvt.info("����ͷ����"+JSON.toJSONString(authCodeReq));
		return authCodeReq;
    }
}

