package com.krakentouch.weChat.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;

import com.krakentouch.weChat.tools.TokenHandler;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 上传多媒体文件
 */
public class UploadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	//myFile属性用来封装上传的文件   
	private File uploadfile;
	  
	//myFileContentType属性用来封装上传文件的类型 
	private String uploadfileContentType;  
	
	//myFileFileName属性用来封装上传文件的文件名
	private String uploadfileFileName;

	@Override
	public String execute() throws Exception {
		  // 设置上传文件目录  
        String uploadPath = ServletActionContext.getServletContext()  
                .getRealPath("upload"); 
		
	    //得到上传的文件
		InputStream is = new FileInputStream(uploadfile);  
		  
		//上传的文件
		File toFile = new File(uploadPath, this.getUploadfileFileName());  
		  
		//输入流
		OutputStream os = new FileOutputStream(toFile); 
		
		//设置缓存
		byte[] buffer = new byte[1024];  
		
		int length = 0;  
		
		//输出到指定路径
		while ((length = is.read(buffer)) > 0) {  
		    os.write(buffer, 0, length);  
		} 
		//关闭输入流
		is.close();  
		//刷新  
		os.flush();
		//关闭输出流
		os.close();
		
		HttpClient httpclient = new DefaultHttpClient();
		
		TokenHandler tokenHandler = new TokenHandler();
		String accessToken = tokenHandler.getToke();
		
		String postUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+accessToken+"&type=image";
		//请求处理页面
		HttpPost httppost = new HttpPost(postUrl);
		//
		File uploadFile = new File(uploadPath, this.getUploadfileFileName());
		//创建待处理的文件
		FileBody file = new FileBody(uploadFile);
		
		//对请求的表单域进行填充
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("file", file);
		//设置请求
		httppost.setEntity(reqEntity);
		//执行
		HttpResponse responseRet = httpclient.execute(httppost);
		if(HttpStatus.SC_OK == responseRet.getStatusLine().getStatusCode()){
			HttpEntity entity = responseRet.getEntity();
			//显示内容
			if (entity != null) {
				System.out.println(EntityUtils.toString(entity));
			}
			if (entity != null) {
				entity.consumeContent();
			}
		}
		
		return "success";
		
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

}
