<%@page import="cn.edu.nyist.jdbcuserman.vo.BookVo"%>
<%@page import="cn.edu.nyist.jdbcuserman.vo.TypeVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%BookVo bookVo = (BookVo)request.getAttribute("bookVo");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>书籍修改</title>
<!-- 1、告诉浏览器表缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container-fluid  well">
		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal" role="form" method="post"
					action="doBookEdit" id="loginFrm" enctype="multipart/form-data">
					<%
						if (request.getAttribute("msg") != null) {
					%>
					<div class="alert alert-warning" role="alert"><%=request.getAttribute("msg")%></div>
					<%
						}
					%>
					<div class="form-group" style="display: none;">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputName"
								name="id"
								value="<%=bookVo.getId()%>" />
						</div>
					</div>
					<div class="form-group">

						<label for="inputName" class="col-sm-2 control-label"> 书名:
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputName"
								name="name"
								value="<%=bookVo.getName()%>" />
						</div>
					</div>
					<div class="form-group">

						<label for="textAreaDescri" class="col-sm-2 control-label">
							描述: </label>
						<div class="col-sm-10">
							<textarea name="descri" class="form-control" id="textAreaDescri"><%=bookVo.getDescri()%></textarea>
						</div>
					</div>

					<div class="form-group">

						<label for="inputPhoto" class="col-sm-2 control-label">
							图片: </label>
						<div class="col-sm-2">
							<input type="file" class="form-control" id="inputPhoto"
								name="photo" />
						</div>
						<div class="col-sm-8">
						<%
						if(bookVo.getPhoto()!=null){
						%>
						<img style="width: 200px" src="upload/<%=bookVo.getPhoto()%>">
						<%
						}
						%>
						</div>
					</div>
					<div class="form-group">

						<label for="inputPrice" class="col-sm-2 control-label">
							价格: </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPrice"
								name="price" 
								value="<%=bookVo.getPrice()%>"/>
						</div>
					</div>
					<div class="form-group">

						<label for="inputPubDate" class="col-sm-2 control-label">
							出版时间: </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputPubDate"
								name="pubDate" 
								value="<%=bookVo.getPubDate()%>"/>
						</div>
					</div>
					<div class="form-group">

						<label for="inputAuthor" class="col-sm-2 control-label">
							作者: </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputAuthor"
								name="author" 
								value="<%=bookVo.getAuthor()%>"/>
						</div>
					</div>
					<div class="form-group">

						<label for="selectTid" class="col-sm-2 control-label"> 类型:
						</label>
						<div class="col-sm-10">
							<select name="tid" class="form-control" id="selectTid">
								
		
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">

							<button type="submit" class="btn btn-default">修改</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="bower_components/jquery/dist/jquery.min.js"></script>
	<script type="text/javascript"
		src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript"
		src="bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
	<script type="text/javascript"
		src="bower_components/jquery-validation/dist/jquery.validate.js"></script>
	<script type="text/javascript"
		src="bower_components/jquery-validation-bootstrap-tooltip/jquery-validate.bootstrap-tooltip.js"></script>
	<script type="text/javascript">
		$(function() {
			//日期控件
			$('#inputPubDate').datepicker({
				format : 'yyyy-mm-dd',
				language : 'zh-CN',//提示中文界面
				autoclose : true//自动关闭
			//自动关闭
			});
			//验证
			$("#loginFrm").validate({
				rules : {
					name : {
						required : true
					},
					descri : {
						required : true
					},
					price : {
						required : true
					},
					pubDate : {
						required : true
					},
					author : {
						required : true
					}
				},
				messages : {
					name : "必须填写",
					descri : "必须填写",
					price : "必须填写",
					pubDate : "必须填写",
					author : "必须填写",
				},
				tooltip_options : {
					name : {
						placement : 'bottom'
					},
					descri : {
						placement : 'bottom'
					},
					price : {
						placement : 'bottom'
					},
					pubDate : {
						placement : 'bottom'
					},
					author : {
						placement : 'bottom'
					}
				}
			});
		});
	</script>
	<script type="text/javascript">
		function fillSel() {
			var sel = document.getElementById("selectTid");
			for(var i = 0;i< types.length;i++){
				sel.appendChild(new Option(types[i].name,types[i].id));
				}
			$("option[value='<%=bookVo.getTid()%>']").attr("selected", "selected");
		}
	</script>
	<script type="text/javascript" src="findAllTypes" onload="fillSel()"></script>
</body>
</html>