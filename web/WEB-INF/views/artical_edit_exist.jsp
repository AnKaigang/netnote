<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日记编辑</title>

<link rel="stylesheet" type="text/css" href="public/css/admin.css">
<script src="public/js/jquery.js" type="text/javascript"></script>
<script src="public/js/json2.js" type="text/javascript"></script>
<script type="text/javascript"
	src="public/js/jquery.cnblogs.thickbox.js"></script>
<script type="text/javascript" src="public/js/admin.js"></script>
<link rel="stylesheet" href="public/css/ui.css">
<link rel="stylesheet" href="public/css/window.css">
<script type="text/javascript">
	window.onbeforeunload = function() {

		//return ('确认离开当前页面吗？未保存的数据将会丢失！');
	}

	$(function() {

		/* $("[name = sId]:eq(${s_id})").attr("checked", true); */

		$("[name = sId]:eq(0)").attr("checked", true);

		$("[name = isopen]:eq(0)").attr("checked", true);
	});
</script>

<script type="text/javascript">
	function tj(tjstatue) {

		if (ue.hasContents()) {
			var con = ue.getContent();
			//alert(con);
			document.getElementById("content").value = con;
	
			if (tjstatue == 1) {
				document.getElementById("status").value = "0";
			}
			if (tjstatue == 2) {
				document.getElementById("status").value = "1";
			}
			document.getElementById("frmMain").submit();
		} else {
			alert("文本框不能为空！");
		}
	}
</script>

<script language="JavaScript" src="public/js/uicontrols.js"></script>
<script language="JavaScript"
	src="public/ueditor/utf8-jsp/ueditor.config.js"></script>
<script language="JavaScript"
	src="public/ueditor/utf8-jsp/ueditor.all.js"></script>

</head>

<body id="content_area" style="width:99%">
	<form method="post" action="updateArtical?id=${article.id }" name="frmMain" id="frmMain"
		style="float:left;width:99%">

		<table id="BodyTable" border="0" cellpadding="0" cellspacing="0"
			width="100%">
			<!--start all-->
			<tbody>
				<tr>
					<td id="Body">
						<div id="Main">

							<!--articaledit-->
							<div id="Editor_Edit">

								<div class="CollapsibleTitle">
									<span>添加日记</span>
								</div>

								<b>标题</b> <br> <input name="title" type="text"
									value="${article.title }" maxlength="200" style="width:80%;">
								<div style="margin-top:10px"></div>
								<b>内容</b> <br />
								<!-- ueditor -->
								<div style="margin:10px">
									<!-- 加载编辑器的容器 -->
									<script id="container" name="content1" type="text/plain"> ${article.content}</script>
									<!-- 配置文件 -->
									<script type="text/javascript"
										src="public/ueditor/utf8-jsp/ueditor.config.js"></script>
									<!-- 编辑器源码文件 -->
									<script type="text/javascript"
										src="public/ueditor/utf8-jsp/ueditor.all.js"></script>
									<!-- 实例化编辑器 -->
									<script type="text/javascript">
										var width = window.screen.width * 0.66;
										var ue = UE.getEditor('container', {
											initialFrameHeight : 500,
											initialFrameWidth : width
										});
									</script>
									<input type="hidden" name="content" id="content" value="" />
									<!-- <input type="button" value="读取" OnClick="tj()" /> -->
								</div>
								<!-- end ueditor -->

								<!--option-->
								<div class="edit_option">

									<!--fenlei-->

									<div class="subCollapsibleTitle">
										<span class="subCollapsibleTitleText">个人分类</span>
									</div>

									<div class="Edit">
										<table style="width:95%;">
											<tbody>
												<tr>
													<c:forEach items="${userSort}" var="sort">
														<td><input type="radio" name="sId"
															value="${sort.id } ">${sort.name }</td>
													</c:forEach>
												</tr>
											</tbody>
										</table>
									</div>


									<div class="subCollapsibleTitle">
										<span class="subCollapsibleTitleText">系统分类</span>
									</div>

									<div class="Edit">
										<table style="width:95%;">
											<tbody>
												<tr>

													<c:forEach items="${defaultSort}" var="sort">
														<td><input type="radio" name="sId"
															value="${sort.id }">${sort.name }</td>
													</c:forEach>

												</tr>
											</tbody>
										</table>
									</div>

									<!--end fenlei-->

									<!--shuoming-->
									<div id="Editor_Edit_APOptions_APSiteHome">

										<div class="subCollapsibleTitle">
											<span class="subCollapsibleTitleText">发布选项</span>
										</div>

										<div
											style="font-size:12px;/*color:#1A64A2;*/padding:5px 5px 5px 5px;">
											【发文说明】<br> 不允许发布任何推广、广告、政治方面的内容。<br>
											如果违反相关规定，会被工作人员移出首页，望理解。<br> <br> <b>请选择日记权限：</b>
										</div>

										<div class="Edit">
											<table style="width:95%;">
												<tbody>
													<tr>
														<td><input type="radio" name="isopen" value="1">自己可见</td>
														<td><input type="radio" name="isopen" value="2">好友可见</td>
														<td><input type="radio" name="isopen" value="3" />所有人可见</td>
													</tr>
												</tbody>
											</table>
										</div>

									</div>
									<!--end shuoming-->
								</div>
								<!--end option-->

							</div>
							<!--end articaledit-->

						</div> <input type="hidden" id="status" name="status" value="" />
						<div class="post_block">
							<input type="button" id="articalPost" value="发布" onclick="tj(1)"
								class="Button"> <input type="button" id="articalDraft"
								value="存为草稿" onclick="tj(2)" class="Button"> <input
								type="reset" name="articalCancel" value="取消" class="Button">
						</div>

						<div style="clear:both;"></div> <span id="tip_posging"
						style="padding-left:20px;color:red; vertical-align: middle;"></span>
						<div style="padding-bottom:10px;margin-top:10px;"></div>


					</td>
				</tr>
			</tbody>
			<!--end all-->
		</table>

	</form>



</body>
</html>

