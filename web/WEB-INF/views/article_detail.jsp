<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>

<title>${artitle.title } - ${artitle.addParam1 } - 日志云</title>
<link type="text/css" rel="stylesheet" href="public/css/blog-common.css"></link>
<link id="MainCss" type="text/css" rel="stylesheet"
	href="public/css/bundle-AnotherEon001.css"></link>
<script src="public/js/blog-common.js" type="text/javascript"></script>
<script src="public/js/jquery.js" type="text/javascript"></script>
<script src="public/js/layer.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		var s = "${error}";
		if (s != "") {
			alert(s);
		}

	})
	function reply(obj) {
		layer.open({
			type : 2,
			title : '回复',
			shadeClose : true,
			shade : 0.3,
			area : [ '50%', '40%' ],
			content : "${pageContext.request.contextPath}/reply?id="
					+ obj.getAttribute("value") + "&rUId="
					+ obj.getAttribute("id") + "&aId=${artitle.id}"
		});
	}
</script>

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
	color: #000;
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


<script type="text/javascript">
	function agree(value) {
		var thisuserid = "<%=session.getAttribute("userid")%>";
		$.post("IsAgreed",{"agreearticleid":value,"AgreeUserId":thisuserid},function(data,textStatus){
				
			if(data=="true")
			{	
				alert("这篇文章你已操作过！");
			 	return false ;
			}else{
						$.ajax({
					type : 'post',
					url : 'agree?articleid=' + value,
					data : {},
					cache : false,
					dataType : 'json',
					success : function(data) {
						//alert(data);
						if (data == true) {
							window.location.reload();
						} else {
							alert("请先登录！");
						}
					},
					error : function() {
						alert("error");
					}
				});
			}
		});

		
	}
	
	
		function disagree(value) {
		var thisuserid = "<%=session.getAttribute("userid")%>";
		$.post("IsAgreed", {
			"agreearticleid" : value,
			"AgreeUserId" : thisuserid
		}, function(data, textStatus) {

			if (data == "true") {
				alert("这篇文章你已操作过！");
				return false;
			} else {
				$.ajax({
					type : 'post',
					url : 'disagree?articleid=' + value,
					data : {},
					cache : false,
					dataType : 'json',
					success : function(data) {
						//alert(data);
						if (data == true) {
							window.location.reload();
						} else {
							alert("请先登录！");
						}
					},
					error : function() {
						alert("error");
					}
				});
			}
		});

	}

	function collect(value) {
		//alert("aa");
		$.ajax({
			type : 'post',
			url : 'collect?articleid=' + value,
			data : {},
			cache : false,
			dataType : 'json',
			success : function(data) {
				//alert(data);
				if (data == true) {
					alert("您已收藏该文章!");
					$("#green_channel_favorite").text('已收藏');
					$("#green_channel_favorite").attr('onclick',
							'return false;');
				} else {
					alert("请先登录！");
				}
			},
			error : function() {
				alert("请先登录！");
			}
		});
	}
</script>

</head>
<body>
	<!-- <a name="top"></a> -->


	<jsp:include page="base/personal_headr.jsp"></jsp:include>

	<div id="wrapper" style="margin-top:30px">
		<div id="header">
			<div id="top">
				<h1>
					<a id="Header1_HeaderTitle" class="headermaintitle" href="#">${artitle.addParam1}</a>
				</h1>
				<div id="subtitle"></div>

			</div>
			<table class="chara1" style="height: 49px; width: 100%; ">
				<tr>
					<td></td>
					<td></td>
					<td style="width: 180px;background-color:#36648B "><a
						href="index">博客首页</a></td>
					<td style="width: 180px;background-color:#36648B  "><a
						href="userindex?userid=${user.id }">个人首页</a></td>
					<td style="width: 180px; background-color:#36648B "><a
						href="addArtical">编写新日记</a></td>
				</tr>
			</table>


			<div id="sub">
				<div class="BlogStats">文章</div>
			</div>
		</div>
		<!-- 		<iframe src="userleft"
			style="width:25%;height:1000px;float: left;border:none;"></iframe> -->

		<jsp:include page="user_left.jsp"></jsp:include>


		<!-- <div id="wrapper"> -->

		<div id="main_container"
			style="width:88%;margin-top:0px;margin-left:12%">
			<div id="content">

				<div id="post_detail">

					<!--content start-->
					<div class="post">
						<h2>${artitle.title }</h2>
						<div class="postostbody">
							<div id="cnblogs_post_body"></div>
							<div id="MySignature"></div>
							<div class="clear"></div>
							<!--artitle end-->
							${artitle.content}


							<div id="BlogPostCategory"></div>
							<div id="EntryTag"></div>
							<div id="blog_post_info">

								<!--guan zhu  shou cang-->
								<div id="green_channel">
<%-- 									<c:if test="${isCollected=='0'|| isCollected=='null'}">
										<a id="green_channel_favorite"
											onclick="collect('${artitle.id}')" href="#">收藏该文</a>
									</c:if>
									<c:if test="${isCollected=='1'}">
										<a id="green_channel_favorite" href="return false;">已收藏</a>
									</c:if> --%>

									<c:choose>
										<c:when test="${isCollected=='1'}">
											<a id="green_channel_favorite" >已收藏</a>
										</c:when>
										<c:otherwise>
											<a id="green_channel_favorite" onclick="collect('${artitle.id}')" href="#">收藏该文</a>
										</c:otherwise>
									</c:choose>

								</div>


								<!--zan cai-->
								<div id="div_digg">
									<div class="diggit" onclick="agree('${artitle.id}')">
										<span class="diggnum" id="digg_count">${artitle.agree}</span>
									</div>
									<div class="buryit" onclick="disagree('${artitle.id}')">
										<span class="burynum" id="bury_count">${artitle.disagree}</span>
									</div>
									<div class="clear"></div>
									<div class="diggword" id="digg_tips">(请您对文章做出评价)</div>
								</div>
								<!--zan cai end-->


							</div>


						</div>
						<p class="postfoot">
							posted on <span id="post-date">${artitle.date}</span> <a
								href="personal_info?userid=${user.id }">${artitle.addParam1}</a>
							阅读(<span id="post_view_count">${artitle.readnum}</span>) 评论(<span
								id="post_comment_count">${artitle.commentnum}</span>)
						</p>
					</div>
					<!--content end-->






				</div>
				<a name="!comments"></a>
				<div id="blog-comments-placeholder">
					<div id="comments_pager_top"></div>
					<a name="评论"></a>


					<!--ping lun-->
					<div id="comments">
						<h3>评论</h3>
						<c:forEach items="${parentList}" begin="0" var="parent"
							varStatus="index">
							<div class="post">
								<h2>
									<a href="#" class="layer">${index.count}楼</a><a name="3462033"
										id="comment_anchor_3462033"></a> &nbsp;&nbsp; <span
										class="comment_actions"> <c:if
											test="${username!=null }">
											<a href="javascript:void(0);" id="${parent.addParam3 }"
												value="${parent.id }" onclick="reply(this)">回复</a>
										</c:if>
									</span>
								</h2>
								<div id="comment_body_3462033" class="blog_comment_body">${parent.content}
								</div>
								<c:forEach items="${parent.childList}" begin="0" var="child">
									<div style="margin-left:20px">
										<a value="${child.id }">${child.addParam1} </a>回复<a
											value="${parent.replyUId}">${child.addParam2}</a>&nbsp;
										${child.content} <font style="font-size: 10px;color:#0033FF">${child.date}</font>
										<c:if test="${username!=null }">
											<a style="margin-left:10px;margin-right:5px;color:#0000FF"
												value="${child.id }" id="${child.addParam3 }"
												onclick="reply(this)">回复</a>
										</c:if>
									</div>
								</c:forEach>
								<span id="comment_3462033_avatar" style="display:none;">http://pic.cnblogs.com/face/983505/20160627103900.png</span>
								<div class="postfoot">
									<span class="comment_date">${parent.date}</span> | <span
										id="a_comment_author_3462033">${parent.addParam1} </span>
								</div>
							</div>
						</c:forEach>
					</div>
					<!--ping lun end-->


					<!--fa biao ping lun-->
					<div id="comments_pager_bottom"></div>
				</div>
				<script type="text/javascript">
					var commentManager = new blogCommentManager();
					commentManager.renderComments(0);
				</script>
				<div id="comment_form" class="commentform">
					<a name="commentform"></a>
					<div id="divCommentShow"></div>
					<c:if test="${username!=null }">
						<div id="comment_form_container">
							<form action="addComment" method="post" id="commentForm">
								<div id="commentform_title">发表评论</div>
								<span id="tip_comment" style="color:Red"></span>
								<p>
									昵称：<input type="text" id="tbCommentAuthor" class="author"
										size="50" value="${username }" />
								</p>
								<div class="commentbox_main">
									<div class="commentbox_title">
										<div class="commentbox_title_left" style="margin-top:20px">评论内容：</div>

									</div>
									<div class="clear"></div>
									<textarea name="content" id="tbCommentBody"
										class="comment_textarea"></textarea>
									<input name="aId" type="hidden" id="tbCommentBody"
										value="${artitle.id}"></input> <input name="rUId"
										type="hidden" id="tbCommentBody" value="${artitle.addParam1}"></input>
								</div>
								<script>
								function checkcomment(){
										if($("#tbCommentBody").val()==""){
											layer.alert("评论内容为空！");
										}else{
											$("#commentForm").submit();
										}
								
								}
								</script>
								
								<p id="commentbox_opt">
									<input id="btn_comment_submit" type="button" onclick="checkcomment()"
										class="comment_btn" value="提交评论"></input> 
								</p>
								<div id="tip_comment2" style="color:Red"></div>
							</form>

						</div>
					</c:if>
					<div id="HistoryToday" class="c_ad_block"></div>
				</div>
				<!--end fa biao ping lun-->


			</div>
			<!--content end-->
		</div>
		<!--main end-->




	</div>
	<div class="clear"></div>



</body>
</html>