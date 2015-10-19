package com.hoolai.qzone.sango;

public class SangoLoginInfo {

	private final String token;
	private final String userIdStr;
	private final String sangoServerUrl;

	public SangoLoginInfo(String token, String userIdStr, String sangoServerUrl) {
		this.token = token;
		this.userIdStr = userIdStr;
		this.sangoServerUrl = sangoServerUrl;
	}

	public String getToken() {
		return token;
	}

	public String getUserIdStr() {
		return userIdStr;
	}
	
	public String getSangoServerUrl() {
		return sangoServerUrl;
	}

}
