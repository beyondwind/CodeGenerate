<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../../include/old_header.jsp"%>
<style type="text/css">
html {
	height: 100%;
	width: 100%;
}

body {
	background: #fafafa;
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

.myshadow {
	color: #999;
	text-shadow: -1px -1px #111, -2px -2px #333, 1px 1px #fff;
}

@media ( max-width : 980px) { /* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" href="javascript:void(0);">Project name</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="javascript:void(0);">Home</a></li>
						<li><a href="javascript:void(0);">About</a></li>
						<li><a href="javascript:void(0);">Contact</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="hero-unit">
			<h2 class="myshadow">完成项目代码生成</h2>
			<p>路径：${sessionScope.project_path }</p>
			<p>
				<a class="btn btn-primary btn-large" href="index.jsp">回到首页</a> <a
					class="btn btn-info btn-large" href="additionSetPage.htm">附加模块</a>
			</p>
		</div>
		<c:forEach items="${requestScope.tableList }" var="table"
			varStatus="i">
			<c:if test="${(i.index mod 3) eq 0}"><div class="row-fluid"></c:if>
			<div class="span4">
				<h3>${table.table_name }</h3>
				<p>${table.table_comments }</p>
				<p>
					<a class="btn" href="javascript:void(0);">View details &raquo;</a>
				</p>
			</div>
			<c:if test="${(i.index mod 3) eq 2}"></div></c:if>
			<c:if test="${i.last and !((i.index mod 3) eq 2)}"></div></c:if>
		</c:forEach>

		<!-- <div class="row-fluid">
			<div class="span4">
				<h2>Heading</h2>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.</p>
				<p>
					<a class="btn" href="javascript:void(0);">View details &raquo;</a>
				</p>
			</div>
			/span
			<div class="span4">
				<h2>Heading</h2>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.</p>
				<p>
					<a class="btn" href="javascript:void(0);">View details &raquo;</a>
				</p>
			</div>
			/span
			<div class="span4">
				<h2>Heading</h2>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.</p>
				<p>
					<a class="btn" href="javascript:void(0);">View details &raquo;</a>
				</p>
			</div>
			/span
		</div>
		/row -->

		<hr>
		<footer>
			<p class="text-center">
				浙江工业大学-<a href="http://www.zjut.com/" title="精弘">精弘网络</a>-随风而已 版权所有
			</p>
			<p class="text-center">
				© 2013 <a href="javascript:void(0);" title="我的浙江工业大学">MYZJUT</a>
			</p>
		</footer>

	</div>
	<!--/.fluid-container-->
</body>
</html>