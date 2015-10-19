package com.hoolai.qzone.sango.vo;

import java.util.Arrays;

public class Officer {

	private int id;
	private String name;
	private int level;
	private int station;
    private int energy;
    
    private int footmanNum;
	private int riderNum;
	private int archerNum;
	private int specialNum;
	
	private int skillNum;
	private Integer[] skills;
	
	private int starLevel;
	private Integer[] starSkills;
	
	public boolean isTakeSoldier(){
		return station != 1 && station != 2;
	}
	
	public boolean isHasSkill(int skillsIndex){
		return skills.length > skillsIndex;
	}
	
	public boolean isSkillClose(int skillsIndex){
		return skillsIndex >= skillNum;
	}

	@Override
	public String toString() {
		return "Officer [id=" + id + ", " + (name != null ? "name=" + name + ", " : "") + "station=" + station + ", energy=" + energy
				+ ", footmanNum=" + footmanNum + ", riderNum=" + riderNum + ", archerNum=" + archerNum + ", specialNum=" + specialNum + ", skillNum="
				+ skillNum + ", " + (skills != null ? "skills=" + Arrays.toString(skills) + ", " : "") + "starLevel=" + starLevel + ", "
				+ (starSkills != null ? "starSkills=" + Arrays.toString(starSkills) : "") + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
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
	
	public int getSkillNum() {
		return skillNum;
	}

	public void setSkillNum(int skillNum) {
		this.skillNum = skillNum;
	}

	public Integer[] getSkills() {
		return skills;
	}

	public void setSkills(Integer[] skills) {
		this.skills = skills;
	}
	
	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public Integer[] getStarSkills() {
		return starSkills;
	}

	public void setStarSkills(Integer[] starSkills) {
		this.starSkills = starSkills;
	}
	
}
