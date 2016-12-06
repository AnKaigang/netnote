<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="side_nav">
                
<div id="cate_title_block">
<div id="cate_title_title"><div class="cate_title">网站分类</div></div>
<ul id="cate_item">
<li id="cate_item_4" >
<a href="index"  target="_self" title="所有博文">所有博文</a>
<c:forEach items="${sortList}" begin="0" var="sort" varStatus="status">
<li id="cate_item_4" >
<a href="indexContent?sortId=${sort.id }"  target="_self" title="${sort.name} ">${sort.name}</a>
</li>
</c:forEach>
</ul>
<div class="cate_bottom"></div>
</div>




<p class="r_l_1"></p><p class="r_l_2"></p><p class="r_l_3"></p>
<div class="l_s"></div>
<p class="r_l_3"></p><p class="r_l_2"></p><p class="r_l_1"></p>
<div class="w_l">
<h4>推荐博客排行</h4>
<div id="blogger_list">
<ul>
<c:forEach items="${userList}" begin="0" var="user">
<li><a href="personal_info?userid=${user.id }" target="_blank">${user.name}</a></li>
</c:forEach>
</ul>
</div>
</div>





<p class="r_l_1"></p><p class="r_l_2"></p><p class="r_l_3"></p>
<div class="l_s"></div>
<p class="r_l_3"></p><p class="r_l_2"></p><p class="r_l_1"></p>
<div class="w_l">
<h4>最新推荐博文</h4>
<div>
<ul>
<c:forEach items="${articleList}" begin="0" var="article">
<li><a href="articledetail?articleid=${article.id}" target="_blank">${article.title}</a></li>
</c:forEach>
</ul>
</div>



<p class="r_l_1"></p><p class="r_l_2"></p><p class="r_l_3"></p></div>
<div class="l_s"></div>        
<p class="r_l_3"></p><p class="r_l_2"></p><p class="r_l_1"></p>
<div class="w_l">                
<h4>统计信息</h4>
<div id="site_stats"><ul><li>博客 - <span>${articletNum }</span></li><li>赞- <span>${agreeNum}</span></li><li>评论 - <span>${commentNum}</span></li></ul></div>      
</div>
<p class="r_l_1"></p><p class="r_l_2"></p><p class="r_l_3"></p>

</div>
