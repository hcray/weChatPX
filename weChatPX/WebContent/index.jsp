<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片上传</title>
</head>
<body>
	<form action="<%=path %>/uploadAction.action" enctype="multipart/form-data" method="post">
		<div>
			<label for="id_uploadfile">图片上传</label>
			<input type="file" name="uploadfile" id="id_uploadifile"/>
		</div>
		<input type="submit" value="上传">
	</form>
</body>
</html>