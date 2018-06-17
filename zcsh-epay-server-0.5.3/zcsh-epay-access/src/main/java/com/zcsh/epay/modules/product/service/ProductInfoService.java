package com.zcsh.epay.modules.product.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.zcsh.epay.db.OracleManager;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.product.mapper.ProductInfoMapper;
import com.zcsh.epay.modules.product.vo.req.ProductInfoReq;
import com.zcsh.epay.modules.product.vo.resp.ProductInfoResp;
import com.zcsh.epay.util.Paging;

/**
 * 商品服务
 * @author Administrator
 */
public class ProductInfoService {

	/**
	 * 商品列表查询
	 * @param req
	 * @return
	 */
	public ResBody queryProductInfoList(ProductInfoReq req){
		ResBody res=null;
		SqlSession session = OracleManager.getSession().openSession();
		try {
			List<ProductInfoResp>list=null;
			Paging page = new Paging();
			if(req.getPageNumber()!=0){
				page.setPageNumber(req.getPageNumber());
			}
			if(req.getPageSize()!=0){
				page.setPageSize(req.getPageSize());
			}
			ProductInfoMapper mapper=session.getMapper(ProductInfoMapper.class);
			list=mapper.queryProductInfoList(page,req);
			
			res=new ResBody(ResBody.SUCCESS_CODE,"查询成功！");
			res.setData(list);
			res.setPage(page);
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("数据库查询异常！");
			res=new ResBody(ResBody.ERROR_CODE,"数据库查询异常！");
		}
		return res;
	}
	
	/**
	 * 商品详细信息查询
	 * @param req
	 * @return
	 */
	public ResBody queryProductDetailsInfo(ProductInfoReq req){
		ResBody res=null;
		SqlSession session = OracleManager.getSession().openSession();
		try {
			ProductInfoResp resp=null;
			ProductInfoMapper mapper=session.getMapper(ProductInfoMapper.class);
			resp=mapper.queryProductDetailsInfo(req);
			res=new ResBody(ResBody.SUCCESS_CODE,"查询成功！");
			res.setData(resp);
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("数据库查询异常！");
			res=new ResBody(ResBody.SUCCESS_CODE,"数据库查询异常！");
		}
		return res;
	}
	
	/**
	 * 查询购物车产品信息
	 * @param req
	 * @return
	 */
	public ResBody queryProductCartList(ProductInfoReq req){
		ResBody res=null;
		SqlSession session = OracleManager.getSession().openSession();
		try {
			List<ProductInfoResp>list=null;
			Paging page = new Paging();
			if(req.getPageNumber()!=0){
				page.setPageNumber(req.getPageNumber());
			}
			if(req.getPageSize()!=0){
				page.setPageSize(req.getPageSize());
			}
			ProductInfoMapper mapper=session.getMapper(ProductInfoMapper.class);
			list=mapper.queryProductCartList(page, req);
			
			res=new ResBody(ResBody.SUCCESS_CODE,"查询成功！");
			res.setData(list);
			res.setPage(page);
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("数据库查询异常！");
			res=new ResBody(ResBody.ERROR_CODE,"数据库查询异常！");
		}
		return res;
	}
}
