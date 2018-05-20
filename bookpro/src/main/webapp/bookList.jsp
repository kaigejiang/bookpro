<%@page import="cn.edu.nyist.jdbcuserman.vo.BookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 1、告诉浏览器表缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"rel="stylesheet" type="text/css" />
<link href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.css"rel="stylesheet" type="text/css" />
<title>图书列表</title>
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						 <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
					</button> <a class="navbar-brand" href="#">凯歌图书网</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">书籍管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="#">查看</a>
								</li>
								<li>
									<a href="#">添加</a>
								</li>
							</ul>
						</li>
					</ul>
					<form class="navbar-form navbar-left" role="search" id="searchFrm" action="bookList">
						<div class="form-group">
							<input type="text" class="form-control" name="name" value='<%=request.getAttribute("name")==null?"":request.getAttribute("name")%>'/>
						</div> 
						<button type="submit" class="btn btn-default" >
							搜索
						</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
						<li>
							<a href="#">登录</a>
						</li>
						<li>
							<a href="#">修改密码</a>
						</li>
					</ul>
				</div>
				
			</nav>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
							<th>id</th>
							<th>name</th>
							<th>descri</th>
							<th>tid</th>
							<th>photo</th>
							<th>price</th>
							<th>author</th>
							<th>pubDate</th>
						</tr>
				</thead>
				<tbody>
				
					<%
					List<BookVo> ls = (List<BookVo>) request.getAttribute("ls");
					for(BookVo bookVo :ls){
					%>
					<tr>
					<td><%=bookVo.getId()%></td>
					<td><%=bookVo.getName()%></td>
					<td><%=bookVo.getDescri()%></td>
					<td><%=bookVo.getTid()%></td>
					<td style="width: 300px"><img alt="" src="upload/<%=bookVo.getPhoto()%>" style="width: 300px"></td>
					<td><%=bookVo.getPrice()%></td>
					<td><%=bookVo.getAuthor()%></td>
					<td><%=bookVo.getPubDate()%></td>
					</tr>
					<%
					}
					%>
	
				</tbody>
			</table>
		</div>
		<div class="text-center" style="border: 0px">
		<ul class="pagination" >
		<%
		int pageNumber= (Integer)request.getAttribute("pageNumber");
		if(pageNumber==1){
		%>
		<li class="disabled"><a href="#">Prev</a></li>
		<%
		}else{
		%>
		<li><a href="bookList?pageNumber=<%=pageNumber-1%>">Prev</a></li>
		<%
		}
		int totalPage = (Integer)request.getAttribute("totalPage");
		if(totalPage<=5){
			for(int i =1;i<=totalPage;i++){
		%>
				<li>
					<a href="bookList?pageNumber=<%=i%>"><%=i%></a>
				</li>
		<%
			}}else{
				if(pageNumber<=3){
		%>
		<li>
					<a href="bookList?pageNumber=1">1</a>
				</li>
				<li>
					<a href="bookList?pageNumber=2">2</a>
				</li>
				<li>
					<a href="bookList?pageNumber=3">3</a>
				</li>
				<li>
					<a href="bookList?pageNumber=4">4</a>
				</li>
				<li>
					<a href="bookList?pageNumber=<%=totalPage%>">..<%=totalPage%></a>
				</li>
		<%
		}else if(pageNumber>3 && pageNumber<totalPage-2){
		%>
		<li>
					<a href="bookList?pageNumber=1">..1</a>
				</li>
				<li>
					<a href="bookList?pageNumber=<%=pageNumber-1%>"><%=pageNumber-1%></a>
				</li>
				<li>
					<a href="bookList?pageNumber=<%=pageNumber%>"><%=pageNumber%></a>
				</li>
				<li>
					<a href="bookList?pageNumber=<%=pageNumber+1%>"><%=pageNumber+1%></a>
				</li>
				<li>
					<a href="bookList?pageNumber=<%=totalPage%>">..<%=totalPage%></a>
				</li>
		
		<%
				}else{
		%>
		<li>
					<a href="bookList?pageNumber=1">..1</a>
				</li>
				<li>
					<a href="bookList?pageNumber=<%=totalPage-3%>"><%=totalPage-3%></a>
				</li>
				<li>
					<a href="bookList?pageNumber=<%=totalPage-2%>"><%=totalPage-2%></a>
				</li>
				<li>
					<a href="bookList?pageNumber=<%=totalPage-1%>"><%=totalPage-1%></a>
				</li>
				<li>
					<a href="bookList?pageNumber=<%=totalPage%>">..<%=totalPage%></a>
				</li>
		<%
				}
		%>
		<%
				}
		%>
		<%
			if(pageNumber==totalPage){
		%>
				<li class="disabled">
					<a href="#">Next</a>
				</li>
				<%
			}else{
				%>
				<li>
					<a href="bookList?pageNumber=<%=pageNumber+1%>">Next</a>
				</li>
				<%
			}
				%>
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<p>
				凯歌版权所有
			</p>
		</div>
	</div>
</div>

<script type="text/javascript"
		src="bower_components/jquery/dist/jquery.min.js"></script>
	<script type="text/javascript"
		src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		 $(function(){
             $("a[ href='bookList?pageNo=<%=pageNumber%>']").parent("li").addClass("active");
             //提交参数
             console.dir($(".pagination a[href^='bookList?pageNumber=']"))
             $(".pagination a[href^='bookList?pageNumber=']").click(function() {
 				//用序列化表单
            	 this.href += "&" + $("#searchFrm").serialize();

 			});
         });
		</script>
</body>
</html>