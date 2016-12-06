<%@page pageEncoding="utf-8"
	%>
<%@ page isELIgnored="false" %> 

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UFT-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/csstyle/menutopcss.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/jscript/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jscript/JsforindexBody.js"></script>
<script type="text/javascript">
	function setConfirm(id, $this){
		if(confirm('确认删除？')){
			$.post("<%=basePath%>/admin/menu/deleteMenu", { id : id }, function(data, result){
				if(result == "success"){
					$($this).parent().parent().remove();
				}
				else{
					alert("删除失败！");
				}
			});
		}
	}
</script>
</head>
<style type="text/css">
	body{
		font-size: 14px; color:#333;	
	}
	a:link,a:visited,a:active{
		color: #056da1; text-decoration: none;
	}
	a:hover{
		color:#00a4ac;
	}
	.solidline{
		border-top:1px solid #dddddd;
		border-left:1px solid #dddddd;
	}
	table tr:not([class]):hover{
		background-color: #E5EBEE;
	}	
	.list-addbtn th:first-child{
		border-top:1px solid #dddddd;
		border-left:1px solid #dddddd;
	}
	.list-addbtn th:last-child{
		border-top:1px solid #dddddd;
		
	}
/* 	.addlistable{ */
/* 		background-color: #fff; */
/* 	    border: 1px solid #ccc; */
/* 	    height: 30px;	     */
/* 	    padding: 5px 10px;	     */
/* 	}	 */
</style>
<body>
<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="#">系统设置</a></li>
		    <li><a href="#">菜单管理</a></li>
	    </ul>	    
    </div>
<table width="50%" border="0" cellpadding="6" cellspacing="0" align="center" style="border-right:1px solid #dddddd;border-bottom:1px solid #dddddd;">
<tr class="list-addbtn">
	<th colspan="6">&nbsp;</th>
	<th colspan="2">
		<a class="addlistable" href="${pageContext.request.contextPath }/admin/menu/selectMenu.do">新增菜单</a>
	</th>
</tr>
<tr align="center">
	<th class="solidline">菜单名称</th>
	<th class="solidline">类型</th>
	<th class="solidline">功能代码</th>
	<th class="solidline">排序</th>
	<th class="solidline">是否启用</th>
	<th class="solidline">备注</th>
	<th colspan=2 class="solidline">操作</th>
	</tr>
		
	      <c:forEach items = "${list }" var = "e" varStatus="s">
	      <tr>
	        	<td class="solidline" style="background:#eee">${e.name }</td>
		        <td class="solidline" style="background:#eee">${e.type}</td>
		        <td class="solidline" style="background:#eee">${e.code}</td>
		        <td class="solidline" style="background:#eee">${e.korder}</td>
		        <td class="solidline" style="background:#eee">${e.enable == 1 ? "是" : "否"}</td>
		        <td class="solidline" style="background:#eee">${e.note}</td>
		        <td class="solidline"  style="background:#eee">
		        <a href="<%=basePath%>/admin/menu/findMenu.do?id=${e.id}" align=center >修改</a>
		        </td>
		        <td class="solidline"  style="background:#eee">
		        <a href="javascript:void(0);"  onclick="setConfirm(${e.id}, this)" align=center>删除</a>
		        </td>
	        		<c:forEach items = "${e.childMenu }" var = "e2" varStatus="status">
	        			<tr>
		        			<td class="solidline" >--${e2.name }</td>
					        <td class="solidline">${e2.type}</td>
					        <td class="solidline">${e2.code}</td>
					        <td class="solidline">${e2.korder}</td>
					        <td class="solidline">${e2.enable == 1 ? "是" : "否"}</td>
					        <td class="solidline">${e2.note}</td>
					        <td class="solidline">
					        <a href="<%=basePath%>/admin/menu/findMenu.do?id=${e2.id}" align=center>修改</a>
					        </td>
					        <td class="solidline">
					        <a href="javascript:void(0);"  onclick="setConfirm(${e2.id}, this)" align=center>删除</a>
					        </td>
			        		<c:if test="${status.first}">
		        			<c:set var="firstId" value="${e2.id }"></c:set>
		        			</c:if>
		        				<c:forEach items = "${e2.childMenu }" var = "e3" varStatus = "status1">
		        					<tr>
			        					<td class="solidline" >----${e3.name }</td>
								        <td class="solidline">${e3.type}</td>
								        <td class="solidline">${e3.code}</td>
								        <td class="solidline">${e3.korder}</td>
								        <td class="solidline">${e3.enable == 1 ? "是" : "否"}</td>
								        <td class="solidline">${e3.note}</td>
								        <td class="solidline">
								        <a href="<%=basePath%>/admin/menu/findMenu.do?id=${e3.id}" align=center>修改</a>
								        </td>
								        <td class="solidline">
								        <a href="javascript:void(0);"  onclick="setConfirm(${e3.id}, this)" align=center>删除</a>
								        </td>
			        					<c:if test="${status1.first }">
			        						<c:set var = "firstId" value = "${e3.id }"></c:set>
			        					</c:if>
			        					<c:forEach items = "${e3.childMenu }" var = "e4" varStatus = "status2">
			        						<tr>
					        					<td class="solidline" >------${e4.name }</td>
										        <td class="solidline">${e4.type}</td>
										        <td class="solidline">${e4.code}</td>
										        <td class="solidline">${e4.korder}</td>
										        <td class="solidline">${e4.enable == 1 ? "是" : "否"}</td>
										        <td class="solidline">${e4.note}</td>
										        <td class="solidline">
										        <a href="<%=basePath%>/admin/menu/findMenu.do?id=${e4.id}" align=center>修改</a>
										        </td>
										        <td class="solidline">
										        <a href="javascript:void(0);"  onclick="setConfirm(${e4.id}, this)" align=center>删除</a>
										        </td>
					        					<c:if test="${status1.first }">
					        						<c:set var = "firstId" value = "${e3.id }"></c:set>
					        					</c:if>
					        				</tr>
			        					</c:forEach>
			        				</tr>
		        				</c:forEach>
	        				</tr>
	        		</c:forEach>
	        	</tr>	
        </c:forEach>
	</table>
</body>
</html>