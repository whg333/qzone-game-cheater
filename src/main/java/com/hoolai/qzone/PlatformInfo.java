package com.hoolai.qzone;

import static com.hoolai.qzone.Global.QZ_PLATFORM;

public class PlatformInfo {
	
	private String openid;
	private String openkey;
	private String pfkey;
	private String pf = QZ_PLATFORM;
	private String supportPlatformPf = QZ_PLATFORM;

	public PlatformInfo(String openid, String openkey, String pfkey) {
		this.openid = openid;
		this.openkey = openkey;
		this.pfkey = pfkey;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOpenkey() {
		return openkey;
	}

	public void setOpenkey(String openkey) {
		this.openkey = openkey;
	}

	public String getPfkey() {
		return pfkey;
	}

	public void setPfkey(String pfkey) {
		this.pfkey = pfkey;
	}

	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	public String getSupportPlatformPf() {
		return supportPlatformPf;
	}

	public void setSupportPlatformPf(String supportPlatformPf) {
		this.supportPlatformPf = supportPlatformPf;
	}
}
