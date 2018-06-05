package com.zcsh.epay.util;
public class AuthCodeReq {
	private String authCode;
	private String channel;
	private String sopRespXml;
	
	public String getAuthCode() {
		return authCode;
	}
	
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSopRespXml() {
		return sopRespXml;
	}

	public void setSopRespXml(String sopRespXml) {
		this.sopRespXml = sopRespXml;
	}
	
 
}
