package com.krakentouch.weChat.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.krakentouch.weChat.tools.PropertyHandler;
import com.krakentouch.weChat.tools.Utils;

public class WechatFilter implements Filter {
	
	//private FilterConfig filterConfig;
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		System.out.println("WechatFilter()...");
		//String token = "Jocn4lqmsOenvNspJijS1Gyk98rmvxLC";
		//String token = filterConfig.getInitParameter("token").toString();
		String token = PropertyHandler.getToken();
	
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		// 验证
		if (Utils.checkSignature(token, signature, timestamp, nonce)) {
			try {
				filterChain.doFilter(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}

		/*
		System.out.println("doFilter ....");

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		Boolean isGet = request.getMethod().equals("GET");

		if (isGet) {
			// doGet(request, response);
		} else {
			doPost(request, response, filterChain);
		}

		// arg2.doFilter(req, res);
	*/
	}
	/*
	private void doPost(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws IOException {

		System.out.println("WechatFilter doPost()...");
		String token = "Jocn4lqmsOenvNspJijS1Gyk98rmvxLC";
	
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		//String echostr = request.getParameter("echostr");//
		// 验证
		if (Utils.checkSignature(token, signature, timestamp, nonce)) {
			try {
				filterChain.doFilter(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
	}

	private void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String path = request.getServletPath();
		String pathInfo = path.substring(path.lastIndexOf("/"));
		String token = "Jocn4lqmsOenvNspJijS1Gyk98rmvxLC";
		String outPut = "error";
		if (pathInfo != null) {
			// _token = pathInfo.substring(1);
			String signature = request.getParameter("signature");// 微信加密签名
			String timestamp = request.getParameter("timestamp");// 时间戳
			String nonce = request.getParameter("nonce");// 随机数
			String echostr = request.getParameter("echostr");//
			// 验证
			if (Utils.checkSignature(token, signature, timestamp, nonce)) {
				outPut = echostr;
			}
		}
		// response.getWriter().write(outPut);
	}
	*/
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("WeChatFilter init...");
		//filterConfig = config;

	}

}
