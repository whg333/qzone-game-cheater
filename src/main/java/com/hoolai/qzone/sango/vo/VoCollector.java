package com.hoolai.qzone.sango.vo;

import static com.hoolai.qzone.sango.SangoCheatRunner.SANGO_AUTH_KEY;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoolai.qzone.LoginUser;

import flex.messaging.io.amf.ASObject;

public class VoCollector {

	private String userInfoToken;
	private final LoginUser loginUser;
	
	private final String sangoServerUrl;
	
	private final User user = new User();
	private final Barrack barrack = new Barrack();
	
	private final List<Long> friendIds = new ArrayList<Long>();
	private final List<Friend> friendList = new ArrayList<Friend>();
	
	private final List<Officer> officerList = new ArrayList<Officer>();
	private final List<ItemBag> itemBagList = new ArrayList<ItemBag>();
	
	private final OwnCity mainCity = new OwnCity();
	private final List<OwnCity> auxiliaryCityList = new ArrayList<OwnCity>();
//	private final List<OwnCity> subCityList = new ArrayList<OwnCity>();
	
	public VoCollector(LoginUser loginUser, String sangoServerUrl){
		this.loginUser = loginUser;
		this.sangoServerUrl = sangoServerUrl;
	}
	
	public void collectUserInfo(Object userInfo){
		ASObjectProperty userInfoAsop = new ASObjectProperty((ASObject)userInfo);
		userInfoToken = userInfoAsop.getStr(SANGO_AUTH_KEY);
		user.setZiJingZuan(userInfoAsop.getInt("ziJingZuan"));
		collectUser(userInfoAsop.getAsop("user"), user);
		collectBarrack(userInfoAsop.getAsop("barrack"));
		collectFirendIds(userInfoAsop.get("friendIds"));
		collectOfficerList(userInfoAsop.get("officerList"));
		collectItemBagList(userInfoAsop.get("itemList"));
		collectCityInfo(userInfoAsop);
		//printUserInfo();
	}

	private void collectUser(ASObjectProperty asoUser, User user){
		user.setName(asoUser.getStr("name"));
		user.setSex(asoUser.getInt("sex"));
		user.setCountry(asoUser.getInt("country"));
		user.setImage(asoUser.getStr("image"));
		user.setActiveDays(asoUser.getInt("activeDays"));
		collectUserProperty(asoUser.getAsop("userProperty"), user.getUserProperty());
	}
	
	private void collectUserProperty(ASObjectProperty asoUserProperty, UserProperty userProperty){
		userProperty.setRank(asoUserProperty.getInt("rank"));
		userProperty.setFame(asoUserProperty.getInt("fame"));
		userProperty.setGold(asoUserProperty.getInt("gold"));
		userProperty.setDiamond(asoUserProperty.getInt("diamond"));
		userProperty.setHonor(asoUserProperty.getInt("honor"));
	}
	
	private void collectBarrack(ASObjectProperty asoBarrack){
		barrack.setFootmanNum(asoBarrack.getInt("footmanNum"));
		barrack.setRiderNum(asoBarrack.getInt("riderNum"));
		barrack.setArcherNum(asoBarrack.getInt("archerNum"));
		barrack.setSpecialNum(asoBarrack.getInt("specialNum"));
	}
	
	private void collectFirendIds(Object friendIdsObj) {
		int friendIdsLength = Array.getLength(friendIdsObj);
		for(int i=0;i<friendIdsLength;i++){
			friendIds.add(new BigDecimal(Array.get(friendIdsObj, i).toString()).longValue());
		}
	}
	
	private void collectOfficerList(Object officerListObj){
		int officerListLength = Array.getLength(officerListObj);
		for(int i=0;i<officerListLength;i++){
			ASObjectProperty officerAsop = new ASObjectProperty((ASObject)Array.get(officerListObj, i));
			Officer officer = new Officer();
			collectOfficer(officerAsop, officer);
			officerList.add(officer);
		}
	}
	
	private void collectOfficer(ASObjectProperty officerAsop, Officer officer){
		officer.setId(officerAsop.getInt("id"));
		officer.setName(officerAsop.getStr("name"));
		officer.setLevel(officerAsop.getInt("level"));
		officer.setStation(officerAsop.getInt("station"));
		officer.setEnergy(officerAsop.getInt("energy"));
		if(officer.isTakeSoldier()){
			officer.setFootmanNum(officerAsop.getInt("footmanNum"));
			officer.setRiderNum(officerAsop.getInt("riderNum"));
			officer.setArcherNum(officerAsop.getInt("archerNum"));
			officer.setSpecialNum(officerAsop.getInt("specialNum"));
		}
		
		officer.setSkillNum(officerAsop.getInt("skillNum"));
		Object skills = officerAsop.get("skills");
		if(skills != null){
			int skillsLength = Array.getLength(skills);
			Integer[] skillArray = new Integer[skillsLength];
			for(int j=0;j<skillsLength;j++){
				skillArray[j] = Integer.valueOf(Array.get(skills, j).toString());
			}
			officer.setSkills(skillArray);
		}
		
		officer.setStarLevel(officerAsop.getInt("starLevel"));
		Object starSkills = officerAsop.get("starSkills");
		if(starSkills != null){
			int starSkillsLength = Array.getLength(starSkills);
			Integer[] starSkillArray = new Integer[starSkillsLength];
			for(int k=0;k<starSkillsLength;k++){
				starSkillArray[k] = Integer.valueOf(Array.get(starSkills, k).toString());
			}
			officer.setStarSkills(starSkillArray);
		}
	}
	
	private void collectItemBagList(Object itemBagListObj) {
		int itemBagListLength = Array.getLength(itemBagListObj);
		for(int i=0;i<itemBagListLength;i++){
			ASObjectProperty itemBagAsop = new ASObjectProperty((ASObject)Array.get(itemBagListObj, i));
			ItemBag itemBag = new ItemBag();
			itemBag.setItemXmlId(itemBagAsop.getInt("itemXmlId"));
			if(itemBag.isDefault()){
				continue;
			}
			itemBag.setNum(itemBagAsop.getInt("num"));
			itemBagList.add(itemBag);
		}
	}
	
	private void collectCityInfo(ASObjectProperty userInfoAsop) {
		if(userInfoAsop.contains("mainCity")){
			collectOwnCity(userInfoAsop.getAsop("mainCity"), mainCity);
		}
		if(userInfoAsop.contains("auxiliaryCityList")){
			collectAuxiliaryCityList(userInfoAsop.get("auxiliaryCityList"));
		}
//		if(userInfoAsop.contains("subCityList")){
//			collectSubCityList(userInfoAsop.get("subCityList"));
//		}
	}
	
	private void collectOwnCity(ASObjectProperty ownCityAsop, OwnCity ownCity) {
		ownCity.setId(ownCityAsop.getInt("id"));
		ownCity.setLevel(ownCityAsop.getInt("level"));
		ownCity.setLordId(ownCityAsop.getInt("lordId"));
		ownCity.setOfficerId(ownCityAsop.getInt("officerId"));
		ownCity.setDefendAt(ownCityAsop.getLong("defendAt"));
		if(ownCity.isOccupied()){
			User lord = new User();
			collectUser(ownCityAsop.getAsop("lord"), lord);
			ownCity.setLord(lord);
			if(ownCity.isOccupiedByRobber()){
				ownCity.setRobberOfficerLevel(ownCityAsop.getAsop("robber").getInt("officerLevel"));
			}else{
				collectOfficer(ownCityAsop.getAsop("officer"), ownCity.getOfficer());
			}
		}
	}
	
	private void collectAuxiliaryCityList(Object auxiliaryCityListObj) {
		int auxiliaryCityListLength = Array.getLength(auxiliaryCityListObj);
		for(int i=0;i<auxiliaryCityListLength;i++){
			ASObjectProperty auxiliaryCityAsop = new ASObjectProperty((ASObject)Array.get(auxiliaryCityListObj, i));
			OwnCity auxiliaryCity = new OwnCity();
			collectOwnCity(auxiliaryCityAsop, auxiliaryCity);
			auxiliaryCityList.add(auxiliaryCity);
		}
	}
	
//	private void collectSubCityList(Object object) {
//		
//	}

	private void printUserInfo() {
		System.out.println(user);
		System.out.println(user.getUserProperty());
		System.out.println(barrack);
		System.out.println(Arrays.toString(friendIds.toArray(new Long[0])));
		System.out.println(Arrays.toString(officerList.toArray(new Officer[0])));
		System.out.println(Arrays.toString(itemBagList.toArray(new ItemBag[0])));
		System.out.println(mainCity);
		System.out.println(Arrays.toString(auxiliaryCityList.toArray(new OwnCity[0])));
	}
	
	private void printFriendList() {
		System.out.println(Arrays.toString(friendList.toArray(new Friend[0])));
	}
	
	public List<Officer> getOfficerList() {
		return officerList;
	}
	
	public Map<Integer, Officer> getOfficerMap() {
		Map<Integer, Officer> officerMap = new HashMap<Integer, Officer>();
		for(Officer officer:officerList){
			officerMap.put(officer.getId(), officer);
		}
		return officerMap;
	}
	
	public List<OwnCity> getOwnCityList() {
		List<OwnCity> ownCityList = new ArrayList<OwnCity>();
		ownCityList.add(mainCity);
		ownCityList.addAll(auxiliaryCityList);
		return ownCityList;
	}

	public void collectFriendList(Object userFriend) {
		ASObjectProperty userFriendAsop = new ASObjectProperty((ASObject)userFriend);
		Object friends = userFriendAsop.get("friends");
		int friendListLength = Array.getLength(friends);
		for(int i=0;i<friendListLength;i++){
			ASObjectProperty friendAsop = new ASObjectProperty((ASObject)Array.get(friends, i));
			Friend friend = new Friend();
			friend.setUserId(friendAsop.getLong("userId"));
			friend.setName(friendAsop.getStr("name"));
			friend.setCountry(friendAsop.getInt("country"));
			friend.setImage(friendAsop.getStr("image"));
			friend.setRank(friendAsop.getInt("rank"));
			friendList.add(friend);
		}
		//printFriendList();
	}

	public List<Friend> getFriendList() {
		return friendList;
	}
	
	public String getUserInfoToken() {
		return userInfoToken;
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}
	
	public User getUser() {
		return user;
	}

	public Barrack getBarrack() {
		return barrack;
	}
	
	public String getSangoServerUrl() {
		return sangoServerUrl;
	}

	public List<ItemBag> getItemBagList() {
		return itemBagList;
	}
	
}
