package com.zcsh.epay.modules.user.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import com.zcsh.epay.action.Action;
import com.zcsh.epay.action.BaseAction;
import com.zcsh.epay.log.LogCvt;
import com.zcsh.epay.message.ResBody;
import com.zcsh.epay.modules.user.service.LoginUserService;
import com.zcsh.epay.modules.user.vo.req.UserInfoReq;
import com.zcsh.epay.util.RequestUtil;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��5�� <br>
 * ������
 */
public class LoginUserAction extends BaseAction implements Action{

	private LoginUserService service=new LoginUserService();
	
	public ResBody perform(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		LogCvt.info("==============�û���Ϣ��ѯ��ʼ===============");
		long start=System.currentTimeMillis();
		try {
			UserInfoReq req = (UserInfoReq) RequestUtil.copyParam(UserInfoReq.class, request);
			LogCvt.info("���������"+JSON.toJSONString(req));
			return service.queryUserInfo(req);
		} catch (Exception e) {
			// TODO: handle exception
			LogCvt.error("��ѯ�쳣��"+e.getMessage(),e);
			return new ResBody(ResBody.ERROR_CODE, "��ѯ�쳣");
		}finally{
			long end=System.currentTimeMillis();
			LogCvt.info("��ʱ��"+(end-start)+"ms");
			LogCvt.info("==============�û���Ϣ��ѯ����===============");
		}
	}

}
