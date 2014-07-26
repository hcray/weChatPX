package com.krakentouch.weChat.bean;

public class UploadMediaRet {
	/***
	 * 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图
	 */
	private String type;
	
	/**
	 * 媒体文件上传后，获取时的唯一标识
	 */
	private String media_id;
	
	/**
	 * 媒体文件上传时间戳
	 */
	private String created_at;
	
	/**
	 * 错误代码
	 */
	private String errcode;
	
	/**
	 * 错误信息
	 */
	private String errmsg;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("type:").append(type)
			.append(" media_id:").append(media_id)
			.append(" created_at:").append(created_at)
			.append(" errcode:").append(errcode)
			.append(" errmsg:").append(errmsg);
		
		return sb.toString();
	}
	
	
	
}
