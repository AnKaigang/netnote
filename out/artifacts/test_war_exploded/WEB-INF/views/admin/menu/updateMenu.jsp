<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
    <%@ page isELIgnored="false" %> 
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


	<link href="${pageContext.request.contextPath }/csstyle/style.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath }/csstyle/H-ui.min.css" rel="stylesheet" type="text/css" />
	<style>
		.forminfo li{height:34px;}
		.input-text{
		max-width:300px;
		}
	</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 
<title>修改</title>
</head>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统管理</a></li>
    <li><a href="#">权限管理</a></li>
    <li><a href="#">菜单管理</a></li>
    </ul>
    </div>
<form action="${pageContext.request.contextPath }/admin/menu/updateMenu.do?id=${m.id}"  method="post" >
<br/><hr>
<ul class="forminfo">
	    <li><label>上级菜单：</label>&nbsp;&nbsp;
			<select name = "pid" value = ${m.pid }>
				<option value = "0" selected = "selected">顶级菜单</option>
				<c:forEach items = "${list }" var = "e" varStatus="s">
					<option value="${e.id }">${e.name }</option>
					<c:forEach items = "${e.childMenu }" var = "e2">
						<option value="${e2.id }">--${e2.name }</option>
						<c:forEach items = "${e2.childMenu }" var = "e3">
							<option value="${e3.id }">----${e3.name }</option>
							<c:forEach items = "${e3.childMenu }" var = "e4">
								<option value="${e4.id }">------${e4.name }</option>
							</c:forEach>
						</c:forEach>
					</c:forEach>	
				</c:forEach>
			</select>
		</li>
	    <li><label>菜单名称：</label>&nbsp;&nbsp;<input name="name" type="text" value = "${m.name }" class="input-text" style="width:400px;"></li>
	    <li><label>功能代码：</label>&nbsp;&nbsp;<input name="code" type="text" value = "${m.code }" class="input-text"></li>
		<li><label>图标网址：</label>&nbsp;&nbsp;<input name="image" type="text" value = "${m.image }" class="input-text"></li>
	    <li><label>打开位置：</label>&nbsp;&nbsp;
			 <select name = "target" value = "${m.target }" class="select">
				<option value = "top">上侧窗口</option>
				<option value = "bottom" >下侧窗口</option>
				<option value = "left" >左侧窗口</option>
				<option value = "right" >右侧窗口</option>
			</select>
	    </li>
	    <li><label>类型：</label>&nbsp;&nbsp;
			<select name = "type">
			<option value = "1" selected = "selected">系统菜单</option>
			<option value = "0" >功能菜单</option>
			</select>
		</li>
		<li><label>排序：</label>&nbsp;&nbsp;<input name="korder" type="text" value = "${m.korder }" class="input-text" style="width:80px;"/></li>
		<li style="line-height:34px;"><label>是否启用：</label>&nbsp;&nbsp;
			<input name="enable" type="radio" value="1" checked="checked">是
			<input name="enable" type="radio" value="0">否
		</li>
		<li><label>备注：</label>&nbsp;&nbsp;<input name="note" type="text" value = "${m.note }" class="input-text"></li>
	    <li><label>&nbsp;</label><input type="submit" value="修改" class="btn-save"/></li>
	    </ul>
	    </hr>
	    </form>
</body>
</html>