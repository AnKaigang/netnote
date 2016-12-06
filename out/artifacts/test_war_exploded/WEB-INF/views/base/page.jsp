<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <div class="pagin">
 
	<form action="index" method="post" id="pageForm" name="pageForm">
    	<div class="message">
    	
    	<span>共<i class="blue">${page.count==null?0:page.count }</i>条记录，当前显示第&nbsp;<i class="blue">${page.page ==null ?1: page.page}&nbsp;</i>页
		<select name="pageSize" id="size" value="${page.pageSize }" onchange="refind();" class="select1">
        	<option <c:if test="${page.pageSize == 10}">selected</c:if> value="10">10</option>
        	<option <c:if test="${page.pageSize == 20}">selected</c:if> value="20">20</option>
        	<option <c:if test="${page.pageSize == 30}">selected</c:if> value="30">30</option>
        	<option <c:if test="${page.pageSize == 50}">selected</c:if> value="50">50</option>
        </select>
        </span>
        </div>
        <ul class="paginList">
        <c:choose>
        	<c:when test="${(page.page == null?1:page.page) <= 1}">
 	 	    	<li class="paginItem"><a href="javascript:;" style="background-image: "><span class="pagepre"></span></a></li>
        	</c:when>
        	<c:otherwise>
        		<li class="paginItem"><a href="javascript:gotoPage(${page.page - 1});"><span class="pagepre"></span></a></li>
        	</c:otherwise>
        </c:choose>
         <!-- 当前页以前页码-->
 		<c:if test="${(page.page - 3)>0}">
        	<li class="paginItem"><a href="javascript:gotoPage(${page.page -3 });">${page.page - 3}</a></li>
        </c:if>
 		<c:if test="${(page.page - 2)>0}">
        	<li class="paginItem"><a href="javascript:gotoPage(${page.page -2 });">${page.page - 2}</a></li>
        </c:if>
        <c:if test="${(page.page - 1)>0}">
        	<li class="paginItem"><a href="javascript:gotoPage(${page.page -1 });">${page.page - 1}</a></li>
        </c:if>
        <!-- 当前页 -->
        <li class="paginItem current"><a href="javascript:gotoPage(${page.page});">${page.page}</a></li>
        <!-- 当前页以后页码-->
        <c:if test="${(page.page + 1) <= page.totalPage}">
        	<li class="paginItem"><a href="javascript:gotoPage(${page.page + 1 });">${page.page + 1}</a></li>
        </c:if>
        <c:if test="${(page.page + 2) <= page.totalPage}">
        	<li class="paginItem"><a href="javascript:gotoPage(${page.page + 2 });">${page.page + 2}</a></li>
        </c:if>
        <c:if test="${(page.page + 3) <= page.totalPage}">
        	<li class="paginItem"><a href="javascript:gotoPage(${page.page + 3 });">${page.page + 3}</a></li>
        </c:if>
        <c:choose>
        	<c:when test="${page.page >= page.totalPage}">
 	 	    	<li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        	</c:when>
        	<c:otherwise>
        		<li class="paginItem"><a href="javascript:gotoPage(${page.page + 1});"><span class="pagenxt"></span></a></li>
        	</c:otherwise>
        </c:choose>
        </ul>
	<input type="hidden" id="page" name="page" value="${page.page }">
	<input type="hidden" id="count" name="count" value="${page.count }">
	<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
	</form>
    </div>
    <script language="JavaScript" type="text/JavaScript">
		function Jumping(){
		  document.pageForm.submit();
		}
		
		function gotoPage(pagenum){
			var s=$("#size").val();
			$("#pageSize").val(s);
			$("#page").val(pagenum);
			document.pageForm.submit();
		}
		function refind(){
			gotoPage(1);
		}
	</script>