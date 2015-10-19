package com.hoolai.qzone.sango.vo;

public class OwnCity {
	
	private static final int NPC_ID = -100;
	private static final int ROBBER_ID = -200;

	private int id;
    private int level;
    
    private long lordId;
    private User lord;
    
    private int robberOfficerLevel;
    
    private int officerId;
	private Officer officer;
	private long defendAt;
    
    public boolean isSelfDefend() {
        return lordId == -1 && officerId > 0 && defendAt != -1;
    }
    
    public boolean isOccupied(){
        return lordId > 0 || isOccupiedByRobber() || lordId == NPC_ID;
    }
    
    public boolean isOccupiedByRobber(){
    	return lordId == ROBBER_ID;
    }
    
    public boolean isMainCity(){
    	return id == -100;
    }
    
	@Override
	public String toString() {
		return "OwnCity [id=" + id + ", level=" + level + ", lordId=" + lordId + ", " + (lord != null ? "lord=" + lord + ", " : "")
				+ "robberOfficerLevel=" + robberOfficerLevel + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getLordId() {
		return lordId;
	}

	public void setLordId(long lordId) {
		this.lordId = lordId;
	}

	public User getLord() {
		return lord;
	}

	public void setLord(User lord) {
		this.lord = lord;
	}

	public int getRobberOfficerLevel() {
		return robberOfficerLevel;
	}

	public void setRobberOfficerLevel(int robberOfficerLevel) {
		this.robberOfficerLevel = robberOfficerLevel;
	}
	
	public int getOfficerId() {
		return officerId;
	}

	public void setOfficerId(int officerId) {
		this.officerId = officerId;
	}

	public Officer getOfficer() {
		if(officer == null){
			officer = new Officer();
		}
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public long getDefendAt() {
		return defendAt;
	}

	public void setDefendAt(long defendAt) {
		this.defendAt = defendAt;
	}
	
}
