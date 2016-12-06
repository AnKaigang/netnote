<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<style type="text/css" id="34719885514"></style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=1">
<title>个人资料</title>


<link href="http://static.cnblogs.com/favicon.ico" rel="shortcut icon">
<link href="public/css/home_mvc.css" rel="stylesheet">

<link href="public/css/user_profile.css" type="text/css"
	rel="Stylesheet">
<style type="text/css">
#main {
	margin-bottom: 20px;
	width: 770px;
}

.tr_title {
	text-align: left;
}

.user_detail_left {
	float: left;
	width: 190px;
}

.user_detail_right {
	float: left;
	width: 550px;
}

.tr_title {
	width: 90px;
}
</style>

<script async="" src="public/js/analytics.js"></script>
<script type="text/javascript" src="public/js/jquery.js"></script>
<script type="text/javascript" src="public/js/common.js"></script>
<script src="public/js/home_mvc.js"></script>

<style type="text/css">
.c_ad_block,[id^="div-gpt-ad-"],#ibanner {
	display: none !important;
	display: none
}

[id="bb9c190068b8405587e5006f905e790c"] {
	display: none;
	position: absolute;
	top: -1000000px;
	visibility: hidden
}
</style>
<script language="javascript" type="text/javascript"
	src="public/js/35ff706fd57d11c141cdefcd58d6562b.js" charset="gb2312"></script>

</head>
<body>


	<div id="top">
		<div id="top_left"></div>
	</div>
	<div class="clear"></div>
	<div id="wrapper">
		<div id="header">
			<div id="logo"></div>
			<div id="nav_block"></div>
			<div class="clear"></div>
			<div id="header_user">
				<div id="header_user_right"></div>
			</div>

			<div class="clear"></div>
		</div>
		<div id="container">
			<div id="app_bar">
				<div class="app_setting"></div>
			</div>
		</div>
		<div id="container_content">
			<div id="main">

				<div class="user_detail_left">
					<img src="${user.header }" alt="${user.name }的头像"
						class="img_avatar">
				</div>
				<div class="user_detail_right">
					<table>
						<tbody>
							<tr>
								<td><a href="personal_info?userid=${user.id }"><b>${user.name }</b></a>
									的详细资料 
								</td>
							</tr>
						</tbody>
					</table>
					<div class="data_block">
						<b>基本资料</b>
						<table>
							<tbody>
								<tr>
									<td class="tr_title">用户名：</td>
									<td>${user.name }</td>
								</tr>
								<tr>
									<td class="tr_title">邮箱：</td>
									<td>${user.mail }</td>
								</tr>
								<tr>
									<td class="tr_title">等级：</td>
									<td>${user.rank }</td>
								</tr>
								<tr>
									<td class="tr_title">积分：</td>
									<td>${user.point }</td>
								</tr>
								<tr>
									<td class="tr_title">注册日期：</td>
									<td>${user.registerdate }</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div id="right_sidebar"></div>
			<div class="clear"></div>
			<a href="personal_info?userid=${user.id }"><input type="button" value="返回我的主页"/></a>
		</div>

		<div class="clear"></div>
	</div>
	<div id="footer"></div>
	</div>
	<script>
		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-476124-14', 'auto');
		ga('send', 'pageview');
	</script>


	<script src="public/js/h.js"></script>
</body>
</html>