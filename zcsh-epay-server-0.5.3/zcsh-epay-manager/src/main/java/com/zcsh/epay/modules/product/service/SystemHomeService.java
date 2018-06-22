package com.zcsh.epay.modules.product.service;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.zcsh.epay.db.OracleManager;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.product.mapper.ProductInfoMapper;
import com.zcsh.epay.modules.product.vo.resp.SystemHomeResp;
import com.zcsh.epay.modules.user.vo.req.UserInfoReq;
import com.zcsh.epay.util.DateUtil;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月21日 <br>
 * 描述：管理平台首页数据统计
 */
public class SystemHomeService {


	public ResBody queryStatisticsData(UserInfoReq req){
		SqlSession session = OracleManager.getSession().openSession();
		ResBody res=null;
		try {
			SystemHomeResp result=null;
			List<SystemHomeResp>list=null;
			ProductInfoMapper mapper=session.getMapper(ProductInfoMapper.class);
			
			req.setDay(new Date());
			req.setMonthDay(DateUtil.addDaysToDate(new Date(), -DateUtil.getDays()));
			list=mapper.queryStatisticsData(req);
			double monthTradingAmount=0;
			double dayTradingAmount=0;
			double weekTradingAmount=0;
			if(list!=null){
				for (SystemHomeResp resp : list) {
					monthTradingAmount=monthTradingAmount+Double.parseDouble(resp.getAmt());
					
					if(DateUtil.dateToString(new Date(), DateUtil.standardFormat).equals(resp.getCreateTime())){
						dayTradingAmount=dayTradingAmount+Double.parseDouble(resp.getAmt());
					}
					
					String startTime,endTime;
					startTime=DateUtil.dateToString(new Date(),DateUtil.standardFormat);
					endTime=DateUtil.dateToString(DateUtil.addDaysToDate(new Date(),-7),DateUtil.standardFormat);
					if(Integer.parseInt(startTime)<=Integer.parseInt(resp.getCreateTime())
							&&Integer.parseInt(resp.getCreateTime())<=Integer.parseInt(endTime)){
						weekTradingAmount=weekTradingAmount+Double.parseDouble(resp.getAmt());
					}
				}
			}
			result=new SystemHomeResp();
			result.setMonthTradingAmount(String.valueOf(monthTradingAmount));
			result.setWeekTradingAmount(String.valueOf(weekTradingAmount));
			result.setDayTradingAmount(String.valueOf(dayTradingAmount));
			
			res =new ResBody(ResBody.SUCCESS_CODE,"查询成功！");
			res.setData(result);
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("查询数据库异常！");
			res =new ResBody(ResBody.ERROR_CODE,"查询数据库异常！");
		}
		return res;
	}
}
