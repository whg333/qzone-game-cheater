package com.hoolai.qzone.sango;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hoolai.qzone.PlatformInfo;

public class SangoLoginHandler {

	private final PlatformInfo platformInfo;
	
	public SangoLoginHandler(PlatformInfo platformInfo){
		this.platformInfo = platformInfo;
	}
	
	public SangoLoginInfo requestSangoLoginInfo(){
		String sangoUrl = "http://sango.qzone.qzoneapp.com/index.jsp?qz_height=900&qz_width=760" +
			"&openid="+platformInfo.getOpenid()+"&openkey="+platformInfo.getOpenkey()+
			"&pf=qzone&pfkey="+platformInfo.getPfkey()+"&qz_ver=8&appcanvas=1&qz_style=25&params=";
		//System.out.println(sangoUrl);
		
		//在浏览器模式为IE8下sangoUrl页面有JS执行报错，其实要获取token和userId可以不必执行JS
		//但设置浏览器模式为IE9~IE11或者火狐系列，便可正确解析sangoUrl页面上的JS
		WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		HtmlPage page = tryGetPage(webClient, sangoUrl);
		String token = page.getHtmlElementById("token").getAttribute("value");
		String userIdStr = page.getHtmlElementById("userId").getAttribute("value");
		//System.out.println("token="+token+",userIdStr="+userIdStr);
		return new SangoLoginInfo(token, userIdStr, sangoUrl);
	}
	
	private HtmlPage tryGetPage(WebClient webClient, String url){
		try{
			return webClient.getPage(url);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		throw new NullPointerException("open url page error!url="+url);
	}
	
}
