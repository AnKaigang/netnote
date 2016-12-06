$.fn.Instance = {
	Menuscoll: function(fh){
		var menuliHeight = $(this).siblings("ul").children("li"),
		liHeight = menuliHeight.height() + menuliHeight.unitExpression("padding-top") * 2;
		return $(this).siblings("ul").animate({scrollTop: fh + (liHeight - 3) * 5 }, 100);
	},
    unitExpression: function (ex) {
        if ($(this).css(ex).split(".").length > 1) {
            return parseInt($(this).css(ex).split(".")[0]);        }
        else {
            return parseInt($(this).css(ex).replace(/[^0-9]/ig, ""));
        }
    },
    reHeight: function(doc){
    	var divElement = doc.children();
    	resizeHeight = $(window).height()-(divElement.eq(0).height() + divElement.eq(3).height()) - 2;
    	divElement.eq(1).height(resizeHeight).children("ul").height(function(){
    		return $(this).parent().height() - 115;
    	}).parent().next().height(resizeHeight).width(function(){
    		return $(window).width() - $(this).prev().width();
    	});
    }
};
$(function(){	
    var body = $("body"),divElement = body.children(), stance = $.fn.Instance; 
    var intime = 150, ovtime = 50, leftMenuDivFrame = divElement.eq(1);
    function g(v){
    	$("#test").html(v);
    }
    divElement.eq(0).find("a[mut]").eq(0).addClass("selected").end().click(function(){//主菜单点击
		$(this).addClass("selected").parent().siblings().children("a[mut]").removeClass("selected");
		leftMenuDivFrame.children(".gninfo").find("ins").text($(this).text());//主菜单文字替换
		//没有子菜单的直接打开左侧
		//alert($(this).prop("outerHTML"));
		//alert($(this).attr("val"))
		if($(this).prev().find("a").length==0){
			var rootPath = getRootPath();
			$.post("menu/left/"+$(this).attr("val"),function(result){//ajax获取菜单
				leftMenuDivFrame.children("ul").empty();
				for(var i=0;i<result.length;i++){
					leftMenuDivFrame.children("ul").append($("<li href='"+result[i].url+"'><div><img width='100%' height='100%' src='"+result[i].image+"' /></div><span>"+result[i].name+"</span></li>").click(function(){
						$(this).addClass("hovlifunc").siblings().removeClass("hovlifunc");
						$("iframe")[0].src = $(this).attr("href");
						
					}));
				}
			},"json");
			leftMenuDivFrame.children(".gninfo").find("ins").text("功能菜单")
			.end().end().children(".textinfo").find("ins").text($(this).text())
			.end().end().children("ul").children().eq($(this).index()).addClass("hovlifunc")
			.siblings().removeClass();//二级菜单文字替换
		}
	}).mouseenter(function(){ //头部hover		
		/*if($(this).attr("add") == $(this).parents("ul").children().length - 1){			
			$(this).prev().css({ left: -88 });
		}控制最后一个菜单的位置*/
		
		$(this).prev().stop().slideDown(0,intime).mouseleave(function(){
			$(this).stop().slideUp(0);
		}).click(function(){//二级菜单div点击消失		
			$(this).slideUp(0);			
		}).children("a").click(function(){//二级菜单点击事件
			/*leftMenuDivFrame.children("ul").stop().animate({height: 0},300, function(){
				$(this).stop().animate({height: stance.reHeight(body)},200);
			});*/
			$(this).parent().next().addClass("selected").parent().siblings().children("a[mut]").removeClass("selected")
			var rootPath = getRootPath();
			$.post("menu/left/"+$(this)[0].id,function(result){//ajax获取菜单
				leftMenuDivFrame.children("ul").empty();
				for(var i=0;i<result.length;i++){
					leftMenuDivFrame.children("ul").append($("<li href='"+result[i].url+"'><div><img width='100%' height='100%' src='"+result[i].image+"' /></div><span>"+result[i].name+"</span></li>").click(function(){
						$(this).addClass("hovlifunc").siblings().removeClass("hovlifunc");
						$("iframe")[0].src = $(this).attr("href");
						
					}));
				}
			},"json");
			
			leftMenuDivFrame.children(".gninfo").find("ins").text($(this).parent().next().text())
			.end().end().children(".textinfo").find("ins").text($(this).text())
			.end().end().children("ul").children().eq($(this).index()).addClass("hovlifunc")
			.siblings().removeClass();//二级菜单文字替换
			
		});
	}).mouseleave(function(e){
		if(e.pageY < 88){
			$(this).siblings().stop().slideUp(ovtime);
		}
	}).end().end().eq(1).children("nav").click(function(){	//菜单箭头
		if($(this).index() > 2){
			$(this).Menuscoll("+=");
		}
		else
		{
			$(this).Menuscoll("-=");
		}		
	});//
//	.siblings("ul").children("li").on("click", function(){  //三级菜单		
//		alert("d");
//		$(this).addClass("hovlifunc").siblings().removeClass("hovlifunc");
//		$("iframe")[0].src = $(this).attr("href");
//	});
    
    /*----------start code---------*/
    stance.reHeight(body);
    $(window).keydown(function(evt){//键盘操作菜单
		g(evt.which);
	})
    $(window).resize(function(){
    	stance.reHeight(body);    	
    });
});
$.fn.extend($.fn.Instance);

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