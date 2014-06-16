package com.krakentouch.weChat.tools;

import java.util.PropertyResourceBundle;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class TokenHandler {
	//用户id
	private static String appid = null;
	//用户凭证
	private static String secret = null;
	
	//静态代码块，加载配置文件
	static{
		PropertyResourceBundle prb = (PropertyResourceBundle) PropertyResourceBundle.getBundle("wechat");
		appid = prb.getString("appID");
		secret = prb.getString("appsecret");
	}
	
	/***
	 * 从微信服务器获取accessToken,并且添加到缓存中
	 */
	public void initToken(){
		CacheManager manager = CacheManager.create();
		Cache cache = manager.getCache("tokenCache");
		String grant_type = "client_credential";
		//从微信服务器获取token
		String accessToken = HttpKit.getAccessToken(grant_type, appid, secret);
		
		Element tokenElement = new  Element(weChatConstants.accessToken,accessToken);
		cache.put(tokenElement);
	}
	
	/***
	 * 从缓存中获取accessToken
	 * @return
	 */
	public String getToke(){
		CacheManager manager = CacheManager.create();
        Cache cache = manager.getCache("tokenCache");
        Element element = cache.get(weChatConstants.accessToken);
        String accessToken = element.getObjectValue().toString();
        return accessToken;
	}
}
