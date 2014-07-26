package com.krakentouch.weChat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.krakentouch.weChat.bean.UploadMediaRet;
import com.krakentouch.weChat.db.DataBase;

public class MediaDao {
	protected Connection connection = null;
	
	/**
	 * 插入到数据库中
	 * @param uploadMedia
	 */
	public void addPicInfo(UploadMediaRet uploadMedia){
		try {
			connection = DataBase.getConn();
			
			String sql = "INSERT INTO T_MEDIA(TYPE, MEDIA_ID, CREATED_AT) values (?,?,?)";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, uploadMedia.getType());
			stmt.setString(2, uploadMedia.getMedia_id());
			stmt.setString(3, uploadMedia.getCreated_at());
			stmt.execute();
			
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/***
	 * 根据用户输入的id查询媒体的id
	 * @return
	 */
	public String selectMediaId(int CREATED_AT){
		String retStr = "";
		try {
			connection = DataBase.getConn();
			
			String sql = "SELECT TYPE, MEDIA_ID, CREATED_AT from T_MEDIA where CREATED_AT = ?";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, CREATED_AT);
			ResultSet ret = stmt.executeQuery();
			if(ret.next()){
				retStr = ret.getString("MEDIA_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retStr;
	}

	/**
	 * 初始化表格
	 */
	public void initTable() {
		try {
			connection = DataBase.getConn();
			Statement stmt = connection.createStatement();

			stmt.executeUpdate("CREATE TABLE T_MEDIA(ID INT AUTO_INCREMENT PRIMARY KEY, TYPE VARCHAR(20) , MEDIA_ID VARCHAR(150) , CREATED_AT VARCHAR(20), RES VARCHAR(50));");
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		new MediaDao().initTable();
	}

}
