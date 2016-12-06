<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%>
<script src="public/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		var user=$("#username1").val();
		if(user=="")
		{
				$("#usera").attr("href","login");
				$("#usera").html("登录");
				$("#user_nav_blog").hide();
				$("#user_quit").html("注册");
				$("#user_quit").attr("href","register");
				
		}
	})
</script>
 <div id="header">
			
             <input type="hidden" id="username1" value="${username}">
            <p class="h_r_3"></p><p class="h_r_2"></p><p class="h_r_1"></p>
            <div id="header_block" style="background:url(public/img/headerBg.jpg);">
				<div id="hd_info">
             <div id="cnts">
                 <%-- <div id="login_area"><span id="span_userinfo" style="color:#000000;font-size:16px"><a id="usera" href="#" style="color:#FFFFFF">${username}</a>·<a id="user_nav_blog" style="color:#FFFFFF" href="#">我的博客</a><a id="user_quit" href="quit" style="color:#FFFFFF" >退出</a><span id="current_spaceId" style="display:none">983725</span></span> --%>
				</div>
                <div class="clear">
				</div>
             </div>
        	</div>
                <div id="logo">
                    <h1>
                        <a href="#" title="您身边的的日记" style="text-decoration:none;"><span style="font:'宋体';color:#FFFFFF;font-size:36px">日志云</span></a>
                    </h1>
                </div>
				
                <div class="clear"></div>
            </div>
            <p class="h_r_1"></p><p class="h_r_2"></p><p class="h_r_3"></p>
        </div>