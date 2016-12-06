; (function ($) {
    $.extend({
        cnblogs_bind: function (options) {
            options = $.extend({
                title: "",
                content: "",
                width:"500px"
            }, options);
            var mask = '<div id="cnblogs_mask"></div>';
            var boxcontain = '<div id="cnblogs_boxcontain">\
                                  <a id="cnblogs_close" href="javascript:void(0);" title="Close"></a>\
                                  <div id="cnblogs_showbox">\
                                      <div id="cnblogs_title">\
                                          <h2>\
                                              正在加载，请稍后...</h2>\
                                      </div>\
                                      <div id="cnblogs_message">\
                                          正在加载，请稍后...<br />\
                                      </div>\
                                  </div>\
                              </div>';
            var cssCode = 'html, body, h1, h2, h3, h4, h5{margin: 0px;padding: 0px;}\
                            #cnblogs_mask{background-color: #000;position: absolute;top: 0px;left: 0px;width: 100%;height: 100%;opacity: 0.5;filter: alpha(opacity=50);display: none;}\
                            #cnblogs_boxcontain{margin: 0 auto;position: absolute;z-index: 999;line-height: 28px;display: none;}\
                            #cnblogs_showbox{padding: 10px;background: #FFF;border-radius: 5px;margin: 20px;min-width:300px;min-height:150px;}\
                            #cnblogs_title{position: relative;height: 27px;border-bottom: 1px solid #999;color:#444;font-size:14px;}\
                            #cnblogs_close{position: absolute;cursor: pointer;outline: none;top: 0;right: 0;z-index: 4;width: 42px;height: 42px;overflow: hidden;background-image: url(/Images/thickboxclose.png);_background: none;}\
							a#cnblogs_close:hover {background-color: transparent;}\
                            #cnblogs_message{padding: 10px 0px;overflow: hidden;line-height: 19px;}';
            if ($("#cnblogs_mask").length == 0) {
                $("body").append(mask + boxcontain);
                $("head").append("<style type='text/css'>" + cssCode + "</style>");
            }
            else{
                $("#cnblogs_boxcontain").remove();
                $("body").append(boxcontain);
            }

            if (options.title != "") {
                $("#cnblogs_title").html(options.title);
            }
            if (options.content != "") {
                $("#cnblogs_message").html(options.content);
            }

            if($(window).width()>parseInt(options.width)){
                $("#cnblogs_boxcontain").css("width",options.width);
            }
            else{
                $("#cnblogs_boxcontain").css("width",$(window).width()-40+"px");
            }
            var height = $("#cnblogs_boxcontain").height();
            var width = $("#cnblogs_boxcontain").width();
            $("#cnblogs_mask").css("height",$("body").height()>$(window).height()?$("body").height():$(window).height()+"px").show();
            var topOffset = ($(window).height() - height) / 2+$(document).scrollTop();
            if(topOffset<0){
                topOffset = 0;
            }
            $("#cnblogs_boxcontain").css("top", topOffset).css("left", ($(window).width() - width) / 2).show();
            if ($.browser.msie && $.browser.version.substr(0, 1) < 7) {
                width = $(window).width() > 600 ? 600 : $(window).width() - 40;
                $("#cnblogs_boxcontain").css("width", width + "px").css("top", ($(window).height() - height) / 2).css("left", ($(window).width() - width) / 2).show();
                $("#cnblogs_mask").css("width", $(window).width() + "px").css("height", $(window).height() + "px").css("background", "#888");
                $("#cnblogs_close").css("top", "30px").css("right", "30px").css("font-size", "20px").text("关闭");
            }

            $("#cnblogs_close").click(function () {
                $("#cnblogs_mask").hide();
                $("#cnblogs_boxcontain").hide();
                return false;
            });
        },
        cnblogs_close: function (options) {
            options = $.extend({
                callback: null
            }, options);
            $("#cnblogs_mask").hide();
            $("#cnblogs_boxcontain").hide();
            if (options.callback != null) {
                options.callback();
            }
        }
    });
})(jQuery);