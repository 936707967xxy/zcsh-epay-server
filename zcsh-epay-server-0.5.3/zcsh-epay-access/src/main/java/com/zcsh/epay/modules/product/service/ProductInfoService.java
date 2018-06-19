package com.zcsh.epay.modules.product.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zcsh.epay.db.OracleManager;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.product.mapper.ProductInfoMapper;
import com.zcsh.epay.modules.product.vo.req.ProductInfoReq;
import com.zcsh.epay.modules.product.vo.resp.ProductInfoResp;
import com.zcsh.epay.modules.product.vo.resp.UserOrderResp;
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
	
	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年6月19日 <br>
	 * 描述： 添加购物车信息
	 * @param req
	 * @return
	 */
	public ResBody addProductCartInfo(ProductInfoReq req){
		ResBody res=null;
		SqlSession session = OracleManager.getSession().openSession();
		try {
			ProductInfoMapper mapper=session.getMapper(ProductInfoMapper.class);
			Integer count=mapper.addProductCartInfo(req);
			session.commit(true);
			if(count>=1){
				res =new ResBody(ResBody.SUCCESS_CODE,"购物车添加成功");
			}else{
				res =new ResBody(ResBody.ERROR_CODE,"购物车添加失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			if(null!=session){
				session.rollback(true);
			}
			LogCvt.error("数据库操作异常:"+e.getMessage());
			res =new ResBody(ResBody.ERROR_CODE,"数据库操作异常");
		}finally{
			if(null!=session){
				session.close();
			}
		}
		return res;
	}
	
	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年6月19日 <br>
	 * 描述： 查询客户端结算页面
	 * @return
	 */
	public ResBody queryProductSettleList(ProductInfoReq req){
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
			List<UserOrderResp>list=null;
			ProductInfoMapper mapper=session.getMapper(ProductInfoMapper.class);
			list=mapper.queryProductSettleList(page,req);
			
			res = new ResBody(ResBody.SUCCESS_CODE,"查询成功");
			res.setData(list);
			res.setPage(page);
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("数据库查询异常"+e.getMessage());
			res = new ResBody(ResBody.ERROR_CODE,"数据库查询异常");
		}
		return res;
	}
}
