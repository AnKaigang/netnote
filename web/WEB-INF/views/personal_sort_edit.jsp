<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>分类编辑</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


<link rel="stylesheet" href="public/css/bootstrap.min.css">
<link href="public/css/admin-new.css" rel="stylesheet">
<script src="public/js/jquery.js"></script>

</head>

<body>

	<jsp:include page="base/personal_headr.jsp"></jsp:include>
	<div id="main">

		<div id="main_body">
		<div style="float:left;margin-left:-20px;margin-top:-20px">
			<jsp:include page="base/personal_article_left.jsp"></jsp:include>
		</div>
			<div id="main_container">
				<jsp:include page="sort_edit.jsp"></jsp:include>
			</div>
		</div>
		<div class="clear"></div>
	</div>
			<jsp:include page="base/personal_bottom.jsp"></jsp:include>
</body>
</html>
