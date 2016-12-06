<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String userid = (String)session.getAttribute("userid");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">
	function agree(value) {
		var thisuserid = "<%= session.getAttribute("userid")%>";
		$.post("IsAgreed",{"agreearticleid":value,"AgreeUserId":thisuserid},function(data,textStatus){
				
			if(data=="true")
			{	
				alert("这篇文章你已赞过！");
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
</script>
<div id="main" style="width:85%">
	<div class="post_nav_block_wrapper">
		<ul class="post_nav_block">
			<c:choose>
				<c:when test="${sort != null}">
					<li><a href="#" class="current_nav">${sort.name }</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="#" class="current_nav">首页</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		<div class="clear"></div>
	</div>
	<div id="post_list" style="width:100%">
		<c:forEach items="${goodArticleList}" begin="0" var="article">
			<div class="post_item">
				<div class="digg">
					<div class="diggit" onClick="agree('${article.id}')">
						<span class="diggnum" id="agreenum">${article.agree }</span>
					</div>
					<div class="clear"></div>
					<div id="agree_tip" class="digg_tip"></div>
				</div>
				<div class="post_item_body">
					<h3>
						<a class="titlelnk" href="articledetail?articleid=${article.id }"
							target="_blank">${article.title }</a>
					</h3>
					<p class="post_item_summary">
						<c:if test="${article.addParam2!=''}">
							<a href="personal_info?userid=${article.byzd1 }" target="_blank"><img
								class="pfs" src="${article.addParam2 }" alt="" height="48"
								width="48"></a>
						</c:if>
						${article.content }
					</p>
					<div class="post_item_foot">
						<a href="personal_info?userid=${article.byzd1 }" class="lightblue">${article.addParam1
							}</a> 发布于 ${article.date } <span class="article_comment"><a
							href="#" title="" class="gray"> 评论(${article.commentnum})</a></span><span
							class="article_view"><a href="#" class="gray">阅读(${article.readnum
								})</a></span>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</c:forEach>


	</div>
	<script>
		editorPickStat();
		aggSite.user.getUserInfo();
	</script>
	<script type="text/javascript">
		var aggSiteModel = {
			"CategoryType" : "SiteHome",
			"ParentCategoryId" : 0,
			"CategoryId" : 808,
			"PageIndex" : 1,
			"TotalPostCount" : 4000,
			"ItemListActionName" : "PostList"
		};
	</script>