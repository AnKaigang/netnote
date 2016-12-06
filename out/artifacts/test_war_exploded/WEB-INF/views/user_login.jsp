<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Login</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="public/css/Loginstyle.css">
  <script type="text/javascript">
  	function test(){
		var image = document.getElementById("VerifyCodeImg");
		var myDate = new Date();
		image.src = "producecode?time="+myDate.getTime();
  	 }
  </script>
<script type="text/javascript" src="public/js/jquery.js"></script>
    <script type="text/javascript">
    $(function(){
       $("#commit").click(function(){
              
              if($.trim($("#account").val())=="")
              {
                 alert("账号不能为空！");
                 $("#account").focus();
                  return false;
              }
           
              if($.trim($("#password").val())=="")
              {
                  alert("密码不能为空！");
                  $("#password").focus();
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
          	        document.getElementById("loginform").submit();
          	        
          	    }else{
          	         test();
          	         alert("验证码不正确！");
          	         $("#VerifyCode").val("");
          	         $("#VerifyCode").focus();
          	         return false;
          	    }
          	  }
          	  )
       
       }
       )
    }
    )
 </script>
 <script type="text/javascript">
 function GetRemember()
 {
   $.post("GetMyRemember",{"name":$.trim($("#account").val())},function(data,textStatus){
   		
   		if(data=="")
   		{
   		}else{
   			$("#password").val(data);
   		}
   });
 }
 </script>
  </head>
  
  <body  >
   <section class="container" >
     <br>
     <div class="login" align="center">
     <h1>My Netnote</h1>
     <br/>
     <label ><font style="font-size:15px" color="${color}">${error}</font></label>
    <form action="loginCheck" method="post" id="loginform" >
      <p><label class="title">账&nbsp;&nbsp;号：</label><input type="text" width="20px" name="account" id="account" onblur="GetRemember()" placeholder="UserId or Email"></p>
        <p><label class="title">密 &nbsp;&nbsp;码：</label><input type="password" name="password" id="password" value="" placeholder="Password"><br/>
        
        </p>
        <p class="remember_me" >
          <label >
             <input type="checkbox" name="remember_me" id="remember_me" value="remember">
            Remember me</input>     
          </label>
          <label></label>
          <label class="MyLink">
          <a href="modifypassword">忘记密码</a> 
          </label>
        </p><br><br>
        <p align="center"><label class="title">验证码：</label><label><input type="text" name="VerifyCode" id="VerifyCode" style="width:90px ;height:25px; "/></label>
        <img align="bottom" style="cursor:pointer;" name="VerifyCodeImg" id="VerifyCodeImg" onclick="test();" src="producecode"/></p>
        <p><label ><input  type="button" name="commit" id="commit" value="Login"/></label>
        <label class="Mybutton"><input type="button" name="register" id="register" value="regist" onclick="location.href='register'" ></input> </label></p>
      </form>
      
    </div>
  </section>
  <secion ><div style="position:relative ;top:70; left:36%"><a href="" style="font-weight: bold" onclick="return false;"> Copyright &copy;  2016 TALLLH Corporation,  All Rights Reserved</a></div> 
 </secion>
  </body>
</html>
