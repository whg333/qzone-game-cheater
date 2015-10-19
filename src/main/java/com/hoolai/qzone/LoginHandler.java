package com.hoolai.qzone;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.hoolai.util.json.JSONUtils;

public class LoginHandler {

	private final LoginUser loginUser;
	private final WebClient webClient;
	
	public LoginHandler(LoginUser loginUser) {
		this.loginUser = loginUser;
		this.webClient = initWebClient();
	}

	/**
	 * 登陆Qzone的浏览器模式必须在IE8的模式下，方可正确解析页面的JS，其他的浏览器模式有些方法属性不兼容
	 */
	@SuppressWarnings("deprecation")
	private WebClient initWebClient(){
		WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_8);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		return webClient;
	}
	
	public PlatformInfo loginAndGetPlatformInfo(int appId){
		long g_tk = loginQzoneAndGetGTK();
		return requestPlatformInfo(appId, g_tk);
	}
	
	private long loginQzoneAndGetGTK() {
		String loginQzoneUrl = "http://xui.ptlogin2.qq.com/cgi-bin/xlogin?"
			+ "proxy_url=http%3A//qzs.qq.com/qzone/v6/portal/proxy.html&daid=5&pt_qzone_sig=1&hide_title_bar=1&"
			+ "low_login=0&qlogin_auto_login=1&no_verifyimg=1&link_target=blank&appid=549000912&style=22&target=self&"
			+ "s_url=http%3A//qzs.qq.com/qzone/v5/loginsucc.html?para=izone&pt_qr_app=%E6%89%8B%E6%9C%BAQQ%E7%A9%BA%E9%97%B4&"
			+ "pt_qr_link=http%3A//z.qzone.com/download.html&self_regurl=http%3A//qzs.qq.com/qzone/v6/reg/index.html&"
			+ "pt_qr_help_link=http%3A//z.qzone.com/download.html";
		
		HtmlPage page = tryGetPage(loginQzoneUrl);
		HtmlTextInput InputUserName = (HtmlTextInput) page.getElementById("u");
		HtmlPasswordInput InputPassword = (HtmlPasswordInput) page.getElementById("p");
		InputUserName.setText(loginUser.getQq());
		InputPassword.setText(loginUser.getPwd());
		HtmlSubmitInput hb = (HtmlSubmitInput) page.getElementById("login_button");
		page = tryClickGetPage(hb);
		//System.out.println(page.asXml());	// 登录已经成功，可以打印页面为xml文件来判断是否登录进去
		
		//System.out.println(page.executeJavaScript("QZONE"));	//执行JS去验证所需JS变量已经存在
		if(Boolean.valueOf(page.executeJavaScript("window.hasOwnProperty('ownerProfileSummary')").getJavaScriptResult().toString())){
			String qqName = page.executeJavaScript("ownerProfileSummary[0]").getJavaScriptResult().toString();
			loginUser.setQqName(qqName);
		}
		
		long g_tk = new BigDecimal(page.executeJavaScript("QZONE.FP.getACSRFToken()").getJavaScriptResult().toString()).longValue();
		//System.out.println(page.executeJavaScript("console.info(QZFL.pluginsDefine.getACSRFToken('dfgdfg'))"));
		return g_tk;
	}
	
	@SuppressWarnings("unchecked")
	private PlatformInfo requestPlatformInfo(int appId, long g_tk) {
		String platformInfoUrl = "http://appframe.qq.com/cgi-bin/qzapps/appframe.cgi?appid="+appId+"&qz_ver=6&pf=qzone&via=QZ.MYAPP&v=1&g_tk="+g_tk;
		//System.out.println(platformInfoUrl);
		
		HtmlPage page = tryGetPage(platformInfoUrl);
		String platformInfo = page.getWebResponse().getContentAsString();
		int startIndex = platformInfo.lastIndexOf("{");
		int endIndex = platformInfo.indexOf("}");
		platformInfo = "{"+platformInfo.substring(startIndex+1, endIndex).trim()+"}";
		//System.out.println(platformInfo);
		
		HashMap<String, String> platformInfoMap = JSONUtils.fromJSON(platformInfo, HashMap.class);
		String openid = platformInfoMap.get("openid");
		String openkey = platformInfoMap.get("openkey");
		String pfkey = platformInfoMap.get("pfkey");
		return new PlatformInfo(openid, openkey, pfkey);
	}
	
	private HtmlPage tryGetPage(String url){
		try{
			return webClient.getPage(url);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		throw new NullPointerException("open url page error!url="+url);
	}
	
	private HtmlPage tryClickGetPage(HtmlSubmitInput hsi){
		try {
			return hsi.click();
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new NullPointerException("click to open url page error!url="+hsi.getBaseURI());
	}
	
}
