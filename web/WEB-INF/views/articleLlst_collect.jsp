<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<!--article list start-->
<div id="content_area">
	<div id="post_list">
		<table cellspacing="0" cellpadding="0" border="0">
			<tbody>
				<tr>
					<th class="post-title">标题</th>
					<th>评论数</th>
					<th>阅读数</th>
					<th>操作</th>
				</tr>

				<c:forEach items="${articles}" var="article">
					<tr>
						<td class="post-title"><a
							href="articledetail?articleid=${article.id }">${article.title}</a>（${article.date
							}）</td>


						<td>${article.commentnum }</td>
						<td>${article.readnum }</td>
						<%-- <td>${deletepage }</td> --%>
						<td><a href="discollect?articleid=${article.id }"
							onclick="return confirm('确认取消收藏？')">取消收藏</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>



</div>

<!--article list end-->


