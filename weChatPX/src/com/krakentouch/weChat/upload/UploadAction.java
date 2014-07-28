package com.krakentouch.weChat.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;

import com.krakentouch.weChat.bean.UploadMediaRet;
import com.krakentouch.weChat.service.MediaService;
import com.krakentouch.weChat.tools.TokenHandler;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 上传多媒体文件
 */
public class UploadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private Map<String, Object> dataMap;

	// myFile属性用来封装上传的文件
	private File uploadfile;

	// myFileContentType属性用来封装上传文件的类型
	private String uploadfileContentType;

	// myFileFileName属性用来封装上传文件的文件名
	private String uploadfileFileName;

	@Override
	public String execute() throws Exception {
		// 设置上传文件目录
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("upload");
		dataMap = new HashMap<String, Object>(); 
		dataMap.put("ret", "0");
		dataMap.put("keyNumber", "");
		dataMap.put("msg", "fail");

		// 得到上传的文件
		InputStream is = new FileInputStream(uploadfile);

		// 上传的文件
		File toFile = new File(uploadPath, this.getUploadfileFileName());

		// 输入流
		OutputStream os = new FileOutputStream(toFile);

		// 设置缓存
		byte[] buffer = new byte[1024];

		int length = 0;

		// 输出到指定路径
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		// 关闭输入流
		is.close();
		// 刷新
		os.flush();
		// 关闭输出流
		os.close();

		CloseableHttpClient httpClient = HttpClients.createDefault();

		TokenHandler tokenHandler = new TokenHandler();
		String accessToken = tokenHandler.getToke();

		String postUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="
				+ accessToken + "&type=image";
		// 请求处理页面
		// HttpPost httppost = new HttpPost(postUrl);
		//
		File uploadFile = new File(uploadPath, this.getUploadfileFileName());

		// 把一个普通参数和文件上传给下面这个地址 是一个servlet
		HttpPost httpPost = new HttpPost(postUrl);

		FileBody bin = new FileBody(uploadFile);
		// 以浏览器兼容模式运行，防止文件名乱码。
		HttpEntity reqEntity = MultipartEntityBuilder.create()
				.addPart("multipartFile", bin).build();

		httpPost.setEntity(reqEntity);

		// 发起请求 并返回请求的响应
		CloseableHttpResponse response = httpClient.execute(httpPost);
		try {
			//System.out.println("----------------------------------------");
			// 打印响应状态
			//System.out.println(response.getStatusLine());
			// 获取响应对象
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				// 打印响应长度
//				System.out.println("Response content length: "+ resEntity.getContentLength());
				// 打印响应内容
				String retString = EntityUtils.toString(resEntity,
						Charset.forName("UTF-8"));
//				System.out.println(retString);

				JSONObject retMessage = JSONObject.fromObject(retString);
				UploadMediaRet retMessageBean = (UploadMediaRet) JSONObject
						.toBean(retMessage, UploadMediaRet.class);
				// 上传成功
				if (retMessageBean != null
						&& retMessageBean.getMedia_id() != null) {
					MediaService mediaService = new MediaService();
					boolean addRet = mediaService.addPicInfo(retMessageBean);
					if(addRet){
						dataMap.put("ret", "1");
						dataMap.put("keyNumber", retMessageBean.getCreated_at());
						dataMap.put("msg", "success");
					}
				}
			}
			// 销毁
			EntityUtils.consume(resEntity);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			response.close();
		}
		return "json";

	}

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getUploadfileContentType() {
		return uploadfileContentType;
	}

	public void setUploadfileContentType(String uploadfileContentType) {
		this.uploadfileContentType = uploadfileContentType;
	}

	public String getUploadfileFileName() {
		return uploadfileFileName;
	}

	public void setUploadfileFileName(String uploadfileFileName) {
		this.uploadfileFileName = uploadfileFileName;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

}
