<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp"%>

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="javascript:void(0);">CodeGenerator</a>
			</div>

			<div id="navbar" class="collapse navbar-collapse navbar-right">
				<ul class="nav navbar-nav">
					<li><a href="<%=basePath%>">Home</a></li>
					<li><a href="javascript:void(0);">step1</a></li>
					<li><a href="javascript:void(0);">step2</a></li>
					<li class="active"><a href="javascript:void(0);">step3</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">
		<div class="starter-template">
			<c:if
				test="${requestScope.result.success and !empty requestScope.result.msg }">
				<div class="alert alert-info alert-dismissible fade in" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Success!</strong>&nbsp;&nbsp;${requestScope.result.msg }
				</div>
			</c:if>
			<c:if
				test="${!requestScope.result.success and !empty requestScope.result.msg }">
				<div class="alert alert-danger alert-dismissible fade in"
					role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Holy guacamole!</strong>&nbsp;&nbsp;${requestScope.result.msg }
				</div>
			</c:if>

			<div class="panel panel-primary">

				<div class="panel-heading">download the code in Rar file.</div>
				<!--/.panel-heading -->

				<div class="panel-body">
					<ul class="list-group">
						<c:forEach var="table" items="${requestScope.result.dataObject }"
							varStatus="status">
							<li class="list-group-item"><span class="badge">${table.columnSize }</span>${table.tableName }
							</li>
						</c:forEach>
					</ul>
				</div>
				<!--/.panel-body -->

				<div class="panel-footer">
					<nav>
						<ul class="pager">
							<li class="previous"><a
								href="<%=basePath%>chooseTablePage.htm"><span
									aria-hidden="true">&larr;</span>Previous Step</a></li>
							<li class="next"><a href="<%=basePath%>download.htm"
								target="_blank"><u>download</u></a></li>
						</ul>
					</nav>
				</div>
				<!--/.panel-footer -->
			</div>
		</div>

		<hr />

		<footer>
			<p class="text-center">
				Code Generator for <a href="http://www.bizpartner.cn/"> ©
					Bizpartner</a>, by <a href="https://github.com/beyondwind">@紫英</a>.
			</p>
		</footer>
	</div>
	<!-- /.container -->

</body>
</html>