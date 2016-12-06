<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link href="${pageContext.request.contextPath }/public/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath }/public/admin/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/public/js/WdatePicker.js"></script>
    <script type="text/javascript">
    	function checkPw() {
    		var pw1 = $("[name='ePassword1']").val();
    		var pw2 = $("[name='ePassword2']").val();
	        if(pw1 == pw2) {
        	/* alert("两次密码一致"); */
            /* document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";*/
            document.getElementById("submit").disabled = false;
        }
        else {
            /* document.getElementById("tishi").innerHTML="<font color='red'>两次密码不相同</font>"; */
          document.getElementById("submit").disabled = true;
        	alert("两次密码不一致，请重新输入");
        }
    }
    </script>
    <style>
        .form-horizontal .row {
            width: 50%;
        }

        .form-horizontal .row-100 {
            width: 100%;
            display: table;
            margin-top: 15px;
            box-sizing: border-box;
        }

        .form-horizontal .row-75 {
            width: 75%;
            display: table;
            margin-top: 15px;
            box-sizing: border-box;
        }

        .form-horizontal .row-25 {
            width: 25%;
            display: table;
            margin-top: 15px;
            box-sizing: border-box;
        }

        .col-13 {
            width: 11.11110%;
        }

        .col-14 {
            width: 38.8889%;
        }

        .col-15 {
            width: 88.8889%;
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
        
        
        .pd-20{
        width: 55%; margin: 0 auto;}
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
    </style>
    <title>基本信息</title>
    <!-- 引用控制层插件样式 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/public/css/zyUpload.css" type="text/css">
		
		<script type="text/javascript" src="${pageContext.request.contextPath }/public/js/jquery.js"></script>
		<!-- 引用核心层插件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath }/public/js/zyFile.js"></script>
		<!-- 引用控制层插件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath }/public/js/zyUpload.js"></script>
		<!-- 引用初始化JS -->
		<script type="text/javascript" src="${pageContext.request.contextPath }/public/js/layer.js"></script>
		<!-- 引用操作功能JS -->
		<script type="text/javascript" src="${pageContext.request.contextPath }/public/js/JsforindexBody.js"></script>
		<script type="text/javascript">
        function quit(){
        	window.opener=null;
        	window.close();
        }
		function submit1(){
			$.ajax({
    	    	type: 'post',
    	    	url: '${pageContext.request.contextPath }/admin/add',
    	    	data: {'name':$("#name").val(),'id':$("#id").val(),'ePassword1':$("#ePassword1").val()},
    	    	dataType:"json",
    	    	success: function(data) {
    	       			if(data=="1"){
    	       			 	
    	       			 	if($("#id").val()===""){
    	       			 		layer.alert("添加成功");
    	       			 	}else{
    	       			 		layer.alert("修改成功");
        	       			 	window.open('${pageContext.request.contextPath }/admin/adminList','_parent'); 
    	       			 	}
    	       			}
    	       			else{
    	       				if($("#id").val()===""){
        	       			 		layer.alert("添加失败");
        	       			 	}else{
        	       			 		layer.alert("修改失败");
            	       			 	window.open('${pageContext.request.contextPath }/admin/adminList','_parent'); 
        	       			 	}
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
		    <li><span>系统管理员</span></li>
		    <li><span>添加/修改</span></li>
	    </ul>	
	    <a href="javascript:void(0);" class="shq-btn-close"></a>    
    </div>
    <br/>
    
    <div class="pd-20">
        <form id="form1" action="" method="post"   class="form form-horizontal">
            <div class="row-75 cl f-l">
       

                <div class="mt-15">
                    <label class="form-label col-13" style="width:120px">管理员用户名：</label>
                    <div class="formControls col-14">
                        <input id="name" type="text" name="name" placeholder="请输入用户名" value="${admin.name }" class="input-text">
                         <input id="id" type="hidden" name="id" value="${admin.id }" >
                    </div>
                </div>
                <div style="clear: both"></div>
            </div>

			<div class="row-75 cl f-l">
                <label class="form-label col-13" style="width:120px">登录密码</label>
                <div class="formControls col-14">
                    <input id="ePassword1" type="password" name="ePassword1" placeholder="请输入登录密码" value=""class="input-text">
                </div>
            </div>
            <div style="clear: both"></div>
            <div class="row-75 cl f-l">
                <label class="form-label col-13" style="width:120px">确认密码</label>
                <div class="formControls col-14">
                    <input type="password" name="ePassword2" onblur="checkPw();" placeholder="请再次输入密码" value="" class="input-text">
                </div>
            </div>
            <div class="row cl" style="margin-top:30px">
                <div class="col-10 col-offset-2" style="margin-top:30px">
                    <button class="btn btn-primary radius" onclick="submit1()" type=button id="submit" style="width:80px"> <i class="Hui-iconfont">&#xe632;</i> 确认</button>
                    <button onclick="quit()" style="margin-left:30px;width:80px" class="btn btn-default radius"  type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
