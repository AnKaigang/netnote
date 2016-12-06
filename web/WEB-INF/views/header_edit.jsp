<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改头像</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script src="public/js/jquery-1.11.0.min.js"></script>
<script src="public/js/jquery.form.js"></script>


<script type="text/javascript">
	function uploadHeader() {
		$("#headImgForm")
				.ajaxSubmit(
						{
							type : "post",
							url : "editHeadImg",
							dataType : "text",
							success : function(data) {
								alert(data);
								$("#showHeadImage")
										.html(
												"<img src='headImage/"+data+"'width='150px' height='100px'/>");
												
								$("#save").html(" <a href='editUserHeader?imgpath=headImage/"+data+"' ><input type='button' value='保存'/></a>");

							}
						});
	}
</script>


</head>

<body>
	<br>



	<form id="headImgForm">
		<div style="width:517px;height:187px;">
			修改头像<br>
			<div style="margin-top:20px;">
				<input type="file" name="uploadFile" id="" />
			</div>
			<div style="margin-top:20px;">
				<input type="button" value="上传图片" id="" onclick="uploadHeader()" /><br>
			</div>
			<div id="showHeadImage" style="margin-left:350px;margin-top:-80px;">
			</div>
			<br />
		</div>
	</form>
	<div id="save"></div>
	<!-- <a href="editUserHeader?imgpath=" ><input type="button" value="保存"/></a> -->


</body>
</html>
