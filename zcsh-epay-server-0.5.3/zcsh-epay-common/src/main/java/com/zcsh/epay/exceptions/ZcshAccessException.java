/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.exceptions;

import com.zcsh.epay.util.ResultCode;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月11日 <br>
 * 描述：
 */
public class ZcshAccessException extends Exception{

	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年6月11日 <br>
	 * 描述：微信小程序公共异常类
	 */
	private static final long serialVersionUID = 1L;
	
	private String            code;
    private String            msg;
    
    /**
	 * 可能携带的业务参数
	 */
	private Object data;

    public ZcshAccessException() {
        super();
    }

    public ZcshAccessException(Throwable e) {
        super(e);
    }

    public ZcshAccessException(String errorMsg) {
    	super(errorMsg);
        this.code = ResultCode.failed.getCode();
        this.msg = errorMsg;
    }
    
    
    public ZcshAccessException(String code, Throwable e) {
        super(e);
        this.code = code;
    }

    public ZcshAccessException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.msg = resultCode.getMsg();
        this.code = resultCode.getCode();
    }
    
    
    public ZcshAccessException(String code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
    
    public ZcshAccessException(String code,String msg,Object data) {
        super(msg);
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public ZcshAccessException(String code, String message, Throwable e) {
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
