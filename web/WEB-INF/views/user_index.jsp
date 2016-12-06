<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%-- jsp注释 --%>
<!-- 配置JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人首页</title>
<link type="text/css" rel="stylesheet" href="public/css/blog-common.css">
<link id="MainCss" type="text/css" rel="stylesheet"
	href="public/css/bundle-AnotherEon001.css">
<script src="public/js/jquery.js" type="text/javascript"></script>
<script src="public/js/blog-common.js" type="text/javascript"></script>
</head>
<style>
.a {
	font-size: 30px;
}

.chara1 {
	font-size: 30px;
	background-color: #20375f;
	width: 600px;
}

.chara1 td {
	text-align: center;
}

.chara1 a:link {
	color: #005799:   
  		text-decoration:none;
}

.chara1 a:visited {
	color: #000000;
	text-decoration: none;
}

.chara1 a:hover {
	color: #f00;
	text-decoration: underline;
}

.chara1 a:active {
	color: #FF0000;
	text-decoration: underline;
}
</style>

<body>

<jsp:include page="base/personal_headr.jsp"></jsp:include>

	<div id="wrapper" style="margin-top:30px">
		<div id="header">
			<div id="top">
				<c:forEach items="${articles}" var="article" begin="0" end="0">
					<h1>
						<a id="Header1_HeaderTitle" class="headermaintitle" href="">${article.byzd1}</a>
					</h1>
				</c:forEach>
				<div id="subtitle"></div>

			</div>
			<table class="chara1" style="height: 49px; width: 100%; ">
				<tr>
					<td></td>
					<td></td>
					<td style="width: 180px;background-color:#36648B "><a href="index">博客首页</a></td>
					<td style="width: 180px;background-color:#36648B  "><a
						href="userindex?userid=${user.id }">个人首页</a></td>
					<td style="width: 180px; background-color:#36648B "><a href="addArtical">编写新日记</a></td>
				</tr>
			</table>


			<div id="sub">
				<div class="BlogStats">文章（${fn:length(articles)}）</div>
			</div>
		</div>
		<!-- 		<iframe src="userleft"
			style="width:25%;height:1000px;float: left;border:none;"></iframe> -->

		<jsp:include page="user_left.jsp"></jsp:include>

		<div id="main_container"
			style="width:88%;margin-top:0px;margin-left:12%">
			<div id="main_content" style="width:100%">
				<div id="content">


					<c:forEach items="${articles}" var="article">
						<c:if test="${concernsign=='0' and article.isopen=='3'}">
							<p class="date">
								<span> <span
									id="homepage1_HomePageDays_DaysList_ctl00_ImageLink"
									title="Day Archive" 
									style="display:inline-block;border-width:0px;">${fn:substring(article.date,0,10)}</span>
								</span>
							</p>

							<div class="post">
								<h2>
									<a
										id="homepage1_HomePageDays_DaysList_ctl00_DayList_TitleUrl_0"
										href="articledetail?articleid=${article.id }">${article.title }</a>
								</h2>
								<div class="postbody">
									<div class="c_b_p_desc">
										<c:if test="${fn:length(article.content) >500 }">${ fn:substring(article.content,0,500) } ...</c:if>
										<c:if test="${fn:length(article.content)<=500 }">${article.content}</c:if>
										<a href="articledetail?articleid=${article.id }" class="c_b_p_desc_readmore">阅读全文</a>
									</div>
								</div>
								<p class="postfoot">
									posted @ ${article.byzd1 } ${article.date } 阅读
									（${article.readnum }） 评论（${article.commentnum }） 
								</p>
							</div>
						</c:if>

						<c:if test="${concernsign=='1'}">
							<p class="date">
								<span> <a
									id="homepage1_HomePageDays_DaysList_ctl00_ImageLink"
									title="Day Archive" href="#"
									style="display:inline-block;border-width:0px;">${article.date
										}</a>
								</span>
							</p>

							<div class="post">
								<h2>
									<a
										id="homepage1_HomePageDays_DaysList_ctl00_DayList_TitleUrl_0"
										href="articledetail?articleid=${article.id}">${article.title }</a>
								</h2>
								<div class="postbody">
									<div class="c_b_p_desc">
										<c:if test="${fn:length(article.content) >500 }">${ fn:substring(article.content,0,500) } ...</c:if>
										<c:if test="${fn:length(article.content)<=500 }">${article.content}</c:if>
										<a href="articledetail?articleid=${article.id}" class="c_b_p_desc_readmore">阅读全文</a>
									</div>
								</div>
								<p class="postfoot">
									posted @ ${article.byzd1 } ${article.date } 阅读
									（${article.readnum }） 评论（${article.commentnum }） <a href="edit_article?articleid=${article.id }"
										rel="nofollow">编辑</a>
								</p>
							</div>
						</c:if>

						<c:if test="${concernsign=='2' and article.isopen!='1'}">
							<p class="date">
								<span> <a
									id="homepage1_HomePageDays_DaysList_ctl00_ImageLink"
									title="Day Archive" href="#"
									style="display:inline-block;border-width:0px;">${article.date
										}</a>
								</span>
							</p>

							<div class="post">
								<h2>
									<a
										id="homepage1_HomePageDays_DaysList_ctl00_DayList_TitleUrl_0"
										href="articledetail?articleid=${article.id}">${article.title }</a>
								</h2>
								<div class="postbody">
									<div class="c_b_p_desc">
										<c:if test="${fn:length(article.content) >500 }">${ fn:substring(article.content,0,500) } ...</c:if>
										<c:if test="${fn:length(article.content)<=500 }">${article.content}</c:if>
										<a href="articledetail?articleid=${article.id}" class="c_b_p_desc_readmore">阅读全文</a>
									</div>
								</div>
								<p class="postfoot">
									posted @ ${article.byzd1 } ${article.date } 阅读
									（${article.readnum }） 评论（${article.commentnum }）
								</p>
							</div>
						</c:if>

						<c:if test="${concernsign=='3' and article.isopen=='3'}">
							<p class="date">
								<span> <a
									id="homepage1_HomePageDays_DaysList_ctl00_ImageLink"
									title="Day Archive" href="#"
									style="display:inline-block;border-width:0px;">${article.date
										}</a>
								</span>
							</p>

							<div class="post">
								<h2>
									<a
										id="homepage1_HomePageDays_DaysList_ctl00_DayList_TitleUrl_0"
										href="articledetail?articleid=${article.id}">${article.title }</a>
								</h2>
								<div class="postbody">
									<div class="c_b_p_desc">
										<c:if test="${fn:length(article.content) >500 }">${ fn:substring(article.content,0,500) } ...</c:if>
										<c:if test="${fn:length(article.content)<=500 }">${article.content}</c:if>
										<a href="articledetail?articleid=${article.id}" class="c_b_p_desc_readmore">阅读全文</a>
									</div>
								</div>
								<p class="postfoot">
									posted @ ${article.byzd1 } ${article.date } 阅读
									（${article.readnum }） 评论（${article.commentnum }） 
								</p>
							</div>
						</c:if>
					</c:forEach>

				</div>
			</div>
		</div>
		<div class="clear"></div>
		<div id="footer">
			<c:forEach items="${articles}" var="article" begin="0" end="0">
				<p id="footer">

					Copyright © ${article.byzd1}<br>


				</p>
			</c:forEach>
		</div>
	</div>
</body>
</html>
