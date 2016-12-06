<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link href="${pageContext.request.contextPath}/public/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/public/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/public/css/icheck.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/public/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/public/css/webuploader.css" rel="stylesheet" type="text/css" />
	<title>用户列表</title>
	<script language="JavaScript" src="${pageContext.request.contextPath }/public/js/jquery.js"></script>
	<script language="JavaScript" src="${pageContext.request.contextPath }/public/js/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/public/js/jquery.ztree.all-3.5.min.js"></script>
	<script language="JavaScript" src="${pageContext.request.contextPath }/public/js/JsforindexBody.js"></script>
	<script language="JavaScript" src="${pageContext.request.contextPath }/public/js/mydate.js"></script>
	<link href="${pageContext.request.contextPath }/public/admin/style.css" rel="stylesheet" type="text/css" />
	
<script type="text/javascript">
	$(document).ready(function(){
	
	  
	  $(".tiptop a").click(function(){
	  $(".tip").fadeOut(200);
	  });
	
	  $(".sure").click(function(){
	  $(".tip").fadeOut(100);
	  });
	
	  $(".cancel").click(function(){
	  $(".tip").fadeOut(100);
	  });
	 
	
	});
	function updateAdmin(obj){
		
		layer.open({
	    type: 2,
	 	    title: '修改管理员信息',
	 	    shadeClose: true,
	 	    shade: 0.4,
	 	    area: ['60%', '500px'],
	 	    content: '${pageContext.request.contextPath}/admin/updateAdmin?id='+ obj.getAttribute("value")
	 	});  
}
	//删除按钮
	function deleteDep(){
		var str=""; 
		$("[name='houseCk']:checked").each(function(){ 
			str+=$(this).val()+",";
		})
		if(str!=""){
			str=str.substring(0,str.length-1);
			$.ajax({
    	    	type: 'post',
    	    	url: '${pageContext.request.contextPath}/admin/deleteAdmin',
    	    	data: {'data':str},
    	    	success: function(a) {
    	    		if(a!="0"){
	       			 	layer.alert("您已成功删除"+a+"名管理员");
	       			    window.open('${pageContext.request.contextPath}/admin/adminList','_self'); 	 
    	    		}  
    	    		else{
    	    			layer.alert("删除失败");
    	    		}
    	    	}
    		});
		}else{
			layer.alert("请选中一名管理员删除");
		}
	}
	
	//checkbox全选 全不选
	 function   cli(){
	  var collid = document.getElementById("ckAll");
	  var coll = document.getElementsByName("houseCk");
	  if (collid.checked){
	     for(var i = 0; i < coll.length; i++)
	       coll[i].checked = true;
	  }else{
	     for(var i = 0; i < coll.length; i++)
	       coll[i].checked = false;
	  }
  }
</script>
</head>


<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">管理员列表</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
	<div class="tools">
    	<ul class="toolbar">
	        <li onclick="deleteDep()" ><span><img src="${pageContext.request.contextPath }/public/img/t03.png" /></span>删除管理员</li>
        </ul>
    </div>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" id="ckAll" onclick="cli();"/></th>
        <th>序号</th>
        <th>用户名</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
	        <c:forEach items="${adminList}" var="admin" varStatus="status">
	        	 <tr  class="houseId">
			     <td><input name="houseCk" type="checkbox" value="${admin.id}" ></td>
			     <td>${status.count}</td>	
			     <td>${admin.name}</td>		     
				 <td><a value="${admin.id }" onclick="updateAdmin(this)">修改</a> </td>
			     </tr> 
	        </c:forEach>
        </tbody>
    </table>
    
    </div>
     

    
    <script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>