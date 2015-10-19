package com.hoolai.qzone.sango.cmd;

import static com.hoolai.qzone.Global.SANGO_APP_ID;

import java.util.ArrayList;
import java.util.List;

import com.hoolai.qzone.LoginHandler;
import com.hoolai.qzone.LoginUser;
import com.hoolai.qzone.PlatformInfo;
import com.hoolai.qzone.sango.SangoCheatRunner;
import com.hoolai.qzone.sango.SangoLoginHandler;
import com.hoolai.qzone.sango.SangoLoginInfo;

public class SangoCheater {
	
	public static void main(String[] args) throws Exception {
		List<LoginUser> loginUsers = new ArrayList<LoginUser>();
		loginUsers.add(new LoginUser("qq1", "pwd1"));
		loginUsers.add(new LoginUser("qq2", "pwd2"));
		loginUsers.add(new LoginUser("qq3", "pwd3"));
		
		for(int i=0;i<loginUsers.size();i++){
			LoginUser loginUser = loginUsers.get(i);
			loginSangoAndGetUserInfo(loginUser);
			System.out.println("\n=====================  我是分隔符"+(i+1)+"   ============================\n");
		}
	}
	
	private static void loginSangoAndGetUserInfo(LoginUser loginUser){
		LoginHandler loginHanlder = new LoginHandler(loginUser);
		PlatformInfo platformInfo = loginHanlder.loginAndGetPlatformInfo(SANGO_APP_ID);
		
		SangoLoginHandler loginSangoHandler = new SangoLoginHandler(platformInfo);
		SangoLoginInfo sangoLoginInfo = loginSangoHandler.requestSangoLoginInfo();
		
		SangoCheatRunner cheatRunner = new SangoCheatRunner(loginUser, platformInfo, sangoLoginInfo);
		cheatRunner.collectUserInfo();
	}

}