package com.krakentouch.weChat.bean;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.krakentouch.weChat.tools.CDATAAdapter;

/***
 * 图片消息
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>1348831860</CreateTime>
 * <MsgType><![CDATA[image]]></MsgType>
 * <PicUrl><![CDATA[this is a url]]></PicUrl>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <MsgId>1234567890123456</MsgId>
 * </xml>
 * @author CYY
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="xml")
public class ImageMsg extends BaseMsg{

	/**
	 * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	@XmlElementWrapper(name="Image",required = true)
	@XmlElement(name = "MediaId", required = true)
	@XmlJavaTypeAdapter(CDATAAdapter.class)
	private Set<String> MediaId;

	public Set<String> getMediaId() {
		return MediaId;
	}

	public void setMediaId(Set<String> mediaId) {
		MediaId = mediaId;
	}
	
}
