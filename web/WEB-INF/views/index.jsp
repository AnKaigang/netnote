<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="base/personal_headr.jsp"></jsp:include>

<!DOCTYPE html>
<html lang="zh-cn"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>日记云，您身边的日记专家,就是这么6</title>
	<link rel="shortcut icon" href="地质处图标" type="image/x-icon">
    <link rel="Stylesheet" type="text/css" href="public/css/aggsite.css">
         <link rel="Stylesheet" type="text/css" href="public/css/style.css">
    <link id="RSSLink" title="RSS" type="application/rss+xml" rel="alternate" href="http://feed.cnblogs.com/blog/sitehome/rss">    
	<script src="public/js/jquery.js" type="text/javascript"></script>
    <script src="public/js/aggsite.js" type="text/javascript"></script> 
</head>

<body >
    
<div id="wrapper">
       <jsp:include page="base/indexHeader.jsp"></jsp:include>
       
    		<jsp:include page="indexContent.jsp"></jsp:include>
            <jsp:include page="base/indexLeft.jsp"></jsp:include>
        	<jsp:include page="base/footer.jsp"></jsp:include>
</div>

</body></html>