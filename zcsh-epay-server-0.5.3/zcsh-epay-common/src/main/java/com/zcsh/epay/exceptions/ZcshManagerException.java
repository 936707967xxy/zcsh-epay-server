/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.exceptions;

import com.zcsh.epay.util.ResultCode;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月11日 <br>
 * 描述：管理平台公共异常类
 */
public class ZcshManagerException extends Exception{

private static final long serialVersionUID = 1L;
	
	private String            code;
    private String            msg;
    
	private Object data;

    public ZcshManagerException() {
        super();
    }

    public ZcshManagerException(Throwable e) {
        super(e);
    }

    public ZcshManagerException(String errorMsg) {
    	super(errorMsg);
        this.code = ResultCode.failed.getCode();
        this.msg = errorMsg;
    }
    
    
    public ZcshManagerException(String code, Throwable e) {
        super(e);
        this.code = code;
    }

    public ZcshManagerException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.msg = resultCode.getMsg();
        this.code = resultCode.getCode();
    }
    
    
    public ZcshManagerException(String code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
    
    public ZcshManagerException(String code,String msg,Object data) {
        super(msg);
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public ZcshManagerException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
        this.msg = message;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
