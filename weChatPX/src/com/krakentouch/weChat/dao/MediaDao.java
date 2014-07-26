package com.krakentouch.weChat.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.krakentouch.weChat.db.DataBase;

public class MediaDao {
	protected Connection connection = null;

	/**
	 * 初始化表格
	 */
	public void initTable() {
		try {
			connection = DataBase.getConn();
			Statement stmt = connection.createStatement();

			stmt.executeUpdate("CREATE TABLE T_MEDIA(ID INT AUTO_INCREMENT PRIMARY KEY, TYPE VARCHAR(20) , MEDIA_ID VARCHAR(150) , CREATED_AT VARCHAR(20), RES VARCHAR(50));");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		new MediaDao().initTable();
	}

}
