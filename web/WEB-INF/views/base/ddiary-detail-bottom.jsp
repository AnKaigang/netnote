<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ddiary-detail-bottom.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="margin-left:20px">
     <p>发表评论</p><hr style="height: 5px; ">
    
    <p>昵称:<p>
  	<div><form action="form_action.asp" method="get">
  评论内容: <br>
  	<input type="text" name="fname" style="height: 129px; width: 367px; "/><br>
 
  <input type="submit" value="提交"/>
    <input type="submit" value="退出登录" />
</form>
<div class="post_item_foot">                    
    <a href="http://www.cnblogs.com/renzongxian/" class="lightblue">${article.addParam1 }</a> 
    发布于 ${article.date } 
    <span class="article_comment"><a href="#" title="" class="gray">
        评论(${article.commentnum})</a></span><span class="article_view"><a href="#" class="gray">阅读(${article.readnum })</a></span></div>

  </body>
</html>
