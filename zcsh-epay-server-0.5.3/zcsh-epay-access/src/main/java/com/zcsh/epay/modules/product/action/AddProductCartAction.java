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
 * 作者：Administrator <br>
 * 创建时间：2018年6月19日 <br>
 * 描述：添加购物车
 */
public class AddProductCartAction extends BaseAction implements Action{

	private ProductInfoService service=new ProductInfoService();
	
	public ResBody perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		long start=System.currentTimeMillis();
		try {
			LogCvt.info("==============购物车信息添加开始===============");
			ProductInfoReq req = (ProductInfoReq) RequestUtil.copyParam(ProductInfoReq.class, request);
			UserSession session=SessionUtils.getLoginSession(request);
			if(session != null){
				if(req == null){
					req = new ProductInfoReq();
				}
				return service.addProductCartInfo(req);
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
			LogCvt.info("==============购物车信息添加结束===============");
		}
	}
}
