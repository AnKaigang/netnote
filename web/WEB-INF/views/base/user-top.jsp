<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ddiary-top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <style>
    .a{
    	font-size:30px;
    }
  	.chara1{
  	font-size:15px;
  	background-color:#90bcff;
  	width:600px;
  	}
  	.chara1 td{
  		text-align:center;
  	}
  	a:link{
  		color:#005799:
  		text-decoration:none;
  	}
  	a:visited{
  		color:#000000;
  		text-decoration:none;
  	}
  	a:hover{
  		color:#FFFF00;
  		text-decoration:underline;
  	}
  	a:active{
  		color:#FF0000;
  		text-decoration:underline;
  	}
  </style>
  
  <body>
  <table class="a">导航栏
  </table>
    <table class="chara1" style="height: 49px; width: 100%; ">
    	<tr>
    		<td><a href="">博客首页</a></td>
    		<td><a href="">编写新日记</a></td>
    		<td><a href="">个人首页</a></td>
    		<td><a href="">草稿箱</a></td>
    		<td><a href="">订阅</a></td>
    		<td><a href="">联系</a></td>
    	</tr>
    </table>
  </body>
</html>
