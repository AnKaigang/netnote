<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
%>
<html>
<head>
	<link href="${pageContext.request.contextPath }/public/css/H-ui.min.css" rel="stylesheet" type="text/css" />
		
	<title>回复</title>
	<script language="JavaScript" src="${pageContext.request.contextPath }/public/js/jquery.js"></script>
	<script language="JavaScript" src="${pageContext.request.contextPath }/public/js/layer.js"></script>
	<link href="${pageContext.request.contextPath }/public/admin/style.css" rel="stylesheet" type="text/css" />
	
    <style>
        .form-horizontal .row {
        }

        .form-horizontal .row-100 {
            width: 100%;
            display: table;
            margin-top: 15px;
            box-sizing: border-box;
        }

        .form-horizontal .row-75 {
            display: table;
            margin-top: 15px;
            box-sizing: border-box;
        }

        .form-horizontal .row-25 {
            display: table;
            margin-top: 15px;
            box-sizing: border-box;
        }

        .col-13 {
        }

        .col-14 {
        }

        .col-15 {
        }

        .mt-15 {
            margin-top: 15px;
        }

        .photo-img {
            height: 123px;
            overflow: hidden;
        }

        .photo-img img {
            width: 100%;
        }
        
        
        .pd-20{margin: 0 auto;}
        .uoloadiv::before{
			color: #eee;
		    content: "+";
		    font-size: 200px;
		    line-height: 86px;
		    font-family: 'monospace';		    
		}	
		.uoloadiv:hover::before{
			color: #EDF6FA; 
		}
		body .pd-20 .form-horizontal .uoloadiv{
			cursor: pointer;border:1px solid #ddd; width:152px;
		}
         #uploadimages{
			display: none;
		}
		.uoloadiv img{
			position: absolute; left:0;top:0;
		}
    </style><script type="text/javascript">
        function quit(){
        	window.opener=null;
        	window.close();
        }
        function submit1(){
       
        	$.ajax({
    	    	type: 'post',
    	    	url: '${pageContext.request.contextPath }/addReply',
    	    	data: {'rId':$("#rId").val(),'content':$("#content").val(),'rUId':$("#rUId").val(),'aId':$("#aId").val()},
    	    	dataType:"json",
    	    	success: function(data) {
    	       			if(data=="1"){
    	       			 	layer.alert("回复成功~");
    	       			 	window.open('${pageContext.request.contextPath }/articledetail?articleid='+$("#aId").val(),'_parent'); 
    	       			}
    	       			else{
    	       				layer.alert("回复失败！");
    	       			}    	       				
    	    	}
    	    });
        }
        </script>

</head>
<body>

	<div class="place">
	    <span>位置</span>
	    <ul class="placeul">
		    <li><span>首页</span></li>
		    <li><span>回复</span></li>
	    </ul>	
	    <a href="javascript:void(0);" class="shq-btn-close"></a>    
    </div>
    <br/>
    
        <form id="form1" method="post" style="width:600px;text-align: center;"  >
            	<div style="text-align: center;">
                  		回复内容：
                        <input type="text" style="width:300px;max-width: 200px" id="content" name="content"  value=" " class="input-text">
                        <div style="margin-top:10px;display: inline-block;margin-left:10px">
                         <input type="hidden" id="aId" name="id" value="${aId }">
                         <input type="hidden" id="rUId" name="id" value="${rUId }">
                         <input type="hidden"  id="rId" name="id" value="${rId }">
                    <button class="btn btn-primary radius" type="button" onclick="submit1()" id="submit" style="width:80px">  确认</button>
                    <button style="margin-left:30px;width:80px;display: inline-block;" class="btn btn-default radius"  type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
               </div></div>    
        </form>
        
</body>
</html>
