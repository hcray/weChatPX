package com.krakentouch.weChat.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.krakentouch.weChat.service.WeChatService;
import com.krakentouch.weChat.tools.PropertyHandler;
import com.krakentouch.weChat.tools.Utils;

public class WeChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private WeChatService weChatService = new WeChatService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");//
		
		//String token = "Jocn4lqmsOenvNspJijS1Gyk98rmvxLC";
		String token = PropertyHandler.getToken();
		String outPut = "";
		// 验证
		if (Utils.checkSignature(token, signature, timestamp, nonce)) {
			outPut = echostr;
		}
		response.getWriter().write(outPut);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		
		ServletInputStream in = request.getInputStream();
		String xmlMsg = Utils.inputStream2String(in);
		System.out.println("收到消息:[" + xmlMsg + "]");
		String retMessage = weChatService.doCommand(xmlMsg);
		
		System.out.println(retMessage.toString());
		response.getWriter().write(retMessage.toString());
		
		/*
		System.out.println("输入消息:[" + xmlMsg + "]");
		// String xml = WeChat.processing(xmlMsg);
		Calendar calendar = Calendar.getInstance();
		long endTime = calendar.getTimeInMillis();
		StringBuffer message = new StringBuffer();
		message.append("<xml>")
		.append("<ToUserName><![CDATA[o3swquD9_l7HV0m5CKEP1viR2Y5s]]></ToUserName>")
		.append("<FromUserName><![CDATA[gh_b7da08268238]]></FromUserName>")
		.append("<CreateTime>"+ endTime +"</CreateTime>")
		.append("<MsgType><![CDATA[text]]></MsgType>")
		.append("<Content><![CDATA[操你大爷]]></Content>")
		.append("</xml>");
		
		System.out.println(message.toString());
		response.getWriter().write(message.toString());
		 */
	}

}
