<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="hhd.restful.bean.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<style type="text/css" id="38401460540"></style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=1">
<title>修改资料</title>


<link href="http://static.cnblogs.com/favicon.ico" rel="shortcut icon">
<link href="public/css/home_mvc.css" rel="stylesheet">


<link href="public/css/settings.css" rel="Stylesheet" type="text/css">

<script async="" src="public/js/analytics.js"></script>
<script type="text/javascript" src="public/js/jquery.js"></script>
<script type="text/javascript" src="public/js/common.js"></script>
<script src="public/js/home_mvc.js"></script>


<script src="public/js/jsencrypt.min.js"></script>
<script type="text/javascript">
	function charEscape(c) {
		c = c.replace(/"/g, '\\"');
		c = c.replace(/'/g, "\\'");
		return c;
	}
	function loginNameToggle() {
		$("#loginName_display_block").toggle();
		$("#loginName_edit_block").toggle();
		$("#txt_loginName").val('');
		$("#tip_loginName").html('');
	}
	function passwordToggle() {
		$("#password_display_block").toggle();
		$("#password_edit_block").toggle();
		$("#txt_oldpwd").val('');
		$("#txt_newpwd").val('');
		$("#txt_confirmpwd").val('');
		$("#tip_password").html('');
	}
	function DisplayNameToggle() {
		$("#displayName_display_block").toggle();
		$("#displayName_edit_block").toggle();
		$("#txt_displayName").val('');
		$("#tip_displayName").html('');
	}
	function EmailToggle() {
		$("#email_display_block").toggle();
		$("#email_edit_block").toggle();
		$("#txt_email").val('');
		$("#tip_email").html('');
	}
	function ChangeLoginName() {//geng xin account
		var value = $("#txt_loginName").val();
		if (value == '') {
			$("#tip_loginName").html('请输入新登录用户名');
			return;
		}
		var oldLoginName = $('#loginName_display_block div').html();
		if (value == oldLoginName) {
			$("#tip_loginName").html('新登录用户名不能与原登录用户名相同');
			return;
		}
		if (value.length < 2) {
			$("#tip_loginName").html('登录用户名不能少于2个字符');
			return;
		}
		if (value.length > 30) {
			$("#tip_loginName").html('登录用户名不能超过30个字符');
			return;
		}
		$("#tip_loginName").html("修改操作中，请稍候...");

			$.ajax({
				type : 'post',
				url : 'editAccount?newaccount=' + value,
				data : {},
				cache : false,
				dataType : 'json',
				success : function(data) {
					//alert(data);
					if (data == true)
					{
						$("#tip_loginName").html("修改完成");
						window.location.reload();
					}
					else
						alert("edit account error!");
				},
				error : function() {
				}
			});
	}
	
	function ChangeDisplayName() {//geng xin nicheng
		var value = $("#txt_displayName").val();
		if (value == '') {
			$("#tip_displayName").html('请输入新显示名称');
			return;
		}
		var oldDisplayName = $('#displayName_display_block div').html();
		if (value == oldDisplayName) {
			$("#tip_displayName").html('新显示名称不能与原显示名称相同');
			return;
		}
		if (value.length < 2) {
			$("#tip_displayName").html('显示名称不能少于2个字符');
			return;
		}
		if (value.replace(/[^\x00-\xff]/g, 'aa').length > 20) {
			$("#tip_displayName").html('显示名称不能超过20个字符/10个中文字');
			return;
		}

		$("#tip_displayName").html("修改操作中，请稍候...");

			$.ajax({
				type : 'post',
				url : 'editUsername?newusername=' + value,
				data : {},
				cache : false,
				dataType : 'json',
				success : function(data) {
					//alert(data);
					if (data == true)
					{
						$("#tip_displayName").html("修改完成");
						window.location.reload();
					}
					else
						alert("edit name error!");
				},
				error : function() {
				}
			});
	}
	

	function ChangeEmail() {//geng gai you xiang
		var value = $("#txt_email").val();
		if (value == '') {
			$("#tip_email").html('请输入新注册邮箱地址');
			return;
		}
		var oldEmail = $('#email_display_block div').html();
		if (value == oldEmail) {
			$("#tip_email").html('新注册邮箱地址不能与原注册邮箱地址相同');
			return;
		}
		var regex = new RegExp('(\\w|\-)+@((\\w|\-)+\\.)+[a-z]{2,3}');
		if (!regex.test(value)) {
			$("#tip_email").html("注册邮件格式不正确");
		}
		var password = $('#txt_change_email_password').val();
		if (password == '') {
			$("#tip_email").html('请输入帐户密码');
			return;
		}

		$("#tip_email").html("修改操作中，请稍候...");

			$.ajax({
				type : 'post',
				url : 'editMail?newmail=' + value + '&password=' + password,
				data : {},
				cache : false,
				dataType : 'json',
				success : function(data) {
					//alert(data);
					if (data == true)
					{
						$("#tip_email").html("修改完成");
						window.location.reload();
					}
					else
						alert("密码错误!");
				},
				error : function() {
				}
			});
	}
	function ChangePwd() { //xiu gai mi ma
		var value = $("#txt_newpwd").val();
		if (value == '') {
			$("#tip_password").html('请输入密码');
			return;
		}
		if (value != $("#txt_confirmpwd").val()) {
			$("#tip_password").html('两次密码输入不一致');
			return;
		}
		var oldpwd = $("#txt_oldpwd").val();
		if (value == oldpwd) {
			$("#tip_password").html("新密码不能与旧密码相同");
			return;
		}
		$("#tip_password").html("修改操作中，请稍候...");

				$.ajax({
				type : 'post',
				url : 'editPassword?newpassword=' + value + '&oldpassword=' + oldpwd,
				data : {},
				cache : false,
				dataType : 'json',
				success : function(data) {
					//alert(data);
					if (data == true)
					{
						$("#tip_password").html("修改完成");
						window.location.reload();
					}
					else
						alert("密码错误");
				},
				error : function() {
				}
			});


	}
</script>

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

				<div class="topic_nav_block_wrapper">
					<ul class="topic_nav_block">
						<li><a href="#" class="current_nav">帐户设置</a></li>
					</ul>
					<div class="clear"></div>
				</div>

				<form id="myform">
					<div class="account_into_block">
						<h3>
							<div>
								<a href="#" onclick="loginNameToggle();return false;"
									class="gray2">修改</a>
							</div>
							登录用户名
						</h3>
						<div id="loginName_display_block">
							<div class="account_right_info">${user.account}</div>
							<span class="account_title">您的登录用户名</span><span
								id="txt_loginName_1" class="text_red"></span>
						</div>
						<div id="loginName_edit_block" class="hide account_edit_block">
							<div class="account_right_info" id="divLoginName">
								${user.account }</div>
							<div id="loginName_edit_box">
								<span class="account_title">新登录用户名：</span> <input
									id="txt_loginName" class="tb_m" type="text"> 4-30个字符<br>
								<div class="account_btn_wrapper">
									<input type="button" onclick="ChangeLoginName();" value="保存">
									<input type="button" onclick="loginNameToggle();" value="取消"
										style="margin-left: 3px;">
								</div>
							</div>
							<span id="tip_loginName" class="text_red"></span>
						</div>
					</div>

					<div class="account_into_block">
						<h3>
							<div>
								<a href="#"
									onclick="DisplayNameToggle();return false;" class="gray2">修改</a>
							</div>
							显示名称
						</h3>
						<div id="displayName_display_block">
							<div class="account_right_info">${user.name }</div>
							<span class="account_title">您的显示名称</span><span
								id="tip_displayName_1" class="text_red"></span>
						</div>
						<div id="displayName_edit_block" class="hide account_edit_block">
							<div class="account_right_info" id="divDisplayName">${user.name }</div>
							<span class="account_title">新显示名称：</span> <input
								id="txt_displayName" class="tb_m" type="text"> 2-20字符
							<div class="account_btn_wrapper">
								<input type="button" onclick="ChangeDisplayName();" value="保存"
									style="margin-left: 0;"> <input type="button"
									onclick="DisplayNameToggle();" value="取消"
									style="margin-left: 3px;"> <span id="tip_displayName"
									class="text_red"></span>
							</div>
						</div>
					</div>

					<div class="account_into_block">
						<h3>
							<div>
								<a href="#"
									onclick="passwordToggle();return false;" class="gray2">修改</a>
							</div>
							密 码
						</h3>
						<div id="password_display_block">
							<div class="account_right_info">******</div>
							<span class="account_title">您的密码</span>
						</div>
						<div id="password_edit_block" class="hide account_edit_block">
							<table>
								<tbody>
									<tr>
										<td class="account_title">旧密码：</td>
										<td><input id="txt_oldpwd" class="tb_m" type="password">
										</td>
									</tr>
									<tr>
										<td class="account_title">新密码：</td>
										<td><input id="txt_newpwd" class="tb_m" type="password">
											至8个字符，最多30个字符</td>
									</tr>
									<tr>
										<td class="account_title">确认密码：</td>
										<td><input id="txt_confirmpwd" class="tb_m"
											type="password"></td>
									</tr>
								</tbody>
							</table>
							<div class="account_btn_wrapper">
								<input type="button" onclick="ChangePwd();" value="保存"
									style="margin-left: 0;"> <input type="button"
									onclick="passwordToggle();" value="取消"
									style="margin-left: 3px;"> <br> <span
									id="tip_password" class="text_red"></span>
							</div>
						</div>
					</div>

					<div class="account_into_block">
						<h3>
							<div>
								<a href="#"
									onclick="EmailToggle();return false;" class="gray2">修改</a>
							</div>
							注册邮箱
						</h3>
						<div id="email_display_block">
							<div class="account_right_info">${user.mail }</div>
							<span class="account_title">您的注册邮箱</span>
						</div>
						<div id="email_edit_block" class="hide account_edit_block">
							<span class="account_title"> 帐户密码： </span> <input
								id="txt_change_email_password" style="width:200px;" class="tb_m"
								type="password"><br> <span class="account_title">新注册邮箱：</span>
							<input id="txt_email" class="tb_m" style="width:200px;"
								type="text">
							<div class="account_btn_wrapper">
								<input type="button" onclick="ChangeEmail();" value="保存"
									style="margin-left: 0;"> <input type="button"
									onclick="EmailToggle();" value="取消" style="margin-left: 3px;">
								<br> <span id="tip_email" class="text_red"></span>
							</div>
						</div>
					</div>
				</form>
				<a href="personal_info?userid=${user.id }"><input type="button" value="返回我的主页"/></a>
				<div class="clear"></div>
				<div id="right_sidebar"></div>
				<div class="clear"></div>
				<div class="clear"></div>
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