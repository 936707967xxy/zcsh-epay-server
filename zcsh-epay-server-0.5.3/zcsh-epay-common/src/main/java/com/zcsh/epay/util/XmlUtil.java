/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zcsh.epay.log.LogCvt;



/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
public class XmlUtil {

    private static final String ENCODING = "utf-8";
//	private static final Object SoapenvEnvelope = null;
	private static Map<String, Unmarshaller> xStreamInstance = new HashMap<String, Unmarshaller>(); 
	private static Map<Class<?>, XStream> xStreamInstance1 = new HashMap<Class<?>, XStream>(); 
	
	@SuppressWarnings("unchecked")
	public static <T> T xml2Bean(String xml,Class<T> clazz) throws Exception {
		 T t = null;  
	        try {  
	        	if(StringUtils.isEmpty(xml)){return null;}
	        	Unmarshaller unmarshaller=xStreamInstance.get(clazz.getName());
	        	if(unmarshaller==null){
	        		JAXBContext context = JAXBContext.newInstance(clazz); 
	        		unmarshaller = context.createUnmarshaller();  
	        		xStreamInstance.put(clazz.getName(), unmarshaller);
	        	}
	        	
	        	synchronized (unmarshaller) {
	        		t = (T) unmarshaller.unmarshal(new StringReader(xml)); 	
				} 
	        	
	        } catch (Exception e) { 
	        	LogCvt.error(e.getMessage(), e); 
	        	throw e;
	           
	        }  
	      return t;  
	} 
	
	@SuppressWarnings("unchecked")
	public static <T> T xml2Bean(String xx,String xml,Class<T> clazz) {
	    T t=null;
		try {  
			if(StringUtils.isEmpty(xml)){return null;}
			XStream xStream = xStreamInstance1.get(clazz);
			if(xStream == null){
				xStream = new XStream(new DomDriver(ENCODING));
				xStream.ignoreUnknownElements();
				xStream.alias("xml", clazz);
				xStreamInstance1.put(clazz, xStream);
			}
			 t = (T) xStream.fromXML(xml);
	    }catch(Exception e){
	    	LogCvt.error(e.getMessage(), e); 
	    }
		return t;
	}
	
	  public static String convertToXml(Object obj) {  
	        return convertToXml(obj, ENCODING);  
	  }  
	  
	    public static String convertToXml(Object obj, String encoding) {  
	        String result = null;  
	        try {  
	            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
	            Marshaller marshaller = context.createMarshaller();  
	            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
	            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);  
	            StringWriter writer = new StringWriter();  
	            marshaller.marshal(obj, writer);  
	            result = writer.toString();  
	        } catch (Exception e) {  
	        	 LogCvt.error(e.getMessage(), e); 
	        }  
	  
	        return result;  
	    }  
	
	
	    public static Map<String,Object> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {
			InputStream is = null;
			try{
		        //������Dom�ķ�ʽ�����ذ�������ҪĿ���Ƿ�ֹAPI�����ذ��ֶ�
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder builder = factory.newDocumentBuilder();
		        is = XmlUtil.getStringStream(xmlString);
		        Document document = builder.parse(is);
		
		        //��ȡ��document�����ȫ�����
		        NodeList allNodes = document.getFirstChild().getChildNodes();
		        Node node;
		        Map<String, Object> map = new HashMap<String, Object>();
		        int i=0;
		        while (i < allNodes.getLength()) {
		            node = allNodes.item(i);
		            if(node instanceof Element){
	                map.put(node.getNodeName(),node.getTextContent());
	            }
		            i++;
		        }
		        return map;
			}finally {
				if( is != null){
					is.close();
				}
			}

	    }

    public static InputStream getStringStream(String sInputString) throws UnsupportedEncodingException {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes("UTF-8"));
        }
        return tInputStringStream;
    }
   
    
    public static String getStringFromMap(Map<String, Object> map, String key, String defaultValue) {
        if (key == "" || key == null) {
            return defaultValue;
        }
        String result = (String) map.get(key);
        if (result == null) {
            return defaultValue;
        } else {
            return result;
        }
    }

    public static int getIntFromMap(Map<String, Object> map, String key) {
        if (key == "" || key == null) {
            return 0;
        }
        if (map.get(key) == null) {
            return 0;
        }
        return Integer.parseInt((String) map.get(key));
    }

    
    public static String json2xml(String jsonString) {
    	XMLSerializer xmlSerializer = new XMLSerializer();
        return xmlSerializer.write(JSONSerializer.toJSON(jsonString));
        // return xmlSerializer.write(JSONArray.fromObject(jsonString));//���ַ�ʽֻ֧��JSON����
    }
}


