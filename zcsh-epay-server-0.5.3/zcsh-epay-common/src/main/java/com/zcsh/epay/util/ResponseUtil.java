/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.zcsh.epay.log.LogCvt;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
/**
 * HttpServletResponse��Ӧ������
 * 
 * @author zhupengcheng@f-road.com.cn
 * @date 2016-11-02
 *
 */
public class ResponseUtil {
	
	/**
	 * ����JSON��ʽ��Ӧ
	 * 
	 * @param response
	 * @param model
	 */
	public static void returnJSONResponse(HttpServletResponse response, Map<String, Object> model) {
		

		String responseMsg = "";
		Map<String, Object> respMap = JSONUtil.toMap(model);
		if(respMap.get("sopRespXml")!= null && !respMap.get("sopRespXml").toString().isEmpty()){
		    response.setContentType("application/xml; charset=UTF-8");  
		    String respXml = respMap.get("sopRespXml").toString();
		    respXml = respXml.replace(":request", ":response");
		    respMap.remove("sopRespXml");
		    String xml = XmlUtil.json2xml(JSON.toJSONString(respMap));
		    xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
		    xml = xml.replace("<o>", "").replace("</o>", "");
		    xml = xml.replaceAll("\n","").replaceAll("\r","");
		    responseMsg = respXml.replace("<bizBody></bizBody>", "<bizBody>"+xml+"</bizBody>");
		    
			//��xml
			LogCvt.info("��Ӧxml���Ϊ��" + responseMsg);
			
		}else{
			//��json
			respMap.remove("sopRespXml");
			response.setContentType("application/json; charset=UTF-8");
			responseMsg = JSON.toJSONString(respMap);
			LogCvt.info("��Ӧjson���Ϊ��" + responseMsg);
		     
		}
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "*");
	    response.setCharacterEncoding("UTF-8");  
//	    response.setContentType("application/json; charset=UTF-8");  
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(responseMsg);  
	    } catch (IOException e) {  
	        LogCvt.error("��Ӧʧ��", e);
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }  
	}

	/**
	 * ����JSON��ʽ��Ӧ
	 * 
	 * @param response
	 * @param model
	 * @param actionName 
	 */
	public static void returnJSONResponse(HttpServletResponse response, Object model, String actionName) {
		
		String responseMsg = "";
		Map<String, Object> respMap = JSONUtil.toMap(model);
		if(respMap.get("sopRespXml")!= null && !respMap.get("sopRespXml").toString().isEmpty()){
		    response.setContentType("application/xml; charset=UTF-8");  
		    String respXml = respMap.get("sopRespXml").toString();
		    respXml = respXml.replace(":request", ":response");
		    respMap.remove("sopRespXml");
		    String xml = XmlUtil.json2xml(JSON.toJSONString(respMap));
		    xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
		    xml = xml.replace("<o>", "").replace("</o>", "");
		    xml = xml.replaceAll("\n","").replaceAll("\r","");
		    responseMsg = respXml.replace("<bizBody></bizBody>", "<bizBody>"+xml+"</bizBody>");
		    
			//��xml
			LogCvt.info("��Ӧxml���Ϊ��" + responseMsg);
			
		}else{
			//��json
			respMap.remove("sopRespXml");
			response.setContentType("application/json; charset=UTF-8");
			responseMsg = JSON.toJSONString(respMap);
			if(!actionName.contains("fileDownload")){
				LogCvt.info("��Ӧjson���Ϊ��" + responseMsg);
			}
		     
		}
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "*");
	    response.setCharacterEncoding("UTF-8");  

	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(responseMsg);  
	    } catch (IOException e) {  
	        LogCvt.error("��Ӧʧ��", e);
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }  
	}
}

