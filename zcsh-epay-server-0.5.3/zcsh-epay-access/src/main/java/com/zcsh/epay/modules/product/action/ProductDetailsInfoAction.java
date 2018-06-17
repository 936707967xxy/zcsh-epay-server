package com.zcsh.epay.modules.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zcsh.epay.action.Action;
import com.zcsh.epay.action.BaseAction;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.product.service.ProductInfoService;
import com.zcsh.epay.modules.product.vo.req.ProductInfoReq;
import com.zcsh.epay.util.RequestUtil;
import com.zcsh.epay.util.ResultCode;
import com.zcsh.epay.utils.SessionUtils;
import com.zcsh.epay.utils.UserSession;
/**
 * 商品详情查询
 * @author Administrator
 */
public class ProductDetailsInfoAction extends BaseAction implements Action{
  
	private ProductInfoService service=new ProductInfoService();
	
	public ResBody perform(HttpServletRequest request, HttpServletResponse response) {
		long start=System.currentTimeMillis();
		try {
			LogCvt.info("==============商品详细信息查询开始===============");
			ProductInfoReq req = (ProductInfoReq) RequestUtil.copyParam(ProductInfoReq.class, request);
			UserSession session=SessionUtils.getLoginSession(request);
			if(session != null){
				if(req == null){
					req = new ProductInfoReq();
				}
				return service.queryProductDetailsInfo(req);
			}else{
				return new ResBody(ResultCode.nologin.getCode(),ResultCode.nologin.getMsg());
			}
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("查询异常");
			return new ResBody(ResBody.ERROR_CODE,"查询异常");
		}finally{
			long end=System.currentTimeMillis();
			LogCvt.info("耗时："+(end-start)+"ms");
			LogCvt.info("==============商品详细信息查询结束===============");
		}
	}
}
