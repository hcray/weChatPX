package com.krakentouch.weChat.bean;

/***
 * 地址位置信息
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>1351776360</CreateTime>
 * <MsgType><![CDATA[location]]></MsgType>
 * <Location_X>23.134521</Location_X>
 * <Location_Y>113.358803</Location_Y>
 * <Scale>20</Scale>
 * <Label><![CDATA[位置信息]]></Label>
 * <MsgId>1234567890123456</MsgId>
 * </xml> 
 * @author CYY
 *
 */
public class LocationMsg extends BaseMsg{

	/**
	 * 地理位置维度
	 */
	private double Location_X;
	
	/**
	 * 地理位置经度
	 */
	private double Location_Y;
	
	/**
	 * 地图缩放大小
	 */
	private int Scale;
	
	/**
	 * 地理位置信息
	 */
	private String Label;
	
	public double getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(double location_X) {
		Location_X = location_X;
	}

	public double getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(double location_Y) {
		Location_Y = location_Y;
	}

	public int getScale() {
		return Scale;
	}

	public void setScale(int scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

}
