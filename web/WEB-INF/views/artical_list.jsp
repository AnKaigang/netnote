<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<!--article list start-->
<div id="content_area">
	<div id="post_list">
		<table cellspacing="0" cellpadding="0" border="0">
			<tbody>
				<tr>
					<th class="post-title">标题</th>
					<th class="publish-status">发布状态</th>
					<th class="publish-status">日记权限</th>
					<th>评论数</th>
					<th>阅读数</th>
					<th>操作</th>
					<th>操作</th>
				</tr>

				<c:forEach items="${articles}" var="article">
					<tr>
						<td class="post-title"><a
							href="articledetail?articleid=${article.id }">${article.title}</a>（${article.date
							}）</td>
						<td><c:if test="${article.status=='0'}">发布</c:if> <c:if
								test="${article.status=='1'}">草稿</c:if> <c:if
								test="${article.status=='2'}">删除</c:if></td>

						<td><c:if test="${article.isopen=='1'}">自己可见</c:if> <c:if
								test="${article.isopen=='2'}">好友可见</c:if> <c:if
								test="${article.isopen=='3'}">所有人可见</c:if></td>
						<td>${article.commentnum }</td>
						<td>${article.readnum }</td>
						<%-- <td>${deletepage }</td> --%>
						<td><c:choose>
								<c:when test="${deletepage == '1'}">
									<a href="recover_article?articleid=${article.id }"
										onclick="return confirm('确认还原?')">还原</a>
								</c:when>
								<c:otherwise>
									<a href="edit_article?articleid=${article.id }">编辑</a>
								</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${deletepage == '1'}">
									<a href="delete_article_true?articleid=${article.id }"
										onclick="return confirm('确认永久删除？')">删除</a>
								</c:when>
								<c:otherwise>
									<a href="delete_article?articleid=${article.id }"
										onclick="return confirm('确认删除?')">删除</a>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>



</div>

<!--article list end-->


