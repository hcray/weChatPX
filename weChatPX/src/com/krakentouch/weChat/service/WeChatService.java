package com.krakentouch.weChat.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.dom4j.DocumentException;

import com.krakentouch.weChat.bean.ImageMsg;
import com.krakentouch.weChat.bean.TextMsg;
import com.krakentouch.weChat.tools.HttpKit;
import com.krakentouch.weChat.tools.JaxbUtil;
import com.krakentouch.weChat.tools.TokenHandler;
import com.krakentouch.weChat.tools.Utils;

public class WeChatService {
	
	/**
	 * 执行消息
	 * @param message
	 * @return
	 */
	public String doCommand(String message){
		String ret = "";
		try {
			Map<String,String> msMap = Utils.parseCommand(message);
			if(msMap.containsKey("MsgType")){
				String msgType = msMap.get("MsgType");
				//文本消息
				if("image".equals(msgType)){
					//文本的内容
					//String content = msMap.get("Content").trim();
					
					String fromUserName = msMap.get("ToUserName");
					String toUserName = msMap.get("FromUserName");
					String mediaId = msMap.get("MediaId");
					
					//图片消息
					ImageMsg imageMsg = new ImageMsg();
					imageMsg.setToUserName(toUserName);
					imageMsg.setFromUserName(fromUserName);
					imageMsg.setMsgType("image");
					
					Calendar calendar = Calendar.getInstance();
					long createTime = calendar.getTimeInMillis();
					imageMsg.setCreateTime(createTime);
					
					Set<String> set = new HashSet<String>();
					set.add(mediaId);
					imageMsg.setMediaId(set);
					
					ret = JaxbUtil.convertToXml(imageMsg, "utf-8");
				}else{
					//文本的内容
					//String content = msMap.get("Content").trim();
					
					String fromUserName = msMap.get("FromUserName");
					TokenHandler tokenHandler = new TokenHandler();
					String accessToken = tokenHandler.getToke();
					String userInfo = HttpKit.getUserInfo(accessToken, fromUserName, "zh_CN");
					System.out.println("userinfo: "+ userInfo);
					JSONObject userInfoJSON = JSONObject.fromObject(userInfo);
					
					String userName = userInfoJSON.getString("nickname");
					
					String toUserName = msMap.get("ToUserName");
					
					//文本消息
					TextMsg textMsg = new TextMsg();
					textMsg.setToUserName(fromUserName);
					textMsg.setFromUserName(toUserName);
					textMsg.setMsgType("text");
					textMsg.setContent(userName + "，您好!我是智能机器人小灰灰，请问有什么可以帮助您的吗？");
					
					Calendar calendar = Calendar.getInstance();
					long createTime = calendar.getTimeInMillis();
					
					textMsg.setCreateTime(createTime);
					
					ret = JaxbUtil.convertToXml(textMsg, "utf-8");
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return ret.toString();
	}
}
