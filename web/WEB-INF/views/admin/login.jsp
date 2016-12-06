<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath }/public/css/main.css" rel="stylesheet" type="text/css"></link>

<title>欢迎登录后台管理系统</title>
<script language="JavaScript" src="${pageContext.request.contextPath }/public/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/public/js/base64encode.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/public/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/public/js/cloud.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/public/js/sha1.js" type="text/javascript"></script>
<script type="text/javascript">
function changeCode(e){
	e.src="${pageContext.request.contextPath }/admin//verifyCode?n="+Math.random();
}
</script>
</head>

<body class="loginBody">

    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  
<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div> 
    <div class   ="loginbody">
    <span class="systemlogo" style="color:#FFFFFF;font-size:40px">日记云管理系统登录</span> 
    <div class="loginbox loginbox2">
    <ul>
    <li><input name="" type="text" class="loginuser" value="" tip="用户名不能为空!" placeholder="请输入登录账号"/></li>
    <li><input name="" type="password" class="loginpwd" value="" tip="请输入登录密码!" placeholder="请输入登录密码"/></li>
    <li class="yzm">
    <span><input name="" type="text" value="" tip="请输入验证码!"  placeholder="验证码"/></span>
    <cite style="background: #fff;">
	<img src="${pageContext.request.contextPath }/admin/verifyCode?n=Math.random();" width="90" height="40" id="checkCode" onclick="changeCode(this);" style="padding-top:1px;cursor: pointer;"></img>
	</cite>
    </li>
    <li>
    <input name="" type="button" class="loginbtn" value="登录" />
    <label>
<!--     <input name="" type="checkbox" value="" />记住密码 -->
    </label><label>
    <a href="#">忘记密码？</a>
    </label></li>
    <li class="loginTip"></li>
    </ul>
    </div>
    </div>
</body>

</html>
