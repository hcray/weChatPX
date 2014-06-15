package com.krakentouch.weChat.tools;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpKit {
	/***
	 * 获取access_token http请求方式: GET
	 * 
	 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&
	 * appid=APPID&secret=APPSECRET
	 * 
	 * @param grant_type
	 *            是 获取access_token填写client_credential
	 * @param appid
	 *            是 第三方用户唯一凭证
	 * @param secret
	 *            是 第三方用户唯一凭证密钥，即appsecret
	 * @return {"access_token":"ACCESS_TOKEN","expires_in":7200} access_token
	 *         获取到的凭证 expires_in 凭证有效时间，单位：秒
	 *         错误：{"errcode":40013,"errmsg":"invalid appid"}
	 */
	public static String getAccessToken(String grant_type, String appid,
			String secret) {
		String retStr = "";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		StringBuffer urlBuffer = new StringBuffer();
		
		urlBuffer.append(weChatConstants.access_token_url)
		.append("grant_type=").append(grant_type)
		.append("&appid=").append(appid)
		.append("&secret=").append(secret);
		
		String url = urlBuffer.toString();
		
		HttpGet httpget = new HttpGet(url);
		
		CloseableHttpResponse response = null;;
		try {
			response = httpclient.execute(httpget);
			
			HttpEntity entity = response.getEntity();
			String charset = "UTF-8"; 
			String responseStr = EntityUtils.toString(entity, charset);
			
			JSONObject jsonObj = JSONObject.fromObject(responseStr);
			
			retStr = jsonObj.getString("access_token");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != response){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		return retStr;
	}
	
	/***
	 * 返回用户基本信息
	 * @param access_token 调用接口凭证
	 * @param openid 普通用户的标识，对当前公众号唯一
	 * @param lang 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 * @return
	 */
	public static String getUserInfo(String access_token, String openid,
			String lang) {
		String retStr = "";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		StringBuffer urlBuffer = new StringBuffer();
		//access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
		urlBuffer.append(weChatConstants.getUserInfoUrl)
		.append("access_token=").append(access_token)
		.append("&openid=").append(openid)
		.append("&lang=").append(lang);
		
		String url = urlBuffer.toString();
		
		HttpGet httpget = new HttpGet(url);
		
		CloseableHttpResponse response = null;;
		try {
			response = httpclient.execute(httpget);
			
			HttpEntity entity = response.getEntity();
			String charset = "UTF-8"; 
			retStr = EntityUtils.toString(entity, charset);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != response){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		return retStr;
	}
	
	public static void main(String[] args) {
		System.out.println(getAccessToken("client_credential","wx7b255430c452b80f","ce56c3b2bdfe9eecbcc9ce8a29ea5db5"));
	}
	
}
