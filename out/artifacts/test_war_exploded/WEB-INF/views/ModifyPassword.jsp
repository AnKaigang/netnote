<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Modify Password</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="public/css/MyModifyStyle.css">
<script type="text/javascript" src="public/js/jquery.js"></script>
  </head>
  <script type="text/javascript">
  function shezhi(){
  		document.getElementById("CheckUserId").style.display ="";
  		document.getElementById("CheckEmail").style.display ="none";
  		document.getElementById("ModifyPawd").style.display ="none";
  		document.getElementById("next1").disabled=true;
  		document.getElementById("next2").disabled=true;
  }
  function dislayAccount(){
  	    document.getElementById("CheckUserId").style.display ="";
  		document.getElementById("CheckEmail").style.display ="none";
  		document.getElementById("ModifyPawd").style.display ="none";
  		
  }
  function displayEmail()
  { 
  		document.getElementById("CheckUserId").style.display ="none";
  		document.getElementById("CheckEmail").style.display ="";
  		document.getElementById("ModifyPawd").style.display ="none";
  }
  function displayModify()
  { 
  		document.getElementById("CheckUserId").style.display ="none";
  		document.getElementById("CheckEmail").style.display ="none";
  		document.getElementById("ModifyPawd").style.display ="";
  }
  </script>
  <script type="text/javascript">
  function CheckName(){
  		 if($.trim($("#account").val())=="")
              {
                  alert("账号不能为空！");
                  return false;
              }
  		 $.post("VerifyAccount",{"account":$.trim($("#account").val())},function(data,textStatus){
  		 		if(data =="error")
  		 		{
  		 		 	alert("此账号不存在！");
  		 		 	$("#content").val("");
  		 		 	
  		 		}else{
  		 		 	document.getElementById("next1").disabled=false;
  		 		}
  		 });
  }
  </script>
  <script type="text/javascript">
  function VerifyMymail()
  {
        if($.trim($("#mail").val())=="")
              {
                  alert("邮箱不能为空！");
                  return false;
              }
              var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
              if(!reg.test($("#mail").val()))
              {
                  alert("邮箱格式不正确！");
                  return false;
              }
  		var email =$.trim($("#mail").val());
  		 $.post("VerifyMail",{"mail":email,"account":$.trim($("#account").val())},function(data,textStatus){
  		 		if(data =="error")
  		 		{
  		 		 	alert("此邮箱不是注册邮箱！");
  		 		}else{
  		 		 	document.getElementById("next2").disabled=false;
  		 		}
  		 });
  }
  </script>
  <script type="text/javascript">
  function Verifypassword()
  {
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
  		var pwd1=$("#password").val();
  		var pwd2=$("#password1").val();
  		if(pwd1 !=pwd2)
  		{
  			alert("两次密码输入不同");
  			$("#password1").val("");
  			$("#password1").focus();
  			return false;
  		}
  		
  		var email =$.trim($("#mail").val());
  		$.post("ModifyPasswd",{"password":$.trim($("#password").val()),"mail":email,"account":$.trim($("#account").val())},function(data,textStatus){
  		 		if(data =="true")
  		 		{
  		 		 	alert("密码修改成功！");
  		 		 	window.location.href="login";
  		 		}else{
  		 		 	alert("账号与邮箱不匹配");
  		 		}
  		 });
  }
  </script>
  <script type="text/javascript">
  var rightmailCode;
  function SendCodetoMail()
  {
  		
  		var email =$.trim($("#mail").val());
  		$.post("getVerifyCodeOfMail",{"mail":email},function(data,textStatus){
  				alert(data);
  				if(data=="error")
  				{
  					alert('发送失败');
  				}else{
  				     rightmailCode = data;
  				}
  		});
  }
  function VerifyMailCode()
  {
 		var  tt= $.trim($("#VerifyCode").val());
 		if(tt !=rightmailCode)
 		{
 			alert("验证码错误！");
 			$("#VerifyCode").focus();
 			return false ;
 		}else{
 			displayModify();
 		}
 		
  }
  </script>
  <body onload="shezhi()">
   <section class="container" >
     <br>
     <div class="login" align="center" id="maindiv">
     <h1>Modify Password</h1>
     <br>
     <form>
     <div id="CheckUserId" class="login1" align="center" >
     <h1><label><img name="InputAccount" id="InputAccount" onclick="return false;" src="public/img/inputaccount.png"  style="width:400px ; height:64px" /></label> </h1>
       <br>
     <h2>输入账号</h2>
   
     <br>
     <p><label class="title">账号：</label><input type="text" name="account" id="account" onblur="CheckName()"/></p>
     <br>
     <p><input type="button" value="返  回" onclick="window.location.href='login'"  style="position:relative ;left:-40px"/><input type="button" name="next1" id="next1" value="下一步" class="button blue larrow" onclick="displayEmail()"  style="position:relative ;left:90px"/></p>
     </div>
      <div id="CheckEmail" class="login1" align="center">
      <h1><label><img name="InputMail" id="InputMail" onclick="return false;" src="public/img/inputmail.png"  style="width:400px ; height:64px" /></label> </h1>
      <br>
      <h2>邮箱验证</h2>
      <br>
      <table>
      <tr>
      <td align="right"><label >邮&nbsp;&nbsp;箱：</label></td><td><input type="text" name="mail" id="mail" onblur="VerifyMymail()"></td>
      </tr>
      <tr style="height:15px"><td></td><td></td></tr>
      <tr >
      <td align="right"><label>验证码：</label></td>
      <td> <input type="text" name="VerifyCode" id="VerifyCode" style="width:90px;height:25px"><label>
          <label><input type="button" name="SendEmail" id="SendEmail" value="发送邮箱验证码" onclick="SendCodetoMail()"/></label>
      </td>
      </tr>
      <tr style="height:20px"><td></td><td></td></tr>
      <tr>
         <td align="right"><input type="button" name="previx1" id="previx1" value="上一步" onclick="dislayAccount()"/></td>
         <td align="right"><input type="button" name="next2" id="next2" value="下一步" onclick="VerifyMailCode()"/></td>
      </tr>
      </table>
      </div>
      <div id="ModifyPawd" class="login1" align="center">
       <h1><label><img name="InputPawd" id="InputPawd" onclick="return false;" src="public/img/inputpawd.png"  style="width:400px ; height:64px" /></label> </h1>
      <br>
      <h2>更改密码</h2>
      <br>
      <table>
      <tr><td align="right">输入密码：</td><td><input type="password" name="password" id="password"/></td></tr>
      <tr style="height:15px"><td></td><td></td></tr>
      <tr><td align="right">确认密码：</td><td><input type="password" name="password1" id="password1"/></td></tr>
      <tr style="height:20px"><td></td><td></td></tr>
      <tr>
      	<td align="right"><input type="button" name="previx2" id="previx" value="上一步" onclick="displayEmail()"></td>
      	<td align="right"><input type="button" name="submit" id="submit" value="提    交" onclick="Verifypassword()"></td>
      </tr>
      </table>
      </div>
      </form>
    </div>
  </section>
  </body>
</html>
