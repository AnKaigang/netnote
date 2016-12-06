
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%
session.setAttribute("Imagename", "defaultHimg.jpg");
String isChange = (String)session.getAttribute("Imagename");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>Register</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link rel="stylesheet" href="public/css/register.css">
<script type="text/javascript" src="public/js/jquery.js"></script>
<script type="text/javascript" src="public/js/layer.js"></script>
<script type="text/javascript">
    $(function(){
       $("#submit").click(function(){
               if($.trim($("#name").val())=="")
              {
                  alert("昵称不能为空！");
                  $("#name").focus();
                  return false;
              }
               var namelength = document.getElementById("name").value.length;
              if(namelength>15 || namelength<6 )
              {
              	  alert("昵称长度不符！");
                  $("#name").focus();
                  return false;
              }
              if($.trim($("#account").val())=="")
              {
                  alert("账号不能为空！");
                  $("#account").focus();
                  return false;
              }
              var accountlength = document.getElementById("account").value.length;
              if(accountlength>15 || accountlength<6 )
              {
              	  alert("账号长度不符！");
                  $("#account").focus();
                  return false;
              }
               if($.trim($("#password").val())=="")
              {
                  alert("密码不能为空！");
                  $("#password").focus();
                  return false;
              }
              if($.trim($("#password1").val())=="")
              {
                  alert("确认密码不能为空！");
                  $("#password1").focus();
                  return false;
              }
              if($.trim($("#password1").val())!=$.trim($("#password").val()))
              {
                  alert("两次输入密码不同！");
                  $("#password1").focus();
                  $("#password1").val("");
                  return false;
              }
              if($.trim($("#mail").val())=="")
              {
                  alert("邮箱不能为空！");
                  $("#mail").focus();
                  return false;
              }
              var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
              if(!reg.test($("#mail").val()))
              {
                  alert("邮箱格式不正确！");
                  $("#mail").focus();
                  return false;
              }
              if($.trim($("#VerifyCode").val())=="")
              {
                  alert("验证码不能为空！");
                  $("#VerifyCode").focus();
                  return false;
              }
          	  $.post("getVerifyCode",function(data,textStatus){
          	    var rightcode = data;
          	    
          	    inputcode = $("#VerifyCode").val();
          	    if( inputcode.toLowerCase()==rightcode.toLowerCase())
          	    {
          	        test();
          	        
          	         //document.getElementById("registerform").submit();
          	    }else{
          	         test();
          	         $("#VerifyCode").val("");
          	         $("#VerifyCode").focus();
          	         alert("验证码不正确！");
          	         return false ;
          	    }
          	  }
          	  );
          	  
       
       }
       )
    }
    )
    
    </script>
</head>
<script type="text/javascript">
  	function test(){
		var image = document.getElementById("VerifyCodeImg");
		var myDate = new Date();
		image.src = "producecode?time="+myDate.getTime();
  	 }
  	 
  	 window.onload=function(){
  	 test();
  	 }
  	 function getMyfilepath()
  	 {
  	 		$("#getimgpath").val($("#imgpath").val());
  	 		$("#header").attr("src",$("#imgpath").val());
  	 }
  </script>
<script>
  function CheckName(){
        if($.trim($("#name").val())=="")
        {
        	return false ;
        }
  		 $.post("VerifyName",{"name":$.trim($("#name").val())},function(data,textStatus){
  		        
  		 		if(data =="error")
  		 		{
  		 		 	alert("此用户名已存在！");
  		 		 	$("#name").val("");
  		 		 	
  		 		}
  		 })
  }
  </script>
<script type="text/javascript">
  function getMyfilepath()
  {
  		$("#getimgpath").val($("#imgpath").val());
  }
 function shangchuan(){
  		$.post($("#FormMyImg").submit(),function(data,textStatus){
  				
  		});
  		document.getElementById("header").src="public/img/loading.gif";
  		setTimeout(checkloadImg,3000);
  		
   }
  </script>
  <script type="text/javascript">
  function CheckAccount()
  {
  		if($.trim($("#account").val())=="")
              {
                  return false;
              }
  		 $.post("VerifyAccount",{"account":$.trim($("#account").val())},function(data,textStatus){
  		 		if(data =="error")
  		 		{
  		 		}else{
  		 			alert("此账号已存在！");
  		 		 	$("#account").val("");
  		 		}
  		 });
  }
  </script>
  <script type="text/javascript">
  function checkloadImg()
  {
  		$.post("HeadFileName",function(data,textStatus){
  		 		 var image = document.getElementById("header");
  		 		 image.src = "headImage/"+data;
  		 		
  		 		alert("上传成功！");
  		});
  }
  </script>
<body>
<iframe name="submitForm" style="display:none"></iframe>
	<section class="container">

	<div class="regist" align="center">

		<h1>Register</h1>

			<table border="0">
					
				<tr>
					<td><input type="image" name="header" id="header"
						style="width:100;height:100" onclick="return false;"
						src="headImage/defaultHimg.jpg"></input></td>
					<td>
					<form action="upload2" method="post" enctype="multipart/form-data"
			name="FormMyImg" id="FormMyImg" target="submitForm">
						<table>
							<tr>
								<td><label class="title">上传头像：</label></td>
								<td></td>
							</tr>
							<tr>

								<td>
									<div class="fileInput left">
										<input type="file" class="upfile" name="imgpath" id="imgpath"
											onchange="getMyfilepath();" /> <input type="button"
											class="upFileBtn" onclick="imgpath.click();" name="get_file" />
									</div>

								</td>
								<td>
									<div align="right">
										<input type="text" name="getimgpath" id="getimgpath" readonly
											style="width:150px; height:31px" /><input type="button"
											name="Myupload" id="Myupload" style="width:30px;height:31px"
											value="上传" onclick="shangchuan()"/>
									</div>
								</td>
							</tr>
						</table>
						</form>
					</td>
				</tr>
					<form action="RigistCheck" method="post" id="registerform" name="registerform">
				<tr>
					<td align="right"><label class="title">昵 称：</label></td>
					<td><label><input type="text" name="name" id="name"
							onblur="CheckName()"></input></label><label style="font-size:14px"><font color="red" >*(6-15字符)</font></label></td>
				</tr>
				<tr>
					<td align="right"><label class="title">账 号：</label></td>
					<td><label><input type="text" name="account" id="account"
							onblur="CheckAccount()"></input></label><label style="font-size:14px"><font color="red" >*(6-15字符)</font></label></td>
				</tr>
				<tr>
					<td align="right"><label class="title">密 码：</label></td>
					<td><label><input type="password" name="password"
							id="password"></input></label><label><font color="red">*</font></label></td>
				</tr>
				<tr>
					<td align="right"><label class="title">确认密码：</label></td>
					<td><label><input type="password" name="password1"
							id="password1"></input></label><label><font color="red">*</font></label></td>
				</tr>
				<tr>
					<td align="right"><label class="title">邮 箱：</label></td>
					<td><label><input type="email" name="mail" id="mail"></input></label><label><font
							color="red">*</font></label></td>
				</tr>
				<tr>
					<td align="right"><label class="title">验 证 码：</label></td>
					<td><label><input type="text" name="VerifyCode"
							id="VerifyCode" style="width:90px; height:25px" /></label></label><label><font
							color="red">*</font></label><label style="position:relative;"> <img
							align="bottom" style="cursor:pointer;" name="VerifyCodeImg"
							id="VerifyCodeImg" onclick="test()" src="producecode" />
					</label></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align="left"><input type="submit" name="submit"
						id="submit" value="注   册" style="position:relative; left:5px;"></input><input type="button" value="返回" style="position:relative; left:65px;" onclick="location.href='login'"/></td>
				</tr>
			</table>	
	</div>
	</form>
	</section>
</body>
</html>
