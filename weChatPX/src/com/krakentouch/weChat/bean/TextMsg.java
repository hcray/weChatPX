package com.krakentouch.weChat.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.krakentouch.weChat.tools.CDATAAdapter;

/***
 * 文本消息
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName> 
 * <CreateTime>1348831860</CreateTime>
 * <MsgType><![CDATA[text]]></MsgType>
 * <Content><![CDATA[this is a test]]></Content>
 * <MsgId>1234567890123456</MsgId>
 * </xml>
 * @author CYY
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class TextMsg extends BaseMsg{
	
	/**
	 *  文本消息内容
	 */
	@XmlElement(name = "Content", required = true)
	@XmlJavaTypeAdapter(CDATAAdapter.class)
	private String Content;
	
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
