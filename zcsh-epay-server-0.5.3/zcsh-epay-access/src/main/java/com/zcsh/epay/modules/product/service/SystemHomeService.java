package com.zcsh.epay.modules.product.service;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.zcsh.epay.db.OracleManager;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.product.mapper.SystemHomeMapper;
import com.zcsh.epay.modules.product.vo.req.SystemHomeReq;
import com.zcsh.epay.modules.product.vo.resp.ProductInfoResp;
import com.zcsh.epay.modules.product.vo.resp.SystemHomeResp;
import com.zcsh.epay.utils.vo.ProductTypeVo;

/**
 * @author Administrator
 *商城首页
 */
public class SystemHomeService {

	/**
	 * 商城首页信息查询
	 * @param req
	 * @return
	 */
	public ResBody querySystemHomeInfo(SystemHomeReq req){
		ResBody res=null;
		SqlSession session = OracleManager.getSession().openSession();
		try {
			List<ProductTypeVo> typeVo=null;
			SystemHomeResp resp=null;
			List<ProductInfoResp>selectProductList=new ArrayList<ProductInfoResp>();
			List<ProductInfoResp>hostProductList=new ArrayList<ProductInfoResp>();
			SystemHomeMapper mapper=session.getMapper(SystemHomeMapper.class);
			
			typeVo=mapper.queryProductType();
			List<ProductInfoResp>product=mapper.queryHomeProduct();
			
			if(product!=null&&product.size()>0){
				for (ProductInfoResp voProduct : product) {
					if("HOT_SALE".equals(voProduct.getShowType())){
						hostProductList.add(voProduct);
					}else if("SELECTED".equals(voProduct.getShowType())){
						selectProductList.add(voProduct);
					}
				}
			}
			if(resp==null){
				resp=new SystemHomeResp();
			}
			resp.setProductTypeList(typeVo);
			resp.setHostProductList(hostProductList);
			resp.setSelectProductList(selectProductList);
			res = new ResBody(ResBody.SUCCESS_CODE,"查询成功！");
			res.setData(resp);
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("查询数据库异常！");
			res = new ResBody(ResBody.ERROR_CODE,"查询数据库异常！");
		}
		return res;
	}	
}
