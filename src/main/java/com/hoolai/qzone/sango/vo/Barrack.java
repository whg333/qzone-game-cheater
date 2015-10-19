package com.hoolai.qzone.sango.vo;

public class Barrack {

	private int footmanNum;
    private int riderNum;
    private int archerNum;
    private int specialNum;
    
	@Override
	public String toString() {
		return "Barrack [footmanNum=" + footmanNum + ", riderNum=" + riderNum + ", archerNum=" + archerNum + ", specialNum=" + specialNum + "]";
	}
	
	public int getFootmanNum() {
		return footmanNum;
	}
	public void setFootmanNum(int footmanNum) {
		this.footmanNum = footmanNum;
	}
	public int getRiderNum() {
		return riderNum;
	}
	public void setRiderNum(int riderNum) {
		this.riderNum = riderNum;
	}
	public int getArcherNum() {
		return archerNum;
	}
	public void setArcherNum(int archerNum) {
		this.archerNum = archerNum;
	}
	public int getSpecialNum() {
		return specialNum;
	}
	public void setSpecialNum(int specialNum) {
		this.specialNum = specialNum;
	}
    
}
