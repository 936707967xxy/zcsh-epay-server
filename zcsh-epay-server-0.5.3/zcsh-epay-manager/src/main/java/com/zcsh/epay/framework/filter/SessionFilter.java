/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.framework.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.util.DateUtil;
import com.zcsh.epay.util.JSONUtil;
import com.zcsh.epay.util.PropertiesUtil;
import com.zcsh.epay.util.ResponseUtil;
import com.zcsh.epay.util.ResultCode;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
public class SessionFilter implements Filter {

	private Pattern[] regPatterns = new Pattern[0];
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		LogCvt.debug("��������ʼ��......");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		LogCvt.debug("==============��������������===============");
		//FrMgSession session =  null;
		HttpServletRequest req = (HttpServletRequest) request;
		String href = req.getRequestURI().replaceAll(req.getContextPath(), "");
		LogCvt.debug("���ڼ����ų����Ƿ����URL["+href+"]...");
		//��exclude url�ڵ������ַ�����������ҪУ���Ƿ��¼
		try{
			
			if(this.checkExclude(href)){
				LogCvt.debug("����������ų��������URL����");
				filterChain.doFilter(request, response);
			}else{
				String token = "0";
				LogCvt.debug("���ڼ����������Ƿ����URL["+href+"]...");
				if(this.checkDownExclude(href)){
					LogCvt.debug("��������������������URL����ͨ��������ȡtoken,�������е�¼У��...");
					token=req.getParameter("token");
					LogCvt.debug("�Ựtoken:"+token);
				}else{
					LogCvt.debug("��������������������URL����ͨ��header��ȡtoken,�������е�¼У��...");
					if(null!=req.getHeader("token")){
						token=req.getHeader("token");
					}
					LogCvt.debug("�Ựtoken:"+token);
				}
				
			}
		}catch(Exception e){
			LogCvt.error("�����������쳣��" + e.getMessage(),e);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("code", ResultCode.badRequest.getCode());
			resultMap.put("message", ResultCode.badRequest.getMsg());
			ResponseUtil.returnJSONResponse((HttpServletResponse)response, resultMap);
			return;
		}finally{
			LogCvt.debug("==============�������������===============");
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		regPatterns = null;
	}
	
	/**
	 * �ж��ų������Ƿ������ǰ����url
	 * @param params
	 * @return true:���� false:������ 
	 */
	private boolean checkExclude(String url){
		String exclude =PropertiesUtil.getValue("exclude_filter_url");
		String[] filterUrl = exclude.split(";");
		if(null!=filterUrl){
			for(String fu : filterUrl){
				if(fu.equals(url)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * ���ڵ���������form���ύ��ʽ��token���Բ�������ʽ��ֵ
	 * ��˽��ж���У��url
	 * @param params
	 * @return true:���� false:������ 
	 */
	private boolean checkDownExclude(String url){
		String exclude =PropertiesUtil.getValue("exclude_filter_downurl");
		String[] filterUrl = exclude.split(";");
		if(null!=filterUrl){
			for(String fu : filterUrl){
				if(fu.equals(url)){
					LogCvt.debug("���������ų��������URL:"+url);
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * �жϵ�ǰ�û��Ƿ�ӵ�и�����Ȩ��
	 * @param params
	 * @return true:ӵ��Ȩ�� false:��Ȩ��
	 */
	private boolean checkUrl(Map<String,Object> params){
		int n = 0;
		SqlSession session =  null;
		/*
		try{
			if(StringUtils.isNotEmpty((String)params.get("roleId"))
					&& StringUtils.isNotEmpty((String)params.get("href"))){
				session = DB2Manager.getSession().openSession();
				FrMgUserMapper userMapper = session.getMapper(FrMgUserMapper.class);
				n = userMapper.checkUrl(params);
			}
		}catch(Exception e){
			LogCvt.error("���URLȨ���쳣��" + e.getMessage(),e);
		}finally{
			if(null!=session)
				session.close();
		}
		return n!=0?true:false;
		*/
		return false;
	}
}
