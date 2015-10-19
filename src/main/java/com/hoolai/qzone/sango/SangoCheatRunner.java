package com.hoolai.qzone.sango;

import java.util.concurrent.TimeUnit;

import com.hoolai.qzone.LoginUser;
import com.hoolai.qzone.PlatformInfo;
import com.hoolai.qzone.amf.HoolaiAMFConnection;
import com.hoolai.qzone.sango.vo.VoCollector;
import com.hoolai.util.json.JSONUtils;

import flex.messaging.io.amf.ASObject;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;

public class SangoCheatRunner implements Runnable{
	
	private static final String SANGO_URL = "http://sango.qzone.qzoneapp.com/messagebroker/amf";
	public static final String SANGO_AUTH_KEY = "authSecret";
	private static final String SANGO_IDENTIFYING_CODE_KEY = "idf_c_key";

	private final PlatformInfo platformInfo;
	private final String requestInfoStr;
	private final SangoLoginInfo sangoLoginInfo;
	
	private final HoolaiAMFConnection connection;
	private final VoCollector voCollector;
	
	private boolean addUserInfoToken;
	
	public SangoCheatRunner(LoginUser loginUser, PlatformInfo platformInfo, SangoLoginInfo sangoLoginInfo) {
		this.platformInfo = platformInfo;
		this.requestInfoStr = JSONUtils.toJSONwithOutNullProp(platformInfo);
		this.sangoLoginInfo = sangoLoginInfo;
		this.connection = initSangoAmfConnection();
		this.voCollector = new VoCollector(loginUser, sangoLoginInfo.getSangoServerUrl());
	}
	
	private HoolaiAMFConnection initSangoAmfConnection() {
		HoolaiAMFConnection connection = new HoolaiAMFConnection();
		try {
			connection.connect(SANGO_URL);
		} catch (ClientStatusException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return connection;
	}

	public void collectUserInfo(){
		Object result = call("UserService.getUserInfo", "0", platformInfo.getOpenid(), requestInfoStr, sangoLoginInfo.getToken(), false);
		if(isCallSuccess(result)){
			voCollector.collectUserInfo(result);
		}
		
		result = call("FriendService.getUserFriends", sangoLoginInfo.getUserIdStr(), false);
		if(isCallSuccess(result)){
			voCollector.collectFriendList(result);
		}
	}
	
	private void sleep4CheckAndLockOperation(){
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			
		}
	}
	
	private Object call(String command, Object... arguments){
		ifNeedAddUserInfoToken(command);
		Object result = null;
		try {
			result = connection.call(command, arguments);
		} catch(Exception e){
			e.printStackTrace();
			closeConnection();
			throw new RuntimeException(e);
		}
		sleep4CheckAndLockOperation();
		return result;
	}
	
	/**
	 * 三国的机制是请求index.jsp的时候会获得一个Md5加密的token，是在TokenProducer类里面写死的，是getUserInfo的token验证<br/>
	 * 但是getUserInfo之后会返回一个authSecret来验证AMF通信的，即除了getUserInfo外的AMF通信，都需要把authSecret加入AMF Header
	 * @param command
	 */
	private void ifNeedAddUserInfoToken(String command){
		if(command.equals("UserService.getUserInfo")){
			return;
		}
		if(addUserInfoToken){
			return;
		}
		connection.addAmfHeader(SANGO_AUTH_KEY, true, voCollector.getUserInfoToken());
		connection.addAmfHeader(SANGO_IDENTIFYING_CODE_KEY, true, "");
		addUserInfoToken = true;
	}
	
	private boolean isCallSuccess(Object result){
		return result != null && Integer.parseInt(((ASObject)result).get("status").toString()) == 2;
	}
	
	@Override
	public void run() {
		try{
			doCheat();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 把需要调用的胡莱三国Service接口一个个按顺序去调用执行即可 */
	public void doCheat(){
		
	}
	
	public void closeConnection(){
		connection.close();
	}
	
	public VoCollector getVoCollector() {
		return voCollector;
	}
	
}
