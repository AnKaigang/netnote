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
	  $("#username").keyup(function(){
			var $this = $(this);
			if($(this).val()){ 
	    		$.post("${pageContext.request.contextPath}/admin/requestUserList",{ name: $(this).val()},
	    				function(data){
	    			if(data.length){
	    				$this.next().empty();    			
		    			for(var i=0;i<data.length;i++){
		    				$this.next().append($("<li>").attr({ value: data[i].id }).text(data[i].name)).fadeIn(100);     	    		
		   	    		}  
	    			}		
	    			else{
	    				$this.val(null).attr({placeholder: "不存在此用户名，请重输入"});
	    			}
	    		});
    		}
    		else{
    			$(this).next().fadeOut(0).empty();
    		}
		}).next().on("click", "li", function(){
			var $this = $(this);
			$(this).parent().prev().val($(this).text());
				$(this).parent().next().val($(this).attr("value"));
				$(this).parent().empty();
				$.getJSON("${pageContext.request.contextPath}/admin/requestDiarySort",{"name":$("#user").val()},function(data){
					//此处返回的data已经是json对象
					//以下其他操作同第一种情况
						var sortElem=$("#disId1");
						sortElem.html("");
						sortElem.append("<option value=''>"+"全部"+"</option>");
						$.each(data,function(i,item){
							sortElem.append("<option value='"+item.id+"'>"+item.name+"</option>");
						});
					}); 
		});	
	
	});
	
	function onAndOff(obj){		
		$.post("${pageContext.request.contextPath}/admin/userOnAndOff",{"id":obj.getAttribute("value")},
				function(data){
			      if(data=="secess"){
			    	  if(obj.text=="解冻"){
			    		  obj.text="冻结";
			    	  }else{
			    		  obj.text="解冻";
			    	  }
			      }else{
			    	  layer.alert(data);
			      }
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
    	    	url: '${pageContext.request.contextPath}/admin/deleteRecycleDiary',
    	    	data: {'data':str},
    	    	dataType:"json",
    	    	success: function(data) {
    	    		alert(data);
    	       			layer.alert(data);
    	        		window.location.href="${pageContext.request.contextPath}/admin/recycleDiary";
    	    	}
    		});
		}else{
			layer.alert("请选中一篇日记清空");
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
    <li><a href="#">用户列表</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
     <form action="${pageContext.request.contextPath}/admin/userList" method="get" id="form1">
		 <input type="hidden" id="currentPage" name="page" value="${page.page}"/>
		 <input type="hidden" id="size2" name="pageSize" value="${page.pageSize}"/>
		 <input type="hidden" name="count" value="${page.count}"/>
		 <input type="hidden" name="houseConditionQuery" value="queryTrue">
		<div class="row cl" style="margin-left:20px">
		  <span id="jiage"></span>
		 <label class="dian-le" style="margin-left:30px;margin-top: 15px;">等级:</label><label class="dian-le"></label>
           <span class="select-box2" style="display:inline-block;">
		<input class="inputClassAdmin" type="text" name="startRank"  value="${startRank}" >-
		<input class="inputClassAdmin" type="text" name="endRank"  value="${endRank}"  >
		</span>
		 <div class="row cl" style="margin-left:20px;display:inline-block;">
		  <span id="jiage"></span>
		 <label class="dian-le" style="margin-left:30px">积分:</label><label class="dian-le"></label>
           <span class="select-box2" style="display:inline-block;">
			<input class="inputClassAdmin" type="text" name="startPoint"  value="${startPoint}" >-
		<input class="inputClassAdmin" type="text" name="endPoint"  value="${endPoint}"  >
		</span>
		 </div>
		

		<div class="row cl" style="margin-left:0px;display:inline-block;margin-top:10px">
		<span style="display:inline-block;">
		 <label class="dian-le">用户名:</label>
		<input id="username" value="${user}" type="text" name="username" class="inputClassAdmin">
		</span >
         &nbsp;&nbsp;&nbsp;&nbsp;
		 <input type="submit" value="查询"class="btn btn-primary radius" style="display: inline-block">
		</div>
		
</div><br/><hr>
    </form> 
	
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>序号</th>
        <th>用户名</th>
        <th>邮箱</th>
        <th>等级</th>
        <th>账号</th>
        <th>注册日期</th>
        <th>积分</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
	        <c:forEach items="${userList}" var="user" varStatus="status">
	        	 <tr  class="houseId">
			     <td>${status.count}</td>
			     <td>${user.name}</td>
			     <td>${user.mail}</td>
			     <td>${user.rank}</td>
			     <td>${user.account}</td>
			     <td>${user.registerdate}</td>
			     <td>${user.point}</td>		
			     
			     	     <c:choose>
    						<c:when test="${user.isEnable ==1}">
                        			<td><a  onclick="onAndOff(this)" value="${user.id }">冻结</a>&nbsp;&nbsp; </td>
    						</c:when>
    						<c:otherwise>
                        			<td><a  onclick="onAndOff(this)" value="${user.id }">解冻</a>&nbsp;&nbsp; </td>
    						</c:otherwise>
					</c:choose>		 	
				 
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