/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月4日 <br>
 * 描述：
 */
public class SetCharacterEncodingFilter implements Filter {

	/**
     * The default character encoding to set for requests that pass through
     * this filter.
     */
    protected String encoding = null;
 
    /**
     * The filter configuration object we are associated with.  If this value
     * is null, this filter instance is not currently configured.
     */
    protected FilterConfig filterConfig = null;
 
    /**
     * Should a character encoding specified by the client be ignored?
     */
    protected boolean ignore = true;
    
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		 this.filterConfig = filterConfig;
	        this.encoding = filterConfig.getInitParameter("encoding");
	        String value = filterConfig.getInitParameter("ignore");
	        if (value == null)
	            this.ignore = true;
	        else if (value.equalsIgnoreCase("true"))
	            this.ignore = true;
	        else if (value.equalsIgnoreCase("yes"))
	            this.ignore = true;
	        else
	            this.ignore = false;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (ignore || (request.getCharacterEncoding() == null)) {
            String encoding = selectEncoding(request);
            if (encoding != null)
                request.setCharacterEncoding(encoding);
        }
 
        chain.doFilter(request, response);
	}

	public void destroy() {
		// TODO Auto-generated method stub
		 this.encoding = null;
	     this.filterConfig = null;
	}
	protected String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }
}
