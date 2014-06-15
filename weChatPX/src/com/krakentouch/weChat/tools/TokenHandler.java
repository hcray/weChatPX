package com.krakentouch.weChat.tools;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class TokenHandler {
	
	/***
	 * 从微信服务器获取accessToken,并且添加到缓存中
	 */
	public void initToken(){
		CacheManager manager = CacheManager.create();
		Cache cache = manager.getCache("tokenCache");
		String grant_type = "client_credential";
		String appid = "wx7b255430c452b80f";
		String secret = "ce56c3b2bdfe9eecbcc9ce8a29ea5db5";
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
