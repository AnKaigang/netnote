<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link href="${pageContext.request.contextPath }/public/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath }/public/css/iconfont.css" rel="stylesheet" type="text/css"></link>
	<link href="${pageContext.request.contextPath }/public/css/CustomStyle.css" rel="stylesheet/less" type="text/css" />
<title>云日志管理系统</title>
	<script language="JavaScript" src="${pageContext.request.contextPath }/public/js/jquery.js"></script>
	<script language="JavaScript" src="${pageContext.request.contextPath }/public/js/less.min.js"></script>
	<script language="JavaScript" src="${pageContext.request.contextPath }/public/js/venderEffect.js"></script>
	<script language="JavaScript" src="${pageContext.request.contextPath }/public/js/jquery.cookie.js"></script>
	<script language="JavaScript" src="${pageContext.request.contextPath }/public/js/Jsformenu.js"></script>
	
</head>  
<body>
<div class="topMenuDivFrame">
    <div class="topleft">
        <a href="${pageContext.request.contextPath }/admin/login" style="font-size:30px;color:#FFFFFF;" target="_parent">日志云管理系统</a>
    </div>
    <ul class="nav">
    <c:set var="firstId" value="0"></c:set>
        <c:forEach items = "${list1 }" var = "e" varStatus="s">
        	<li>
        		<c:if test="${not empty e.childMenu }">
	        	<div class = "towleverlmenu">
	        		<c:forEach items = "${e.childMenu }" var = "e2" varStatus="status">
		        		<c:if test="${status.first}">
	        			<c:set var="firstId" value="${e2.id }"></c:set>
	        			</c:if>
	        			<a href = 'javascript:;' id="${e2.id }">${e2.name }</a>
	        		</c:forEach>
	        	</div>
	        	</c:if>
	        	<a href="javascript:;" mut="top" add="${s.index+1 }" val="${e.id }"><img src="${pageContext.request.contextPath }/${e.image }" height="45" width="45"  title="${e.name }"/><h2>${e.name }</h2></a>
	        </li>
        </c:forEach>
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
    </ul>
    <div class="topright">
        <ul>
            <li><span><img src="${pageContext.request.contextPath }/public/img/help.png" title="帮助" class="helpimg"/></span><a href="#">帮助</a></li>
            <li><a href="#">关于</a></li>
            <li><a href="${pageContext.request.contextPath }/admin/quit" target="_parent">退出</a></li>
        </ul>

        <div class="user">
            <span>${name }</span>
        </div>
    </div>
</div>
<div class="leftMenuDivFrame">
	<div class="gninfo minfo">
		<span></span>
		<ins>功能菜单</ins>
	</div>
	<div class="textinfo minfo">
		<span>
			<img src="${pageContext.request.contextPath }/public/img/leftico01.png">
		</span>
		<ins></ins>
	</div>
    <nav class="menuarr muarrowUp Hui-iconfont">&#xe6d6;</nav>
    <ul id="threeMenu">
		<li></li>
	</ul>
    <nav class="menuarr muarrowDown Hui-iconfont">&#xe6d5;</nav>
</div>
<div class="indexDivFrame">
	<iframe src="index" width="101%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes"></iframe>
</div>
<div class="FooterDivFrame">
    <i>版权所有 2016 diaryYun.org</i>
</div>
<!-- <script type="text/javascript">
	$(function(){	
		getSubMenu("${firstId}");
	});
</script> -->
</body>
</html>