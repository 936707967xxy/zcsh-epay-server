/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.message;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.zcsh.epay.exceptions.ZcshBusinessException;
import com.zcsh.epay.util.Paging;
import com.zcsh.epay.util.ResultCode;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
public class ResBody {
	final public static String	SUCCESS_CODE	= "0000";//�ɹ�
	final public static String	ERROR_CODE		= "9999";//ʧ��
	String						code			= SUCCESS_CODE;
	String						message			= "�ɹ�";
	Paging						page;
	Object						data;
	String                      sopRespXml      ="";

	public ResBody() {
		// TODO Auto-generated constructor stub
	}

	public ResBody(String code, String message) {
		this.message = message;
		this.code = code;
	}

	public static ResBody createErrorResBody(String message) {
		return new ResBody(ERROR_CODE, message);
	}

	public static ResBody createErrorResBody(String message, Object data) {
		ResBody res = new ResBody(ERROR_CODE, message);
		res.setData(data);
		return res;
	}

	public ResBody(Object data) {
		this.code = ResultCode.success.getCode();
		this.message = ResultCode.success.getMsg();
		this.data = data;
	}

	public ResBody(List list, Paging paging) {
		this.data = list;
		this.page = paging;
	}
	
	
    
    
    /**
     *     
     * ����һ���µ�ʵ�� RespBody.
     *
     * @param status ö�ٶ���
     * @param data ���ݶ���
     */
    public ResBody(ResultCode resultCode, Object data) {
    	this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
        this.data = data;
    }
    
    /**
     * 
     * ����һ���µ�ʵ�� RespBody.
     *
     * @param status ö�ٶ���
     * @param page ��ҳ����
     * @param data  ���ݶ���
     */
    public ResBody(Paging page,Object data) {
    	this.code = ResultCode.success.getCode();
		this.message = ResultCode.success.getMsg();
        this.page = page;
        this.data = data;
    }
    
    
    /**
     * ���󷵻�
     * @Title: error
     * @Description: TODO(������һ�仰�����������������)
     * @param @param e
     * @param @return    ����
     * @return RespBody    ��������
     * @throws
     */
    public static ResBody error(ResultCode resultCode) {
        return new ResBody(resultCode.getCode(),resultCode.getMsg());
    }
    
    
    /**
     * ���󷵻�
     * @Title: result
     * @Description: TODO(��̬�����װ)
     * @param @param e  �쳣����
     * @param @return    ����
     * @return RespBody    ��������
     * @throws
     */
    public static ResBody error(ZcshBusinessException e) {
        return new ResBody(e.getCode(),e.getMsg());
    }
    
    /**
     * �ɹ�����
     * @Title: result
     * @Description: TODO(��̬��װ����)
     * @param @param data ���ݶ���
     * @param @return    ����
     * @return RespBody    ��������
     * @throws
     */
    public static ResBody result(Object data) {
        return new ResBody(data);
    }
    
    
    
    public String getSopRespXml() {
		return sopRespXml;
	}

	public void setSopRespXml(String sopRespXml) {
		this.sopRespXml = sopRespXml;
	}

	/**
     * �ɹ�����
     * @Title: result
     * @Description: TODO(��̬��װ����)
     * @param @param page    ��ҳ����
     * @param @param content  ���ݶ���
     * @param @return    ����
     * @return RespBody    ��������
     * @throws
     */
    public static ResBody result(Paging page, Object data) {
        return new ResBody(page, data);
    }
    
    
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Paging getPage() {
		return page;
	}

	public void setPage(Paging page) {
		this.page = page;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toJson() {
		return JSON.toJSONString(this);
	}
}

