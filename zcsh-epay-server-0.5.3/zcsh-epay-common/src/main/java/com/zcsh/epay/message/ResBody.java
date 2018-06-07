package com.zcsh.epay.message;

import java.util.List;
import com.alibaba.fastjson.JSON;
import com.zcsh.epay.exceptions.ZcshBusinessException;
import com.zcsh.epay.util.Paging;
import com.zcsh.epay.util.ResultCode;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月4日 <br>
 * 描述：
 */
public class ResBody {
	final public static String	SUCCESS_CODE	= "0000";//成功
	final public static String	ERROR_CODE		= "9999";//失败
	String						code			= SUCCESS_CODE;
	String						message			= "成功";
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
     * 创建一个新的实例 RespBody.
     *
     * @param status 枚举对象
     * @param data 数据对象
     */
    public ResBody(ResultCode resultCode, Object data) {
    	this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
        this.data = data;
    }
    
    /**
     * 
     * 创建一个新的实例 RespBody.
     *
     * @param status 枚举对象
     * @param page 分页对象
     * @param data  数据对象
     */
    public ResBody(Paging page,Object data) {
    	this.code = ResultCode.success.getCode();
		this.message = ResultCode.success.getMsg();
        this.page = page;
        this.data = data;
    }
    
    
    /**
     * 错误返回
     * @Title: error
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param e
     * @param @return    参数
     * @return RespBody    返回类型
     * @throws
     */
    public static ResBody error(ResultCode resultCode) {
        return new ResBody(resultCode.getCode(),resultCode.getMsg());
    }
    
    
    /**
     * 错误返回
     * @Title: result
     * @Description: TODO(静态构造封装)
     * @param @param e  异常对象
     * @param @return    参数
     * @return RespBody    返回类型
     * @throws
     */
    public static ResBody error(ZcshBusinessException e) {
        return new ResBody(e.getCode(),e.getMsg());
    }
    
    /**
     * 成功返回
     * @Title: result
     * @Description: TODO(静态封装构造)
     * @param @param data 数据对象
     * @param @return    参数
     * @return RespBody    返回类型
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
     * 成功返回
     * @Title: result
     * @Description: TODO(静态封装构造)
     * @param @param page    分页对象
     * @param @param content  数据对象
     * @param @return    参数
     * @return RespBody    返回类型
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