package com.krakentouch.weChat.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.krakentouch.weChat.tools.CDATAAdapter;



/**
 * 消息基本字段的封装
 * @author CYY
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseMsg {
	/**
	 * 开发者微信号
	 */
	@XmlElement(name = "ToUserName", required = true)
	@XmlJavaTypeAdapter(CDATAAdapter.class)
	private String ToUserName;
	
	/**
	 * 	 发送方帐号（一个OpenID）
	 */
	@XmlElement(name = "FromUserName", required = true)
	@XmlJavaTypeAdapter(CDATAAdapter.class)
	private String FromUserName;
	
	/**
	 * 	 消息创建时间 （整型）
	 */
	@XmlElement(name = "CreateTime", required = true)
	private long CreateTime;
	
	/**
	 * text
	 */
	@XmlElement(name = "MsgType", required = true)
	@XmlJavaTypeAdapter(CDATAAdapter.class)
	private String MsgType;
	
	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
