/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.util.CalendarUtil;
import com.zcsh.epay.util.EmptyChecker;
import com.zcsh.epay.util.ResponseUtil;
import com.zcsh.epay.util.ResultCode;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
public class AgreServlet extends HttpServlet {
	public static final String FILE_NAME = "actionConfig.properties";
	
	
	static {
		if (ActionFactory.prop.isEmpty()) {
			InputStream in = null;
			BufferedReader bf = null;
			InputStreamReader isr = null;
			try {
				in = ConfigLoad.loadAsStream(FILE_NAME);
				isr = new InputStreamReader(in, "utf-8");
				bf = new BufferedReader(isr);
				ActionFactory.prop.load(bf);
			} catch (Exception e) {
				LogCvt.debug("��ʼ���쳣" + e.getMessage(), e);
			} finally{
				try{
					if(isr!=null) isr.close();
					if(bf!=null) bf.close();
					if(in!=null) in.close();
				}catch(Exception e){}
			}
		}
	}

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8975149911488164961L;

	protected ActionFactory getActionFactory = new GetActionFactory();
	protected ActionFactory postActionfactory = new PostActionFactory();

	public AgreServlet() {
		super();
	}

	protected String getActionName(HttpServletRequest request) {
		String path = request.getRequestURI();
		if (EmptyChecker.isNotEmpty(path)) {
			return path.substring(path.lastIndexOf("/") + 1, path.length());
		}
		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setThreadName(request);
		LogCvt.info("[Servlet]-GET����["+request.getRequestURI()+"]......");
		long startTime = System.currentTimeMillis();
		String actionName = getActionName(request);
		try {
			Action action = getActionFactory.create(actionName);
			Object perform = action.perform(request, response);
			if(null!=perform){
				ResponseUtil.returnJSONResponse(response, perform, actionName);
			}
		} catch (InstantiationException e) {
			LogCvt.error("ʵ����Action�����. [" + actionName + "]", e);
		} catch (IllegalAccessException e) {
			LogCvt.error("ʵ����Action�����. [" + actionName + "]", e);
		} catch (Exception e) {
			LogCvt.error("Actionҵ��ִ�д���", e);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("code", ResultCode.badRequest.getCode());
			resultMap.put("message", ResultCode.badRequest.getMsg());
			ResponseUtil.returnJSONResponse(response, resultMap);
		}

		LogCvt.info("[Servlet]-GET�������["+request.getRequestURI()+"],[��ʱ��"+(System.currentTimeMillis()-startTime)+"ms]......");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		setThreadName(request);
		LogCvt.info("[Servlet]-POST����["+request.getRequestURI()+"]......");
		long startTime = System.currentTimeMillis();
		String actionName = getActionName(request);
		try {
			Action action = postActionfactory.create(actionName);
			Object perform = action.perform(request, response);
			if(null!=perform){
				ResponseUtil.returnJSONResponse(response, perform, actionName);
			}
		} catch (InstantiationException e) {
			LogCvt.error("ʵ����Action�����. [" + actionName + "]", e);
		} catch (IllegalAccessException e) {
			LogCvt.error("ʵ����Action�����. [" + actionName + "]", e);
		} catch (Exception e) {
			LogCvt.error("Actionҵ��ִ�д���", e);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("code", ResultCode.badRequest.getCode());
			resultMap.put("message", ResultCode.badRequest.getMsg());
			ResponseUtil.returnJSONResponse(response, resultMap);
		}
		LogCvt.info("[Servlet]-POST�������["+request.getRequestURI()+"],[��ʱ��"+(System.currentTimeMillis()-startTime)+"ms]......");
	}
	
	public static int random(int begin, int end){
        return (int)(Math.random() * (end - begin) + begin);
    }
	
	private static SimpleDateFormat sdf = new SimpleDateFormat(CalendarUtil.YYYYMMDDHHMMSSSSS);
	private void setThreadName(HttpServletRequest request){
		StringBuffer sb=new StringBuffer();
		sb.append(sdf.format(new Date()));
		sb.append("-"+random(100000, 999999));
		Thread.currentThread().setName(String.valueOf(sb));
	}
}

