/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.modules.pay.wechat.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.weixin4j.WeixinException;
import org.weixin4j.WeixinSupport;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.util.StringUtil;
import com.zcsh.epay.util.wechat.IpUtils;
import com.zcsh.epay.util.wechat.WechatPayUtil;
import com.zcsh.epay.util.wechat.vo.OAuthJsToken;
import com.zcsh.epay.utils.wx.ResultCode;
import com.zcsh.epay.utils.wx.WechatConstants;
/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月7日 <br>
 * 描述：微信服务
 */
public class WechatLoginService extends WeixinSupport{

    /**
     * 作者：Administrator <br>
     * 创建时间：2018年6月7日 <br>
     * 描述： 小程序后台登录，向微信平台发送获取access_token请求，并返回openId
     * @param code
     * @param request
     * @return
     * @throws WeixinException
     * @throws IOException
     */
	public Map<String, Object> wechatLogin(String code, HttpServletRequest request) throws WeixinException, IOException {
        if (code == null || code.equals("")) {
            throw new WeixinException("invalid null, code is null.");
        }
        Map<String, Object> ret = new HashMap<String, Object>();
        //拼接参数
        String param = "?grant_type=" + WechatConstants.GRANT_TYPE + "&appid=" + WechatConstants.APPID + "&secret=" + WechatConstants.SECRET + "&js_code=" + code;
        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.get("https://api.weixin.qq.com/sns/jscode2session" + param);
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj != null) {
            Object errcode = jsonObj.get(ResultCode.ERRCODE);
            if (errcode != null) {
                //返回异常信息
                throw new WeixinException(getCause(Integer.parseInt(errcode.toString())));
            }
            ObjectMapper mapper = new ObjectMapper();
            OAuthJsToken oauthJsToken = mapper.readValue(jsonObj.toJSONString(),OAuthJsToken.class);
            LogCvt.info("openid=" + oauthJsToken.getOpenid());
            ret.put("openid", oauthJsToken.getOpenid());
        }
        return ret;
    }
	
	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年6月7日 <br>
	 * 描述： 发起微信支付
	 * @param openid
	 * @param request
	 * @return
	 */
    public ResBody wechatPay(String openid, HttpServletRequest request){
    	ResBody res = new ResBody();
        try{
            //生成的随机字符串
            String nonce_str = StringUtil.getRandomStringByLength(32);
            //商品名称
            String body = "测试商品名称";
            //获取本机的ip地址
            String spbill_create_ip = IpUtils.getIpAddr(request);
            String orderNo = "123456789";
            String money = "1";//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败
            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", WechatConstants.APPID);
            packageParams.put("mch_id", WechatConstants.MCH_ID);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", orderNo);//商户订单号
            packageParams.put("total_fee", money);//支付金额，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WechatConstants.NOTIFY_URL);
            packageParams.put("trade_type", WechatConstants.TRADETYPE);
            packageParams.put("openid", openid);

            // 除去数组中的空值和签名参数
            packageParams = WechatPayUtil.paraFilter(packageParams);
            String prestr = WechatPayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = WechatPayUtil.sign(prestr, WechatConstants.KEY, "utf-8").toUpperCase();
            LogCvt.info("=======================第一次签名：" + mysign + "=====================");

            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml>" + "<appid>" + WechatConstants.APPID + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + WechatConstants.MCH_ID + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WechatConstants.NOTIFY_URL + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + orderNo + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + money + "</total_fee>"
                    + "<trade_type>" + WechatConstants.TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";

            LogCvt.info("调用统一下单接口 请求XML数据：" + xml);
            //调用统一下单接口，并接受返回的结果
            String result = WechatPayUtil.httpRequest(WechatConstants.NOTIFY_URL, "POST", xml);
            LogCvt.info("调用统一下单接口 返回XML数据：" + result);

            // 将解析结果存储在HashMap中
            Map<String,String> map = WechatPayUtil.doXMLParse(result);
            String return_code = (String) map.get("return_code");//返回状态码
            //返回给移动端需要的参数
            Map<String, Object> response = new HashMap<String, Object>();
            if(return_code == ResultCode.SUCCESS || return_code.equals(return_code)){
                // 业务结果
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误

                String stringSignTemp = "appId=" + WechatConstants.APPID + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=" + WechatConstants.SIGNTYPE + "&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = WechatPayUtil.sign(stringSignTemp, WechatConstants.KEY, "utf-8").toUpperCase();
                LogCvt.info("=======================第二次签名：" + paySign + "=====================");
                response.put("paySign", paySign);
                //更新订单信息
                //业务逻辑代码
            }
            response.put("appid", WechatConstants.KEY);
            res.setCode("0000");
            res.setData(response);
        }catch(Exception e){
            e.printStackTrace();
            res.setCode("1111");
            res.setMessage("发起失败");
        }
        return res;
    }
    
    /**
     * 作者：Administrator <br>
     * 创建时间：2018年6月7日 <br>
     * 描述： 微信支付
     * @param request
     * @param response
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public void wechatNotify(HttpServletRequest request,HttpServletResponse response) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        LogCvt.info("接收报文参数："+notityXml);

        Map<String,String> map = WechatPayUtil.doXMLParse(notityXml);

        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            //验证签名是否正确
            if(WechatPayUtil.verify(WechatPayUtil.createLinkString(map), (String)map.get("sign"), WechatConstants.KEY, "utf-8")){
                /**此处添加自己的业务逻辑代码start**/


                /**此处添加自己的业务逻辑代码end**/
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        }else{
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        LogCvt.info("微信返回报文"+resXml);
        LogCvt.info("微信支付回调数据结束");
        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }
}
