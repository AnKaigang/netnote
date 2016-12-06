<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>日记管理</title>
<link rel="stylesheet" href="public/css/bootstrap.min.css">
<link href="public/css/admin-new.css" rel="stylesheet">
<script src="public/js/jquery.js"></script>


</head>
<body>

<jsp:include page="base/personal_headr.jsp"></jsp:include>
	<div id="main">

		<div id="main_body">

				<jsp:include page="base/personal_article_left.jsp"></jsp:include>
			<!--main container-->
			<div id="main_container">
			
				<jsp:include page="artical_list.jsp"></jsp:include>

			</div>

			<!--main container end-->


			<div class="clear"></div>
		</div>
	</div>

<jsp:include page="base/personal_bottom.jsp"></jsp:include>

</body>
</html>


