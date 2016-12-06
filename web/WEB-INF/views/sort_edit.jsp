<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="zh-cn">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类编辑</title>

<link rel="stylesheet" type="text/css" href="public/css/admin.css">
<script src="public/js/jquery.js" type="text/javascript"></script>
<script src="public/js/json2.js" type="text/javascript"></script>
<script type="text/javascript"
	src="public/js/jquery.cnblogs.thickbox.js"></script>
<script type="text/javascript" src="public/js/admin.js"></script>

<script type="text/javascript">
	function delType(id) { //执行删除行的操作       
		if (confirm("确定删除当前分类吗？")) {
			//alert(id);
			$.ajax({
				type : 'post',
				url : 'deleteSort?sortid=' + id,
				data : {},
				cache : false,
				dataType : 'json',
				success : function(data) {
					if (data == true)
						window.location.reload();
					else
						alert("该分类中存在文章，请先删除文章或修改该文章所在分类!");
				},
				error : function() {
				}
			});

		} else
			return false;

	}

	function edit_verify() {
		var EditNewTitle = document.getElementById("EditNewTitle");
		//alert(EditNewTitle.value);
		var EditNewTitleRequired = document
				.getElementById("EditNewTitleRequired");
		if (EditNewTitle.value == "") {
			EditNewTitleRequired.style.display = "inline";
			return false;
		}

		//alert($("#editid").val() );
		//alert($("#EditNewTitle").val());

		$.ajax({
			type : 'post',
			url : 'updateSort?sortid=' + $("#editid").val() + "&sortname="
					+ $("#EditNewTitle").val(),
			data : {},
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data == true)
					window.location.reload();
				else
					alert("edit error!");
			},
			error : function() {
			}
		});

	}

	function add_verify() {
		var AddNewTitle = document.getElementById("AddNewTitle");
		//alert(AddNewTitle.value);
		var AddNewTitleRequired = document
				.getElementById("AddNewTitleRequired");
		if (AddNewTitle.value == "") {
			AddNewTitleRequired.style.display = "inline";
			return false;
		}

		$.ajax({
			type : 'post',
			url : 'addSort?sortname=' + $("#AddNewTitle").val(),
			data : {},
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data == true)
					window.location.reload();
				else
					alert("add error!");
			},
			error : function() {
			}
		});

	}

	function editType(name, id) {
		//alert(name);
		$("#EditNewTitle").val(name);
		$("#editid").val(id);
		//alert($("#editid").val());
	}
</script>

</head>


<body id="Posts">




	<div style="float:left;width:100%;margin-left:-20px;margin-top: -20px;">

		<form method="post" action="#" onsubmit="" id="frmMain">



			<table id="BodyTable" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tbody>

					<tr>
						<!-- need to add left -->

						<td id="Body">
							<div id="Main">

								<div id="Messages"></div>
								<div id="Edit">
									<div id="Edit_Header" class="CollapsibleHeader">
										<span id="Edit_headerTitle">Edit Categories</span>
									</div>
									<div id="Edit_Contents">

										<table class="Listing" cellspacing="0" id="Edit_dgrItems"
											style="border-collapse:collapse;">
											<tbody>
												<tr class="Header">
													<td>Category</td>
													<td>Number</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>


												<c:forEach items="${sorts}" var="sort">
													<tr>
														<td name="name">${sort.name}</td>
														<td name="number">${sort.number}</td>
														<td><a id="editbutton"
															onclick="editType('${sort.name}','${sort.id}')">编辑</a></td>
														<td><a  onclick="return delType('${sort.id}')">删除</a></td>
													</tr>
												</c:forEach>


											</tbody>
										</table>

									</div>
								</div>



								<!-- edit -->
								<div id="Add">
									<div id="Add_Header" class="CollapsibleTitle">
										<span id="Add_headerTitle">Edit Category</span>
									</div>
									<div id="Edit_Contents" class="Edit">

										<label class="Block"> Title&nbsp; </label> <input
											name="EditNewTitle" type="text" id="EditNewTitle"
											style="width: 350px"> <input type="hidden"
											name="editid" id="editid" value="" /> <span
											id="EditNewTitleRequired" style="display:none;color:red">标题不能为空</span>
										<br /> </label>

									</div>
									<div style="margin-top: 8px">
										<input type="button" name="EditPost" value="Edit"
											onclick="return edit_verify()" id="EditPost" class="Button">
										<br> &nbsp;
									</div>
								</div>


								<!-- add begin -->
								<div id="Add">
									<div id="Add_Header" class="CollapsibleTitle">
										<span id="Add_headerTitle">Add New Category</span>
									</div>
									<div id="Add_Contents" class="Edit">
										<label class="Block"> Title&nbsp; </label> <input
											name="AddNewTitle" type="text" id="AddNewTitle"
											style="width: 350px"> <span id="AddNewTitleRequired"
											style="display:none;color:red">标题不能为空</span>
									</div>
									<div style="margin-top: 8px">
										<input type="button" name="AddPost" value="Add"
											onclick="return add_verify()" id="AddPost" class="Button">
										<br> &nbsp;
									</div>


								</div>

								<!-- add begin -->

							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>