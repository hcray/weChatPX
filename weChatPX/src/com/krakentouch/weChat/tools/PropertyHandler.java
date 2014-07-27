package com.krakentouch.weChat.tools;

import java.util.PropertyResourceBundle;

public class PropertyHandler {
	private static PropertyResourceBundle prb = null;
	
	private static String token = null;
	
	static{
		 prb = (PropertyResourceBundle) PropertyResourceBundle.getBundle("wechat");
		 token = prb.getString("Token");
	}

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		PropertyHandler.token = token;
	}
	
	
}
