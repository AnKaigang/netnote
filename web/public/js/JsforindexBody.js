$.fn.Instance = {
	Locahref: function(n){
		if(!n||isNaN(n))n=1;
		var winhf = window.location.href, wfplit = winhf.split("/");
		return wfplit[wfplit.length - n];
	},
    unitExpression: function (ex) {
        if ($(this).css(ex).split(".").length > 1) {
            return parseInt($(this).css(ex).split(".")[0]);        }
        else {
            return parseInt($(this).css(ex).replace(/[^0-9]/ig, ""));
        }
    },
    isCodeValid: function(sfz){
    	var Valid = !!sfz.match(/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i);
    	return (!Valid) ? false : true;
    },
    isMobil: function (tel) {

        var telReg = !!tel.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);

        return (!telReg) ? false : true;
    },
    isNamerg: function (name) {

        var namerg = /[\u4e00-\u9fa5]/;

        return (!namerg.test(name)) ? false : true;
    },

    CheckMail: function (mail) {

        var checkStr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

        return (!checkStr.test(mail)) ? false : true;
    },

    CheckIsIE: function (version) {

        if ($.browser.msie) {

            var VersionStr = $.browser.version.replace(/[^0-9]/ig, "");

            return (parseInt(VersionStr.substr(0, VersionStr.length - 1)) == version) ? true : false;
        }
        else {
            return false;
        }
    },
    ValidationIsEmpty: function(body){    	
    	for(var i=0;i<$(this).length;i++){
    		if(!$(this).eq(i).val()){
    			this.alterInfo($(this).eq(i).attr("placeholder")+"的值不能为空!");
    			body.children().last().find("a").inptFocus($(this).eq(i)); 
    			return false;
    		}
    	}    	
    	return true;
    },    
    inptFocus: function(obj){    	
    	return $(this).on("click", function(){
    		obj.select();
    	});
    },
    alterInfo: function(info, fn){
    	layer.alert(info, {
            skin: 'layui-layer-lan',
            closeBtn: 1,
            shift: 6 //动画类型
        }, fn);
    	
    }
}

 
$(function(){
	var body = $("body"),divElement = body.children(), stance = $.fn.Instance; 
	var locaherfString = (stance.Locahref(1).indexOf("?") > 0) ? stance.Locahref(1).split("?")[0] :stance.Locahref(1);
	switch(locaherfString){
		case "index":
			var BtnAll = $(":button"), formFytext = $("form").eq(0).find(":text");			
			BtnAll.eq(0).click(function(){
				formFytext.each(function(k){$(this).val(k)});
				
			});
			
			break;
		case "findALLMenu":
			$("table tr:not(:first):nth-child(odd)").css({ backgroundColor: "#f5f8fa" });
			break;
		
		case "toAdd":	//业务设置     组织机构    新增员工	
			$(".uoloadiv").click(function(){				
				$(".webuploader_pick").click();//点击打开图片筛选弹出框
			});
			$.diaologFunc("uploadimages", "uploadImg");//上传图片的DIV-ID与AJAX路径
			break;		
		case "addInquiryTable":	
		case "findInquiryList":	
		case "uqdateInquiryTable":	
		case "new":
			$(".focusText").focus(function(){
				$(this).select().next().fadeIn(100);
			}).blur(function(){
				$(this).next().fadeOut(300);
			}).keyup(function(){
				var $this = $(this);
				if($(this).val()){ //智能查询与楼盘字典查询
		    		$.post($(this).attr("key"),{ estateValue: $(this).val(),
		    			                         cId:$("#city option:selected").val(),
		    			                         eId:$("#city2 option:selected").val(),
		    			                         bId:$("#city3 option:selected").val()},function(data){
		    			if(data.length){
		    				$this.next().empty();    			
			    			for(var i=0;i<data.length;i++){
			    				$this.next().append($("<li>").attr({ value: data[i].eId }).text(data[i].eName)).fadeIn(100);     	    		
			   	    		}  
		    			}		
		    			else{
		    				$this.val(null).attr({placeholder: "楼盘字典不存在此小区名，请重输入"});
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
					$.post("hpAddress2", 
							{ "estateId1": $this.attr("value")}, 
							function(data){
								//alert(JSON.stringify(data));
								$("#hpAddress1").empty();
								$("#hpAddress2").empty();
								for(var i=0;i<data.length;i++){
									$("#hpAddress1").append("<option value="+data[i].bId+">"+data[i].bBuildingName+"</option>");
									if(i==0){
										for(var j=1;j<data[i].bCell+1;j++)
										$("#hpAddress2").append("<option value="+data[i].bId+">"+j+"单元"+"</option>");
									}
								}
					});
			});	
			//loadUnit();
			if(locaherfString == "new"){
				var StorageHideObjec = [];
				$.diaologFunc("uploadimages", "uploadImg");//上传图片调用方法(id,ajax-pach);	
				if($("body").has(".webuploader_sel").length > 0){//上传的图片选择select事件清空原有的图片
					$(".webuploader_sel").show(0).change(function(){
						if($("#preview").children().length){
							$(".upload_append_list").each(function(){
								//StorageHideObjec.push(this.id);
								//$(this).find(".file_del").click();	
								$(this).fadeOut(300);
							});
						};
					});
				}	
				$("#editPicframeUl").find("em").click(function(){
					
					jQuery.post("deleteImg", {
						"id" : $(this).parent().val(),
						"content":$(this).parent().find("img")[0].src
					}, function(result) {
						if(result==1){
							$(this).parent().parent().remove();
						}else{
							alert("删除失败");
						}
					});
				});	
								
				//位置： 首页  房源管理  二手房  新增二手房
				$("#tab-system").find(":button").click(function(){//新增保存和修改保存事件
					var objctElement = $(this).parents("form").find("select, :text, textarea");					
					if(objctElement.ValidationIsEmpty(body)){
						$.post("edithouse", $("#form1").serialize(), function(data){
							if(data.success){
								stance.alterInfo(data.message, function(){
									window.location.href="houseList";
								});			        	        
			        	       }
							else{
		        	    	    stance.alterInfo("操作失败"); 
			        	       } 
						});
					}
				});
			}
			// 位置： 首页 客源管理  客源添加或修改
			if(locaherfString == "addInquiryTable" || locaherfString == "uqdateInquiryTable"){
				$("#save-btn-primary").click(function(){//新增保存和修改保存事件						
					var objctElement = $(this).parents("form").find("select, :text, textarea");	
					var submiType = $.trim($(this).attr("posType")), alerText = null;
					alerText = (submiType == "uqdateInquiry") ? "修改" : "添加";
					if(objctElement.ValidationIsEmpty(body)){
		   				var newText = $(this).parents("form").find(":text");	
		   				//客户姓名的验证
		   				//alert("dfasdfa")
		   				if(!stance.isNamerg(newText.eq(0).val())){
		   					stance.alterInfo("用户名只允许为中文");
		   					body.children().last().find("a").inptFocus(newText.eq(0));
		   					return;
		   				}
		   				//验证手机号是否正确
		   				if(!stance.isMobil(newText.eq(1).val())){
		   					stance.alterInfo("手机号格式错误!");
		   					body.children().last().find("a").inptFocus(newText.eq(1));
		   					return;
		   				}
		   				//联系人的验证
		   				if(!stance.isNamerg(newText.eq(2).val())){
		   					stance.alterInfo("联系人只允许为中文");
		   					body.children().last().find("a").inptFocus(newText.eq(2));
		   					return;
		   				}
		   				//验证身份证号是否正确
		   				if(!stance.isCodeValid(newText.eq(3).val())){
		   					stance.alterInfo("身份证格式错误!");
		   					body.children().last().find("a").inptFocus(newText.eq(3));
		   					return;
		   				}
		   						
		   				//价格最大价格要比最小价格大
		   				/*if(){
		   					
		   				}*/
		   				
		   				/*if(newText.eq(0).val() < newText.eq(1).val()){
							alertLayer("价格上限不应该小于价格下限！", 4);	
							inptFocus($(okbtn), newText.eq(0));
							return;
						}*/
		   				/*if(locaherfString == "addInquiryTable"){
		   					//添加一个判断，如果是添加弹添加成功，如果是修改,调修改方法弹修改成功
		   					alert("进入添加");
			   				$.post("addInquiry", $("#form1").serialize(), function(data){
			   					if(data.success){
			   						stance.alterInfo("添加成功", function(){
			   							window.location.href="findInquiryList";
			   						});			        	        
			   	        	       }
			   					else{
			   	       	    	    stance.alterInfo("添加失败"); 
			   	        	       } 
			   				});
		   				}
		   				if(locaherfString == "uqdateInquiryTable"){
		   					alert("进入修改");
		   					$.post("uqdateInquiry", $("#form1").serialize(), function(data){
			   					if(data.success){
			   						stance.alterInfo("修改成功", function(){
			   							window.location.href="findInquiryList";
			   						});			        	        
			   	        	       }
			   					else{
			   	       	    	    stance.alterInfo("修改失败"); 
			   	        	       } 
			   				});
		   				}*/
		   				//添加一个判断，如果是添加弹添加成功，如果是修改,调修改方法弹修改成功
		   				$.post(submiType, $("#form1").serialize(), function(data){
		   					if(data.success){
		   						stance.alterInfo(alerText + "成功!", function(){
		   							window.location.href="findInquiryList";
		   						});			        	        
		   	        	       }
		   					else{
		   	       	    	    stance.alterInfo(alerText + "失败"); 
		   	        	       } 
		   				});
		   				
		   				
		   				
		   				
		   			}   
				});			
			}// 位置： 首页 客源管理  客源添加或修改============结束=====	
			
			break;				
	};	// end switch
	switch(stance.Locahref(2)){
	case"find":   //业务设置	组织机构       员工修改
		$(".uoloadiv").click(function(){				
			$(".webuploader_pick").click();//点击打开图片筛选弹出框
		});
		$.diaologFunc("uploadimages", "../uploadImg");//上传图片的DIV-ID与AJAX路径
		break;
	}
	
});
$.fn.extend($.fn.Instance);