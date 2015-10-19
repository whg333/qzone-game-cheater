package com.hoolai.qzone.sango.vo;

public class Friend {
	
	protected long userId;

	/** 用户平台名字 */
	protected String name;
	
	/** 国别 */
	protected int country;

	/** 头像 */
	protected String image;
	
	private int rank;

	public long getUserId() {
		return userId;
	}

	protected void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public int getCountry() {
		return country;
	}
	
	public String getCountryName(){
		return CountryType.getCountryType(country).toString();
	}

	protected void setCountry(int country) {
		this.country = country;
	}

	protected String getImage() {
		return image;
	}

	protected void setImage(String image) {
		this.image = image;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
