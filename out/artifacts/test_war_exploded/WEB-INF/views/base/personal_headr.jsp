<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="public/css/admin.css">

<!-- denglutou start-->
<div id="blog_top_nav_block">
	<div id="site_nav">
		<a href="index">网站首页</a> <a href="javascript:history.back(-1)">返回<<
		</a>
	</div>
	<div id="login_area" style="top:3px">
		<span id="span_userinfo"> <%
 	String userid = (String) session.getAttribute("userid");
 	if (userid == null) {
 %> <a id="lnkBlogUrl" href="login">未登录</a> <%
 	} else {
 %> <a id="lnkBlogUrl" href="personal_article_list?userid=${userid }">个人中心</a>·
			<a id="lnkBlogUrl" href="personal_info?userid=${userid }">${username}</a>
			· <a href="logout?userid=${userid }"
			onclick="return confirm('确认要退出登录吗?')">退出</a> <%
 	}
 %></span>


	</div>
	<div class="clear"></div>
</div>
<!-- denglutou end-->
