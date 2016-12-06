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
	<title>回收站</title>
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
	 
	  $("#user").keyup(function(){
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
		$.post("${pageContext.request.contextPath}/admin/rebackDiary",{"id":obj.getAttribute("value")},
				function(data){
			      if(data=="sucess"){
			    	  window.open('${pageContext.request.contextPath}/admin/recycleDiary','_self'); 
			      }else{
			    	  layer.alert("还原失败");
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
    	    	success: function(a) {
    	    		if(a!="0"){
	       			 	layer.alert("您已成功清除"+a+"篇日记");
	    	    		window.open('${pageContext.request.contextPath}/admin/recycleDiary','_self'); 
    	    		}  
    	    		else{
    	    			layer.alert("清空失败");
    	    		}

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
    <li><a href="#">回收站</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
          <form action="${pageContext.request.contextPath}/admin/recycleDiary" method="get" id="form1">
		 <input type="hidden" id="currentPage" name="page" value="${page.page}"/>
		 <input type="hidden" id="size2" name="pageSize" value="${page.pageSize}"/>
		 <input type="hidden" name="count" value="${page.count}"/>
		 <input type="hidden" name="houseConditionQuery" value="queryTrue">
		 	<div class="row cl" style="display:inline-block;">
		<span style="display:inline-block;">
		 <label class="dian-le">发表人:</label>
		<input id="user" value="${user}" onchange="requestDiarySort()" type="text" name="username" style="border:1px; solid:#ccc; background-color:#FFFFFF;border-color:#CCCCCC; border-style:solid; border-radius: 3px;    width: 120px;	height: 28px;    line-height:30px;    text-align:left;    padding-left:10px;">
		<ul class="pasgersour-selector-zhineng" id="searchEID" value="${uId }"></ul>
		</span >
		</div>
		<div class="row cl" style="display:inline-block;margin-bottom:0px;">
		 <label class="dian-le" style="margin-bottom:0px;">文章类型:</label><label class="dian-le"></label>
		 <div class="formControls col-1" style="margin-bottom:0px;">
         <span class="select-box2">
		 <select name="select" id="disId1" class="select" style="width:110px">
		 	<option value="" >全部</option>
		 	<c:forEach items="${sortList }" var="sort">		 	
		 			<c:choose>
    						<c:when test="${sId ==sort.id}">
                        			<option value="${sort.id}"  selected="selected"> ${sort.name}</option>
    						</c:when>
    						<c:otherwise>
                        			<option value="${sort.id}" > ${sort.name}</option>
    						</c:otherwise>
					</c:choose>		 	
		 	</c:forEach>
		 </select>
		 </span>
		 </div>
		 </div>
		 
		
		<div class="row cl" style="margin-left:20px;display:inline-block;">
		  <span id="jiage"></span>
		 <label class="dian-le" style="margin-left:30px">发表日期</label><label class="dian-le">:</label>
           <span class="select-box2" style="display:inline-block;">
		<input class="select" type="text" name="startDate" onfocus="MyCalendar.SetDate(this)" value="${start}" style="width:120px">-
		<input class="select" type="text" name="endDate" onfocus="MyCalendar.SetDate(this)" value="${end}"  style="width:120px">
		</span>
		</div>
		<div class="row cl" style="margin-left:20px;display:inline-block;">
         &nbsp;&nbsp;&nbsp;&nbsp;
		 <input type="submit" value="查询"class="btn btn-primary radius" style="display: inline-block">
		</div>
		
<br/><hr>
    </form> 
    <div class="tools" style="margin-top:20px">
    	<ul class="toolbar">
	        <li onclick="deleteDep()" ><span><img src="${pageContext.request.contextPath }/public/img/t03.png" /></span>清空日记</li>
        </ul>
    </div>
	
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" id="ckAll" onclick="cli();"/></th>
        <th>文章编号</th>
        <th>标题</th>
        <th>发表日期</th>
        <th>发表人</th>
        <th>点赞数</th>
        <th>踩数</th>
        <th>类别</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
	        <c:forEach items="${articleList}" var="article" varStatus="status">
	        	 <tr  class="houseId">
			     <td><input name="houseCk" type="checkbox" value="${article.id}" ></td>
			     <td>${status.count}</td>
			     <td>${article.title}</td>
			     <td>${article.date}</td>
			     <td>${article.addParam1}</td>
			     <td>${article.agree}</td>
			     <td>${article.disagree}</td>
			     <td>${article.readnum}</td>			     
				 <td><a onclick="onAndOff(this)" value="${article.id }">还原</a> </td>
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