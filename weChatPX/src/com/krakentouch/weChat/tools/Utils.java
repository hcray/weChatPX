package com.krakentouch.weChat.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Utils {
	
	/**
	 * 验证
	 * @param token
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static final boolean checkSignature(String token,String signature,String timestamp,String nonce){
		List<String> params = new ArrayList<String>();
		params.add(token);
		params.add(timestamp);
		params.add(nonce);
		Collections.sort(params,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String temp = params.get(0)+params.get(1)+params.get(2);
		return DigestUtils.sha1Hex(temp).equals(signature);
	}
	
	/**
	 * 读入输入流中的字符串
	 * @param in
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static final String inputStream2String(InputStream in) throws UnsupportedEncodingException, IOException{
		if(in == null)
			return "";
		
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n, "UTF-8"));
		}
		return out.toString();
	}
	
	/***
	 * 解析XML格式的命令
	 * @param commandStr
	 * @return 包含命令的map
	 * @throws DocumentException 
	 */
	public static Map<String,String> parseCommand(String commandStr) throws DocumentException{
		//LOG.debug("parseCommand(String commandStr) " + commandStr);
		Map<String,String> retMap = new HashMap<String,String>();
		Document doc = null;
		try {
			//去除首尾的空格
			commandStr = commandStr.trim();
			doc = DocumentHelper.parseText(commandStr); // 将字符串转为XML
			//LOG.debug("doc: " + doc);
			Element rootElt = doc.getRootElement(); // 获取根节点
			//LOG.debug("rootElt: " + rootElt);
			Iterator<?> it = rootElt.elementIterator();
			while(it.hasNext()){
				Element element = (Element) it.next();
				//是否该元素只含有text或是空元素
				//if(element.isTextOnly()){
					String name = element.getName();
					String value = element.getText();
					retMap.put(name, value);
				/*
				}else{
					Iterator<?> elementIt = element.elementIterator();
					while(elementIt.hasNext()){
						Element element2 = (Element) elementIt.next();
						//是否该元素只含有text或是空元素
						if(element2.isTextOnly()){
							String name = element2.getName();
							String value = element2.getText();
							retMap.put(name, value);
						}
					}
				}
				 */
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			//LOG.error(e.getMessage());
			throw e;
		}
		return retMap;
	}
	
	/***
	 * 添加前缀和后缀
	 * @param str
	 * @return
	 */
	public static String addCDATA(String str){
		String PREFIX_CDATA = "<![CDATA[";
		String SUFFIX_CDATA = "]]>";
		StringBuffer retStr = new StringBuffer();
		retStr.append(PREFIX_CDATA).append(str).append(SUFFIX_CDATA);
		return retStr.toString();
	}
}
