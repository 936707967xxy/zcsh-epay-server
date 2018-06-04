/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
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
 * 作者：Administrator <br>
 * 创建时间：2018年6月4日 <br>
 * 描述：
 */
public class SessionFilter implements Filter {

	private Pattern[] regPatterns = new Pattern[0];
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		LogCvt.debug("过滤器初始化......");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		LogCvt.debug("==============过滤器启动拦截===============");
		//FrMgSession session =  null;
		HttpServletRequest req = (HttpServletRequest) request;
		String href = req.getRequestURI().replaceAll(req.getContextPath(), "");
		LogCvt.debug("正在检索排除项是否包含URL["+href+"]...");
		//除exclude url内的请求地址，其他请求均要校验是否登录
		try{
			
			if(this.checkExclude(href)){
				LogCvt.debug("检索结果：排除项包含该URL请求");
				filterChain.doFilter(request, response);
			}else{
				String token = "0";
				LogCvt.debug("正在检索下载项是否包含URL["+href+"]...");
				if(this.checkDownExclude(href)){
					LogCvt.debug("检索结果：下载项包含该URL请求，通过参数获取token,继续进行登录校验...");
					token=req.getParameter("token");
					LogCvt.debug("会话token:"+token);
				}else{
					LogCvt.debug("检索结果：下载项不包含该URL请求，通过header获取token,继续进行登录校验...");
					if(null!=req.getHeader("token")){
						token=req.getHeader("token");
					}
					LogCvt.debug("会话token:"+token);
				}
				
			}
		}catch(Exception e){
			LogCvt.error("过滤器拦截异常：" + e.getMessage(),e);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("code", ResultCode.badRequest.getCode());
			resultMap.put("message", ResultCode.badRequest.getMsg());
			ResponseUtil.returnJSONResponse((HttpServletResponse)response, resultMap);
			return;
		}finally{
			LogCvt.debug("==============过滤器拦截完成===============");
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		regPatterns = null;
	}
	
	/**
	 * 判断排除项内是否包含当前请求url
	 * @param params
	 * @return true:包含 false:不包含 
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
	 * 由于导出功能是form表单提交方式，token是以参数的形式传值
	 * 因此进行二次校验url
	 * @param params
	 * @return true:包含 false:不包含 
	 */
	private boolean checkDownExclude(String url){
		String exclude =PropertiesUtil.getValue("exclude_filter_downurl");
		String[] filterUrl = exclude.split(";");
		if(null!=filterUrl){
			for(String fu : filterUrl){
				if(fu.equals(url)){
					LogCvt.debug("检索发现排除项包含该URL:"+url);
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 判断当前用户是否拥有该请求权限
	 * @param params
	 * @return true:拥有权限 false:无权限
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
			LogCvt.error("检查URL权限异常：" + e.getMessage(),e);
		}finally{
			if(null!=session)
				session.close();
		}
		return n!=0?true:false;
		*/
		return false;
	}
}
