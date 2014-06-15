package com.krakentouch.weChat.tools;

public class weChatConstants {
	/**
	 * 获取access token
	 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
	 */
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?";
	
	/**
	 * 通过OpenID来获取用户基本信息
	 * https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
	 */
	public final static String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?";
	
	/**
	 * 缓存的token名字
	 */
	public final static String accessToken = "accessToken";
}
