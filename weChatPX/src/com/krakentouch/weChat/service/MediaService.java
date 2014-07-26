package com.krakentouch.weChat.service;

import com.krakentouch.weChat.bean.UploadMediaRet;
import com.krakentouch.weChat.dao.MediaDao;

public class MediaService {
	private MediaDao mediaDao;
	
	/***
	 * 添加到数据库
	 * @param uploadMedia
	 */
	public void addPicInfo(UploadMediaRet uploadMedia){
		mediaDao = new MediaDao();
		mediaDao.addPicInfo(uploadMedia);
	}
	
	/**
	 * 根据创建时间查询保存微信服务器的媒体id
	 * @param CREATED_AT
	 * @return
	 */
	public String selectMediaId(int CREATED_AT){
		mediaDao = new MediaDao();
		return mediaDao.selectMediaId(CREATED_AT);
	}
	
}
