<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人首页</title>
<link type="text/css" rel="stylesheet" href="public/css/blog-common.css" />
<link id="MainCss" type="text/css" rel="stylesheet"
	href="public/css/bundle-AnotherEon001.css" />
<script type="text/javascript" src="public/js/laydate.js"></script>
<link type="text/css" rel="stylesheet" href="public/css/laydate.css" />
<script src="public/js/jquery.js" type="text/javascript"></script>
<script src="public/js/blog-common.js" type="text/javascript"></script>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("hello").click();
	};

	function delConcern(concerned_userid) { //执行删除 guan zhu 的操作       
		if (confirm("确定取消关注吗？")) {
			//alert(id);
			$.ajax({
				type : 'post',
				url : 'deleteConcern?concerned_user_id=' + concerned_userid,
				data : {},
				cache : false,
				dataType : 'json',
				success : function(data) {
					//alert(data);
					if (data == true)
						window.location.reload();
					else
						alert("delete concern error!");
				},
				error : function() {
				}
			});

		} else
			return false;
	}

	function addConcern(concerned_userid) { //执行  guan zhu 的操作       
		if (confirm("确定添加关注吗？")) {
			//alert(id);
			$.ajax({
				type : 'post',
				url : 'addConcern?concerned_user_id=' + concerned_userid,
				data : {},
				cache : false,
				dataType : 'json',
				success : function(data) {
					//alert(data);
					if (data == true)
						window.location.reload();
					else
						alert("add concern error!");
				},
				error : function() {
				}
			});

		} else
			return false;
	}
</script>
<%
	Date date = new Date();
	request.setAttribute("date", date);
%>
</head>
<body>
	<div id="wrapper" style="width:30%">
		<div id="main_container">
			<div id="leftmenu">
				<h3>公告</h3>
				<br>
				<div id="blog-news">
					<div id="blog-news">

						<p>
							<img style="z-index: 0; width: 100px; float: left; height: 100px"
								src="${user.header }" alt="${user.name }的头像" class="img_avatar"><br>
						</p>

					</div>

					<div id="blog-news">
						<div id="profile_block">
							<c:if test="${concernsignleft=='0'}">
							昵称：<a href="personal_info?userid=${user.id }">${user.name}</a>
								<br>
							年龄：<a href="personal_info?userid=${user.id }">${days}</a>
								<br>
							 积分：<a href="personal_info?userid=${user.id }">${user.point }</a>
								<br>
							等级： <a href="personal_info?userid=${user.id }">${user.rank }</a>
								<br>
							粉丝： <a href="personal_info?userid=${user.id }">${fn:length(concernme)}</a>
								<br>
							关注： <a href="personal_info?userid=${user.id }">${fn:length(myconcern)}</a>
								<br>
								<a href="login">+加关注</a>
							</c:if>
							<c:if test="${concernsignleft=='1'}">
							昵称：<a href="personal_info?userid=${user.id }">${user.name}</a>
								<br>
							年龄：<a href="personal_info?userid=${user.id }">${days}</a>
								<br>
							 积分：<a href="personal_info?userid=${user.id }">${user.point }</a>
								<br>
							等级： <a href="personal_info?userid=${user.id }">${user.rank }</a>
								<br>
							粉丝： <a href="personal_info?userid=${user.id }">${fn:length(concernme)}</a>
								<br>
							关注： <a href="personal_info?userid=${user.id }">${fn:length(myconcern)}</a>
								<br>
							</c:if>
							<c:if test="${concernsignleft=='2'}">
							昵称：<a href="personal_info?userid=${user.id }">${user.name}</a>
								<br>
							年龄：<a href="personal_info?userid=${user.id }">${days}</a>
								<br>
							 积分：<a href="personal_info?userid=${user.id }">${user.point }</a>
								<br>
							等级： <a href="personal_info?userid=${user.id }">${user.rank }</a>
								<br>
							粉丝： <a href="personal_info?userid=${user.id }">${fn:length(concernme)}</a>
								<br>
							关注： <a href="personal_info?userid=${user.id }">${fn:length(myconcern)}</a>
								<br>
								<div id="p_b_follow">
									已关注 <a href="javascript:void(0);"
										onclick="delConcern('${user.id}')">-取消</a>
								</div>
							</c:if>
							<c:if test="${concernsignleft=='3'}">
							昵称：<a href="personal_info?userid=${user.id }">${user.name}</a>
								<br>
							年龄：<a href="personal_info?userid=${user.id }">${days}</a>
								<br>
							 积分：<a href="personal_info?userid=${user.id }">${user.point }</a>
								<br>
							等级： <a href="personal_info?userid=${user.id }">${user.rank }</a>
								<br>
							粉丝： <a href="personal_info?userid=${user.id }">${fn:length(concernme)}</a>
								<br>
							关注： <a href="personal_info?userid=${user.id }">${fn:length(myconcern)}</a>
								<br>
								<div id="p_b_follow">
									<a href="javascript:void(0);"
										onclick="addConcern('${user.id}')">+加关注</a>
								</div>
							</c:if>

						</div>
					</div>
				</div>

<%-- 				<div style="margin-left:50px">
					<input id="hello" type="hidden" class="laydate-icon"
						onclick="laydate()"
						value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
				</div> --%>
			</div>
		</div>


	</div>
</html>