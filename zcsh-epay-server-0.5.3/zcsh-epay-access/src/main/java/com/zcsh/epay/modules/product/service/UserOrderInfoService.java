package com.zcsh.epay.modules.product.service;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.zcsh.epay.db.OracleManager;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.product.mapper.UserOrderMapper;
import com.zcsh.epay.modules.product.vo.req.UserOrderReq;
import com.zcsh.epay.modules.product.vo.resp.UserOrderResp;
import com.zcsh.epay.util.Paging;
import com.zcsh.epay.utils.enums.QueryTypeEnum;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月15日 <br>
 * 描述：订单查询
 */
public class UserOrderInfoService {

	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年6月15日 <br>
	 * 描述： 我的-->查询待支付订单、查询待收货订单、查询全部订单
	 * @param req
	 * @return
	 */
	public ResBody queryUserOrderInfo(UserOrderReq req){
		ResBody res=null;
		SqlSession session = OracleManager.getSession().openSession();
		try {
			Paging page = new Paging();
			if(req.getPageNumber()!=0){
				page.setPageNumber(req.getPageNumber());
			}
			if(req.getPageSize()!=0){
				page.setPageSize(req.getPageSize());
			}
			
			UserOrderMapper mapper=session.getMapper(UserOrderMapper.class);
			
			if(QueryTypeEnum.QUERY_TYPE_WP.getCode().equals(req.getQueryType())){
				
			}else if(QueryTypeEnum.QUERY_TYPE_WG.getCode().equals(req.getQueryType())){
				
			}else if(QueryTypeEnum.QUERY_TYPE_AO.getCode().equals(req.getQueryType())){
				
			}else{
				LogCvt.error("查询类型未知！");
				return null;
			}
			List<UserOrderResp>list=mapper.queryUserOrderInfo(page,req);
			res = new ResBody(ResBody.SUCCESS_CODE,"查询成功！");
			res.setData(list);
			res.setPage(page);
		} catch (Exception e) {
			// TODO: handle exception
			res = new ResBody(ResBody.ERROR_CODE,"数据库查询异常！");
			LogCvt.error("数据库查询异常！");
		}
		return res;
	}
	
	
}
