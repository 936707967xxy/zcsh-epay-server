/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.util.security;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月11日 <br>
 * 描述：加密工具
 */
public class SecurityUtils {
    /**
     * 作者：Administrator <br>
     * 创建时间：2018年6月11日 <br>
     * 描述： 登录状态加密
     * @param context [OPEN_ID+SESSION_KEY]组合
     * @param KEY 配置文件读取TOKEN_KEY
     * @return
     */
    public static String wechatSessionSecurity(String context,String key){
    	StringBuffer sb =new StringBuffer();
    	try {
    		if(key!=null&&!key.isEmpty()){
    			String key_context=MD5.encodeByMd5AndSalt(key);
    			String value_context=MD5.encodeByMd5AndSalt(context);
    			String core_context=MD5.encodeByMd5AndPwdSalt(key_context+value_context);
    			sb.append(key_context)
    			.append(core_context)
    			.append(value_context);
    		}
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
    }
    
    public static void main(String[] args) {
    	String key ="zcsh";
    	System.out.println(MD5.encodeByMd5AndSalt(key));
	}
}
