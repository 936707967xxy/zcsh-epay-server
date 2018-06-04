/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.exceptions;

import com.zcsh.epay.util.ResultCode;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
/**
 * �Զ���ҵ���쳣
 */
public class ZcshBusinessException extends Exception {

    private static final long serialVersionUID = 5730339086217985460L;

    private String            code;
    private String            msg;
    
    /**
	 * ����Я����ҵ�����
	 */
	private Object data;

    public ZcshBusinessException() {
        super();
    }

    public ZcshBusinessException(Throwable e) {
        super(e);
    }

    public ZcshBusinessException(String errorMsg) {
    	super(errorMsg);
        this.code = ResultCode.failed.getCode();
        this.msg = errorMsg;
    }
    
    
    public ZcshBusinessException(String code, Throwable e) {
        super(e);
        this.code = code;
    }

    public ZcshBusinessException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.msg = resultCode.getMsg();
        this.code = resultCode.getCode();
    }
    
    
    public ZcshBusinessException(String code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
    
    public ZcshBusinessException(String code,String msg,Object data) {
        super(msg);
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public ZcshBusinessException(String code, String message, Throwable e) {
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

