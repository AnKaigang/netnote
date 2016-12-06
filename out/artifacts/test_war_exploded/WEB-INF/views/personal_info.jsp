<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="hhd.restful.bean.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, user-scalable=1">
<title>personal_info</title>



<link href="public/css/home_mvc.css" rel="stylesheet">


<script async="" src="public/js/analytics.js"></script>
<script type="text/javascript" src="public/js/jquery.js"></script>
<script type="text/javascript" src="public/js/common.js"></script>
<script src="public/js/home_mvc.js"></script>

<script type="text/javascript">
	function delConcern(concerned_userid) { //执行删除 guan zhu 的操作       
		if (confirm("确定取消关注吗？")) {
			//alert(id);
			$.ajax({
				type : 'post',
				url : 'deleteConcern?concerned_user_id=' + concerned_userid,
				data : {},
				cache : false,
				dataType : 'json',
				success : function(data) {
					//alert(data);
					if (data == true)
						window.location.reload();
					else
						alert("delete concern error!");
				},
				error : function() {
				}
			});

		} else
			return false;
	}

	function addConcern(concerned_userid) { //执行  guan zhu 的操作       
		if (confirm("确定添加关注吗？")) {
			//alert(id);
			$.ajax({
				type : 'post',
				url : 'addConcern?concerned_user_id=' + concerned_userid,
				data : {},
				cache : false,
				dataType : 'json',
				success : function(data) {
					//alert(data);
					if (data == true)
						window.location.reload();
					else
						alert("add concern error!");
				},
				error : function() {
				}
			});

		} else
			return false;
	}
</script>



</head>
<body style="MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">

<jsp:include page="base/personal_headr.jsp"></jsp:include>

	<div id="container_content" style="margin-top:30px"> 

		<div id="main">

			<link href="public/css/home.css" rel="stylesheet">

			<link href="public/css/user_profile.css" rel="stylesheet">

			<link href="public/css/ing_common_bundle.css" rel="stylesheet">

			<link href="public/css/ui-dialog_bundle.css" rel="stylesheet">

			<script src="public/js/ing_common_bundle.js"></script>

			<script src="public/js/dialog_bundle.js"></script>


			<!--user info start-->
			<div id="user_profile_block">
				<table>
					<tbody>
						<tr>
							<td valign="top">
								<div class="user_avatar">
									<img src="${user.header }" alt="${user.name }的头像"
										class="img_avatar"><br>
									<table class="user_profile_nav_block">
										<tbody>
											<tr>
												<td>
													<ul class="avatar_nav_block" id="avatar_opt_nav">
														<c:if test="${concernsign=='1'}">
															<li><a href="editHeader" class="link_account">修改头像</a></li>
														</c:if>
														<li><a href="personal_info_detail?userid=${user.id }" class="link_account">个人资料</a></li>
														<c:if test="${concernsign=='1'}">
															<li><a href="personal_info_edit" class="link_account">修改个人信息</a></li>
														</c:if>
													</ul>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</td>
							<td valign="top">
								<div>
									<h1 class="display_name">${user.name}</h1>
									<div>
										<ul id="user_profile" class="user_profile">

											<li><span class="text_gray">年龄：</span><span>${days}天</span></li>
											<li><span class="text_gray">博客：</span><a
												href="userindex?userid=${user.id }" class="gray"
												target="_blank">${user.name }的日记主页</a></li>
										</ul>
									</div>
									<div class="text_gray" style="padding-top: 10px;">
										<div class="data_count_block" style="padding: 0px 0 15px;">
											<div class="data_left">
												<div class="follow_count">
													<a id="following_count" href="#">${fn:length(myconcern)}</a>
												</div>
												<a href="">关注</a>
											</div>
											<div class="data_right">
												<div class="follow_count">
													<a id="follower_count" href="#">${fn:length(concernme)}</a>
												</div>
												<a href="">粉丝</a>
											</div>
											<div class="clear"></div>
										</div>

										<c:if test="${concernsign=='2'}">
											<div style="display:block">
												<!-- yiguanzhu -->
												<ul class="dropdown-wrapper" id="followedPanel"
													style="display:block">
													<li><span>已关注</span>
														<ul class="dropdown-sublist" style="cursor:pointer">
															<li onclick="delConcern('${user.id}')">取消关注</li>
														</ul></li>
												</ul>
												<!-- yiguanzhu end -->

												<!-- guanzhu -->
												<button class="follow_button" id="unFollowedPanel"
													style="display:none" onclick="follow()">+关注</button>
												<!-- guanzhu end -->
											</div>
										</c:if>

										<c:if test="${concernsign=='3'}">
											<div style="display:block">
												<!-- yiguanzhu -->
												<ul class="dropdown-wrapper" id="followedPanel"
													style="display:none">
													<li><span>已关注</span>
														<ul class="dropdown-sublist" style="cursor:pointer">
															<li onclick="">取消关注</li>
														</ul></li>
												</ul>
												<!-- yiguanzhu end -->

												<!-- guanzhu -->
												<button class="follow_button" id="unFollowedPanel"
													style="display:block" onclick="addConcern('${user.id}')">+关注</button>
												<!-- guanzhu end -->
											</div>
										</c:if>



										<div id="follow_block_info"></div>
									</div>
								</div>
								<div class="clear"></div> <br>
								<div class="user_intro"></div>
							</td>
						</tr>
					</tbody>
				</table>
				<br>
			</div>
			<!--user info end-->



			<div class="topic_nav_block_wrapper">

				<span class="title_block">最新动态</span>
				<div class="clear"></div>
			</div>


			<!--article list start-->
			<div class="feed_block">

				<div class="block_arrow">

					<ul id="feed_list">

						<c:forEach items="${articles}" var="article">
							<li class="feed_item"><div class="feed_avatar">
									<a href="#"><img src="${user.header }" alt="${user.name }"></a>
								</div>
								<div class="feed_body" id="feed_content_5610230">
									<div class="feed_title">
										<a href="" class="feed_author">${user.name }</a> 发表日记：<a
											href="articledetail?articleid=${article.id }"
											target="_blank">${article.title }</a> <span class="feed_date">${article.date
											}</span>
									</div>
									<div class="feed_desc">${article.content }</div>
								</div>
								<div class="clear"></div></li>
						</c:forEach>

					</ul>
				</div>
				<div class="clear"></div>
			</div>

			<!--artlcle list end-->


		</div>


		<!--fllow start-->
		<div id="right_sidebar">

			<div id="followees_followers">
				<div class="avatar_list">
					<h3>
						<a href="#">我关注的人(${fn:length(myconcern)})</a>
					</h3>
					<ul>
						<c:forEach items="${myconcernUserlist}" var="user">
							<li>
								<div class="avatar_pic">
									<a href="personal_info?userid=${user.id }"><img
										src="${user.header }" alt="${user.name }的头像"
										title="${user.name }"></a>
								</div>
								<div class="avatar_name">
									<a href="personal_info?userid=${user.id }"
										title="${user.name }">${user.name }</a>
								</div>
								<div class="remark_name"></div>
							</li>
						</c:forEach>


					</ul>
					<div class="clear"></div>
					<br>
					<h3>
						<a href="#">我的粉丝(${fn:length(concernme)})</a>
					</h3>
					<ul>
						<c:forEach items="${concernmeUserlist}" var="user">
							<li>
								<div class="avatar_pic">
									<a href="personal_info?userid=${user.id }"><img
										src="${user.header }" alt="${user.name }的头像"
										title="${user.name }"></a>
								</div>
								<div class="avatar_name">
									<a href="personal_info?userid=${user.id }"
										title="${user.name }">${user.name }</a>
								</div>
							</li>
						</c:forEach>

					</ul>
					<div class="clear"></div>
				</div>
			</div>

		</div>

		<!--follow end-->

		<div class="clear"></div>
	</div>






</body>
</html>