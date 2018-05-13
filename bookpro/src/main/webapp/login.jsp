<!DOCTYPE HTML>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的第一个web项目</title>
<!-- 1、告诉浏览器表缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bs/css/paper/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
.container-fluid {
	width: 50%;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -304px;
	margin-top: -123px;
}
</style>
</head>
<body>
	<div class="container-fluid well">
		<div class="row">
			<div class="col-md-12">
				<form class="form-horizontal" role="form" method="post"
					action="login">
					<div class="form-group">
						<%
							if (request.getAttribute("msg") != null) {
						%>
						<div class="alert alert-warning" role="alert"><%=request.getAttribute("msg")%></div>

						<%
							}
						%>

						<label for="inputEmail3" class="col-sm-2 control-label">
							用户名： </label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputName"
								name="name" value="" />
						</div>
					</div>

					<div class="form-group">

						<label for="inputPassword3" class="col-sm-2 control-label">
							密码： </label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="inputPassword3" />
						</div>
					</div>

					<div class="form-group">

						<label for="inputVcode" class="col-sm-2 control-label">
							验证码 </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="inputVcode"
								name="vcode" maxlength="4" />
						</div>
						<div class="col-sm-4">
							<img alt="" src="vcode.png" id="vcodeImg" title="单击换图">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">

							<button type="submit" class="btn btn-default">登录</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="bs/js/jquery.min.js"></script>
	<script type="text/javascript" src="bs/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#vcodeImg").click(function(evt) {
				//不加不会换 
				this.src = "vcode.png?t=" + Math.random();
			});
		});
	</script>
</body>
</html>