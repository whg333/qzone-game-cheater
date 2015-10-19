package com.hoolai.qzone;

public class LoginUser {

	private final String qq;
	private final String pwd;
	
	private String qqName = "未知";
	
	@Override
	public String toString() {
		return qq+"["+qqName+"]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((qq == null) ? 0 : qq.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginUser other = (LoginUser) obj;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (qq == null) {
			if (other.qq != null)
				return false;
		} else if (!qq.equals(other.qq))
			return false;
		return true;
	}

	public LoginUser(String qq, String pwd) {
		this.qq = qq;
		this.pwd = pwd;
	}

	public String getQq() {
		return qq;
	}

	public String getPwd() {
		return pwd;
	}
	
	public String getQqName() {
		return qqName;
	}

	public void setQqName(String qqName) {
		this.qqName = qqName;
	}
	
}
