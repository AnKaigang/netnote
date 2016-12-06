<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>






<!--left container start-->
<div id="left_container" style="margin-top: 0px;">
	<div id="left_nav">
		<h2>操作</h2>
		<ul>
			<li><a href="personal_article_list">日记列表</a></li>
			<li><a href="addArtical" target="">添加新日记</a></li>
			<li><a href="personal_article_draft_list">草稿箱</a></li>
			<li><a href="personal_article_recycle_list">回收站</a></li>
			<li><a href="personal_article_collect_list">我的收藏</a></li>
		</ul>
	</div>

	<!-- morenfenlei -->
	<div id="sidebar_categories">
		<h2>默认分类</h2>
		<ul id="post_categories">

			<li><a href="personal_article_sorted_list?sortid=1" >美文精选</a></li>
			<li><a href="personal_article_sorted_list?sortid=2" >情感日记</a></li>
			<li><a href="personal_article_sorted_list?sortid=3" >牛人牛事</a></li>
			<li><a href="personal_article_sorted_list?sortid=4" >幽默天地</a></li>
			<li><a href="personal_article_sorted_list?sortid=5" >休闲娱乐</a></li>
		</ul>
	</div>
	<!-- end morenfenlei -->

	<!-- gerenfenlei -->
	<div id="sidebar_categories">
		<h2>个人分类</h2>
		<ul id="post_categories">
			<li><a href="diaryType">[编辑分类]</a></li>


			<c:forEach items="${sorts}" var="sort">
				<li><a href="personal_article_sorted_list?sortid=${sort.id }">${sort.name
						}</a></li>
			</c:forEach>

		</ul>
	</div>
	<!-- end gerenfenlei -->

</div>

<!--left container start-->
