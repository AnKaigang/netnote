
// Cloud Float...
    var $main = $cloud = mainwidth = null;
    var offset1 = 450;
	var offset2 = 0;
	
	var offsetbg = 0;
    
    $(document).ready(
        function () {
            $main = $("#mainBody");
			$body = $("body");
            $cloud1 = $("#cloud1");
			$cloud2 = $("#cloud2");
			
            mainwidth = $main.outerWidth();            
            
            $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
        	$(window).resize(function(){   
            $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            });               
            var inText = $(":text, :password"), loginTip = $(".loginTip"), 
            	inArray=new Array(), isChecked = false, btn = $(":button");
        	inText.each(function(){
        		inArray.push($(this).attr("tip"));
        	}).keydown(function(et){
        		if(et.which == 13) {btn.trigger("click");}
        	});
        	
        	btn.click(function(){
        		for(var i = 0; i < inText.length; i++){
        			if(!inText.eq(i).val()){loginTip.text(inArray[i]);inText.eq(i).focus(); return; }
        		} 
        		var password="";
        		$.get("passwordCode",function(code){
        			if(code.length>50){
        				password=hex_sha1(code+hex_sha1(inText.eq(1).val()));
        				var postData = { username: inText.eq(0).val(), password:password,checkCode:inText.eq(2).val()};   
                		var rootPath = getRootPath();
                		$.post(rootPath+"/admin/pass", postData, function(data){
                			if(data.info==1){         				
//                				if($(":checkbox").attr("checked") == true){//记住用户名和密码VW5hbWVz | VXBhc3M=        					
//                					$.cookie(base64encode("Upass"), base64encode(data.pass));
//                				}     
                				
                				//$.cookie("Unames", data.user);
                				window.location.href = "main";
                			}
                			else
                				{
                					loginTip.html(data.error);
                					$("#checkCode").attr("src","verifyCode");
                				}        			
                		}, "json");  
        			}
        			else{
        				loginTip.html("获取密钥错误！");
        				$("#checkCode").attr("src","verifyCode");
        			}
        		});  		
        	});
        }
    );

    /// 飘动
    setInterval(function flutter() {
        if (offset1 >= mainwidth) {
            offset1 =  -580;
        }

        if (offset2 >= mainwidth) {
			 offset2 =  -580;
        }
		
        offset1 += 1.1;
		offset2 += 1;
        $cloud1.css("background-position", offset1 + "px 100px")
		
		$cloud2.css("background-position", offset2 + "px 460px")
    }, 70);
	
	
	setInterval(function bg() {
        if (offsetbg >= mainwidth) {
            offsetbg =  -580;
        }

        offsetbg += 0.9;
        $body.css("background-position", -offsetbg + "px 0")
    }, 90 );
	
	//js获取项目根路径，如： http://localhost:8083/uimcardprj
	function getRootPath(){
	    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	    var curWwwPath=window.document.location.href;
	    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	    var pathName=window.document.location.pathname;
	    var pos=curWwwPath.indexOf(pathName);
	    //获取主机地址，如： http://localhost:8083
	    var localhostPaht=curWwwPath.substring(0,pos);
	    //获取带"/"的项目名，如：/uimcardprj
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	    return(localhostPaht+projectName);
	}