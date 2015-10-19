package com.hoolai.qzone.sango.vo;

public class User extends Friend{

	/** 性别 */
	private int sex;

	/** 玩家连续登陆的天数 */
	private int activeDays;
	
	private UserProperty userProperty;
	
	/** 剩余战斗次数 */
	private int dayFightCount;
	
	/** 紫晶钻 */
	private int ziJingZuan;

	@Override
	public String toString() {
		return "User [" + (name != null ? "name=" + name + ", " : "") + "sex=" + sex + ", country=" + country + ", activeDays=" + activeDays + ", "
				+ (image != null ? "image=" + image : "") + "]";
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getActiveDays() {
		return activeDays;
	}

	public void setActiveDays(int activeDays) {
		this.activeDays = activeDays;
	}
	
	public UserProperty getUserProperty() {
		if(userProperty == null){
			userProperty = new UserProperty();
		}
		return userProperty;
	}
	
	public int getZiJingZuan() {
		return ziJingZuan;
	}

	public void setZiJingZuan(int ziJingZuan) {
		this.ziJingZuan = ziJingZuan;
	}
	
	public int getRank(){
		return getUserProperty().getRank();
	}
	
	public int getFame(){
		return getUserProperty().getFame();
	}
	
	public int getGold(){
		return getUserProperty().getGold();
	}
	
	public int getHonor(){
		return getUserProperty().getHonor();
	}
	
	public static void main(String[] args) {
		String s = "http://sango.qzone.qzoneapp.com/index.jsp?qz_height=900&qz_width=760&openid=000000000000000000000000089B14EC&openkey=5A74E862AB21F270F6F19C27522B31D3&pf=qzone&pfkey=dd00e196a53838563317bcf94a3f5977&qz_ver=8&appcanvas=1&qz_style=25&params=";
		System.out.println(s);
		System.out.println(s.replaceAll("&", "\\\\&"));
	}

}
