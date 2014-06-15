package com.krakentouch.weChat.bean;

/***
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>1357290913</CreateTime>
 * <MsgType><![CDATA[video]]></MsgType>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
 * <MsgId>1234567890123456</MsgId>
 * </xml>
 * @author CYY
 *
 */
public class VideoMsg extends BaseMsg{
	
	/**
	 *  视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String MediaId;
	
	/**
	 *  视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String ThumbMediaId;
	
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
}
