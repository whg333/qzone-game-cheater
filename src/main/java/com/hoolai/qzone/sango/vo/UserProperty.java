package com.hoolai.qzone.sango.vo;

public class UserProperty {

	/** 用户的等级 */
	private int rank;

	/** 经验 */
	private int fame;

	/** 黄金数 */
	private int gold;

	/** 钻石 */
	private int diamond;

	/** 功勋 */
	private int honor;

	@Override
	public String toString() {
		return "UserProperty [rank=" + rank + ", fame=" + fame + ", gold=" + gold + ", diamond=" + diamond + ", honor=" + honor + "]";
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getFame() {
		return fame;
	}

	public void setFame(int fame) {
		this.fame = fame;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getDiamond() {
		return diamond;
	}

	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}

	public int getHonor() {
		return honor;
	}

	public void setHonor(int honor) {
		this.honor = honor;
	}

}
