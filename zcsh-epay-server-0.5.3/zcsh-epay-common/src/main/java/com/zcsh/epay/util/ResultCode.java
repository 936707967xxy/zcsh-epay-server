/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.util;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
public enum ResultCode {
	success("0000","�����ɹ�"),
	failed("9999","����ʧ��"), //�����Ĵ�����Ϣ�Զ���
	//��¼���
	nologin("9900","δ��¼��Ự��ʱ�������µ�¼"),
	nouser("9000","�޴��û�"),
	errortimesout("9100","����������30���Ӻ����Ի�����������¼"),
	firstlogin("9200","�״ε�¼,�����޸�����"),
	nopermission("9300","������Ȩ��"),
	badRequest("4000", "�޷����������"),
	resetSelf("9400","��ǰ��¼�û������ã������µ�¼"),
	userOnline("9500","��ǰ�û��ѵ�¼���Ƿ�ǿ�Ƶ�¼��");
	private String code;
	
	private String msg;
	
	private ResultCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}

